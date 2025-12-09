public class RawMaterialStage extends LifeCycleStage{
    //Hashmap to store the material and their corresponding amounts/quantity
    private HashMap<Material, Double> materialsAndTheirQuantity


//Constructor
    public RawMaterialStage(String stageName){
        super(stageName);
        materialsAndExtractedQuantity = new HashMap<>();
        
    }

//Getters and Setters

    public double getImpact(){
        for (Map.Entry<Material, Double> entry: materialsAndTheirQuantity.entrySet()){
            Material material = entry.getKey();
            double quantity = entry.getValue();

            impactValue += (material.getEmissionFactorPerKg() * quantity);
        }
        return impactValue;
    }

    public void addMaterial(Material material, double quantity){
        //Error handling
        if (material == null || quantity <= 0){
            throw new IllegalArgumentException("Invalid material or quantity: material cannot be null and quantity cannot be negative");
        }
        if (materialsAndTheirQuantity.containsKey(material)){
            throw new IllegalArgumentException("Material already exists");
        }
        materialsAndTheirQuantity.put(material, quantity)
    }

    public void removeMaterialByName(String materialName){
        if (materialName == null || materialName.isBlank()){
            throw new IllegalArgumentException("Invalid material name");
        }
    }


}
