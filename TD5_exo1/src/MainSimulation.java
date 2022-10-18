public class MainSimulation {
    public static void main(String[] args) throws Exception {
        FabriqueVehicule fabriqueVehicule = new FabriqueIntersection(80, 10, 5, 5);
        Simulateur simulateur = new Simulateur(fabriqueVehicule);
    }
}
