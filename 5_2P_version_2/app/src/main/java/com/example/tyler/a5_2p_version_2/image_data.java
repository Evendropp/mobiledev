package com.example.tyler.a5_2p_version_2;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class image_data implements Parcelable{

    private String name;
    private String location;
    private String date;
    private ArrayList keywords;
    private Boolean isshared;
    private String who;
    private Integer rating;

    image_data(String name, String location, String date, ArrayList<String> keywords, Boolean isshared, String who, Integer rating)
    {
        update(name, location, date, keywords, isshared, who, rating);
    }

    public void update(String name, String location, String date, ArrayList<String> keywords, Boolean isshared, String who, Integer rating)
    {
        this.name = name;
        this.location = location;
        this.date = date;
        this.keywords = keywords;
        this.isshared = isshared;
        this.who = who;
        this.rating = rating;
    }

    private image_data(Parcel in) {
        name = in.readString();
        location = in.readString();
        date = in.readString();
        keywords = in.readArrayList(image_data.class.getClassLoader());
        byte shareable = in.readByte();
        isshared = shareable == 1;
        who = in.readString();
        rating = in.readInt();

    }

    public static final Creator<image_data> CREATOR = new Creator<image_data>() {
        @Override
        public image_data createFromParcel(Parcel in) {
            return new image_data(in);
        }

        @Override
        public image_data[] newArray(int size) {
            return new image_data[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(location);
        parcel.writeString(date);
        parcel.writeList(keywords);
        parcel.writeByte((byte)(isshared ? 1 : 0));
        parcel.writeString(who);
        parcel.writeInt(rating);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

    public Boolean getIsshared() {
        return isshared;
    }

    public void setIsshared(Boolean isshared) {
        this.isshared = isshared;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String toString() {
        return this.name;
    }
}
