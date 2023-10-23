import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private InvoicePanel invoicePanel;

    public MainFrame() {
        setTitle("Invoice Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        invoicePanel = new InvoicePanel();
        add(invoicePanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
