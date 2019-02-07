package com.improve.utils;

import android.text.TextUtils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Title: CommonUtils
 * <p>
 * Description:
 * </p>
 *
 * @author Changbao
 * @date 2019/1/19
 */
public class CommonUtils {

    /**
     * 获取Token
     */
    public static String getToken(String phoneNumber, String timestamp) {
        String num = getStringEncrypt(phoneNumber);
        String time = getStringEncrypt(new StringBuilder(timestamp).reverse().toString());
        return encryption(num + time);
    }

    /**
     * Function(函数名称): getStringEncrypt
     * Description(描述): 与远程服务器校验的算法，获取加密结果(加密原型：手机号或者时间戳)
     * <p>
     * 在接口中涉及的token的加密获取,采用如下方法
     * 1.将手机号码丢入getStringEncrypt中,取出加密码1
     * 2.将时间戳字符串反转,丢入getStringEncrypt中,取出加密码2
     * 3.将加密码1与加密码2拼接,通过MD5加密(32位小写),得到token码
     * </p>
     *
     * @param primaryData 原始数据
     * @return 加密结果
     * ------------------------------------------------------------
     **/
    private static String getStringEncrypt(String primaryData) {
        String encryptString = null;
        //计算字符串的长度
        int len = primaryData.length();
        int i = 0, tempNumber = 0;

        for (int j = len - 1; j > 5; j--) {
            //第一个字符的ASCII码
            int firstNum = Integer.valueOf(primaryData.charAt(i));
            //第二个字符的ASCII码
            int secondNum = Integer.valueOf(primaryData.charAt(j));

            tempNumber += firstNum * secondNum + 11808;
            i++;
        }
        encryptString = tempNumber + "";
        return encryptString;
    }

    /**
     * MD5 32位加密函数
     */
    private static String encryption(String s) {
        String reMD5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(s.getBytes());
            byte b[] = md.digest();
            int i;

            StringBuffer skr = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    skr.append("0");
                }
                skr.append(Integer.toHexString(i));
            }
            reMD5 = skr.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return reMD5;
    }

    /**
     * 获取精确到秒的时间戳
     */
    public static String getSecondTimestamp() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        int len = timestamp.length();
        if (len > 3) {
            return String.valueOf(timestamp.substring(0, len - 3));
        } else {
            return "0";
        }
    }

    /**
     * 验证手机格式
     *
     * @param mobileNum 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
     *                  联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
     *                  总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
     */
    public static boolean isPhoneNum(String mobileNum) {
        // "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        String telRegex = "[1][358]\\d{9}";
        if (TextUtils.isEmpty(mobileNum)) {
            return false;
        } else {
            return mobileNum.matches(telRegex);
        }
    }

    /**
     * 至少包含大小写字母及数字中的两种
     */
    public static boolean isLetterDigit(String password) {
        boolean isDigit = false;  //定义一个boolean值，用来表示是否包含数字
        boolean isLetter = false; //定义一个boolean值，用来表示是否包含字母
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) { //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            } else if (Character.isLetter(password.charAt(i))) { //用char包装类中的判断字母的方法判断每一个字符
                isLetter = true;
            }
        }
        String regex = "^[a-zA-Z0-9]+$";
        boolean isRight = isDigit && isLetter && password.matches(regex);
        return isRight;
    }



    /*************************************************************************/

    /**
     * java的异常（Exception）信息的详细记录
     *
     * @param e
     * @return
     */
    public static String getExceptionInfo(Exception e) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream pout = new PrintStream(out);
        e.printStackTrace(pout);
        String ret = new String(out.toByteArray());
        pout.close();
        try {
            out.close();
        } catch (Exception et) {
        }
        return ret;
    }

    /**
     * java的异常（Exception）信息的详细记录
     *
     * @param e
     * @return
     */
    public static String getExceptionInfo2(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        e.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString();
    }
}
