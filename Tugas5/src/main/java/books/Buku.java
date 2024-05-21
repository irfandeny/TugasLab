package books;

public class Buku {
    String id, title, author, category;
    int stock, daysToReturn;

    public Buku(String id, String title, String author, String category, int stock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getCategory(){
        return category;
    }
    public int getStock() {
        return stock;
    }
    public int getDaysToReturn(){
        return daysToReturn;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setCategory(String category){
        this.category=category;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public void setDaysToReturn(int daysToReturn) {
        this.daysToReturn = daysToReturn;
    }
}
