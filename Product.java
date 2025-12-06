import java.util.ArrayList;

public class Product {
    private String name;
    private ArrayList<LifeCycleStage> stages;

    public Product(String name) {
        this.name = name;
        this.stages = new ArrayList<>();
    }

    public void addStage(LifeCycleStage stage) {
        if (stage == null) {
            throw new IllegalArgumentException("Stage cannot be null");
        }
        stages.add(stage);
    }

    public double calculateTotalImpact() {
        double totalImpact = 0;
        for (LifeCycleStage stage : stages) {
            totalImpact += stage.getImpact();
        }
        return totalImpact;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }
    public void removeStage(LifeCycleStage stage) {
        if (stage == null) {
            throw new IllegalArgumentException("Stage cannot be null");
        }
        if (!stages.contains(stage)) {
            throw new IllegalArgumentException("Stage not found");
        }
        stages.remove(stage);
    }


   
}
