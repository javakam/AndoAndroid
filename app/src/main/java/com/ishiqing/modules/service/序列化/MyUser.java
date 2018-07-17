package com.ishiqing.modules.service.序列化;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by javakam on 2018/7/17.
 */
public class MyUser implements Parcelable {
    private String uname;
    private MyBook myBook;
    private List<MyBook> books;

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * 序列化
     *
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uname);
        dest.writeParcelable(this.myBook, flags);
        dest.writeTypedList(this.books);
    }

    public MyUser() {
    }

    protected MyUser(Parcel in) {
        this.uname = in.readString();
        //1 《Android开发艺术探索》
        this.myBook = in.readParcelable(Thread.currentThread().getContextClassLoader());
        //2 default generate
//        this.myBook = in.readParcelable(MyBook.class.getClassLoader());
        this.books = in.createTypedArrayList(MyBook.CREATOR);

        // Bundle  Bitmap  Intent


    }

    /**
     * 反序列化
     */
    public static final Parcelable.Creator<MyUser> CREATOR = new Parcelable.Creator<MyUser>() {
        @Override
        public MyUser createFromParcel(Parcel source) {
            return new MyUser(source);
        }

        @Override
        public MyUser[] newArray(int size) {
            return new MyUser[size];
        }
    };
}
