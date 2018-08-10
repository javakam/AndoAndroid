package com.sq.library.utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by javakam on 2018/8/10.
 */
public class CommonUtil {
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
