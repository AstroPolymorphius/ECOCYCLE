public class ModeOfTransportation {
    //Encapsulating the data fields
    private String name;
    private double emissionRate;
    private double distanceCovered;
    private double impact;



    
    //parameterized constructor for the mode of transportation
    public ModeOfTransportation(String name, double emissionRate, double distanceCovered){
        this.name = name;
        this.emissionRate = emissionRate;
        this.distanceCovered = distanceCovered;
        impact = emissionRate * distanceCovered;
    }



    //Getters
    public String getName(){
        return name;
    }

    public double getEmissionRate(){
        return emissionRate;
    }
    public double getDistanceCovered(){
        return distanceCovered;
    }

    public double getImpact(){
        return impact;
    }



    //Setters
    public void setName(String name){
        //Error handling
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("Mode of Transportation's name cannot be left empty or blank");
        }
        else{
            this.name = name;
        }

    }

    public void setEmissionRate(double emissionRate){
        //Error handling
        if (emissionRate <= 100){
            throw new IllegalArgumentException("The mode of transportation's emission rate cannot be 0 or negative");
        }
        else{
            this.emissionRate = emissionRate;
        }
    }

    public void setDistanceCovered(double distanceCovered){
        if (distanceCovered < 0){
            throw new IllegalArgumentException("Distance covered cannot be negative or 0");
        }
        else{
            this.distanceCovered = distanceCovered;
        }
    }



}
