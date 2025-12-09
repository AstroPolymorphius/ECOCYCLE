public class TransportationStage extends LifeCycleStage {

    private ModeOfTransportation mode;
    private double distance;





    public TransportationStage(ModeOfTransportation mode,double distance){
        super(Transportation);
        this.mode = mode;
        this.distance = distance;

        @ Override
        public double getImpact() {
            return mode.getEmissionRate() * distance;

    }
}
