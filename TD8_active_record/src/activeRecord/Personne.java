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

    public ArrayList<Personne> findAll() throws SQLException {
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

    public Personne findById(int id) throws SQLException {
        Personne p = null;
        Connection connection = DBConnection.getInstance().getConnection();
        DatabaseMetaData dbMetaData = connection.getMetaData();
        String sql = "Select * from Personne where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        p = new Personne(rs.getObject(2).toString(), rs.getObject(3).toString());
        return p;

    }
    public ArrayList<Personne> findByName(String nom) throws SQLException {
        ArrayList<Personne> personnes = new ArrayList<>();
        Connection connection = DBConnection.getInstance().getConnection();
        DatabaseMetaData dbMetaData = connection.getMetaData();
        String sql = "Select * from Personne where nom = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,nom);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            personnes.add(new Personne(rs.getObject(2).toString(), rs.getObject(3).toString()));
        }
        return personnes;
    }

    public static void createTable() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "CREATE TABLE `Personne` (\n" +
                "  `id` int(11) NOT NULL,\n" +
                "  `nom` varchar(40) NOT NULL,\n" +
                "  `prenom` varchar(40) NOT NULL\n" +
                ")";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
    }

    public static void deleteTable() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "DROP TABLE Personne";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
    }


}
