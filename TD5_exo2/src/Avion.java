class Avion extends Thread {
    private String nom;
    private Aeroport a;

    public Avion(String s) {
        this.nom = s;
    }

    public void run() {
        this.a = new Aeroport();
        System.out.println("Je suis avion " + this.nom + "sur aeroport" + this.a);
    }
}