package activeRecord;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DBConnectionTest {

    @Test
    void getConnectionTest() throws SQLException {
        //prepa données
        Connection c1,c2;
        //methode à tester
        c1 = DBConnection.getInstance().getConnection();
        c2 = DBConnection.getInstance().getConnection();
        //validation
        assertEquals(c1,c2,"ca devrait être la même connection");

    }
}