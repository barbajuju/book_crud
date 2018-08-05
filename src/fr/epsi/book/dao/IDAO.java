package fr.epsi.book.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by ssylla on 22/12/2017.
 */
public interface IDAO<T> {

    public T find( String id ) throws SQLException, ObjectNotFoundException;
    public List<T> findAll() throws SQLException;

    public void create( T o ) throws SQLException;
    public void update( T o ) throws SQLException;
    public void delete( T o ) throws SQLException;
}
