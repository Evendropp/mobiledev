package com.example.tyler.a5_2p;

import android.os.Parcel;
import android.os.Parcelable;

public class Fruits implements Parcelable{

    private String name;
    private String date;

    /// TODO http://www.vogella.com/tutorials/AndroidParcelable/article.html
    // constructor
    public Fruits(String name,String date )
    {
        this.name = name;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    //parcelling
    public Fruits(Parcel in) {
        this.date = in.readString();
        this.name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.date);
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
