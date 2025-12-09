public class DisposalMethod extends DisposalStage{
    // stores name of disposal method e.g landfill
    private String name;
    // stores emission factor of disposal method
    private double emissionFactor;

    public DisposalMethod(String name, double emissionFactor) {
        this.setName(name);
        this.setEmissionFactor(emissionFactor);
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
    @Override
    public String toString() {
        return "Disposal Method Name: " + name +
               ", Emission Factor: " + emissionFactor;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DisposalMethod that = (DisposalMethod) obj;
        return name.equals(that.name);
    }
    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
