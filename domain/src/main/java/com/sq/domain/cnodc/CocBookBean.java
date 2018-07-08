package com.sq.domain.cnodc;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 台账
 * <p>
 * Created by javakam on 2018/7/8.
 */
@Entity
public class CocBookBean {
    /**
     * abgdt : 2018-06-21
     * anlkl : 10100
     * anln1 : 101000002844
     * bukrs : G400
     * herst : 制造商
     * id : 7541E829977EFCA1CECDBF74229CBE12
     * kostl : G400040008
     * lease :
     * ltextC : G400040008 科技信息部
     * ltextP : G400000001 中国石油天然气勘探开发公司本部
     * prctr : G400000001
     * txa50 : ThinkpadX260
     * txk50 : 10100 固定资产
     * txt50 : 笔记本电脑
     * xstil :
     * zbgr : 保密办
     * zbyzd1 :
     * zbyzd2 :
     * zbyzd3 :
     * zccbh : CNG014198R
     * zccrq1 : 2011-02-09
     * zcfdd : 通信干扰器
     * zcharItem12 : 0120
     * zcpjh : 123456
     * zsczt : 正常
     * zssdwmc : 总工程师办公室-科技信息部
     * zsyzt : 010102
     * zywzt : 正常
     * zzcml : 14990101
     * zzcmlmc : 14990101 其它工具及仪器
     * zzdmc : 010102 生产经营用-其他
     */

    private String abgdt;
    private String anlkl;
    private String anln1;
    private String bukrs;
    private String herst;
    @Id
    private String id;
    private String kostl;
    private String lease;
    private String ltextC;
    private String ltextP;
    private String prctr;
    private String txa50;
    private String txk50;
    private String txt50;
    private String xstil;
    private String zbgr;
    private String zbyzd1;
    private String zbyzd2;
    private String zbyzd3;
    private String zccbh;
    private String zccrq1;
    private String zcfdd;
    private String zcharItem12;
    private String zcpjh;
    private String zsczt;
    private String zssdwmc;
    private String zsyzt;
    private String zywzt;
    private String zzcml;
    private String zzcmlmc;
    private String zzdmc;

    @Generated(hash = 373406896)
    public CocBookBean(String abgdt, String anlkl, String anln1, String bukrs,
                       String herst, String id, String kostl, String lease, String ltextC,
                       String ltextP, String prctr, String txa50, String txk50, String txt50,
                       String xstil, String zbgr, String zbyzd1, String zbyzd2, String zbyzd3,
                       String zccbh, String zccrq1, String zcfdd, String zcharItem12,
                       String zcpjh, String zsczt, String zssdwmc, String zsyzt, String zywzt,
                       String zzcml, String zzcmlmc, String zzdmc) {
        this.abgdt = abgdt;
        this.anlkl = anlkl;
        this.anln1 = anln1;
        this.bukrs = bukrs;
        this.herst = herst;
        this.id = id;
        this.kostl = kostl;
        this.lease = lease;
        this.ltextC = ltextC;
        this.ltextP = ltextP;
        this.prctr = prctr;
        this.txa50 = txa50;
        this.txk50 = txk50;
        this.txt50 = txt50;
        this.xstil = xstil;
        this.zbgr = zbgr;
        this.zbyzd1 = zbyzd1;
        this.zbyzd2 = zbyzd2;
        this.zbyzd3 = zbyzd3;
        this.zccbh = zccbh;
        this.zccrq1 = zccrq1;
        this.zcfdd = zcfdd;
        this.zcharItem12 = zcharItem12;
        this.zcpjh = zcpjh;
        this.zsczt = zsczt;
        this.zssdwmc = zssdwmc;
        this.zsyzt = zsyzt;
        this.zywzt = zywzt;
        this.zzcml = zzcml;
        this.zzcmlmc = zzcmlmc;
        this.zzdmc = zzdmc;
    }

    @Generated(hash = 349754348)
    public CocBookBean() {
    }

    @Override
    public String toString() {
        return "CocBookBean{" +
                "abgdt='" + abgdt + '\'' +
                ", anlkl='" + anlkl + '\'' +
                ", anln1='" + anln1 + '\'' +
                ", bukrs='" + bukrs + '\'' +
                ", herst='" + herst + '\'' +
                ", id='" + id + '\'' +
                ", kostl='" + kostl + '\'' +
                ", lease='" + lease + '\'' +
                ", ltextC='" + ltextC + '\'' +
                ", ltextP='" + ltextP + '\'' +
                ", prctr='" + prctr + '\'' +
                ", txa50='" + txa50 + '\'' +
                ", txk50='" + txk50 + '\'' +
                ", txt50='" + txt50 + '\'' +
                ", xstil='" + xstil + '\'' +
                ", zbgr='" + zbgr + '\'' +
                ", zbyzd1='" + zbyzd1 + '\'' +
                ", zbyzd2='" + zbyzd2 + '\'' +
                ", zbyzd3='" + zbyzd3 + '\'' +
                ", zccbh='" + zccbh + '\'' +
                ", zccrq1='" + zccrq1 + '\'' +
                ", zcfdd='" + zcfdd + '\'' +
                ", zcharItem12='" + zcharItem12 + '\'' +
                ", zcpjh='" + zcpjh + '\'' +
                ", zsczt='" + zsczt + '\'' +
                ", zssdwmc='" + zssdwmc + '\'' +
                ", zsyzt='" + zsyzt + '\'' +
                ", zywzt='" + zywzt + '\'' +
                ", zzcml='" + zzcml + '\'' +
                ", zzcmlmc='" + zzcmlmc + '\'' +
                ", zzdmc='" + zzdmc + '\'' +
                '}';
    }

    public String getAbgdt() {
        return abgdt;
    }

    public void setAbgdt(String abgdt) {
        this.abgdt = abgdt;
    }

    public String getAnlkl() {
        return anlkl;
    }

    public void setAnlkl(String anlkl) {
        this.anlkl = anlkl;
    }

    public String getAnln1() {
        return anln1;
    }

    public void setAnln1(String anln1) {
        this.anln1 = anln1;
    }

    public String getBukrs() {
        return bukrs;
    }

    public void setBukrs(String bukrs) {
        this.bukrs = bukrs;
    }

    public String getHerst() {
        return herst;
    }

    public void setHerst(String herst) {
        this.herst = herst;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKostl() {
        return kostl;
    }

    public void setKostl(String kostl) {
        this.kostl = kostl;
    }

    public String getLease() {
        return lease;
    }

    public void setLease(String lease) {
        this.lease = lease;
    }

    public String getLtextC() {
        return ltextC;
    }

    public void setLtextC(String ltextC) {
        this.ltextC = ltextC;
    }

    public String getLtextP() {
        return ltextP;
    }

    public void setLtextP(String ltextP) {
        this.ltextP = ltextP;
    }

    public String getPrctr() {
        return prctr;
    }

    public void setPrctr(String prctr) {
        this.prctr = prctr;
    }

    public String getTxa50() {
        return txa50;
    }

    public void setTxa50(String txa50) {
        this.txa50 = txa50;
    }

    public String getTxk50() {
        return txk50;
    }

    public void setTxk50(String txk50) {
        this.txk50 = txk50;
    }

    public String getTxt50() {
        return txt50;
    }

    public void setTxt50(String txt50) {
        this.txt50 = txt50;
    }

    public String getXstil() {
        return xstil;
    }

    public void setXstil(String xstil) {
        this.xstil = xstil;
    }

    public String getZbgr() {
        return zbgr;
    }

    public void setZbgr(String zbgr) {
        this.zbgr = zbgr;
    }

    public String getZbyzd1() {
        return zbyzd1;
    }

    public void setZbyzd1(String zbyzd1) {
        this.zbyzd1 = zbyzd1;
    }

    public String getZbyzd2() {
        return zbyzd2;
    }

    public void setZbyzd2(String zbyzd2) {
        this.zbyzd2 = zbyzd2;
    }

    public String getZbyzd3() {
        return zbyzd3;
    }

    public void setZbyzd3(String zbyzd3) {
        this.zbyzd3 = zbyzd3;
    }

    public String getZccbh() {
        return zccbh;
    }

    public void setZccbh(String zccbh) {
        this.zccbh = zccbh;
    }

    public String getZccrq1() {
        return zccrq1;
    }

    public void setZccrq1(String zccrq1) {
        this.zccrq1 = zccrq1;
    }

    public String getZcfdd() {
        return zcfdd;
    }

    public void setZcfdd(String zcfdd) {
        this.zcfdd = zcfdd;
    }

    public String getZcharItem12() {
        return zcharItem12;
    }

    public void setZcharItem12(String zcharItem12) {
        this.zcharItem12 = zcharItem12;
    }

    public String getZcpjh() {
        return zcpjh;
    }

    public void setZcpjh(String zcpjh) {
        this.zcpjh = zcpjh;
    }

    public String getZsczt() {
        return zsczt;
    }

    public void setZsczt(String zsczt) {
        this.zsczt = zsczt;
    }

    public String getZssdwmc() {
        return zssdwmc;
    }

    public void setZssdwmc(String zssdwmc) {
        this.zssdwmc = zssdwmc;
    }

    public String getZsyzt() {
        return zsyzt;
    }

    public void setZsyzt(String zsyzt) {
        this.zsyzt = zsyzt;
    }

    public String getZywzt() {
        return zywzt;
    }

    public void setZywzt(String zywzt) {
        this.zywzt = zywzt;
    }

    public String getZzcml() {
        return zzcml;
    }

    public void setZzcml(String zzcml) {
        this.zzcml = zzcml;
    }

    public String getZzcmlmc() {
        return zzcmlmc;
    }

    public void setZzcmlmc(String zzcmlmc) {
        this.zzcmlmc = zzcmlmc;
    }

    public String getZzdmc() {
        return zzdmc;
    }

    public void setZzdmc(String zzdmc) {
        this.zzdmc = zzdmc;
    }
}
