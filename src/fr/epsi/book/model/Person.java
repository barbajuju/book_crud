package fr.epsi.book.model;

/**
 * Created by ssylla on 21/12/2017.
 */
public class Person {

    private String id;
    private String lastName;
    private String firstName;
    private String email;
    private Book book;

    public Person( String id, String lastName, String firstName, String email ) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public Book getBook() {
        return book;
    }

    public void setBook( Book book ) {
        this.book = book;
    }
}
