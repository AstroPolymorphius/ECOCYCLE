public class Material {
    //Encapsulated data fields
    private String name;
    private double emissionFactor;





    //Parameterized constructor using the material's name and emission factor
    public Material(String name, double emissionFactor) {
        this.name = name;
        this.emissionFactor = emissionFactor;
    }



    // Getters
    public String getName() {
        return name;
    }

    public double getEmissionFactor() {
        return emissionFactor;
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
}
