public class Machine {

    private String name;
    private double energyConsumption;


    public Machine(String name, double energyConsumption) {
        this.setName(name);
        this.setEnergyConsumption(energyConsumption);
    }

    public String getName() {
        return name;
    }

    public double getEnergyConsumption() {
        return energyConsumption;
    }
    public void setEnergyConsumption(double energyConsumption) {
        if (energyConsumption < 0) {
            throw new IllegalArgumentException("Energy consumption cannot be negative");
        }
        this.energyConsumption = energyConsumption;
    }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }
    @Override
    public String toString() {
        return "Machine Name: " + name +
               ", Energy Consumption: " + energyConsumption;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Machine that = (Machine) obj;
        return name.equals(that.name);
    }
    @Override
    public int hashCode() {
        return name.hashCode();
    }
    
}
