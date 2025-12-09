public class RawMaterialStage extends LifeCycleStage{
    private Material material;
    private double quantity;


//CONSTRUCTORS
    public RawMaterialStage(String stageName, double impactValue ,Material material, double quantity){
        super(stageName, impactValue);
        this.material = material;
        this.quantity = quantity;
    }

//Getters and Setters
    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }


    public Material getMaterial(){
        return material;
    }

    public void setMaterial(Material material){
        this.material = material;
    }


}
