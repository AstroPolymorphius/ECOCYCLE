public class Material {
    //Encapsulated data fields
    private String name;
    private double emissionFactor;





    //Parameterized constructor using the material's name and emission factor
    public Material(String name, double emissionFactor) {
        this.setName(name);
        this.setEmissionFactor(emissionFactor);
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
        this.name = name;
    }

    public void setEmissionFactor(double emissionFactor) {
        //Error handling
        if (emissionFactor < 0) {
            throw new IllegalArgumentException("Emission factor cannot be negative or zero");
        }
        this.emissionFactor = emissionFactor;
    }
    @Override
    public String toString() {
        return "Material Name: " + name +
               ", Emission Factor: " + emissionFactor;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Material that = (Material) obj;
        return name.equals(that.name);
    }
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
