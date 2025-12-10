
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
public class RawMaterialStage extends LifeCycleStage{
    private Map<Material, Double> materials;


//CONSTRUCTORS
    public RawMaterialStage(){
        super("Raw Material Stage");
        this.materials = new HashMap<>();
    }

//Getters and Setters
    public double getMaterialImpact(Material material) {
        if (material == null) {
            throw new IllegalArgumentException("Material cannot be null");
        }
        if (!this.materials.containsKey(material)) {
            throw new IllegalArgumentException("Material not found");
        }
        return this.materials.get(material) * material.getEmissionFactor();
    }
    public void addMaterial(Material material, double quantity) {
        if (material == null || quantity < 0) {
            throw new IllegalArgumentException("Material cannot be null or quantity cannot be negative");
        }
        this.materials.put(material, quantity);
    }
    public void removeMaterial(Material material) {
        if (material == null) {
            throw new IllegalArgumentException("Material cannot be null");
        }
        if (!this.materials.containsKey(material)) {
            throw new IllegalArgumentException("Material not found");
        }
        this.materials.remove(material);
    }


    public Map<Material, Double> getMaterials() {
        return Collections.unmodifiableMap(materials);
    }

    public void clearMaterials() {
        this.materials.clear();
    }
    public double getMaterialWeight(Material material) {
        if (material == null) {
            throw new IllegalArgumentException("Material cannot be null");
        }
        return this.materials.getOrDefault(material, 0.0);
    }
    public void setMaterialWeight(Material material, double weight) {
        if (material == null || weight < 0) {
            throw new IllegalArgumentException("Material cannot be null or weight cannot be negative");
        }
        this.materials.put(material, weight);
    }
    public double getImpactValue() {
        double totalImpact = 0;
        for (Map.Entry<Material, Double> entry : this.materials.entrySet()) {
            totalImpact += entry.getValue() * entry.getKey().getEmissionFactor();
        }
        return totalImpact;
    }
    


}
