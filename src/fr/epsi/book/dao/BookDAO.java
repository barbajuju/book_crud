package fr.epsi.book.dao;

import fr.epsi.book.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssylla on 22/12/2017.
 */
public class BookDAO implements IDAO<Book> {
    private static final String FIND_ALL_QUERY = "SELECT * FROM book";
    private static final String FIND_QUERY = "SELECT * FROM book WHERE id = ?";
    private static final String INSERT_QUERY = "INSERT INTO book ( id, name ) VALUES ( ?, ? )";
    private static final String UPDATE_QUERY = "UPDATE book SET name = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM book WHERE id = ?";

    @Override
    public Book find( String id ) throws SQLException, ObjectNotFoundException {
        Book book = null;
        Connection cnx = DBConnectionManager.getConnection();
        PreparedStatement st = cnx.prepareStatement( FIND_QUERY );
        st.setString( 1, id );
        ResultSet rs = st.executeQuery();
        if ( rs.next() ) {
            book = new Book( rs.getString( "id" ), rs.getString( "name" ) );
        }
        st.close();
        if ( null == book ) throw new ObjectNotFoundException();
        return book;
    }

    @Override
    public List<Book> findAll() throws SQLException {

        List<Book> list = new ArrayList<>();
        Connection cnx = DBConnectionManager.getConnection();
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery( FIND_ALL_QUERY );
        while ( rs.next() ) {
            Book book = new Book( rs.getString( "id" ), rs.getString( "name" ) );
            list.add( book );
        }
        st.close();
        return list;
    }

    @Override
    public void create( Book book ) throws SQLException {

        Connection cnx = DBConnectionManager.getConnection();
        PreparedStatement st = cnx.prepareStatement( INSERT_QUERY );
        st.setString( 1, book.getId() );
        st.setString( 2, book.getName() );
        st.executeUpdate();
        st.close();
    }

    @Override
    public void update( Book book ) throws SQLException {
        Connection cnx = DBConnectionManager.getConnection();
        PreparedStatement st = cnx.prepareStatement( UPDATE_QUERY );
        st.setString( 1, book.getName() );
        st.setString( 2, book.getId() );
        st.executeUpdate();
        st.close();
    }

    @Override
    public void delete( Book book ) throws SQLException {
        Connection cnx = DBConnectionManager.getConnection();
        PreparedStatement st = cnx.prepareStatement( DELETE_QUERY );
        st.setString( 1, book.getId() );
        st.executeUpdate();
        st.close();
    }
}
