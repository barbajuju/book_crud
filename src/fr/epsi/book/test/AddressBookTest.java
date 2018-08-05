package fr.epsi.book.test;

import fr.epsi.book.dao.BookDAO;
import fr.epsi.book.model.Book;

import java.nio.channels.SelectionKey;
import java.util.List;
import java.util.Scanner;

public class AddressBookTest {

    private static Book currentBook;
    private static Scanner scanner = new Scanner( System.in );

    public static void main( String[] args ) {

        dspMainMenu();
    }

    public static void addPerson() {
        System.out.println( "**************************************" );
        System.out.println( "**********Ajout d'un contact**********" );
        //TODO
        dspContactsMenu();
    }

    public static void editPerson() {
        System.out.println( "**************************************" );
        System.out.println( "******Modification d'un contact*******" );
        //TODO
    }

    public static void deletePerson() {
        System.out.println( "**************************************" );
        System.out.println( "******Suppression d'un contact*******" );
        //TODO
    }

    public static void dspContacts() {
        System.out.println( "**************************************" );
        System.out.println( "********Liste de vos contacts*********" );
        //TODO
        dspContactsMenu();
    }

    public static void sort() {
        int response;
        do {
            System.out.println( "*******Choix du critère*******" );
            System.out.println( "* 1 - Par défaut             *" );
            System.out.println( "* 2 - Nom                    *" );
            System.out.println( "* 3 - Prénom                 *" );
            System.out.println( "* 4 - Email                  *" );
            System.out.println( "****************************" );
            System.out.print( "*Entrez votre choix : " );
            response = scanner.nextInt();
        } while ( 0 >= response || response > 4 );
        scanner.nextLine();
        //TODO
        dspContactsMenu();
    }

    public static void dspContactsMenu() {
        int response;
        do {
            System.out.println( "**************************************" );
            System.out.println( "*****************Menu*****************" );
            System.out.println( "* 1 - Ajouter un contact             *" );
            System.out.println( "* 2 - Modifier un contact            *" );
            System.out.println( "* 3 - Supprimer un contact           *" );
            System.out.println( "* 4 - Lister les contacts            *" );
            System.out.println( "* 5 - Rechercher un contact          *" );
            System.out.println( "* 6 - Trier les contacts             *" );
            System.out.println( "* 7 - Quitter                        *" );
            System.out.println( "**************************************" );
            System.out.print( "*Entrez votre choix : " );
            response = scanner.nextInt();
        } while ( 0 >= response || response > 7 );
        scanner.nextLine();
        switch ( response ) {
            case 1:
                addPerson();
                break;
            case 2:
                editPerson();
                break;
            case 3:
                deletePerson();
                break;
            case 4:
                dspContacts();
                break;
            case 5:
                break;
            case 6:
                sort();
                break;
        }
    }

    public static void dspMainMenu() {
        int response;
        do {
            System.out.println( "*****************************************" );
            System.out.println( "*****************Menu********************" );
            System.out.println( "* 1 - Ajouter un carnet d'adresses      *" );
            System.out.println( "* 2 - Modifier un carnet d'adresses     *" );
            System.out.println( "* 3 - Supprimer un carnet d'adresses    *" );
            System.out.println( "* 4 - Lister les carnets d'adresse      *" );
            System.out.println( "* 5 - Rechercher un carnet d'adresses   *" );
            System.out.println( "* 6 - Sélectionner un carnet d'adresses *" );
            System.out.println( "* 7 - Quitter                           *" );
            System.out.println( "*****************************************" );
            System.out.println( "*****************************************" );
            System.out.print( "* Entrez votre choix : " );
            response = scanner.nextInt();
        } while ( 0 >= response || response > 7 );
        scanner.nextLine();
        switch ( response ) {
            case 1:
                addBook();
                break;
            case 2:
                editBook();
                break;
            case 3:
                deleteBook();
                break;
            case 4:
                dspBooks();
                break;
            case 5:
                searchBook();
                break;
            case 6:
                selectCurrentBook();
                break;
        }
    }

    public static void addBook() {
        System.out.println( "************************************************" );
        System.out.println( "**********Ajout d'un carnet d'adresses**********" );
        System.out.print( "Entrez le nom du carnet d'adresses : " );
        String name = scanner.nextLine();
        Book book = new Book( name );
        BookDAO dao = new BookDAO();
        try {
            dao.create( book );
        } catch ( java.sql.SQLException e ) {
            System.out.println( e.getMessage() );
        }
        dspMainMenu();
    }

    public static void editBook() {
        System.out.println( "***********************************************" );
        System.out.println( "*******Modification d'un carnet d'adresses******" );
        BookDAO dao = new BookDAO();
        try {
            List<Book> list = dao.findAll();
            if ( list.size() > 0 ) {
                System.out.println( "Sélectionnez le carnet d'adresses à modifier..." );
                System.out.println( "*******************************************************" );
                System.out.println( "index |id             | nom           " );
                System.out.println( "*******************************************************" );
                for ( int i = 0; i < list.size(); ++i ) {
                    System.out.println( i + "  | " + list.get( i ).getId() + "            | " + list.get( i ).getName() );
                }
                int response;
                do {
                    System.out.print( "Entrez votre choix (index/-1 pour annuler : " );
                    response = scanner.nextInt();
                } while ( response < -1 || response >= list.size() );
                scanner.nextLine();
                if ( -1 != response ) {
                    Book book = list.get( response );
                    System.out.println( "Modification du carnet d'adresses (" + book.getId() + ")" );
                    System.out.print( "Modifiez le nom du carnet (" + book.getId() + ") : " );
                    String name = scanner.nextLine();
                    if ( !name.equals( "" ) && !name.equals( book.getName() ) ) {
                        book.setName( name );
                        dao.update( book );
                    }
                }
            } else {
                System.out.println( "Aucun élément trouvé !!!" );
            }
        } catch ( java.sql.SQLException e ) {
            System.out.println( e.getMessage() );
        }
        dspMainMenu();
    }

    public static void deleteBook() {
        System.out.println( "************************************************" );
        System.out.println( "*******Suppression d'un carnet d'adresses*******" );
         BookDAO dao = new BookDAO();
        try {
            List<Book> list = dao.findAll();
            if ( list.size() > 0 ) {
                System.out.println( "Choix du carnet d'adresses à supprimer" );
                System.out.println( "***************************************************************" );
                System.out.println( "idx|id                                               | nom     " );
                System.out.println( "***************************************************************" );
                for ( int i = 0; i < list.size(); ++i ) {
                    System.out.println( i + "  | " + list.get( i ).getId() + "            | " + list.get( i ).getName() );
                }
                int response;
                do {
                    System.out.print( "Entrez l'id à supprimer (/-1 pour annuler) : " );
                    response = scanner.nextInt();
                } while ( response < -1 || response >= list.size() );
                scanner.nextLine();
                if ( -1 != response ) {
                    Book book = list.get( response );
                    dao.delete(book);
                }
            } else {
                System.out.println( "Aucun élément à supprimer" );
            }
        } catch ( java.sql.SQLException e ) {
            System.out.println( e.getMessage() );
        }
        dspMainMenu();
    }

    public static void dspBooks() {
        System.out.println( "***********************************************" );
        System.out.println( "*******Liste des carnets d'adresse******" );
        BookDAO dao = new BookDAO();
        try {
            List<Book> list = dao.findAll();
            System.out.println( "***************************************************************" );
            System.out.println( "idx|id                                               | nom     " );
            System.out.println( "***************************************************************" );
            for ( int i = 0; i < list.size(); ++i ) {
                System.out.println( i + "  | " + list.get( i ).getId() + "            | " + list.get( i ).getName() );
            }
        } catch ( java.sql.SQLException e ) {
            System.out.println( e.getMessage() );
        }
        dspMainMenu();
    }

    public static void searchBook() {
        System.out.println( "***********************************************" );
        System.out.println( "*******Rechercher un carnet d'adresses*********" );
        BookDAO dao = new BookDAO();
        try {
            List<Book> list = dao.findAll();
                System.out.println( "Indiquez le nom du carnet à rechercher:" );
                String reponse;
                reponse = scanner.nextLine();

                	for ( int i = 0; i < list.size(); ++i ) {
                		String nomactuel = list.get(i).getName();
                		if (reponse.equals(nomactuel)) {
                		System.out.println( "Voici le carnet recherché : " + reponse);
                        System.out.println( i + "  | " + list.get( i ).getId() + "            | " + list.get( i ).getName() );
                		}
                	}
        } catch ( java.sql.SQLException e ) {
            System.out.println( e.getMessage() );
        }
        if ( null != currentBook ) {
            dspContactsMenu();
        } else {
            dspMainMenu();
        }
    }

    public static void selectCurrentBook() {
        System.out.println( "***********************************************" );
        System.out.println( "*******Sélectionner un carnet d'adresses********" );
        BookDAO dao = new BookDAO();
        try {
            List<Book> list = dao.findAll();
            if ( list.size() > 0 ) {
                System.out.println( "Sélectionnez le carnet courant..." );
                System.out.println( "*******************************************************" );
                System.out.println( "index |id             | nom           " );
                System.out.println( "*******************************************************" );
                for ( int i = 0; i < list.size(); ++i ) {
                    System.out.println( i + "  | " + list.get( i ).getId() + "            | " + list.get( i ).getName() );
                }
                int response;
                do {
                    System.out.print( "Entrez votre choix (index/-1 pour annuler : " );
                    response = scanner.nextInt();
                } while ( response < -1 || response >= list.size() );
                scanner.nextLine();
                if ( -1 != response ) {
                    currentBook = list.get( response );
                } else {
                    currentBook = null;
                }
            } else {
                System.out.println( "Aucun élément trouvé !!!" );
            }
        } catch ( java.sql.SQLException e ) {
            System.out.println( e.getMessage() );
        }
        if ( null != currentBook ) {
            dspContactsMenu();
        } else {
            dspMainMenu();
        }
    }
}
