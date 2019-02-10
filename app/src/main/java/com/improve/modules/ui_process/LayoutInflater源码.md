### LayoutInflater
先看下 LayoutInflater.inflate 源码：
```
/**
 * Inflate a new view hierarchy from the specified XML node. Throws
 * {@link InflateException} if there is an error.
 * <p>
 * <em><strong>Important</strong></em>&nbsp;&nbsp;&nbsp;For performance
 * reasons, view inflation relies heavily on pre-processing of XML files
 * that is done at build time. Therefore, it is not currently possible to
 * use LayoutInflater with an XmlPullParser over a plain XML file at runtime.
 *
 * @param parser XML dom node containing the description of the view
 *        hierarchy.
 * @param root Optional view to be the parent of the generated hierarchy (if
 *        <em>attachToRoot</em> is true), or else simply an object that
 *        provides a set of LayoutParams values for root of the returned
 *        hierarchy (if <em>attachToRoot</em> is false.)
 * @param attachToRoot Whether the inflated hierarchy should be attached to
 *        the root parameter? If false, root is only used to create the
 *        correct subclass of LayoutParams for the root view in the XML.
 * @return The root View of the inflated hierarchy. If root was supplied and
 *         attachToRoot is true, this is root; otherwise it is the root of
 *         the inflated XML file.
 */
public View inflate(XmlPullParser parser, @Nullable ViewGroup root, boolean attachToRoot) {
    synchronized (mConstructorArgs) {
        Trace.traceBegin(Trace.TRACE_TAG_VIEW, "inflate");
        final Context inflaterContext = mContext;
        final AttributeSet attrs = Xml.asAttributeSet(parser);
        Context lastContext = (Context) mConstructorArgs[0];
        mConstructorArgs[0] = inflaterContext;
        //1 此方法最后的返回值，初始化为传入的root
        View result = root;
        try {
            // Look for the root node.
            int type;
            while ((type = parser.next()) != XmlPullParser.START_TAG && type != XmlPullParser.END_DOCUMENT) {
                // Empty
            }
            //2 能走到这里，说明type是START_TAG 或 END_DOCUMENT,如果一开始就是 END_DOCUMENT，那说明xml文件有问题.
            if (type != XmlPullParser.START_TAG) {
                throw new InflateException(parser.getPositionDescription()+ ": No start tag found!");
            }
            //3 获得当前start tag的name,能到这里，那type一定是START_TAG，也就是xml文件里的root
            final String name = parser.getName();
            if (DEBUG) {
                System.out.println("**************************");
                System.out.println("Creating root view: " + name);
                System.out.println("**************************");
            }
            //4 判断当前的tag是否是merge标签，root必须非空且attachToRoot为true，否则抛异常结束。
            if (TAG_MERGE.equals(name)) {
                if (root == null || !attachToRoot) {
                    throw new InflateException("<merge /> can be used only with a valid ViewGroup root and attachToRoot=true");
                }
                //5 递归的inflate
                rInflate(parser, root, inflaterContext, attrs, false);
            } else {
                //6 动态载入 resource
                // Temp is the root view that was found in the xml
                final View temp = createViewFromTag(root, name, inflaterContext, attrs);

                //7 创建ViewGroup的LayoutParams
                //8 当root不为空，attachToRoot 为false时，为temp设置layout属性，当该view以后被添加到父view当中时，这些layout属性会自动生效
                ViewGroup.LayoutParams params = null;
                if (root != null) {
                    if (DEBUG) {
                        System.out.println("Creating params from root: " + root);
                    }
                    // Create layout params that match root, if supplied
                    params = root.generateLayoutParams(attrs);
                    if (!attachToRoot) {
                        // Set the layout params for temp if we are not attaching. (If we are, we use addView, below)
                        temp.setLayoutParams(params);
                    }
                }

                if (DEBUG) {
                    System.out.println("-----> start inflating children");
                }

                //9 递归inflate剩下的所有children
                // Inflate all children under temp against its context.
                rInflateChildren(parser, temp, attrs, true);

                if (DEBUG) {
                    System.out.println("-----> done inflating children");
                }
                //10 通过View.addView方法，把temp（子View/ViewGroup）添加到根视图root上
                // 情形一：container true
                // We are supposed to attach all the views we found (int temp) to root. Do that now.
                if (root != null && attachToRoot) {
                    root.addView(temp, params);
                }
                // 情形二(常用)：container=null 或 container false
                // 联想到如下使用场景：
                /*
                LayoutInflater factory = LayoutInflater.from(this);
                factory.inflate(R.layout.xx,null);
                factory.inflate(R.layout.xx,container,false)
                */
                // Decide whether to return the root that was passed in or the top view found in xml.
                if (root == null || !attachToRoot) {
                    result = temp;
                }
            }
        } catch (XmlPullParserException e) {
            final InflateException ie = new InflateException(e.getMessage(), e);
            ie.setStackTrace(EMPTY_STACK_TRACE);
            throw ie;
        } catch (Exception e) {
            final InflateException ie = new InflateException(parser.getPositionDescription() + ": " + e.getMessage(), e);
            ie.setStackTrace(EMPTY_STACK_TRACE);
            throw ie;
        } finally {
            // Don't retain static reference on context.
            mConstructorArgs[0] = lastContext;
            mConstructorArgs[1] = null;
            Trace.traceEnd(Trace.TRACE_TAG_VIEW);
        }
        //11 返回渲染的视图
        return result;
    }
}
```
简单总节一下：<br>
LayoutInflater 中的 inflate 方法首先通过 XmlPullParser 去判别layout.xml中的标签是否合理，如果合理<br>
则获取最外层的标签名:
```
final String name = parser.getName();
```
然后根据这个name去判断最外层是否是 merge 标签，<br>
如果是 merge，并且满足
```
"<merge /> can be used only with a valid ViewGroup root and attachToRoot=true"
```
即merge必须出在一个ViewGroup里，并且attachToRoot=true 的条件开始递归执行 rInflate 方法去递归绘制视图；<br>
如果不是 merge ，则通过标签的名称和属性绘制视图，并且为其设置layout属性：
```
final View temp = createViewFromTag(root, name, inflaterContext, attrs);
params = root.generateLayoutParams(attrs);// ViewGroup.generateLayoutParams 中：new LayoutParams(getContext(), AttributeSet);
temp.setLayoutParams(params); root.generateLayoutParams(attrs)
```
其内部是在方法 createView 中通过反射创建出来的视图。(也就是说，我们在layout.xml里写的TextView是通过反射创建出来的！) <br>
然后递归把剩下的标签也渲染绘制 rInflateChildren ，最后判断root、attachToRoot进行判断， <br>
一种是通过 View.addView，把temp（子View/ViewGroup）添加到根视图root上；<br>
另一种情况是 将引用指向 temp -> result = temp; <br>
并将结果result返回。