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
        JButton addMaterialButton = new JButton("Add Material");
        JButton addMachineButton = new JButton("Add Machine");
        JButton addTransportButton = new JButton("Add Transport");
        JButton addDisposalButton = new JButton("Add Disposal Method");
        JButton viewImpactButton = new JButton("View Total Impact");

        rightPanel.add(addStageButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rightPanel.add(removeStageButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        rightPanel.add(addMaterialButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        rightPanel.add(addMachineButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        rightPanel.add(addTransportButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        rightPanel.add(addDisposalButton);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 15)));
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
                
                // Ask for stage type
            String stageType = (String) JOptionPane.showInputDialog(
                    frame,
                    "Select stage type:",
                    "Stage Type",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new String[]{"Raw Material", "Manufacturing", "Transportation", "Disposal"},
                    "Raw Material"
            );
            if (stageType == null) return;

            LifeCycleStage stage;
            switch (stageType) {
                case "Raw Material":
                    stage = new RawMaterialStage();
                    break;
                case "Manufacturing":
                    stage = new ManufacturingStage();
                    break;
                case "Transportation":
                    stage = new TransportationStage();
                    break;
                case "Disposal":
                    stage = new DisposalStage();
                    break;
                default:
                    return;
            }

            product.addStage(stage);
            stageListModel.addElement(stage.getStageName());
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

        // Add Material
        addMaterialButton.addActionListener(e -> addItemToStage("Material"));

        // Add Machine
        addMachineButton.addActionListener(e -> addItemToStage("Machine"));

        // Add Transport
        addTransportButton.addActionListener(e -> addItemToStage("Transport"));

        // Add Disposal
        addDisposalButton.addActionListener(e -> addItemToStage("Disposal"));

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

    //Helper method to add items
    private void addItemToStage(String type) {
        if (product == null) { JOptionPane.showMessageDialog(frame, "Create a product first!"); return; }
        int selectedIndex = stageList.getSelectedIndex();
        if (selectedIndex < 0) { JOptionPane.showMessageDialog(frame, "Select a stage first!"); return; }

        LifeCycleStage stage = product.getStageByName(stageListModel.get(selectedIndex));

        switch (type) {
            case "Material":
                if (!(stage instanceof RawMaterialStage)) { JOptionPane.showMessageDialog(frame, "Select a Raw Material Stage."); return; }
                RawMaterialStage rawStage = (RawMaterialStage) stage;

                JTextField nameField = new JTextField();
                JTextField emissionField = new JTextField();
                JTextField quantityField = new JTextField();
                Object[] inputs = {"Material Name:", nameField, "Emission Factor:", emissionField, "Quantity:", quantityField};
                int result = JOptionPane.showConfirmDialog(frame, inputs, "Add Material", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    try {
                        Material mat = new Material(nameField.getText().trim(), Double.parseDouble(emissionField.getText()));
                        rawStage.addMaterial(mat, Double.parseDouble(quantityField.getText()));
                        JOptionPane.showMessageDialog(frame, "Material added!");
                    } catch (NumberFormatException ex) { JOptionPane.showMessageDialog(frame, "Invalid number input."); }
                }
                break;

            case "Machine":
                if (!(stage instanceof ManufacturingStage)) { JOptionPane.showMessageDialog(frame, "Select a Manufacturing Stage."); return; }
                ManufacturingStage manStage = (ManufacturingStage) stage;

                JTextField mNameField = new JTextField();
                JTextField mImpactField = new JTextField();
                JTextField mHoursField = new JTextField();
                Object[] mInputs = {"Machine Name:", mNameField, "Emission Factor:", mImpactField, "Hours Used:", mHoursField};
                int mResult = JOptionPane.showConfirmDialog(frame, mInputs, "Add Machine", JOptionPane.OK_CANCEL_OPTION);
                if (mResult == JOptionPane.OK_OPTION) {
                    try {
                        Machine machine = new Machine(mNameField.getText().trim(), Double.parseDouble(mImpactField.getText()));
                        manStage.addMachine(machine, Double.parseDouble(mHoursField.getText()));
                        JOptionPane.showMessageDialog(frame, "Machine added!");
                    } catch (NumberFormatException ex) { JOptionPane.showMessageDialog(frame, "Invalid number input."); }
                }
                break;

            case "Transport":
                if (!(stage instanceof TransportationStage)) { JOptionPane.showMessageDialog(frame, "Select a Transportation Stage."); return; }
                TransportationStage transStage = (TransportationStage) stage;

                JTextField tModeField = new JTextField();
                JTextField tDistanceField = new JTextField();
                JTextField tWeightField = new JTextField();
                Object[] tInputs = {"Mode of Transport:", tModeField, "Distance:", tDistanceField, "Weight:", tWeightField};
                int tResult = JOptionPane.showConfirmDialog(frame, tInputs, "Add Transport", JOptionPane.OK_CANCEL_OPTION);
                if (tResult == JOptionPane.OK_OPTION) {
                    try {
                        ModeOfTransportation mot = new ModeOfTransportation(tModeField.getText().trim(), Double.parseDouble(tDistanceField.getText()));
                        transStage.addMode(mot, Double.parseDouble(tWeightField.getText()));
                        JOptionPane.showMessageDialog(frame, "Transport added!");
                    } catch (NumberFormatException ex) { JOptionPane.showMessageDialog(frame, "Invalid number input."); }
                }
                break;

            case "Disposal":
                if (!(stage instanceof DisposalStage)) { JOptionPane.showMessageDialog(frame, "Select an End of Life Stage."); return; }
                DisposalStage endStage = (DisposalStage) stage;

                JTextField dNameField = new JTextField();
                JTextField dImpactField = new JTextField();
                JTextField dWeightField = new JTextField();
                Object[] dInputs = {"Disposal Method:", dNameField, "Emission Factor:", dImpactField, "Weight:", dWeightField};
                int dResult = JOptionPane.showConfirmDialog(frame, dInputs, "Add Disposal Method", JOptionPane.OK_CANCEL_OPTION);
                if (dResult == JOptionPane.OK_OPTION) {
                    try {
                        DisposalMethod dm = new DisposalMethod(dNameField.getText().trim(), Double.parseDouble(dImpactField.getText()));
                        endStage.addMethod(dm, Double.parseDouble(dWeightField.getText()));
                        JOptionPane.showMessageDialog(frame, "Disposal Method added!");
                    } catch (NumberFormatException ex) { JOptionPane.showMessageDialog(frame, "Invalid number input."); }
                }
                break;
        }
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(LCA_GUI::new);
    }
    
}
