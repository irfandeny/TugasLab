package com.data;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.util.iMenu;
import books.Buku;


public class Student extends User implements iMenu {
    String name, nim, faculty, studyProgram;
    private static ArrayList<Buku> borrowedBooks;
    static Scanner scanner;

    public Student(String name, String nim, String faculty, String studyProgram) {
        this.name = name;
        this.nim = nim;
        this.faculty = faculty;
        this.studyProgram = studyProgram;
        setBorrowedBooks(new ArrayList<>());
        scanner = new Scanner(System.in);
    }
    public Student(){
    }

    public void displayInfo(Student student){
        System.out.println("Program login sebagai: " + student.getName() + " (" + student.getNim()+")");
    }

    @Override
    public void Menu() {
        Scanner input = new Scanner(System.in);
        boolean selesai = false;
        while (!selesai) {
            try {
                System.out.println("===== Student Menu =====");
                System.out.println("1. Pinjam Buku");
                System.out.println("2. Tampilkan Buku yang dipinjam");
                System.out.println("3. Kembalikan buku");
                System.out.println("4. Logout");
                System.out.print("Pilihan Opsi (1-4): ");
                int pilihan = input.nextInt();
                input.nextLine();
                switch (pilihan) {
                    case 1:
                        choiceBooks();
                        break;
                    case 2:
                        showBorrowedBooks();
                        break;
                    case 3:
                        returnBooks();
                        break;
                    case 4:
                        selesai = true;
                        logout();
                        System.out.println("dari student menu");
                        break;
                    default:
                        System.out.println("Pilihan tidak tersedia");
                }
            } catch (InputMismatchException e) {
                System.out.println("Pilihan harus berupa angka (1-4). Silakan coba lagi.");
                input.nextLine();
            }
        }
    }

    public void showBorrowedBooks() {
        if (getBorrowedBooks().isEmpty()) {
            System.out.println("Kamu belum meminjam buku");
        } else {
            System.out.println("Books yang dipinjam oleh :");
            System.out.println("=========================================================================================================================");
            System.out.printf("|| %-20s || %-20s || %-20s || %-20s || %-3s ||", "ID Buku", "Nama Buku", "Penulis", "Kategori","Durasi");
            System.out.println("\n=========================================================================================================================");
            for (Buku buku : getBorrowedBooks()) {
                System.out.printf("|| %-20s || %-20s || %-20s || %-20s || %-7d||\n", buku.getId(), buku.getTitle(), buku.getAuthor(),buku.getCategory(),buku.getDaysToReturn());
            }
            System.out.print("=========================================================================================================================\n");
        }
    }

    public void choiceBooks() {
        super.displayBook();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan Id buku yang ingin dipinjam:");
        String idBuku = scanner.nextLine();
        Buku bukuygPinjam = null;
        for (Buku buku : User.bookList) {
            if (buku.getId().equals(idBuku)) {
                bukuygPinjam = buku;
                break;
            }
        }

        if (bukuygPinjam == null) {
            System.out.println("Id buku tidak tersedia");
            return;
        }

        if (bukuygPinjam.getStock() > 0) {
            bukuygPinjam.setStock(bukuygPinjam.getStock() - 1);
            getBorrowedBooks().add(bukuygPinjam);
            System.out.println("berapa hari buku akan dipinjam? (maksimal 15 hari)");
            int jumlahHariPeminjaman = scanner.nextInt();
            for (Buku buku : getBorrowedBooks()) {
                buku.setDaysToReturn(jumlahHariPeminjaman);
            }
            System.out.println("Buku berhasil dipinjam, kamu harus mengembalikannya sebelum " + jumlahHariPeminjaman + " hari.");
        } else {
            System.out.println("Buku tidak tersedia");
        }
    }

    public void logout() {
        if (getBorrowedBooks().isEmpty()) {
            System.out.println("log out");
        } else {
            showBorrowedBooks();
            System.out.print("kamu yakin ingin meminjan buku? (Y/N):");
            String jawab = scanner.nextLine();
            if (jawab.equalsIgnoreCase("N")) {
                returnBooks();
            } else {
                System.out.println("Peminjaman buku berhasil dilakukan");
                System.out.println("Kamu telah log out");
            }
        }
    }

    public void returnBooks() {
        if(getBorrowedBooks().isEmpty()) {
            System.out.println("kamu tidak ada buku terpinjam untuk dikembalikan.");
            return;
        }
        System.out.print("Masukkan id buku yang ingin di kembalikan:");
        String idBuku = scanner.nextLine();
        Buku bukuPinjam = null;
        for(Buku buku : User.bookList) {
            if(buku.getId().equals(idBuku)) {
                bukuPinjam = buku;
                break;
            }
        }
        if(bukuPinjam == null) {
            System.out.println("Buku tidak ditemukan.");
            return;
        }
        for(Buku buku : getBorrowedBooks()) {
            if(buku.getId().equals(idBuku)) {
                bukuPinjam.setStock(bukuPinjam.getStock() + 1);
                getBorrowedBooks().remove(buku);
                System.out.println("Buku berhasil dikembalikan.");
                return;
            }
        }
        System.out.println("Buku tidak ditemukan di daftar buku terpinjam.");
    }

    public String getNim() {
        return nim;
    }
    public String getName() {
        return name;
    }
    public String getFaculty(){
        return faculty;
    }
    public String getStudyProgram(){
        return studyProgram;
    }
    public static ArrayList<Buku> getBorrowedBooks() {
        return borrowedBooks;
    }
    public static void setBorrowedBooks(ArrayList<Buku> borrowedBooks) {
        Student.borrowedBooks = borrowedBooks;
    }
}

