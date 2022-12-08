package activeRecord;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FilmTest {
    //ATTENTION test à faire avec la base de donnée
    Film film_blade_runner;
    Personne personne_scott;
    Personne p;
    Film f;

    @BeforeEach
    void setUp() throws SQLException {
        this.film_blade_runner = Film.findById(4);
        this.personne_scott = Personne.findByName("Scott").get(0);
        p = new Personne("nom", "prenom");
        f = new Film("test", p);
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
        for (Film f : films) {
            System.out.println("Titre: " + f.getTitre() + " Id_real: " + f.getId_real());
        }
    }


    @Test
    void getRealisateur() throws SQLException, RealisateurAbsentException {
        Personne p = film_blade_runner.getRealisateur();
        assertEquals("Scott", p.getNom());
    }

    @Test
    void testLeveException() {
        Personne p = new Personne("nom", "prenom");
        Film f = new Film("test", p);
        RealisateurAbsentException thrown = Assertions.assertThrows(RealisateurAbsentException.class, () -> {
            f.getRealisateur();
        });
    }


}