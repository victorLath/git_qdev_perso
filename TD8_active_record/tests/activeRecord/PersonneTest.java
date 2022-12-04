package activeRecord;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PersonneTest {
    //test à faire avec une base de donnée vide
    @BeforeEach
    void setUp() throws SQLException {
        Personne.createTable();
        new Personne("Jean","Jacque").save();
        new Personne("Dupont","Paul").save();

    }

    @AfterEach
    void tearDown() throws SQLException {
        Personne.deleteTable();
    }

    @Test
    void findAll() throws SQLException {
        ArrayList<Personne> arrayList = Personne.findAll();
        assertEquals(2,arrayList.size());
    }

    @Test
    void findById() throws SQLException {
        Personne personne = Personne.findById(2);
        assertEquals("Dupont",personne.getNom());
    }

    @Test
    void findByName() throws SQLException {
        ArrayList<Personne> personne = Personne.findByName("Jean");
        assertEquals(1,personne.size());
    }


}