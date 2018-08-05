package fr.epsi.book.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ssylla on 21/12/2017.
 */
public class Book {

    private String id;
    private String name;

    private List<Person> contacts;

    public Book() {
        id = UUID.randomUUID().toString();
        contacts = new ArrayList<>(  );
    }

    public Book( String name ) {
        this();
        this.name = name;
    }

    public Book( String id, String name ) {
        this.id = id;
        this.name = name;
        contacts = new ArrayList<>(  );
    }

    public Book( String id, String name, List<Person> contacts ) {
        this.id = id;
        this.name = name;
        this.contacts = contacts;
    }

    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public List<Person> getContacts() {
        return contacts;
    }

    public void setContacts( List<Person> contacts ) {
        this.contacts = contacts;
    }
}
