public class FabriquePieton implements FabriqueVehicule{

    public Vehicule creerVehicule(double vitesse, String type) {
        return new Pieton(vitesse,type);
    }

    @Override
    public Vehicule creerVehicule() {
        return new Pieton(0,"");
    }
}
