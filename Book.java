package Library;

public class Book {

    private String name;
    private String author;
    private String publisher;
    private String address;
    private String status;
    private int qty;
    private double price;
    private int brwcopies;

    public Book(){
    }

    public Book(String name, String author, String publisher, String address,
                int qty, double price, int brwcopies) {
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.address = address;
        this.qty = qty;
        this.price = price;
        this.brwcopies = brwcopies;
    }

    @Override
    public String toString() {
        return  "Book name = " + name + "\n"+
                "Book Author = " + author + "\n"+
                "Book Publisher = " + publisher + "\n"+
                "Book Collection Address = " + address + "\n"+
                "Qty = " + String.valueOf(qty) + "\n"+
                "Price = " + String.valueOf(price) + "\n"+
                "Library.Borrowing Copies = " + String.valueOf(brwcopies);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getBrwcopies() {
        return brwcopies;
    }

    public void setBrwcopies(int brwcopies) {
        this.brwcopies = brwcopies;
    }

    public String toString2() {
        String text=  name + "<N/>" + author + "<N/>" + publisher + "<N/>" + address + "<N/>" +
                String.valueOf(qty) + "<N/>" + String.valueOf(price) + "<N/>" + String.valueOf(brwcopies);
        return text;
    }
}
