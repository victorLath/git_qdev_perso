package activeRecord;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FilmTest {
    //test avec la base de donnée
    Film film_blade_runner;
    Personne personne_scott;

    @BeforeEach
    void setUp() throws SQLException {
        this.film_blade_runner = Film.findById(4);
        this.personne_scott = Personne.findByName("Scott").get(0);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findById() throws SQLException {
        assertEquals("Blade Runner", film_blade_runner.getTitre());
    }

    @Test
    void findByRealisateur() throws SQLException {
        ArrayList<Film> films = Film.findByRealisateur(this.personne_scott);
        assertEquals("Scott", this.personne_scott.getNom());
        assertEquals(2, this.personne_scott.getId());
        assertEquals(2, films.size());
        System.out.println("Test findByRealisateur:");
        for (Film f : films){
            System.out.println("Titre: " + f.getTitre()+" Id_real: "+f.getId_real());
        }
    }


    @Test
    void getRealisateur() throws SQLException {
        Personne p = film_blade_runner.getRealisateur();
        assertEquals("Scott",p.getNom());
    }

}