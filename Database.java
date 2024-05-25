package Library;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Database {

    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<String> userNames = new ArrayList<String>();
    private ArrayList<Book> books = new ArrayList<Book>();
    private ArrayList<String> bookNames = new ArrayList<String>();
    private ArrayList<Order> orders = new ArrayList<Order>();
    private ArrayList<Borrowing> borrowings = new ArrayList<Borrowing>();

    private File usersFile = new File("C:\\Library Management System\\Data\\Users");
    private File booksFile = new File("C:\\Library Management System\\Data\\Books");
    private File ordersFile = new File("C:\\Library Management System\\Data\\Orders");
    private File borrowingsFile = new File("C:\\Library Management System\\Data\\Borrowings");
    private File folder = new File("C:\\Library Management System\\Data");

    public Database(){
        if (!folder.exists()){
            folder.mkdirs();
        }
        if(!usersFile.exists()){
            try{
                usersFile.createNewFile();
            } catch (Exception e){}
        }
        if(!booksFile.exists()){
            try {
                booksFile.createNewFile();
            } catch (Exception e){}
        }
        if(!ordersFile.exists()) {
            try {
                ordersFile.createNewFile();
            } catch (Exception e) {
            }
        }
        if(!borrowingsFile.exists()) {
            try {
                borrowingsFile.createNewFile();
            } catch (Exception e) {
            }
        }
//        getUsers();
//        getBooks();
//        getOrders();
//        getBorrowings();
    }

    public void AddUser(User s) {
        users.add(s);
        userNames.add(s.getName());
        saveUsers();
    }

    public int login(String phoneNumber, String email){
        int n =-1;
        for(User s : users){
            if (s.getPhoneNumber().matches(phoneNumber) && s.getEmail().matches(email)){
                n = users.indexOf(s);
                break;
            }
        } return n;
    }
    public User getUser(int n){
        return users.get(n);
    }

    public void AddBook(Book book){
        books.add(book);
        bookNames.add(book.getName());
        saveBooks();
    }

    private void getUsers(){
        String text1 = "";
        try{
            BufferedReader br1 = new BufferedReader(new FileReader(usersFile));
            String s1;
            while ((s1 = br1.readLine()) != null) {
                text1 = text1 + s1;
            }
            br1.close();
        }
        catch (Exception e){
            System.err.println(e.toString());
        }

        if(!text1.matches("") || !text1.isEmpty()){
            String[] a1 = text1.split("<NewUser/>");
            for (String s : a1) {
                String[] a2 = s.split("<N/>");
                if (a2[3].matches("Admin")) {
                    User user = new Admin(a2[0], a2[1], a2[2]);
                    users.add(user);
                    userNames.add(user.getName());
                } else {
                    User user = new NormalUser(a2[0], a2[1], a2[2]);
                    users.add(user);
                    userNames.add(user.getName());
                }
            }
        }
    }

    protected void saveUsers(){
        String text1 = "";
        for(User user : users) {
            text1 = text1 + user.toString()+"<NewUser/>\n";
        }
        try {
            PrintWriter pw = new PrintWriter(usersFile);
            pw.print(text1);
            pw.close();
        }
        catch(Exception e){
            System.err.println(e.toString());
        }
    }

    protected void saveBooks(){
        String text1 = "";
        for(Book book : books) {
            text1 = text1 + book.toString2()+"<NewBook/>\n";
        }
        try {
            PrintWriter pw = new PrintWriter(booksFile);
            pw.print(text1);
            pw.close();
        } catch(Exception e){
            System.err.println(e.toString());
        }
    }
    private void getBooks(){
        String text1 = "";
        try{
            BufferedReader br1 = new BufferedReader(new FileReader(booksFile));
            String s1;
            while ((s1 = br1.readLine()) != null) {
                text1 = text1 + s1;
            } br1.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }

        if(!text1.matches("") || !text1.isEmpty()){
            String[] a1 = text1.split("<NewBook/>");
            for (String s : a1) {
                Book book = parseBook(s);
                books.add(book);
                bookNames.add(book.getName());
            }
        }
    }
    public Book parseBook(String s){
        String[] a = s.split("<N/>");
        Book book = new Book();
        book.setName(a[0]);
        book.setAuthor(a[1]);
        book.setPublisher(a[2]);
        book.setAddress(a[3]);
        book.setQty(Integer.parseInt(a[4]));
        book.setPrice(Double.parseDouble(a[5]));
        book.setBrwcopies(Integer.parseInt(a[6]));
        return book;
    }

    public ArrayList<Book> getAllBooks(){
        return books;
    }

    public int getBook(String bookname){
        int i = -1;
        for(Book book : books) {
            if (book.getName().matches(bookname)){
                i = books.indexOf(book);
            }
        } return i;
    }

    public Book getBook(int i){
        return books.get(i);
    }
    public void deleteBook(int i){
        books.remove(i);
        bookNames.remove(i);
        saveBooks();
    }

    public void deleteAllData(){
        if(!usersFile.exists()){
            try{
                usersFile.delete();
            } catch (Exception e){}
        }
        if(!booksFile.exists()){
            try {
                booksFile.delete();
            } catch (Exception e){}
        }
        if(!ordersFile.exists()) {
            try {
                ordersFile.delete();
            } catch (Exception e) {}
        }
        if(!borrowingsFile.exists()) {
            try {
                borrowingsFile.delete();
            } catch (Exception e) {}
        }
    }

    public void addOrder(Order order, Book book, int bookindex){
        orders.add(order);
        books.set(bookindex,book);
        saveOrders();
        saveBooks();
    }

    protected void saveOrders(){
        String text1 = "";
        for(Order order : orders) {
            text1 = text1 + order.toString2()+"<NewOrder/>\n";
        }
        try {
            PrintWriter pw = new PrintWriter(ordersFile);
            pw.print(text1);
            pw.close();
            System.out.println("Order Saved");
        } catch(Exception e){
            System.err.println(e.toString());
        }
    }

    private void getOrders(){
        String text1 = "";
        try{
            BufferedReader br1 = new BufferedReader(new FileReader(ordersFile));
            String s1;
            while ((s1 = br1.readLine()) != null) {
                text1 = text1 + s1;
            } br1.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }

        if(!text1.matches("") || !text1.isEmpty()){
            String[] a1 = text1.split("<NewOrder/>");
            for (String s : a1) {
                Order order = parseOrder(s);
                orders.add(order);
            }
        }
    }
    public boolean userExists(String name){
        boolean f = false;
        for(User user : users){
            if(user.getName().toLowerCase().matches(name.toLowerCase())){
                f = true;
                break;
            }
        } return f;
    }
    private User getUserByName(String name){
        User u = new NormalUser("");
        for(User user : users){
            if(user.getName().matches(name)){
                u = user;
                break;
            }
        } return u;
    }
    public Order parseOrder(String s){
        String[] a = s.split("<N/>");
        Order order = new Order(books.get(getBook(a[0])),getUserByName(a[1]),
                Integer.parseInt(a[2]),Double.parseDouble(a[3]));
        return order;
    }

    public ArrayList<Order> getAllOrders(){
        return orders;
    }

    protected void saveBorrowings(){
        String text1 = "";
        for(Borrowing borrowing : borrowings) {
            text1 = text1 + borrowing.toString2()+"<NewBorrowing/>\n";
        }
        try {
            PrintWriter pw = new PrintWriter(borrowingsFile);
            pw.print(text1);
            pw.close();
//            System.out.println("Order Saved");
        } catch(Exception e){
            System.err.println(e.toString());
        }
    }

    private void getBorrowings(){
        String text1 = "";
        try{
            BufferedReader br1 = new BufferedReader(new FileReader(borrowingsFile));
            String s1;
            while ((s1 = br1.readLine()) != null) {
                text1 = text1 + s1;
            } br1.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }

        if(!text1.matches("") || !text1.isEmpty()){
            String[] a1 = text1.split("<NewBorrowing/>");
            for (String s : a1) {
                Borrowing borrowing = parseBorrowing(s);
                borrowings.add(borrowing);
            }
        }
    }

    private Borrowing parseBorrowing(String s){
        String[] a = s.split("<N/>");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate start = LocalDate.parse(a[0],formatter);
        LocalDate finish = LocalDate.parse(a[1],formatter);
        Book book = getBook(getBook(a[3]));
        User user = getUserByName(a[4]);
        Borrowing brw = new Borrowing(start, finish, user, book);
        return brw;
    }

    public void borrowBook(Borrowing brw, Book book, int bookindex){
        borrowings.add(brw);
        books.set(bookindex, book);
        saveBorrowings();
        saveBooks();
    }

    public ArrayList<Borrowing> getBrws(){
        return borrowings;
    }

    public void returnBook(Borrowing b , Book book, int bookindex){
        borrowings.remove(b);
        books.set(bookindex, book);
        saveBorrowings();
        saveBooks();
    }
}
