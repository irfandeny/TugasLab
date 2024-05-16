package com.data;

import books.Buku;


public class User {
        public static Buku[] bookList = new Buku[100];
        public static int bookCount = 0;

        
        public static void addBook(String id, String title, String author, String category, int stock){
            bookList[User.bookCount++] = new Buku(id, title, author, category, stock);
        }
        public static Buku[] getBookList() {
            return bookList;
        }
        
        public void displayBook() {
            System.out.println("=========================================================================================================================");
            System.out.printf("|| %-25s || %-25s || %-25s || %-20s || %-3s ||", "ID Buku", "Nama Buku", "Penulis", "Kategori", "Stok");
            System.out.println("\n=========================================================================================================================");
            for (Buku buku : getBookList()) {
                if (buku != null) {
                    System.out.printf("|| %-25s || %-25s || %-25s || %-20s || %-3d  ||\n", buku.getId(), buku.getTitle(), buku.getAuthor(), buku.getCategory(), buku.getStock());
                }
            }
            System.out.print("=========================================================================================================================\n");
        }
    }
