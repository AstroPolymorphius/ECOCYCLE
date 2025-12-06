public class DisposalMethod {
    private String name;
    private double emissionFactor;
    
    public DisposalMethod(String name, double emissionFactor) {
        this.name = name;
        this.emissionFactor = emissionFactor;
    }
    
    public double getEmissionFactor() {
        return emissionFactor;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }
    public void setEmissionFactor(double emissionFactor) {
        if (emissionFactor < 0) {
            throw new IllegalArgumentException("Emission factor cannot be negative");
        }
        
        this.emissionFactor = emissionFactor;
    }

    
}
