public class FabriqueVoiture implements FabriqueVehicule {


    @Override
    public Vehicule creerVehicule() {
       return new Voiture(0,"");
    }
}
