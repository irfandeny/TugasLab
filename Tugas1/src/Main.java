import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean selesai = false;
        String nim, username, password;
        System.out.println("SELAMAT DATANG");

        while (!selesai) {
            System.out.println("===== Library System =====");
            System.out.println("1. Login as Student");
            System.out.println("2. Login as Admin");
            System.out.println("3. Exit");
            System.out.print("Pilihan Opsi (1-3): ");
            int pilihan = input.nextInt();

            switch (pilihan) {
                case 1:
                    System.out.print("Enter your NIM : ");
                    nim = input.next();
                    if (nim.length() == 15) {
                        System.out.println("Successful Login as Student");
                    } else {
                        System.out.println("User Not Found");
                    }
                    break;
                case 2:
                    System.out.print("Enter your username (admin): ");
                    username = input.next();
                    System.out.print("Enter your password (admin): ");
                    password = input.next();
                    if(username.equals(password)) {
                        System.out.println("Successful Login as Admin");
                    } else {
                        System.out.println("Admin User Not Found!!");
                    }
                    break;
                case 3:
                    selesai = true;
                    System.out.println("Program selesai, Exit program");
                    break;
                default:
                    System.out.println("Pilihan tidak tersedia");
            }
        }

        input.close();
    }
}