package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.RenderingHints;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class TransactionFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    // LinkedList to hold transaction history
    private LinkedList<String> transactionList;

    public TransactionFrame(String transactionHistory) {
        setTitle("Transaction History");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 700, 500);
        setLocationRelativeTo(null); // Center the frame

        // Initialize the LinkedList and populate it with transactions
        transactionList = new LinkedList<>();
        populateTransactionList(transactionHistory);

        // Custom JPanel with gradient background
        JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color color1 = new Color(135, 206, 250); // Light sky blue
                Color color2 = new Color(70, 130, 180); // Steel blue
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Title label
        JLabel lblTransactionHistory = new JLabel("Transaction History");
        lblTransactionHistory.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTransactionHistory.setHorizontalAlignment(SwingConstants.CENTER);
        lblTransactionHistory.setForeground(Color.WHITE);
        lblTransactionHistory.setBounds(50, 20, 600, 50);
        contentPane.add(lblTransactionHistory);

        // Transaction history text area
        JTextArea transactionHistoryArea = new JTextArea();
        transactionHistoryArea.setEditable(false);
        transactionHistoryArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        transactionHistoryArea.setBackground(new Color(245, 245, 245)); // Light gray background
        transactionHistoryArea.setForeground(new Color(34, 34, 34)); // Dark text color
        transactionHistoryArea.setBorder(new LineBorder(new Color(70, 130, 180), 2, true)); // Rounded border
        transactionHistoryArea.setLineWrap(true);
        transactionHistoryArea.setWrapStyleWord(true);

        // Display transaction history using LinkedList
        transactionHistoryArea.setText(getTransactionHistory());

        JScrollPane scrollPane = new JScrollPane(transactionHistoryArea);
        scrollPane.setBounds(50, 90, 600, 280);
        scrollPane.setBorder(new LineBorder(new Color(70, 130, 180), 2, true)); // Match the text area border
        contentPane.add(scrollPane);

        // Close button with improved styling
        JButton btnClose = new JButton("Close");
        btnClose.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnClose.setBackground(new Color(220, 20, 60)); // Crimson red
        btnClose.setForeground(Color.WHITE);
        btnClose.setFocusPainted(false); // Remove focus outline
        btnClose.setBounds(250, 400, 200, 50);
        btnClose.setBorderPainted(false); // Remove button border
        btnClose.setOpaque(true); // Make button opaque for rounded effect
        btnClose.setBorder(new LineBorder(Color.WHITE, 2, true)); // Rounded border
        btnClose.addActionListener(e -> this.dispose());
        contentPane.add(btnClose);
    }

    // Method to populate the LinkedList with transaction history
    private void populateTransactionList(String transactionHistory) {
        if (transactionHistory != null && !transactionHistory.isEmpty()) {
            String[] transactions = transactionHistory.split("\n");
            for (String transaction : transactions) {
                transactionList.add(transaction);
            }
        }
    }

    // Method to retrieve all transactions from the LinkedList
    private String getTransactionHistory() {
        StringBuilder historyBuilder = new StringBuilder();
        for (String transaction : transactionList) {
            historyBuilder.append(transaction).append("\n");
        }
        return historyBuilder.toString();
    }
}