import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Calculator extends JFrame implements ActionListener{

    JLabel operationLabel;
    JTextField displayTextField;
    JRadioButton onRadioButton,offRadioButton;
    JButton zeroButton,oneButton,twoButton,threeButton,fourButton,fiveButton,
            sixButton,sevenButton,eightButton,nineButton,dotButton,clearButton,
            deleteButton,equalButton,multiplyButton,divideButton,addButton,
            subtractButton,squareButton,reciprocalButton,sqrtButton;
    ButtonGroup buttonGroup;
    double number1=0,number2=0,result=0;
    int optionForCalculation;
    String resultString;

    public Calculator(){
        setTitle("Calculator");
        setSize(305,510);
        setLayout(null);
        getContentPane().setBackground(Color.gray);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        operationLabel = new JLabel();
        displayTextField = new JTextField();

        onRadioButton= new JRadioButton("On");
        offRadioButton = new JRadioButton("Off");

        zeroButton = new JButton("0");
        oneButton = new JButton("1");
        twoButton = new JButton("2");
        threeButton = new JButton("3");
        fourButton = new JButton("4");
        fiveButton = new JButton("5");
        sixButton = new JButton("6");
        sevenButton = new JButton("7");
        eightButton = new JButton("8");
        nineButton = new JButton("9");
        dotButton = new JButton(".");
        clearButton = new JButton("C");
        deleteButton = new JButton("del");
        equalButton = new JButton("=");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");
        addButton = new JButton("+");
        subtractButton = new JButton("-");
        squareButton = new JButton("x\u00B2");
        reciprocalButton = new JButton("1/x");
        sqrtButton = new JButton("\u221A");

        operationLabel.setBounds(250,0,50,50);
        operationLabel.setForeground(Color.white);
        add(operationLabel);

        displayTextField.setBounds(10,40,270,40);
        displayTextField.setFont(new Font("Arial",Font.BOLD,20));
        displayTextField.setEditable(false);
        displayTextField.setHorizontalAlignment(SwingConstants.RIGHT);
        add(displayTextField);

        onRadioButton.setBounds(10, 95, 60, 40);
        onRadioButton.setSelected(true);
        onRadioButton.setFont(new Font("Arial", Font.BOLD, 14));
        onRadioButton.setBackground(Color.black);
        onRadioButton.setForeground(Color.white);
        add(onRadioButton);

        offRadioButton.setBounds(10, 120, 60, 40);
        offRadioButton.setSelected(false);
        offRadioButton.setFont(new Font("Arial", Font.BOLD, 14));
        offRadioButton.setBackground(Color.black);
        offRadioButton.setForeground(Color.white);
        add(offRadioButton);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(onRadioButton);
        buttonGroup.add(offRadioButton);

        zeroButton.setBounds(10, 410, 130, 40);
        zeroButton.setFont(new Font("Arial", Font.BOLD, 20));
        add(zeroButton);

        oneButton.setBounds(10, 350, 60, 40);
        oneButton.setFont(new Font("Arial", Font.BOLD, 20));
        add(oneButton);

        twoButton.setBounds(80, 350, 60, 40);
        twoButton.setFont(new Font("Arial", Font.BOLD, 20));
        add(twoButton);

        threeButton.setBounds(150, 350, 60, 40);
        threeButton.setFont(new Font("Arial", Font.BOLD, 20));
        add(threeButton);

        fourButton.setBounds(10, 290, 60, 40);
        fourButton.setFont(new Font("Arial", Font.BOLD, 20));
        add(fourButton);

        fiveButton.setBounds(80, 290, 60, 40);
        fiveButton.setFont(new Font("Arial", Font.BOLD, 20));
        add(fiveButton);

        sixButton.setBounds(150, 290, 60, 40);
        sixButton.setFont(new Font("Arial", Font.BOLD, 20));
        add(sixButton);

        sevenButton.setBounds(10, 230, 60, 40);
        sevenButton.setFont(new Font("Arial", Font.BOLD, 20));
        add(sevenButton);

        eightButton.setBounds(80, 230, 60, 40);
        eightButton.setFont(new Font("Arial", Font.BOLD, 20));
        add(eightButton);

        nineButton.setBounds(150, 230, 60, 40);
        nineButton.setFont(new Font("Arial", Font.BOLD, 20));
        add(nineButton);

        dotButton.setBounds(150, 410, 60, 40);
        dotButton.setFont(new Font("Arial", Font.BOLD, 20));
        add(dotButton);

        equalButton.setBounds(220, 350, 60, 100);
        equalButton.setFont(new Font("Arial", Font.BOLD, 20));
        equalButton.setBackground(new Color(239, 188, 2));
        add(equalButton);

        divideButton.setBounds(220, 110, 60, 40);
        divideButton.setFont(new Font("Arial", Font.BOLD, 20));
        divideButton.setBackground(new Color(239, 188, 2));
        add(divideButton);

        sqrtButton.setBounds(10, 170, 60, 40);
        sqrtButton.setFont(new Font("Arial", Font.BOLD, 18));
        add(sqrtButton);

        multiplyButton.setBounds(220, 230, 60, 40);
        multiplyButton.setFont(new Font("Arial", Font.BOLD, 20));
        multiplyButton.setBackground(new Color(239, 188, 2));
        add(multiplyButton);

        subtractButton.setBounds(220, 170, 60, 40);
        subtractButton.setFont(new Font("Arial", Font.BOLD, 20));
        subtractButton.setBackground(new Color(239, 188, 2));
        add(subtractButton);

        addButton.setBounds(220, 290, 60, 40);
        addButton.setFont(new Font("Arial", Font.BOLD, 20));
        addButton.setBackground(new Color(239, 188, 2));
        add(addButton);

        squareButton.setBounds(80, 170, 60, 40);
        squareButton.setFont(new Font("Arial", Font.BOLD, 20));
        add(squareButton);

        reciprocalButton.setBounds(150, 170, 60, 40);
        reciprocalButton.setFont(new Font("Arial", Font.BOLD, 15));
        add(reciprocalButton);

        deleteButton.setBounds(150, 110, 60, 40);
        deleteButton.setFont(new Font("Arial", Font.BOLD, 12));
        deleteButton.setBackground(Color.red);
        deleteButton.setForeground(Color.white);
        add(deleteButton);

        clearButton.setBounds(80, 110, 60, 40);
        clearButton.setFont(new Font("Arial", Font.BOLD, 12));
        clearButton.setBackground(Color.red);
        clearButton.setForeground(Color.white);
        add(clearButton);

        addActionListenerEvent();

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==onRadioButton){
            enableCalculator();
            displayTextField.setText("");
            operationLabel.setText("");
        } 
        else if(e.getSource()==offRadioButton){
            disableCalculator();
            displayTextField.setText("");
            operationLabel.setText("");
        } 
        else if(e.getSource()==zeroButton){
            
            if(displayTextField.getText().equals("0")){
                return;
            } else{
                displayTextField.setText(displayTextField.getText() + "0");
            }

        } 
        else if(e.getSource()==oneButton){
            displayTextField.setText(displayTextField.getText() + "1");
        } 
        else if(e.getSource()==twoButton){
            displayTextField.setText(displayTextField.getText() + "2");
        } 
        else if(e.getSource()==threeButton){
            displayTextField.setText(displayTextField.getText() + "3");
        } 
        else if(e.getSource()==fourButton){
            displayTextField.setText(displayTextField.getText() + "4");
        } 
        else if(e.getSource()==fiveButton){
            displayTextField.setText(displayTextField.getText() + "5");
        } 
        else if(e.getSource()==sixButton){
            displayTextField.setText(displayTextField.getText() + "6");
        } 
        else if(e.getSource()==sevenButton){
            displayTextField.setText(displayTextField.getText() + "7");
        } 
        else if(e.getSource()==eightButton){
            displayTextField.setText(displayTextField.getText() + "8");
        } 
        else if(e.getSource()==nineButton){
            displayTextField.setText(displayTextField.getText() + "9");
        } 
        else if(e.getSource()==dotButton){
            
            if(displayTextField.getText().contains(".")){
                return;
            } else {
                displayTextField.setText(displayTextField.getText() + ".");
            }

        } 
        else if(e.getSource()==equalButton){
            number2 = Double.parseDouble(displayTextField.getText());

            switch (optionForCalculation) {
                case 1:
                    result = number1 + number2;
                    break;
                case 2:
                    result = number1 - number2;
                    break;
                case 3:
                    result = number1 * number2;
                    break;
                case 4:

                if(number2 == 0){
                    displayTextField.setText("Error");
                    return;
                }

                    result = number1 / number2;
                    break;
            }

            if(Double.toString(result).endsWith(".0")){
                displayTextField.setText(Double.toString(result).replace(".0", ""));
            } else {
                displayTextField.setText(Double.toString(result));
            }
            operationLabel.setText("");
            number1=result;

        } 
        else if(e.getSource()==divideButton){
            resultString = displayTextField.getText();
            number1 = Double.parseDouble(displayTextField.getText());
            optionForCalculation = 4;
            displayTextField.setText("");
            operationLabel.setText(number1 + "/");
        } 
        else if(e.getSource()==sqrtButton){
            number1 = Double.parseDouble(displayTextField.getText());
            double sqrt = Math.sqrt(number1);
            displayTextField.setText(Double.toString(sqrt));
        } 
        else if(e.getSource()==multiplyButton){
            resultString = displayTextField.getText();
            number1 = Double.parseDouble(displayTextField.getText());
            optionForCalculation = 3;
            displayTextField.setText("");
            operationLabel.setText(number1 + "*");
        } 
        else if(e.getSource()==subtractButton){
            resultString = displayTextField.getText();
            number1 = Double.parseDouble(displayTextField.getText());
            optionForCalculation = 2;
            displayTextField.setText("");
            operationLabel.setText(number1 + "-");
        } 
        else if(e.getSource()==addButton){
            resultString = displayTextField.getText();
            number1 = Double.parseDouble(displayTextField.getText());
            optionForCalculation = 1;
            displayTextField.setText("");
            operationLabel.setText(number1 + "+");
        } 
        else if(e.getSource()==squareButton){
            number1 = Double.parseDouble(displayTextField.getText());
            double square = Math.pow(number1, 2);
            resultString = Double.toString(square);

            if(resultString.endsWith(".0")){
                displayTextField.setText(resultString.replace(".0", ""));
            } else {
                displayTextField.setText(resultString);
            }

        } 
        else if(e.getSource()==reciprocalButton){
            number1 = Double.parseDouble(displayTextField.getText());
            double reciprocal = 1/number1;
            resultString = Double.toString(reciprocal);

            if(resultString.endsWith(".0")){
                displayTextField.setText(resultString.replace(".0", ""));
            } else {
                displayTextField.setText(resultString);
            }
        } 
        else if(e.getSource()==deleteButton){
            int length = displayTextField.getText().length();
            int number=length-1;

            if(length>0){
                StringBuilder backspace = new StringBuilder(displayTextField.getText());
                backspace.deleteCharAt(number);
                displayTextField.setText(backspace.toString());
            }

        } 
        else if(e.getSource()==clearButton){
            operationLabel.setText("");
            displayTextField.setText("");
        }
    }

    public void enableCalculator(){
        displayTextField.setEnabled(true);
        onRadioButton.setEnabled(false);
        offRadioButton.setEnabled(true);
        zeroButton.setEnabled(true);
        oneButton.setEnabled(true);
        twoButton.setEnabled(true);
        threeButton.setEnabled(true);
        fourButton.setEnabled(true);
        fiveButton.setEnabled(true);
        sixButton.setEnabled(true);
        sevenButton.setEnabled(true);
        eightButton.setEnabled(true);
        nineButton.setEnabled(true);
        dotButton.setEnabled(true);
        equalButton.setEnabled(true);
        divideButton.setEnabled(true);
        sqrtButton.setEnabled(true);
        multiplyButton.setEnabled(true);
        subtractButton.setEnabled(true);
        addButton.setEnabled(true);
        squareButton.setEnabled(true);
        reciprocalButton.setEnabled(true);
        deleteButton.setEnabled(true);
        clearButton.setEnabled(true);
    }

    public void disableCalculator(){
        displayTextField.setEnabled(false);
        onRadioButton.setEnabled(true);
        offRadioButton.setEnabled(false);
        zeroButton.setEnabled(false);
        oneButton.setEnabled(false);
        twoButton.setEnabled(false);
        threeButton.setEnabled(false);
        fourButton.setEnabled(false);
        fiveButton.setEnabled(false);
        sixButton.setEnabled(false);
        sevenButton.setEnabled(false);
        eightButton.setEnabled(false);
        nineButton.setEnabled(false);
        dotButton.setEnabled(false);
        equalButton.setEnabled(false);
        divideButton.setEnabled(false);
        sqrtButton.setEnabled(false);
        multiplyButton.setEnabled(false);
        subtractButton.setEnabled(false);
        addButton.setEnabled(false);
        squareButton.setEnabled(false);
        reciprocalButton.setEnabled(false);
        deleteButton.setEnabled(false);
        clearButton.setEnabled(false);
    }

    public void addActionListenerEvent(){
        onRadioButton.addActionListener(this);
        offRadioButton.addActionListener(this);
        zeroButton.addActionListener(this);
        oneButton.addActionListener(this);
        twoButton.addActionListener(this);
        threeButton.addActionListener(this);
        fourButton.addActionListener(this);
        fiveButton.addActionListener(this);
        sixButton.addActionListener(this);
        sevenButton.addActionListener(this);
        eightButton.addActionListener(this);
        nineButton.addActionListener(this);
        dotButton.addActionListener(this);
        equalButton.addActionListener(this);
        divideButton.addActionListener(this);
        sqrtButton.addActionListener(this);
        multiplyButton.addActionListener(this);
        subtractButton.addActionListener(this);
        addButton.addActionListener(this);
        squareButton.addActionListener(this);
        reciprocalButton.addActionListener(this);
        deleteButton.addActionListener(this);
        clearButton.addActionListener(this);
    }

    public static void main(String[] args) {
        new Calculator();
    }

}