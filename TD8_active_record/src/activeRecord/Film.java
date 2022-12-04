package activeRecord;

import java.sql.*;
import java.util.ArrayList;

public class Film {
    private String titre;
    private int id;
    private int id_real;

    Film(String titre, Personne p) {
        this.titre = titre;
        this.id_real = p.getId();
        this.id = -1;
    }

    private Film(int id, String titre, int id_real) {
        this.titre = titre;
        this.id_real = id_real;
        this.id = id;
    }


    public static Film findById(int id) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        Film f = null;
        String sql = "Select * from Film where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        f = new Film(rs.getInt("id"), rs.getString("titre"), rs.getInt("id_rea"));
        return f;

    }

    public static ArrayList<Film> findByRealisateur(Personne p) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        ArrayList<Film> array = new ArrayList<>();
        String sql = "Select * from Film where id_rea = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, p.getId());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            array.add(new Film(rs.getInt("id"), rs.getString("titre"), rs.getInt("id_rea")));
        }
        return array;
    }

    public static void createTable() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "CREATE TABLE film ( ID INTEGER  AUTO_INCREMENT, titre varchar(40) NOT NULL, id_rea varchar(40) NOT NULL, PRIMARY KEY (ID))";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.executeUpdate();
    }

    public static void deleteTable() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String drop = "DROP TABLE film";
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(drop);
    }

    public Personne getRealisateur() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        Personne p = null;
        String sql = "Select * from Personne inner join Film on Personne.id = Film.id_rea where film.id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, this.id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        p = new Personne(rs.getString("nom"), rs.getString("prenom"));
        p.setId(rs.getInt("id_rea"));
        return p;
    }

    public void save() throws SQLException, RealisateurAbsentException {
        if (this.id == -1) saveNew();
        else update();
    }

    public void saveNew() throws SQLException, RealisateurAbsentException {
        Connection connection = DBConnection.getInstance().getConnection();
        String SQLPrep = "INSERT INTO film (titre,id_rea) VALUES (?,?);";
        PreparedStatement prep = connection.prepareStatement(SQLPrep, Statement.RETURN_GENERATED_KEYS);
        prep.setString(1, this.titre);
        prep.setInt(2, this.id_real);
        prep.executeUpdate();
    }

    public void update() throws SQLException, RealisateurAbsentException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "Update film set id=?,titre = ?,id_rea = ? where id = ? ";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, this.id);
        ps.setString(2, this.titre);
        ps.setInt(3, this.id_real);
        ps.setInt(4, this.id);
        ps.executeUpdate();

    }

    public String getTitre() {
        return titre;
    }

    public int getId() {
        return id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_real(int id_real) {
        this.id_real = id_real;
    }

    public int getId_real() {
        return id_real;
    }
}
