public class Aeroport {
    private static Aeroport instance;
    private Aeroport() {
    }

    public static synchronized Aeroport getInstance() {
        if(instance == null) instance = new Aeroport();
        return instance;
    }

    public synchronized void faireDecoller(String nomAvion){
        System.out.println("debut du décollage");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("fin du décollage");

    }
}