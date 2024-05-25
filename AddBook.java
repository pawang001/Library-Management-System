package Library;

import java.util.Scanner;

public class AddBook implements IOOperation {

    @Override
    public void oper(Database database, User user){

        Scanner s = new Scanner(System.in);
        Book book = new Book();
        System.out.println("\nEnter book Name: ");
        String name = s.next();
        if(database.getBook(name)>-1) {
            System.out.println("There is a book with this name!");
            user.menu(database, user);
            return;
        } else {
            book.setName(name);
            System.out.println("Enter book Author: ");
            book.setAuthor(s.next());
            System.out.println("Enter book Publisher: ");
            book.setPublisher(s.next());
            System.out.println("Enter book Collection Address: ");
            book.setAddress(s.next());
            System.out.println("Enter qty: ");
            book.setQty(s.nextInt());
            System.out.println("Enter price: ");
            book.setPrice(s.nextDouble());
            System.out.println("Enter borrowing copies: ");
            book.setBrwcopies(s.nextInt());
            database.AddBook(book);
            System.out.println("Book added successfully\n");
            user.menu(database, user);
            s.close();
        }
    }
}
