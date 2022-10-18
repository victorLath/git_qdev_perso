public class FabriqueVoiture implements FabriqueVehicule {

    @Override
    public Vehicule creerVehicule(double vitesse, String type) {
        return new Voiture(vitesse,type);
    }

    @Override
    public Vehicule creerVehicule() {
       return new Voiture(0,"");
    }
}
