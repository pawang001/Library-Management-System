package Library;

public class Order {

    private Book book;
    private User user;
    private int qty;
    private double price;

    public Order(){
    }

    public Order(Book book, User user, int qty, double price) {
        this.book = book;
        this.user = user;
        this.qty = qty;
        this.price = price;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @Override
    public String toString() {
        return "Book name =" + book.getName() +"\n" +
                "Username=" + user.getName() +"\n" +
                "Qty=" + String.valueOf(qty) +"\n" +
                "Price=" + String.valueOf(price);
    }
    public String toString2() {
        return book.getName() +"<N/>" + user.getName() +"<N/>" + String.valueOf(qty) +"<N/>" +
                String.valueOf(price);
    }
}
