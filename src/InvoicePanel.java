import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class InvoicePanel extends JPanel {
    private JTextField productNameField;
    private JTextField quantityField;
    private JTextField unitPriceField;
    private JButton addItemButton;
    private JButton removeItemButton;
    private JTextArea invoiceDisplay;
    private JLabel totalLabel;
    private Customer customer;

    private Invoice invoice;

    public InvoicePanel() {
        productNameField = new JTextField(20);
        quantityField = new JTextField(5);
        unitPriceField = new JTextField(5);
        addItemButton = new JButton("Add Item");
        removeItemButton = new JButton("Remove Item");
        invoiceDisplay = new JTextArea(10, 30);
        totalLabel = new JLabel("Total Amount Due: $0.00");

        customer = new Customer("Duc Toan Le", "1234 Main St");
        invoice = new Invoice(customer);

        addItemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String productName = productNameField.getText();
                int quantity = Integer.parseInt(quantityField.getText());
                double unitPrice = Double.parseDouble(unitPriceField.getText());
                Product product = new Product(productName, unitPrice);
                LineItem item = new LineItem(product, quantity);
                invoice.addLineItem(item);

                updateDisplay();
            }
        });

        removeItemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<LineItem> lineItems = invoice.getLineItems();
                String productName = productNameField.getText();

                for (int i = lineItems.size() - 1; i >= 0; i--) {
                    LineItem item = lineItems.get(i);
                    if (item.getProduct().getName().equals(productName)) {
                        lineItems.remove(i);
                        break; // Remove only one item with the same product name
                    }
                }

                updateDisplay();
            }
        });

        setLayout(new GridLayout(5, 2));

        add(new JLabel("Product Name:"));
        add(productNameField);
        add(new JLabel("Quantity:"));
        add(quantityField);
        add(new JLabel("Unit Price:"));
        add(unitPriceField);
        add(addItemButton);
        add(removeItemButton);
        add(new JScrollPane(invoiceDisplay));
        add(totalLabel);
    }

    private void updateDisplay() {
        StringBuilder sb = new StringBuilder();
        List<LineItem> lineItems = invoice.getLineItems();

        sb.append("Invoice for: ").append(customer.getName()).append("\n");
        sb.append("Customer Address: ").append(customer.getAddress()).append("\n\n");

        for (LineItem item : lineItems) {
            sb.append(item.getProduct().getName()).append(": ")
                    .append(item.getQuantity()).append(" x $").append(item.getProduct().getUnitPrice())
                    .append(" = $").append(item.calculateTotal()).append("\n");
        }

        invoiceDisplay.setText(sb.toString());
        totalLabel.setText("Total Amount Due: $" + invoice.calculateTotal());
    }
}
