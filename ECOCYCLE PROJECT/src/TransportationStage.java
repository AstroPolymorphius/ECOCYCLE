import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
public class TransportationStage extends LifeCycleStage {
    private Map<ModeOfTransportation, Double> modes;

    public TransportationStage(){
        super("Transportation Stage");
        this.modes = new HashMap<>();
    }
    public double getModeImpact(ModeOfTransportation mode) {
        if (mode == null) {
            throw new IllegalArgumentException("Mode cannot be null");
        }
        if (!this.modes.containsKey(mode)) {
            throw new IllegalArgumentException("Mode not found");
        }
        return this.modes.get(mode) * mode.getEmissionRate();
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
        if (!this.modes.containsKey(mode)) {
            throw new IllegalArgumentException("Mode not found");
        }
        this.modes.remove(mode);
    }
    public double getDistance(ModeOfTransportation mode) {
        if (mode == null) {
            throw new IllegalArgumentException("Mode cannot be null");
        }
        return this.modes.getOrDefault(mode, 0.0);
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
    public Map<ModeOfTransportation, Double> getModes() {
        return Collections.unmodifiableMap(this.modes);
    }
    public double getImpactValue() {
        double totalImpact = 0;
        for (Map.Entry<ModeOfTransportation, Double> entry : this.modes.entrySet()) {
            totalImpact += entry.getValue() * entry.getKey().getEmissionRate();
        }
        return totalImpact;
    }
   

        
    }

