package com.example.tyler.a5_1p;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Books {
    private static Books sBooks;

    private HashMap<String, Book> mBooks;

    public static  Books get(Context context) {
        if (sBooks == null) {
            sBooks = new Books(context);
        }
        return sBooks;
    }

    private Books(Context context)
    {
        mBooks = new HashMap<String, Book>();
        mBooks.put("Lord of the Rings: fellowship of the ring", new Book ("5/7"));
        mBooks.put("The Hobbit", new Book ("10/10"));
        mBooks.put("The Martian", new Book("five stars", ContextCompat.getDrawable(context, R.drawable.the_martian)));
        mBooks.put("Book book book", new Book("a rating of sorts", ContextCompat.getDrawable(context, R.drawable.book_image)));
        mBooks.put("this is a test", new Book("another rating of sorts"));
        mBooks.put("Six books down", new Book("this book has another rating", ContextCompat.getDrawable(context, R.drawable.book_image)));
        mBooks.put("another book", new Book("rated 7/10"));
        mBooks.put("booktastic", new Book("i am rated a thingo"));
        mBooks.put("ninth book", new Book("8/10"));
        mBooks.put("final book", new Book("10/10"));
    }

    public HashMap<String, Book> getBooks() {
        return mBooks;
    }
    public Book getBook (String name)
    {
        if(mBooks.containsKey(name)){
            return mBooks.get(name);
        }
        return null;
    }

    public List<String> getBookNames()
    {
        return new ArrayList<String>(mBooks.keySet());
    }

}
