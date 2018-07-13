### TextView
- 文本长度过长显示不全
```
private void adjustTvTextSize(TextView tv, int maxWidth, String text) {
    int avaiWidth = maxWidth - tv.getPaddingLeft() - tv.getPaddingRight() - 10;
    if (avaiWidth <= 0) {
        return;
    }
    TextPaint textPaintClone = new TextPaint(tv.getPaint());

    // note that Paint text size works in px not sp
    float trySize = textPaintClone.getTextSize();
    while (textPaintClone.measureText(text) > avaiWidth) {
        trySize--;
        textPaintClone.setTextSize(trySize);
    }
    tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, trySize);
}
```
当文本过长字体自动缩小，智能适配。原理是布局宽高固定的情况下，字体单位改用dp表示。
- 如果要像微信一样，所有字体都不允许随系统调节而发生大小变化，要怎么办呢？【一些机型无效】
> 可利用Android的Configuration类中的fontScale属性，其默认值为1，会随系统调节字体大小而发生变化，如果我们强制让其等于默认值，
就可以实现字体不随用户调节而改变，在工程的Application或BaseActivity中添加下面的代码：
```
@Override
public void onConfigurationChanged(Configuration newConfig) {
     if (newConfig.fontScale != 1) {//非默认值
         getResources();
     }
     super.onConfigurationChanged(newConfig);
}

@Override
public Resources getResources() {
     Resources res = super.getResources();
     if (res.getConfiguration().fontScale != 1) {//非默认值
        Configuration newConfig = new Configuration();
        newConfig.setToDefaults();//设置默认
        res.updateConfiguration(newConfig, res.getDisplayMetrics());
     }
     return res;
}
```
设置应用不能随系统调节，在检测到fontScale属性不为默认值1的情况下，强行进行改变。

### FlexboxLayout
>   开发中，不推荐直接使用 **FlexboxLayout** ，而是使用 **【RecyclerView + FlexboxLayoutManager】** 的方案，
这样一来既支持View回收，又能实现Flexbox的效果，二者兼得。(当然，目前 FlexboxLayout 只是1.0.0版本，
以后是否会推出更强大的功能也是未知呢)

-   基于 FlexboxLayout 原生的单选多选事件参考 ：https://blog.csdn.net/LXLYHM/article/details/78753558  <br>
不过还是推荐用 **【RecyclerView + FlexboxLayoutManager】** 去实现。