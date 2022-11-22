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
        ArrayList<Personne> array = new ArrayList<>();
        Connection connection = DBConnection.getInstance().getConnection();
        DatabaseMetaData dbMetaData = connection.getMetaData();
        String sql = "Select * from Personne where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        p = new Personne(rs.getObject(2).toString(), rs.getObject(3).toString());
        return p;

    }
    public findByName
}
