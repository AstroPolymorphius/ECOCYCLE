import java.awt.*;
import javax.swing.*;

public class LCA_GUI {
    
  
    private JFrame frame;
    private JTextField productNameField;
    private DefaultListModel<String> stageListModel;
    private JList<String> stageList;
    private Product product; 

    
    public LCA_GUI() {
        productNameField = new JTextField(20);
        initialize();
    }

    private void initialize() {
        frame = new JFrame("ECOCYCLE - Life Cycle Assessment");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

       
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        topPanel.add(new JLabel("Product Name:"));
        topPanel.add(productNameField);
        
        JButton createProductButton = new JButton("Create/Reset Product");
        topPanel.add(createProductButton);
        frame.add(topPanel, BorderLayout.NORTH);

        
        stageListModel = new DefaultListModel<>();
        stageList = new JList<>(stageListModel);
        stageList.setFont(new Font("SansSerif", Font.PLAIN, 14));
        JScrollPane stageScroll = new JScrollPane(stageList);
        stageScroll.setBorder(BorderFactory.createTitledBorder("Life Cycle Stages"));
        frame.add(stageScroll, BorderLayout.CENTER);

        // --- RIGHT PANEL: Stage/Input Controls ---
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton addStageButton = new JButton("Add Stage");
        JButton removeStageButton = new JButton("Remove Selected Stage");
        
        // Input item buttons
        JButton addMaterialButton = new JButton("Add Material");
        JButton addMachineButton = new JButton("Add Machine");
        JButton addTransportButton = new JButton("Add Transport");
        JButton addDisposalButton = new JButton("Add Disposal Method");
        JButton viewImpactButton = new JButton("View Total Impact");

        // Layout buttons
        rightPanel.add(addStageButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rightPanel.add(removeStageButton);
        
        rightPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        rightPanel.add(new JLabel("Add Inputs to Selected Stage:"));
        
        rightPanel.add(addMaterialButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        rightPanel.add(addMachineButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        rightPanel.add(addTransportButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        rightPanel.add(addDisposalButton);
        
        rightPanel.add(Box.createVerticalGlue()); 
        rightPanel.add(viewImpactButton);

        frame.add(rightPanel, BorderLayout.EAST);

        
        
        // Creating a product
        createProductButton.addActionListener(e -> {
            String name = productNameField.getText().trim();
            try {
                
                product = new Product(name); 
                stageListModel.clear();
                JOptionPane.showMessageDialog(frame, "Product '" + name + "' created!");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        //Adding a life cycle stage
        addStageButton.addActionListener(e -> {
            if (product == null) { JOptionPane.showMessageDialog(frame, "Create a product first!", "Error", JOptionPane.ERROR_MESSAGE); return; }
            
            String stageName = JOptionPane.showInputDialog(frame, "Enter Stage Name:");
            if (stageName == null || stageName.trim().isEmpty()) return;

            String[] stageTypes = {"Raw Material", "Manufacturing", "Transportation", "Disposal"};
            String selectedType = (String) JOptionPane.showInputDialog(frame, "Select Stage Type:", "Add New Stage", JOptionPane.QUESTION_MESSAGE, null, stageTypes, stageTypes[0]);
            
            if (selectedType != null) {
                try {
                    // FIX: Ensure stage object is created with the name
                    LifeCycleStage stage = createStageByType(selectedType, stageName.trim()); 
                    if (stage != null) {
                        product.addStage(stage);
                        stageListModel.addElement(stage.getStageName());
                    }
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Removing a lifecyle stage
        removeStageButton.addActionListener(e -> {
            if (product == null) { JOptionPane.showMessageDialog(frame, "Create a product first!", "Error", JOptionPane.ERROR_MESSAGE); return; }
            int selectedIndex = stageList.getSelectedIndex();
            if (selectedIndex >= 0) {
                String stageName = stageListModel.get(selectedIndex);
                LifeCycleStage stage = product.getStageByName(stageName);
                try {
                    if (stage != null) {
                        product.removeStage(stage);
                        stageListModel.remove(selectedIndex);
                    }
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Select a stage to remove!", "Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Item addition handlers
        addMaterialButton.addActionListener(e -> addItemToStage("Material"));
        addMachineButton.addActionListener(e -> addItemToStage("Machine"));
        addTransportButton.addActionListener(e -> addItemToStage("Transport"));
        addDisposalButton.addActionListener(e -> addItemToStage("Disposal"));

        // Viewing total impact
        viewImpactButton.addActionListener(e -> {
            if (product == null) { JOptionPane.showMessageDialog(frame, "Create a product first!", "Error", JOptionPane.ERROR_MESSAGE); return; }
            double totalImpact = product.calculateTotalImpact();
            JOptionPane.showMessageDialog(frame, 
                "Total Environmental Impact in Units of CO2: " + String.format("%.2f", totalImpact), 
                "LCA Results", JOptionPane.INFORMATION_MESSAGE);
        });

        frame.setVisible(true);
    }
    
    
    private LifeCycleStage createStageByType(String type, String name) {
       
        return switch (type) {
            case "Raw Material" -> new RawMaterialStage();
            case "Manufacturing" -> new ManufacturingStage();
            case "Transportation" -> new TransportationStage();
            case "Disposal" -> new DisposalStage();
            default -> null;
        };
    }

   
    private void addItemToStage(String type) {
        if (product == null) { JOptionPane.showMessageDialog(frame, "Create a product first!"); return; }
        int selectedIndex = stageList.getSelectedIndex();
        if (selectedIndex < 0) { JOptionPane.showMessageDialog(frame, "Select a stage first!"); return; }

        LifeCycleStage stage = product.getStageByName(stageListModel.get(selectedIndex));
        if (stage == null) { JOptionPane.showMessageDialog(frame, "Error: Stage object not found."); return; }

        JTextField nameField = new JTextField();
        JTextField factorField = new JTextField(); 
        JTextField quantityField = new JTextField(); 
        
        String factorLabel = ""; 
        String quantityLabel = "";
        
        Object[] inputs = new Object[0];
        
        try {
            switch (type) {
                case "Material":
                    if (!(stage instanceof RawMaterialStage rawStage)) { JOptionPane.showMessageDialog(frame, "Select a Raw Material Stage."); return; }
                    factorLabel = "Emission Factor (CO2 per kg):";
                    quantityLabel = "Quantity (kg):";
                    inputs = new Object[]{"Material Name:", nameField, factorLabel, factorField, quantityLabel, quantityField};
                    
                    if (JOptionPane.showConfirmDialog(frame, inputs, "Add Material", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                        Material mat = new Material(nameField.getText().trim(), Double.parseDouble(factorField.getText()));
                        rawStage.addMaterial(mat, Double.parseDouble(quantityField.getText()));
                    }
                    break;

                case "Machine":
                    if (!(stage instanceof ManufacturingStage manStage)) { JOptionPane.showMessageDialog(frame, "Select a Manufacturing Stage."); return; }
                    factorLabel = "Energy Consumption (kWh/hr):";
                    quantityLabel = "Hours Used:";
                    inputs = new Object[]{"Machine Name:", nameField, factorLabel, factorField, quantityLabel, quantityField};
                    
                    if (JOptionPane.showConfirmDialog(frame, inputs, "Add Machine", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                        Machine machine = new Machine(nameField.getText().trim(), Double.parseDouble(factorField.getText()));
                        manStage.addMachine(machine, Double.parseDouble(quantityField.getText()));
                    }
                    break;
                    
                case "Transport":
                    if (!(stage instanceof TransportationStage transStage)) { JOptionPane.showMessageDialog(frame, "Select a Transportation Stage."); return; }
                    factorLabel = "Emission Rate (g CO2/km):";
                    quantityLabel = "Distance (km):";
                    inputs = new Object[]{"Mode Name:", nameField, factorLabel, factorField, quantityLabel, quantityField};
                    
                    if (JOptionPane.showConfirmDialog(frame, inputs, "Add Transport", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                        ModeOfTransportation mot = new ModeOfTransportation(nameField.getText().trim(), Double.parseDouble(factorField.getText()));
                        transStage.addMode(mot, Double.parseDouble(quantityField.getText()));
                    }
                    break;
                    
                case "Disposal":
                    if (!(stage instanceof DisposalStage endStage)) { JOptionPane.showMessageDialog(frame, "Select an End of Life Stage."); return; }
                    factorLabel = "Emission Factor (CO2-eq/kg):";
                    quantityLabel = "Weight (kg):";
                    inputs = new Object[]{"Disposal Method:", nameField, factorLabel, factorField, quantityLabel, quantityField};
                    
                    if (JOptionPane.showConfirmDialog(frame, inputs, "Add Disposal Method", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                        DisposalMethod dm = new DisposalMethod(nameField.getText().trim(), Double.parseDouble(factorField.getText()));
                        endStage.addMethod(dm, Double.parseDouble(quantityField.getText()));
                    }
                    break;
            }
            
            
            JOptionPane.showMessageDialog(frame, type + " added successfully!");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid number input for factor or quantity.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Logic Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(LCA_GUI::new);
    }
}