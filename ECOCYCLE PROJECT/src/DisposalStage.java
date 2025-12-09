
import java.util.HashMap;

public class DisposalStage {
    // stores different methods of disposal and the weights of waste disposed
    private HashMap<DisposalMethod, Double> disposalMethods;

    public DisposalStage() {
        this.disposalMethods = new HashMap<>();
    }

    public double getMethodImpact(DisposalMethod method) {
        if (method == null) {
            throw new IllegalArgumentException("Method cannot be null");
        }
        if (!this.disposalMethods.containsKey(method)) {
            throw new IllegalArgumentException("Method not found");
        }
        return this.disposalMethods.get(method) * method.getEmissionFactor();
    }

    public void addMethod(DisposalMethod method, double weight) {
        if (method == null || weight < 0) {
            throw new IllegalArgumentException("Method cannot be null or weight cannot be negative");
        }
        this.disposalMethods.put(method, weight);
    }
    public void removeMethod(DisposalMethod method) {
        if (method == null) {
            throw new IllegalArgumentException("Method cannot be null");
        }
        if (!this.disposalMethods.containsKey(method)) {
            throw new IllegalArgumentException("Method not found");
        }
        this.disposalMethods.remove(method);
    }

    public HashMap<DisposalMethod, Double> getDisposalMethods() {
        return new HashMap<>(this.disposalMethods);
    }

    public double getMethodWeight(DisposalMethod method) {
        if (method == null) {
            throw new IllegalArgumentException("Method cannot be null");
        }
        if (!this.disposalMethods.containsKey(method)) {
            throw new IllegalArgumentException("Method not found");
        }
        return disposalMethods.get(method);
    }
    public void setMethodWeight(DisposalMethod method, double weight) {
        if (method == null || weight < 0) {
            throw new IllegalArgumentException("Method cannot be null or weight cannot be negative");
        }
        this.disposalMethods.put(method, weight);
    }
    public void clearMethods() {
        this.disposalMethods.clear();
    }
    
    public double getStageImpactValue() {
        double totalImpact = 0;
        for (DisposalMethod method : this.disposalMethods.keySet()) {
            totalImpact += this.disposalMethods.get(method) * method.getEmissionFactor();
        }
        return totalImpact;
    }
    @Override
    public String toString() {
        return "Disposal Stage: " + this.disposalMethods;
    }
}
