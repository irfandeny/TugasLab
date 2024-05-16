import java.util.ArrayList;
import java.util.Scanner;

class Admin {
    String inputUsername, inputPassword;
    String adminUsername = "admin";
    String adminPassword = "password";
    public static ArrayList<Student> userStudent;
//construktor
    public Admin(ArrayList<Student> userStudent) {
        Admin.userStudent = userStudent;
    }
    public Admin() {
    }
//method login admin
    public void loginAdmin() {
        Scanner scanner = new Scanner(System.in);
        Main logad = new Main();
        System.out.print("Enter username: ");
        inputUsername = scanner.nextLine();
        System.out.print("Enter password: ");
        inputPassword = scanner.nextLine();
        if(inputUsername.equals(adminUsername) && inputPassword.equals(adminPassword)){
            System.out.println("Login berhasil. Selamat datang, admin!");
            logad.menuAdmin();
        }else{
            System.out.println("Username atau password salah. Silakan coba lagi.");
        }
    }
    //method displayStudent
    public void tampilkanDaftarMahasiswa() {
        System.out.println("===== Daftar Mahasiswa =====");
        for(Student student : userStudent) {
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
            userStudent.add(new Student(name, nim, faculty, studyProgram));
            System.out.println("Berhasil ditambahkan");
    }
}