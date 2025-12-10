import java.util.Comparator;
public class ProductComparator implements Comparator<Product> {

    
    @Override
    public int compare(Product productA, Product productB) {
        double impactA = productA. calculateTotalImpact();
        double impactB = productB. calculateTotalImpact();

    
        return Double.compare(impactA, impactB);
    }

    public String generateComparisonReport(Product productA, Product productB) {
        int comparisonResult = compare(productA, productB);

        if (comparisonResult < 0) {
            return productA.getName() + " is more environmentally efficient than " +
                   productB.getName() + " (Total Impact: " + productA.calculateTotalImpact() + " < " + productB.calculateTotalImpact() + ").";
        } else if (comparisonResult > 0) {
            return productB.getName() + " is more environmentally efficient than " +
                   productA.getName() + " (Total Impact: " + productB.calculateTotalImpact() + " < " + productA.calculateTotalImpact() + ").";
        } else {
            return productA.getName() + " and " + productB.getName() + " have the same environmental impact.";
        }
    }
}
