package com.example.tyler.a5_1p;

import android.content.Context;

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
