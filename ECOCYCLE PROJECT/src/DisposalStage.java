public class DisposalStage {
    private DisposalMethod method;
    private double weight;

    public DisposalStage(DisposalMethod method, double weight) {
        this.method = method;
        this.weight = weight;
    }

    public double getImpact() {
        return weight * method.getEmissionFactor();
    }

    public void setMethod(DisposalMethod method) {
        if (method == null) {
            throw new IllegalArgumentException("Method cannot be null");
        }
        this.method = method;
    }

    public void setWeight(double weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("Weight cannot be negative");
        }
        this.weight = weight;
    }

    public DisposalMethod getMethod() {
        return method;
    }

    public double getWeight() {
        return weight;
    }
}
