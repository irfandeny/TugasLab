package com.data;

import books.Buku;
import java.util.ArrayList;


public class User {
    public static ArrayList<Buku> bookList = new ArrayList<>();

    public static void addBook(String id, String title, String author, String category, int stock){
        bookList.add(new Buku(id, title, author, category, stock));
    }


}
