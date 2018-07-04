package com.shiqing.retrofit.youdao;

import java.util.List;

/**
 * Created by javakam on 2018/3/20.
 */
public class TranslateYouDao {

    /**
     * translateResult : [[{"tgt":"我快乐","src":"merry me"}]]
     * errorCode : 0
     * type : en2zh-CHS
     */

    private int errorCode;
    private String type;
    private List<List<TranslateResultBean>> translateResult;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<List<TranslateResultBean>> getTranslateResult() {
        return translateResult;
    }

    public void setTranslateResult(List<List<TranslateResultBean>> translateResult) {
        this.translateResult = translateResult;
    }

    public static class TranslateResultBean {
        @Override
        public String toString() {
            return "TranslateResultBean{" +
                    "tgt='" + tgt + '\'' +
                    ", src='" + src + '\'' +
                    '}';
        }

        /**
         * tgt : 我快乐
         * src : merry me
         */

        private String tgt;
        private String src;

        public String getTgt() {
            return tgt;
        }

        public void setTgt(String tgt) {
            this.tgt = tgt;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }
    }

    @Override
    public String toString() {
        return "TranslateYouDao{" +
                "errorCode=" + errorCode +
                ", type='" + type + '\'' +
                ", translateResult=" + translateResult +
                '}';
    }
}
