import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TransportationStage extends LifeCycleStage {
    private final Map<ModeOfTransportation, Double> modes;
    
    public TransportationStage() {
        super("Transportation Stage");
        this.modes = new HashMap<>();
    }
    
    
    
    public double getModeImpact(ModeOfTransportation mode) {
        if (mode == null) {
            throw new IllegalArgumentException("Mode cannot be null");
        }
        
        Double distance = this.modes.get(mode);
        
        if (distance == null) {
            throw new IllegalArgumentException("Mode not found");
        }
        return distance * mode.getEmissionRate();
    }
    
    
    public Map<ModeOfTransportation, Double> getModes() {
        return Collections.unmodifiableMap(this.modes);
    }
    
    public double getDistance(ModeOfTransportation mode) {
        if (mode == null) {
            throw new IllegalArgumentException("Mode cannot be null");
        }
        
        return this.modes.getOrDefault(mode, 0.0);
    }
    
    
    public void addMode(ModeOfTransportation mode, double distance) {
        if (mode == null || distance < 0) {
            throw new IllegalArgumentException("Mode cannot be null or distance cannot be negative");
        }
        this.modes.put(mode, distance);
    }
    
    public void removeMode(ModeOfTransportation mode) {
        if (mode == null) {
            throw new IllegalArgumentException("Mode cannot be null");
        }
        // Streamlined check: Use remove()'s return value for existence check (FIXED)
        if (this.modes.remove(mode) == null) {
            throw new IllegalArgumentException("Mode not found to remove");
        }
    }
    
    public void setDistance(ModeOfTransportation mode, double distance) {
        if (mode == null || distance < 0) {
            throw new IllegalArgumentException("Mode cannot be null or distance cannot be negative");
        }
        this.modes.put(mode, distance);
    }
    
    public void clearModes() {
        this.modes.clear();
    }
    
    
    
    @Override
    public double getImpactValue() {
        double totalImpact = 0;
        for (Map.Entry<ModeOfTransportation, Double> entry : this.modes.entrySet()) {
            totalImpact += entry.getValue() * entry.getKey().getEmissionRate();
        }
        return totalImpact;
    }
}