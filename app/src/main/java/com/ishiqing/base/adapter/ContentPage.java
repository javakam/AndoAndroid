package com.ishiqing.base.adapter;

/**
 * QMUITabSegment + ViewPager
 * <p>
 * 默认 Tab 最多只能放十个 ，可以通过增加枚举类型并修改 SIZE 值进行增加。
 * <p>
 *
 * @author javakam
 * @date 2018/6/20
 */
public enum ContentPage {
    ITEM0(0),
    ITEM1(1),
    ITEM2(2),
    ITEM3(3),
    ITEM4(4),
    ITEM5(5),
    ITEM6(6),
    ITEM7(7),
    ITEM8(8),
    ITEM9(9);

    public static final int SIZE = 10;

    private final int position;

    /**
     * 枚举有参构造器，默认 private 修饰
     *
     * @param page
     */
    ContentPage(int page) {
        this.position = page;
    }

    public int getSi() {
        return ordinal();
    }

    public static ContentPage getPage(int index) {
        switch (index) {
            case 0:
                return ITEM0;
            case 1:
                return ITEM1;
            case 2:
                return ITEM2;
            case 3:
                return ITEM3;
            case 4:
                return ITEM4;
            case 5:
                return ITEM5;
            case 6:
                return ITEM6;
            case 7:
                return ITEM7;
            case 8:
                return ITEM8;
            case 9:
                return ITEM9;
            default:
                return ITEM0;
        }
    }

    public int getPosition() {
        return position;
    }

}
