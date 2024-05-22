package books;

import com.data.Admin;
import com.data.User;
import java.util.Scanner;


public class TextBook extends Buku {
    String category = "Text Book";
    public TextBook(String id, String title, String author, String category, int stock) {
        super(id, title, author, category, stock);
    }

    public void masukanBuku() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan judul buku: ");
        setTitle(scanner.nextLine());
        System.out.print("Masukkan penulis buku: ");
        setAuthor(scanner.nextLine());
        System.out.print("Masukkan stok buku: ");
        setStock(scanner.nextInt());
        scanner.nextLine();
        setId(Admin.generateId());
        setCategory(category);
        User.bookList.add(new TextBook(getId(), getTitle(), getAuthor(), getCategory(), getStock()));
        System.out.println("Buku berhasil ditambahkan");
    }
}
