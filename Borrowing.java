package Library;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Borrowing {

    LocalDate start;
    LocalDate finish;
    int daysleft;
    User user;
    Book book;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Borrowing(Book book, User user){
        start = LocalDate.now();
        finish = start.plusDays(14);
        daysleft = Period.between(start,finish).getDays();
        this.book = book;
        this.user = user;
    }

    public Borrowing(LocalDate start, LocalDate finish, User user, Book book) {
        this.start = start;
        this.finish = finish;
        this.daysleft = Period.between(finish,LocalDate.now()).getDays();
        this.user = user;
        this.book = book;
    }

    public String getStart() {
        return formatter.format(start);
    }

    public String getFinish() {
        return formatter.format(finish);
    }

    public int getDaysleft() {
        return Period.between(finish, LocalDate.now()).getDays();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Borrowing time = " + start + "\nExpiry date = " + finish +
                "\nDays left = " + daysleft;
    }

    public String toString2() {
        return getStart() + "<N/>" + getFinish() + "<N/>" + getDaysleft()
                + "<N/>" +book.getName() + "<N/>" + user.getName() + "<N/>";
    }
}
