package com.example.tyler.a5_2p;

import android.os.Parcel;
import android.os.Parcelable;

public class Fruits implements Parcelable{

    private String name;
    private String date;

    /// TODO http://www.vogella.com/tutorials/AndroidParcelable/article.html
    public Fruits(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Fruits createFromParcel(Parcel in) {
            return new Fruits(in);
        }

        @Override
        public Fruits[] newArray(int size) {
            return new Fruits[size];
        }
    };
}
