
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

public class ManufacturingStage extends LifeCycleStage{
    private final Map<Machine, Double> machinesUsed;

//Constructors
    public ManufacturingStage(){
        super("Manufacturing Stage");
        this.machinesUsed = new HashMap<>();
    }
    public double getMachineImpact(Machine machine) {
        if (machine == null) {
            throw new IllegalArgumentException("Machine cannot be null");
        }
        if (!this.machinesUsed.containsKey(machine)) {
            throw new IllegalArgumentException("Machine not found");
        }
        return this.machinesUsed.get(machine) * machine.getEnergyConsumption();
    }
    public void addMachine(Machine machine, double hoursUsed) {
        if (machine == null || hoursUsed < 0) {
            throw new IllegalArgumentException("Machine cannot be null or hours used cannot be negative");
        }
        this.machinesUsed.put(machine, hoursUsed);
    }
    public void removeMachine(Machine machine) {
        if (machine == null) {
            throw new IllegalArgumentException("Machine cannot be null");
        }
        if (!this.machinesUsed.containsKey(machine)) {
            throw new IllegalArgumentException("Machine not found");
        }
        this.machinesUsed.remove(machine);
    }
    public double getHoursUsed(Machine machine) {
        if (machine == null) {
            throw new IllegalArgumentException("Machine cannot be null");
        }
        return this.machinesUsed.getOrDefault(machine, 0.0);
    }
    public void setHoursUsed(Machine machine, double hoursUsed) {
        if (machine == null || hoursUsed < 0) {
            throw new IllegalArgumentException("Machine cannot be null or hours used cannot be negative");
        }
        this.machinesUsed.put(machine, hoursUsed);
    }
    public void clearMachines() {
        this.machinesUsed.clear();
    }
    public double getImpactValue() {
        double totalImpact = 0;
        for (Map.Entry<Machine, Double> entry : this.machinesUsed.entrySet()) {
            totalImpact += entry.getValue() * entry.getKey().getEnergyConsumption();
        }
        return totalImpact;
    }
    public Map<Machine, Double> getMachinesUsed() {
        return Collections.unmodifiableMap(this.machinesUsed);
<<<<<<< HEAD
<<<<<<< HEAD
=======


    
    

>>>>>>> b6826631da9cbfabe38fdfaa32d27e07e43df4d1

=======
    }
>>>>>>> d69bc8c939b571fe6ce022669ce2c4321b15ef9d

    
    


}