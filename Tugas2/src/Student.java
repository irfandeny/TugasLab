import java.util.ArrayList;
import java.util.Scanner;

public class Student {
    String name, nim, faculty, studyProgram;
    public static ArrayList<Buku> bukuTerpinjam;
    static Scanner scanner;
//construktor
    public Student(String name, String nim, String faculty, String studyProgram) {
        this.name = name;
        this.nim = nim;
        this.faculty = faculty;
        this.studyProgram = studyProgram;
        bukuTerpinjam = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    public Student() {
    }
//getter setter
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
    //method displayBook
    public static void displayBook() {
        System.out.println("Buku yang tersedia");
        for (Buku buku : Main.bookList) {
            System.out.println(buku.getTitle() + "\t||\t" + buku.getAuthor() + "\t||\t" + buku.getStock());
        }
    }
    //method tampilBuku
    public void tampilBukuTerpinjam() {
        if (bukuTerpinjam.isEmpty()) {
            System.out.println("Kamu belum meminjam buku");
        } else {
            System.out.println("Books borrowed by " + name + " (" + nim + "):");
            for (Buku buku : bukuTerpinjam) {
                System.out.println(buku.getId() + "\t||\t" + buku.getTitle() + "\t||\t" + buku.getAuthor());
            }
        }
    }
    //method Pinjam buku
    public static void pinjamBuku() {
        System.out.println("Masukkan Id buku yang ingin dipinjam:");
        String idBuku = scanner.nextLine();
        Buku bukuygPinjam = null;
        for (Buku buku : Main.bookList) {
            if (buku.getId().equals(idBuku)) {
                bukuygPinjam = buku;
                break;
            }
        }
        if (bukuygPinjam != null) {
            if (bukuygPinjam.getStock() > 0) {
                bukuTerpinjam.add(bukuygPinjam);
                bukuygPinjam.setStock(bukuygPinjam.getStock() - 1);
                System.out.println("Buku berhasil dipinjam");
            } else {
                System.out.println("Buku tidak ditemukan");
            }
        }
    }
//method log out
    public void logout() {
        System.out.print("Logging out...");
    }
}
