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

    FabriqueIntersection(double probaVoiture,double probaBus,double probaByc,double probaPieton){
        this.probaPieton = probaPieton;
        this.probaBus = probaBus;
        this.probaVoiture = probaVoiture;
        this.probaByc = probaByc;
    }
}
