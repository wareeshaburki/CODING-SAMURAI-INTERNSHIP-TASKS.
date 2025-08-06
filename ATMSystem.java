import java.awt.*;
import javax.swing.*;

public class ATMSystem extends JFrame {
    private double balance = 1000.0;
    private JTextField amountField;
    private JLabel balanceLabel, messageLabel;
    public ATMSystem() {
        setTitle("ATM System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUndecorated(true);
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        setBounds(env.getMaximumWindowBounds());
        JPanel panel = new JPanel();
        panel.setBackground(new Color(44, 62, 80));
        panel.setLayout(null);

        JLabel title = new JLabel("Welcome to the ATM");
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(new Color(236, 240, 241));
        title.setBounds(500, 50, 600, 50);
        panel.add(title);

        balanceLabel = new JLabel("Balance: $" + balance);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 28));
        balanceLabel.setForeground(new Color(39, 174, 96));
        balanceLabel.setBounds(570, 150, 300, 40);
        panel.add(balanceLabel);

        JLabel amountText = new JLabel("Amount: ");
        amountText.setFont(new Font("Arial", Font.PLAIN, 22));
        amountText.setForeground(new Color(236, 240, 241));
        amountText.setBounds(540, 220, 100, 30);
        panel.add(amountText);

        amountField = new JTextField();
        amountField.setFont(new Font("Arial", Font.PLAIN, 22));
        amountField.setBounds(640, 220, 200, 30);
        panel.add(amountField);

        JButton depositButton = new JButton("Deposit");
        depositButton.setFont(new Font("Arial", Font.BOLD, 22));
        depositButton.setBackground(new Color(41, 128, 185));
        depositButton.setForeground(Color.WHITE);
        depositButton.setBounds(540, 280, 140, 50);
        panel.add(depositButton);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setFont(new Font("Arial", Font.BOLD, 22));
        withdrawButton.setBackground(new Color(192, 57, 43));
        withdrawButton.setForeground(Color.WHITE);
        withdrawButton.setBounds(700, 280, 140, 50);
        panel.add(withdrawButton);

        JButton checkButton = new JButton("Check Balance");
        checkButton.setFont(new Font("Arial", Font.BOLD, 22));
        checkButton.setBackground(new Color(39, 174, 96));
        checkButton.setForeground(Color.WHITE);
        checkButton.setBounds(500, 350, 380, 50);
        panel.add(checkButton);

        messageLabel = new JLabel("");
        messageLabel.setFont(new Font("Arial", Font.BOLD, 20));
        messageLabel.setForeground(new Color(241, 196, 15));
        messageLabel.setBounds(580, 420, 400, 30);
        panel.add(messageLabel);

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 18));
        exitButton.setBackground(new Color(127, 140, 141));
        exitButton.setForeground(Color.WHITE);
        exitButton.setBounds(630, 470, 100, 40);
        panel.add(exitButton);

        depositButton.addActionListener(e -> deposit());
        withdrawButton.addActionListener(e -> withdraw());
        checkButton.addActionListener(e -> updateBalance());
        exitButton.addActionListener(e -> System.exit(0));
        setContentPane(panel);
        setVisible(true);
    }
    private void updateBalance() {
        balanceLabel.setText("Balance: $" + String.format("%.2f", balance));
        messageLabel.setText("Your current balance is $" + String.format("%.2f", balance));
    }
    private void deposit() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount <= 0) {
                messageLabel.setText("Enter a positive amount to deposit");
            } else {
                balance += amount;
                updateBalance();
                messageLabel.setText("Deposited $" + String.format("%.2f", amount));
            }
            amountField.setText("");
        } catch (NumberFormatException ex) {
            messageLabel.setText("Invalid amount entered");
        }
    }

    private void withdraw() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount <= 0) {
                messageLabel.setText("Enter a positive amount to withdraw");
            } else if (amount > balance) {
                messageLabel.setText("Insufficient balance");
            } else {
                balance -= amount;
                updateBalance();
                messageLabel.setText("Withdrew $" + String.format("%.2f", amount));
            }
            amountField.setText("");
        } catch (NumberFormatException ex) {
            messageLabel.setText("Invalid amount entered");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ATMSystem::new);
    }
}
