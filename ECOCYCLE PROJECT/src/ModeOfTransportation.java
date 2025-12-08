public class ModeOfTransportation {
    //Encapsulating the data fields
    private String name;
    private double emissionRate;



    
    //parameterized constructor for the mode of transportation
    public ModeOfTransportation(String name, double emissionRate){
        this.name = name;
        this.emissionRate = emissionRate;
    }



    //Getters
    public String getName(){
        return name;
    }

    public double getEmissionRate(){
        return emissionRate;
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



}
