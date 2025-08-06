import java.awt.*;
import javax.swing.*;

public class Calculator extends JFrame {
    private JTextField display;
    private StringBuilder currentInput = new StringBuilder();
    private double firstNum = 0;
    private String operator = "";
    private boolean startNew = true;
    public Calculator() {
        setTitle("Calculator");
        setSize(350, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setEditable(false);
        display.setBackground(Color.WHITE);
        display.setHorizontalAlignment(JTextField.RIGHT);
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 5, 5));
        buttonPanel.setBackground(new Color(220, 220, 220));
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C"
        };
        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 22));
            if ("/*-+=C".contains(text)) {
                btn.setBackground(new Color(70, 130, 180));
                btn.setForeground(Color.WHITE);
            } else {
                btn.setBackground(Color.LIGHT_GRAY);
            }
            btn.addActionListener(e -> handleButton(text));
            buttonPanel.add(btn);
        }
        setLayout(new BorderLayout(10, 10));
        add(display, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }
    private void handleButton(String text) {
        if (text.matches("[0-9]") || text.equals(".")) {
            if (startNew) {
                currentInput.setLength(0);
                startNew = false;
            }
            currentInput.append(text);
            display.setText(currentInput.toString());
        } else if ("+-*/".contains(text)) {
            try {
                firstNum = Double.parseDouble(display.getText());
            } catch (NumberFormatException ex) {
                display.setText("Error");
                return;
            }
            operator = text;
            startNew = true;
        } else if (text.equals("=")) {
            try {
                double secondNum = Double.parseDouble(display.getText());
                double result = 0;
                switch (operator) {
                    case "+": result = firstNum + secondNum; break;
                    case "-": result = firstNum - secondNum; break;
                    case "*": result = firstNum * secondNum; break;
                    case "/":
                        if (secondNum == 0) {
                            display.setText("Division by zero!");
                            startNew = true;
                            return;
                        }
                        result = firstNum / secondNum; break;
                }
                display.setText(String.valueOf(result));
                startNew = true;
            } catch (NumberFormatException ex) {
                display.setText("Error");
            }
        } else if (text.equals("C")) {
            display.setText("");
            currentInput.setLength(0);
            firstNum = 0;
            operator = "";
            startNew = true;
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator().setVisible(true));
    }
}
