public abstract class Vehicule {
    protected String type;
    protected double vitesseMax;
    protected double vitesse;

    public Vehicule(double v,String t) {
        this.vitesse = v;
        this.type = t;
    }

    public Vehicule() {
        this.vitesse = 0;
        this.type = "";
    }

    public double getVitesse() {
        return vitesse;
    }

    public String getType() {
        return type;
    }

    public void accelerer(double v) {
        this.vitesse += v;
    }

    public void deccelerer(double v) {
        if ((this.vitesse -= v) >= 0) {
            this.vitesse -= v;
        }

    }
}
