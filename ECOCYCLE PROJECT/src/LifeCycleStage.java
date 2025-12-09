public abstract class LifeCycleStage {
    private String stageName;

//Constructors
    public LifeCycleStage(String stageName) {
        this.setStageName(stageName);
    }
    public abstract double getImpactValue();


//Getters and Setters
    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        if(stageName == null || stageName.trim().isEmpty()){
            throw new IllegalArgumentException("Stage name cannot be null or empty");
        }
        this.stageName = stageName;
    }

//Displaying the information
    @Override
    public String toString() {
        return "\nStage Name: " + stageName + "\nImpact Value: " + this.getImpactValue();
    }


}
