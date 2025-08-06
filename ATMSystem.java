import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ATMSystem extends JFrame {
    private double balance = 1000.0;
    private JTextField amountField;
    private JLabel balanceLabel, messageLabel;
    public ATMSystem() {
        setTitle("ATM System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        setBounds(environment.getMaximumWindowBounds());
        JPanel panel = new JPanel();
        panel.setBackground(new Color(44, 62, 80));
        panel.setLayout(null);
        JLabel title = new JLabel("ATM Machine");
        title.setFont(new Font("Arial", Font.BOLD, 38));
        title.setForeground(Color.WHITE);
        title.setBounds(550, 40, 300, 50);
        panel.add(title);
        balanceLabel = new JLabel("Balance: $" + String.format("%.2f", balance));
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 26));
        balanceLabel.setForeground(new Color(39, 174, 96));
        balanceLabel.setBounds(570, 120, 300, 40);
        panel.add(balanceLabel);
        JLabel amountText = new JLabel("Amount:");
        amountText.setFont(new Font("Arial", Font.PLAIN, 20));
        amountText.setForeground(Color.WHITE);
        amountText.setBounds(540, 180, 100, 30);
        panel.add(amountText);
        amountField = new JTextField();
        amountField.setFont(new Font("Arial", Font.PLAIN, 20));
        amountField.setBounds(640, 180, 200, 30);
        panel.add(amountField);
        JButton depositButton = new JButton("Deposit");
        depositButton.setFont(new Font("Arial", Font.BOLD, 20));
        depositButton.setBackground(new Color(41, 128, 185));
        depositButton.setForeground(Color.WHITE);
        depositButton.setBounds(540, 230, 140, 40);
        panel.add(depositButton);
        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setFont(new Font("Arial", Font.BOLD, 20));
        withdrawButton.setBackground(new Color(192, 57, 43));
        withdrawButton.setForeground(Color.WHITE);
        withdrawButton.setBounds(700, 230, 140, 40);
        panel.add(withdrawButton);
        JButton checkButton = new JButton("Check Balance");
        checkButton.setFont(new Font("Arial", Font.BOLD, 20));
        checkButton.setBackground(new Color(39, 174, 96));
        checkButton.setForeground(Color.WHITE);
        checkButton.setBounds(590, 290, 180, 40);
        panel.add(checkButton);
        messageLabel = new JLabel("");
        messageLabel.setFont(new Font("Arial", Font.BOLD, 18));
        messageLabel.setForeground(new Color(241, 196, 15));
        messageLabel.setBounds(570, 340, 350, 30);
        panel.add(messageLabel);
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 16));
        exitButton.setBackground(new Color(127, 140, 141));
        exitButton.setForeground(Color.WHITE);
        exitButton.setBounds(650, 390, 100, 35);
        panel.add(exitButton);
        depositButton.addActionListener(e -> deposit());
        withdrawButton.addActionListener(e -> withdraw());
        checkButton.addActionListener(e -> showBalance());
        exitButton.addActionListener(e -> System.exit(0));
        setContentPane(panel);
        setVisible(true);
    }
    private void showBalance() {
        balanceLabel.setText("Balance: $" + String.format("%.2f", balance));
        messageLabel.setText("Your balance is $" + String.format("%.2f", balance));
    }
    private void deposit() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount <= 0) {
                messageLabel.setText("Please enter a positive amount.");
            } else {
                balance += amount;
                showBalance();
                messageLabel.setText("You deposited $" + String.format("%.2f", amount));
            }
            amountField.setText("");
        } catch (NumberFormatException ex) {
            messageLabel.setText("Please enter a valid number.");
        }
    }
    private void withdraw() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount <= 0) {
                messageLabel.setText("Please enter a positive amount.");
            } else if (amount > balance) {
                messageLabel.setText("You don't have enough money.");
            } else {
                balance -= amount;
                showBalance();
                messageLabel.setText("You withdrew $" + String.format("%.2f", amount));
            }
            amountField.setText("");
        } catch (NumberFormatException ex) {
            messageLabel.setText("Please enter a valid number.");
        }
    }
    public static void main(String[] args) {
        new ATMSystem();
    }
}
