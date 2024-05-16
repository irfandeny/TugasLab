
import java.util.ArrayList;
import java.util.Scanner;

public class User {
    Scanner scanner =  new Scanner(System.in);
    public static ArrayList<Buku> bookList;
    public void displayBook() {
        System.out.println("=========================================================================================================================");
        System.out.printf("|| %-25s || %-25s || %-25s || %-20s || %-3s ||", "ID Buku", "Nama Buku", "Penulis", "Kategori", "Stok");
        System.out.println("\n=========================================================================================================================");

        for (Buku buku : Main.bookList) {
            System.out.printf("|| %-25s || %-25s || %-25s || %-20s || %-3d  ||\n", buku.getId(), buku.getTitle(), buku.getAuthor(),buku.getCategory(), buku.getStock());
        }
        System.out.print("=========================================================================================================================\n");
    }
    public void tambahBuku() {
        String kategori = null;
        String judul;
        String penulis;
        int stok;

            System.out.print("Pilih kategori buku: ");
            System.out.println("1. History Book");
            System.out.println("2. Story Book");
            System.out.println("3. TextBook");
            System.out.print("Pilihan Opsi (1-3): ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // consume newline character
            switch (pilihan) {
                case 1:
                    kategori = "History Book";
                    break;
                case 2:
                    kategori = "Story Book";
                    break;
                case 3:
                    kategori = "text Book";
                default:
                    System.out.println("Pilihan tidak tersedia");
            }
        System.out.print("Masukkan judul buku: ");
        judul = scanner.nextLine();
        System.out.print("Masukkan penulis buku: ");
        penulis = scanner.nextLine();
        System.out.print("Masukkan stok buku: ");
        stok = scanner.nextInt();
        scanner.nextLine(); // consume newline character
        String id = Admin.generateId();
        Buku bukuBaru = new Buku(id, judul, penulis, kategori, stok);
        Main.bookList.add(bukuBaru);
        System.out.println("Berhasil ditambahkan");
    }
}
