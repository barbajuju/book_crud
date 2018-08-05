package fr.epsi.book.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by ssylla on 22/12/2017.
 */
public class DBConnectionManager {

    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/book?useSSL=false";
    private static final String DB_USER = "julien";
    private static final String DB_USER_PWD = "julien";
    private Connection cnx;

    private static DBConnectionManager single;

    private DBConnectionManager() throws SQLException {
        cnx = DriverManager.getConnection( DB_URL, DB_USER, DB_USER_PWD );
    }

    public Connection getCnx() {
        return cnx;
    }

    public static Connection getConnection() throws SQLException {
        if ( null == single || single.getCnx().isClosed() ) {
            single = new DBConnectionManager();
        }
        return single.getCnx();
    }

    @Override
    protected void finalize() throws Throwable {
        if ( cnx != null && !cnx.isClosed() ) {
            cnx.close();
        }
        super.finalize();
    }
}
