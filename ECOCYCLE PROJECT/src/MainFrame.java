import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    private JTextField productNameField;
    private JTable stagesTable;
    private DefaultTableModel tableModel;

    private Product currentProduct;

    public MainFrame() {
        super("Life Cycle Impact Calculator");

        setLayout(new BorderLayout());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        currentProduct = new Product("");

        // NORTH PANEL (product name + add stage)
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Product Name:"));
        productNameField = new JTextField(20);
        topPanel.add(productNameField);

        JButton addStageBtn = new JButton("Add Stage");
        addStageBtn.addActionListener(e -> openStageSelection());
        topPanel.add(addStageBtn);

        add(topPanel, BorderLayout.NORTH);

        // CENTER PANEL (table of stages)
        tableModel = new DefaultTableModel(new String[]{"Stage", "Impact Value"}, 0);
        stagesTable = new JTable(tableModel);
        add(new JScrollPane(stagesTable), BorderLayout.CENTER);

        // SOUTH PANEL (actions)
        JPanel bottomPanel = new JPanel();

        JButton calcBtn = new JButton("Calculate Impact");
        calcBtn.addActionListener(e -> calculateImpact());
        bottomPanel.add(calcBtn);

        JButton compareBtn = new JButton("Compare Products");
        compareBtn.addActionListener(e -> new CompareProductsFrame());
        bottomPanel.add(compareBtn);

        JButton chartBtn = new JButton("View Impact Chart");
        chartBtn.addActionListener(e -> new ChartFrame(currentProduct));
        bottomPanel.add(chartBtn);

        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void openStageSelection() {
        new StageSelectionDialog(this, currentProduct, tableModel);
    }

    private void calculateImpact() {
        currentProduct.setName(productNameField.getText());
        double impact = currentProduct.getTotalImpact();

        JOptionPane.showMessageDialog(this,
                "Total Environmental Impact: " + impact + " CO₂ units");
    }
}
