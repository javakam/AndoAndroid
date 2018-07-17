package com.ishiqing.modules.service.序列化;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by javakam on 2018/7/17.
 */
public class MyBook implements Parcelable {
    private String bid;
    private String author;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.bid);
        dest.writeString(this.author);
    }

    public MyBook() {
    }

    protected MyBook(Parcel in) {
        this.bid = in.readString();
        this.author = in.readString();
    }

    public static final Parcelable.Creator<MyBook> CREATOR = new Parcelable.Creator<MyBook>() {
        @Override
        public MyBook createFromParcel(Parcel source) {
            return new MyBook(source);
        }

        @Override
        public MyBook[] newArray(int size) {
            return new MyBook[size];
        }
    };
}
