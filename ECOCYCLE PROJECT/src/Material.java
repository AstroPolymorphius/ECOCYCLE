public class Material {
    //Encapsulated data fields
    private String name;
    private double emissionFactor;
    private double quantity;





    //Parameterized constructor using the material's name and emission factor
    public Material(String name, double emissionFactor, double quantity) {
        this.name = name;
        this.emissionFactor = emissionFactor;
        this.quantity = quantity;
    }



    // Getters
    public String getName() {
        return name;
    }

    public double getEmissionFactor() {
        return emissionFactor;
    }
    public double getQuantity() {
        return quantity;
    }


    // Setters
    public void setName(String name) {
        //Error handling
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty or blank");
        }
        else{
            this.name = name;
        }
            
    }

    public void setEmissionFactor(double emissionFactor) {
        //Error handling
        if (emissionFactor <= 0) {
            throw new IllegalArgumentException("Emission factor cannot be negative or zero");
        }
        else{
            this.emissionFactor = emissionFactor;
        }
    }
    public void setQuantity(double quantity) {
        //Error handling
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity cannot be negative or zero");
        }
        else{
            this.quantity = quantity;
        }
    }
}
