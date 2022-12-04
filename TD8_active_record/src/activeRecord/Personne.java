package activeRecord;

import java.sql.*;
import java.util.ArrayList;

public class Personne {
    private int id;
    private String nom;
    private String prenom;
    Personne(String nom, String prenom) {
        this.id = -1;
        this.nom = nom;
        this.prenom = prenom;
    }

    public static ArrayList<Personne> findAll() throws SQLException {
        ArrayList<Personne> array = new ArrayList<>();
        Connection connection = DBConnection.getInstance().getConnection();
        DatabaseMetaData dbMetaData = connection.getMetaData();
        String sql = "Select * from Personne";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            array.add(new Personne(rs.getObject(2).toString(), rs.getObject(3).toString()));
        }
        return array;
    }

    public static Personne findById(int id) throws SQLException {
        Personne p = null;
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "Select * from Personne where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        p = new Personne(rs.getString("nom"), rs.getString("prenom"));
        return p;

    }

    public static ArrayList<Personne> findByName(String nom) throws SQLException {
        ArrayList<Personne> personnes = new ArrayList<>();
        Connection connection = DBConnection.getInstance().getConnection();
        DatabaseMetaData dbMetaData = connection.getMetaData();
        String sql = "Select * from Personne where nom = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, nom);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Personne p = new Personne(rs.getString("nom"), rs.getString("prenom"));
            p.setId(rs.getInt("id"));
            personnes.add(p);
        }
        return personnes;
    }

    public static void createTable() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "CREATE TABLE Personne ( ID INTEGER  AUTO_INCREMENT, NOM varchar(40) NOT NULL, PRENOM varchar(40) NOT NULL, PRIMARY KEY (ID)) ";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.executeUpdate();
    }

    public static void deleteTable() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String drop = "DROP TABLE Personne";
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(drop);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void delete() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement("DELETE FROM Personne where personne.id = ?");
        ps.setInt(1, this.id);
        ps.executeUpdate();
        this.id = -1;
    }

    public void save() throws SQLException {
        if (id == -1) saveNew();
        else update();
    }

    public void saveNew() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String SQLPrep = "INSERT INTO Personne (nom, prenom) VALUES (?,?);";
        PreparedStatement prep = connection.prepareStatement(SQLPrep, Statement.RETURN_GENERATED_KEYS);
        prep.setString(1, this.nom);
        prep.setString(2, this.prenom);
        prep.executeUpdate();
    }

    public void update() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "Update personne set id=?,nom = ?,prenom = ? where id = ? ";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, this.id);
        ps.setString(2, this.nom);
        ps.setString(3, this.prenom);
        ps.setInt(4, this.id);
        ps.executeUpdate();

    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
