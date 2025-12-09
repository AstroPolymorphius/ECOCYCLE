public class Machine {

    private String name;
    private double energyConsumption;




    public Machine(String name, double energyConsumption) {
        this.name = name;
        this.energyConsumption = energyConsumption;
    }



    public String getName() {
        return name;
    }

    public double getEnergyConsumption() {
        return energyConsumption;
    }
}
