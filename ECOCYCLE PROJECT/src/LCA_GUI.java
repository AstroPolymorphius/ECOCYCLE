import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LCA_GUI{
    //
    private JFrame frame;
    private JTextField productNameField;
    private DefaultListModel<String> stageListModel;
    private JList<String> stageList;
    private Product product;

    //Constructor
    public LCA_GUI(){
        initialize();
    }

    private void initialize(){
        frame = new JFrame("ECOCYCLE");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout( new BorderLayout());

        //The top section: for collecting the data about the product itself
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Product name:"));
        productNameField = new JTextField(20);
        topPanel.add(productNameField);

        //The button for creating the button
        JButton createProductButton = new JButton("Create Product");
        topPanel.add(createProductButton);
        frame.add(topPanel, BorderLayout.NORTH);

        //Centre Panel: Stage List
        stageListModel = new DefaultListModel<>();
        stageList = new JList<>(stageListModel);
        JScrollPane stageScroll = new JScrollPane(stageList);
        stageScroll.setBorder(BorderFactory.createTitledBorder("Life Cycle Stages"));
        frame.add(stageScroll, BorderLayout.CENTER);

        //Right Panel: Stage Controls
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        //Stage buttons
        JButton addStageButton = new JButton("Add Stage");
        JButton removeStageButton = new JButton("Remove Stage");
        JButton viewImpactButton = new JButton("View Total Impact");

        rightPanel.add(addStageButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rightPanel.add(removeStageButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rightPanel.add(viewImpactButton);

        frame.add(rightPanel, BorderLayout.EAST);

        //Event Handlers
        // Creating a product
        createProductButton.addActionListener(e -> {
            String name = productNameField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Product name cannot be empty!");
                return;
            }
            product = new Product(name);
            stageListModel.clear();
            JOptionPane.showMessageDialog(frame, "Product '" + name + "' created!");
        });

        //Adding a stage
        addStageButton.addActionListener(e -> {
            if (product == null) {
                JOptionPane.showMessageDialog(frame, "Create a product first!");
                return;
            }
            String stageName = JOptionPane.showInputDialog(frame, "Enter Stage Name:");
            if (stageName != null && !stageName.trim().isEmpty()) {
                // For now, we only add RawMaterialStage; can add options for other stage types
                RawMaterialStage stage = new RawMaterialStage();
                try {
                    product.addStage(stage);
                    stageListModel.addElement(stage.getStageName());
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                }
            }
        });

        //Removing a stage
        removeStageButton.addActionListener(e -> {
            if (product == null) {
                JOptionPane.showMessageDialog(frame, "Create a product first!");
                return;
            }
            int selectedIndex = stageList.getSelectedIndex();
            if (selectedIndex >= 0) {
                LifeCycleStage stage = product.getStageByName(stageListModel.get(selectedIndex));
                try {
                    product.removeStage(stage);
                    stageListModel.remove(selectedIndex);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Select a stage to remove!");
            }
        });

        //Viewing total impact
        viewImpactButton.addActionListener(e -> {
            if (product == null) {
                JOptionPane.showMessageDialog(frame, "Create a product first!");
                return;
            }
            double totalImpact = product.calculateTotalImpact();
            JOptionPane.showMessageDialog(frame, "Total Environmental Impact: " + totalImpact);
        });

        frame.setVisible(true);
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(LCA_GUI::new);
    }
    
}
