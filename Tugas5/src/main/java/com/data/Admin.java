package com.data;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;
import com.util.iMenu;
import books.HistoryBook;
import books.StoryBook;
import books.TextBook;
import exception.custom.IllegalAdminAccess;

public class Admin extends User implements iMenu {
    String adminUsername = "admin";
    String adminPassword = "password";
    public static ArrayList<Student> studentList = new ArrayList<>();
    Scanner scanner =  new Scanner(System.in);


    @Override
    public void Menu() {
        Scanner input = new Scanner(System.in);
        boolean selesai = false;
        while (!selesai) {
            try {
                System.out.println("===== Admin Menu =====");
                System.out.println("1. Tambah Buku");
                System.out.println("2. Hapus Buku");
                System.out.println("3. Tampilkan Daftar Buku");
                System.out.println("4. Tampilkan Daftar Mahasiswa");
                System.out.println("5. Tambah Mahasiswa");
                System.out.println("6. Log out");
                System.out.print("Pilihan Opsi (1-6): ");
                int pilihan = input.nextInt();
                switch (pilihan) {
                    case 1:
                        inputBook();
                        break;
                    case 2:
                        hapusBuku();
                        break;
                    case 3:
                        displayBook();
                        break;
                    case 4:
                        displayStudents();
                        break;
                    case 5:
                        addStudent();
                        break;
                    case 6:
                        selesai = true;
                        System.out.println("Logging out... from admin menu");
                        break;
                    default:
                        System.out.println("Pilihan tidak tersedia");
                }
            } catch (InputMismatchException e) {
                System.out.println("Pilihan harus berupa angka (1-6). Silakan coba lagi.");
                input.nextLine();
            }
        }
    }

    public void addStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter NIM: ");
        String nim;
        do {
            nim = scanner.nextLine();
            if (nim.length() != 15) {
                System.out.println("Invalid NIM format. NIM should have 15 characters.");
                System.out.print("Enter NIM again: ");
            }
        } while (nim.length() != 15);

        System.out.print("Enter faculty: ");
        String faculty = scanner.nextLine();
        System.out.print("Enter study program: ");
        String studyProgram = scanner.nextLine();
        addedStudent(name, nim, faculty, studyProgram);
        System.out.println("Berhasil ditambahkan");
    }


    public void inputBook() {
        System.out.println("Pilih kategori buku: ");
        System.out.println("1. History Book");
        System.out.println("2. Story Book");
        System.out.println("3. TextBook");
        System.out.print("Pilihan Opsi (1-3): ");
        int pilihan = scanner.nextInt();
        scanner.nextLine();
        switch (pilihan) {
            case 1:
                HistoryBook historyBook = new HistoryBook("", "", "", "", 0);
                historyBook.masukanBuku();
                break;
            case 2:
                StoryBook storyBook = new StoryBook("", "", "", "", 0);
                storyBook.masukanBuku();
                break;
            case 3:
                TextBook textBook = new TextBook("", "", "", "", 0);
                textBook.masukanBuku();
                break;
            default:
                System.out.println("Pilihan tidak tersedia");
        }
    }

    @Override
    public void displayBook() {
        super.displayBook();
    }

    public void displayStudents() {
        System.out.println("==================================================================================================");
        System.out.printf("|| %-20s || %-20s || %-20s || %-20s ||", "NIM", "Nama", "Fakultas", "Program Studi");
        System.out.println("\n==================================================================================================");
        for(Student student : getStudentList()) {
            System.out.printf("|| %-20s || %-20s || %-20s || %-20s || \n", student.getNim(), student.getName(), student.getFaculty(), student.getStudyProgram());
        }
        System.out.println("==================================================================================================");
    }

    public boolean isAdmin(String inputUsername, String inputPassword) throws IllegalAdminAccess {
        if (!this.adminUsername.equals(inputUsername) || !this.adminPassword.equals(inputPassword)) {
            throw new IllegalAdminAccess("Invalid credentials");
        }
        return true;
    }

    public static String generateId() {
        String uuid = UUID.randomUUID().toString();
        String[] uuidParts = uuid.split("-");
        return uuidParts[0].substring(0, 4) + "-" + uuidParts[1].substring(0, 4) + "-" + uuidParts[2].substring(0, 4);
    }

    public static ArrayList<Student> getStudentList(){
        return studentList;
    }

    public Student getStudentByNim(String nim) {
        for (Student student : studentList) {
            if (student.getNim().equals(nim)) {
                return student;
            }
        }
        return null;
    }
    public void addedStudent(String name, String nim, String faculty, String studyProgram) {
        studentList.add(new Student(name, nim, faculty, studyProgram));
    }

    public void hapusBuku() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan ID buku yang ingin dihapus: ");
        String id = scanner.nextLine();

        for (int i = 0; i < User.bookList.size(); i++) {
            if (User.bookList.get(i) != null && User.bookList.get(i).getId().equals(id)) {
                User.bookList.remove(i);
                System.out.println("Buku berhasil dihapus");
                return;
            }
        }
        System.out.println("Buku tidak ditemukan");
    }

}
