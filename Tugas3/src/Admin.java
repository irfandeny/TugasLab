import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

class Admin extends User{
    String inputUsername, inputPassword;
    String adminUsername = "admin";
    String adminPassword = "password";
    public ArrayList<Student> userStudent;
    Scanner scanner =  new Scanner(System.in);



    //method login admin
    public boolean isAdmin(String inputUsername, String inputPassword) {
        return this.adminUsername.equals(inputUsername) && this.adminPassword.equals(inputPassword);
    }
    public void loginAsAdmin() {
        Main mn = new Main();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        inputUsername = scanner.nextLine();
        System.out.print("Enter password: ");
        inputPassword = scanner.nextLine();
        if (isAdmin(inputUsername, inputPassword)) {
            System.out.println("Login berhasil. Selamat datang, admin!");
            mn.menuAdmin();
        } else {
            System.out.println("Username atau password salah. Silakan coba lagi.");
        }
    }

    //method displayStudent
    public void tampilkanDaftarMahasiswa() {
        System.out.println("===== Daftar Mahasiswa =====");
        for(Student student : Main.userStudent) {
            System.out.println(student.getNim() + "\t||\t" + student.getName() + "\t||\t" + student.getFaculty() + "\t||\t" + student.getStudyProgram());
        }
    }
    //method addStudent
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
        Main.userStudent.add(new Student(name, nim, faculty, studyProgram));
        System.out.println("Berhasil ditambahkan");
    }

    @Override
    public void tambahBuku() {
        System.out.println("Masukkan inputan sesuai permintaan:");
        super.tambahBuku();
    }
    @Override
    public void displayBook() {
        super.displayBook();
    }
    public void hapusBuku() {
        System.out.print("Masukkan ID buku yang ingin dihapus: ");
        String id = scanner.nextLine();
        for (Buku buku : Main.bookList) {
            if (buku.getId().equals(id)) {
                Main.bookList.remove(buku);
                System.out.println("Buku berhasil dihapus");
                return;
            }
        }
        System.out.println("Buku tidak ditemukan");
    }

    public static String generateId() {
        String uuid = UUID.randomUUID().toString();
        String[] uuidParts = uuid.split("-");
        return uuidParts[0].substring(0, 4) + "-" + uuidParts[1].substring(0, 4) + "-" + uuidParts[2].substring(0, 4);
    }

}