import java.util.Random;

public class FabriqueIntersection {
    protected double probaVoiture;
    protected double probaBus;
    protected double probaByc;
    protected double probaPieton;

    FabriqueIntersection(){
        this.probaVoiture = 0.8;
        this.probaBus = 0.05;
        this.probaByc = 0.05;
        this.probaPieton = 0.10;
}

    public FabriqueIntersection(double probaPieton, double probaVoiture, double probaBus, double probaBicyclette) throws Exception {
        if ((probaBicyclette + probaPieton + probaVoiture + probaBus) != 100) throw new Exception("Pas à 100%");
        if (probaVoiture < 0) throw new Exception("Proba Negatif");
        if (probaBicyclette < 0) throw new Exception("Proba Negatif");
        if (probaPieton < 0) throw new Exception("Proba Negatif");
        if (probaBus < 0) throw new Exception("Proba Negatif");
        this.probaPieton = probaPieton;
        this.probaVoiture = probaVoiture;
        this.probaBus = probaBus;
        this.probaByc = probaByc;
    }

    public Vehicule creerVehicule() {
        /**
         * 80% pour voiture, 10% pour Pieton et 5% pour bus et bicyclette
         */
        int alea = new Random().nextInt(0, 100);
        if (alea < probaVoiture) return new Voiture();
        alea -= probaVoiture;
        if(alea < probaPieton) return new Pieton();
        alea -= probaPieton;
        if(alea < probaBus) return new Bus();
        return new Bicyclette();
    }
}
