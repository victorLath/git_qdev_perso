import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Simulateur {
    private FabriqueVehicule fabriqueVehicule;

    Simulateur(FabriqueVehicule fabriqueVehicule) {
        this.fabriqueVehicule = fabriqueVehicule;
    }

    public Map<String, Integer> genererStats() {
        Map<String, Integer> stats = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            Vehicule vehicule = this.fabriqueVehicule.creerVehicule();
            String type = vehicule.getType();
            if (stats.containsKey(type)) {
                int val = stats.get(type);
                val++;
                stats.put(type, val);
            } else {
                stats.put(type, 1);
            }
        }
        return stats;
    }

    public void ecrireStats(){
        System.out.println(genererStats());
        Map<String,Integer> stats = genererStats();
        Set<String> types = stats.keySet();
        for (String type: types){
            System.out.println(type+";"+stats.get(type));
        }
    }
}
