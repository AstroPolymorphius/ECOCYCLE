
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DisposalStage extends LifeCycleStage{
    // stores different methods of disposal and the weights of waste disposed
    private final Map<DisposalMethod, Double> disposalMethods;
    // the double is for the weight of waste disposed
    
    public DisposalStage() {
        super("Disposal Stage");
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

    public Map<DisposalMethod, Double> getDisposalMethods() {
        return Collections.unmodifiableMap(this.disposalMethods);
    }

    public double getMethodWeight(DisposalMethod method) {
        if (method == null) {
            throw new IllegalArgumentException("Method cannot be null");
        }
        
        return this.disposalMethods.getOrDefault(method, 0.0);
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
    @Override
    public double getImpactValue() {
        double totalImpact = 0;
        for (Map.Entry<DisposalMethod, Double> entry : this.disposalMethods.entrySet()) {
            totalImpact += entry.getValue() * entry.getKey().getEmissionFactor();
        }
        return totalImpact;
    }
}
