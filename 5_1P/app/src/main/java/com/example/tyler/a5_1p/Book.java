package com.example.tyler.a5_1p;

import android.graphics.drawable.Drawable;

public class Book {
    String rating;
    Drawable image;

    public Book(String rating) {
        this.rating = rating;
        this.image = null  ;
    }

    public Book(String rating, Drawable image)
    {
        this.rating = rating;
        this.image = image;
    }
    public String getRating() {
        return rating;
    }

    public Drawable getImage()
    {
        return image;
    }

    public void setImage( Drawable image)
    {
        this.image = image;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
