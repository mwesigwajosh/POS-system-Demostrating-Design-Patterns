package com.pos;
import javax.swing.*;
import java.awt.*;

public class ReceiptPrinter extends JFrame {

    private JTextArea textArea;

    public ReceiptPrinter(String receiptData) {
        setTitle("Receipt Printer");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
        
        // Set receipt content in the text area
        textArea.setText(receiptData);

        JButton printButton = new JButton("Print");
        printButton.addActionListener(e -> printReceipt());
        add(printButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void printReceipt() {
        // Print the receipt
        try {
            textArea.print();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
