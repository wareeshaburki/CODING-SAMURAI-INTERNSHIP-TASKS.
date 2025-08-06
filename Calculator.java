import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator extends JFrame {
    private JTextField display;
    private StringBuilder input = new StringBuilder();
    private double firstValue = 0;
    private String operation = "";
    private boolean resetInput = true;
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
        JPanel panel = new JPanel(new GridLayout(5, 4, 5, 5));
        panel.setBackground(new Color(220, 220, 220));
        String[] keys = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C"
        };
        for (String key : keys) {
            JButton button = new JButton(key);
            button.setFont(new Font("Arial", Font.BOLD, 22));
            if ("/*-+=C".contains(key)) {
                button.setBackground(new Color(70, 130, 180));
                button.setForeground(Color.WHITE);
            } else {
                button.setBackground(Color.LIGHT_GRAY);
            }
            button.addActionListener(e -> processKey(key));
            panel.add(button);
        }
        setLayout(new BorderLayout(10, 10));
        add(display, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }
    private void processKey(String key) {
        if (key.matches("[0-9]") || key.equals(".")) {
            if (resetInput) {
                input.setLength(0);
                resetInput = false;
            }
            input.append(key);
            display.setText(input.toString());
        } else if ("+-*/".contains(key)) {
            try {
                firstValue = Double.parseDouble(display.getText());
            } catch (NumberFormatException ex) {
                display.setText("Error");
                return;
            }
            operation = key;
            resetInput = true;
        } else if (key.equals("=")) {
            try {
                double secondValue = Double.parseDouble(display.getText());
                double result = 0;
                switch(operation) {
                    case "+":
                        result = firstValue + secondValue; 
                        break;
                    case "-": 
                        result = firstValue - secondValue; 
                        break;
                    case "*": 
                        result = firstValue * secondValue; 
                        break;
                    case "/":
                        if (secondValue == 0) {
                            display.setText("Division by zero!");
                            resetInput = true;
                            return;
                        }
                        result = firstValue / secondValue; 
                        break;
                }
                display.setText(String.valueOf(result));
                resetInput = true;
            } catch (NumberFormatException ex) {
                display.setText("Error");
            }
        } else if (key.equals("C")) {
            display.setText("");
            input.setLength(0);
            firstValue = 0;
            operation = "";
            resetInput = true;
        }
    }
    public static void main(String[] args) {
        new Calculator();
    }
}
