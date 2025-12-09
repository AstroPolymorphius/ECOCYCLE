public class ManufacturingStage extends LifeCycleStage{
    private Machine machine;
    private double hoursUsed;



//Constructors
    public ManufacturingStage(String stageName, double impactValue, Machine machine, double hoursUsed){
        super(stageName,impactValue);
        this.machine = machine;
        this.hoursUsed = hoursUsed;
    }


//Getters and Setters
    public Machine getMachine(){
        return machine;
    }

    public double getHoursUsed(){
        return hoursUsed;
    }


    public void setHoursUsed(double hoursUsed){
        this.hoursUsed = hoursUsed;
    }

    public void setMachine(Machine machine){
        this.machine = machine;
    }

}
