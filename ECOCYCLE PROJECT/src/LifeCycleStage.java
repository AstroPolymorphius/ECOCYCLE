public class LifeCycleStage {
    private String stageName;
    private double impactValue;



//Constructors
    public LifeCycleStage(String stageName, double impactValue) {
        this.stageName = stageName;
        this.impactValue = impactValue;
    }


//Getters and Setters
    public String getStageName() {
        return stageName;
    }

    public double getStageImpactValue() {
        return impactValue;
    }


    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public void setImpactValue(double impactValue) {
        this.impactValue = impactValue;
    }


//Displaying the information
    @Override
    public String toString() {
        return "\nStage Name: " + stageName + "\nValue of Impact: " + impactValue;
    }


}
