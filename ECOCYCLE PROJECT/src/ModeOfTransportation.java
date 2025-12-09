public class ModeOfTransportation {
    //Encapsulating the data fields
    private String name;
    private double emissionRate;
    
    //parameterized constructor for the mode of transportation
    public ModeOfTransportation(String name, double emissionRate){
        this.setName(name);
        this.setEmissionRate(emissionRate);}
        
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
        this.name = name;

    }

    public void setEmissionRate(double emissionRate){
        //Error handling
        if (emissionRate <= 0){
            throw new IllegalArgumentException("The mode of transportation's emission rate cannot be 0 or negative");
        }
        this.emissionRate = emissionRate;
    }

    @Override
    public String toString() {
        return "Mode of Transportation Name: " + name +
               ", Emission Rate: " + emissionRate;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ModeOfTransportation that = (ModeOfTransportation) obj;
        return name.equals(that.name);
    }
    @Override
    public int hashCode() {
        return name.hashCode();
    }



}
