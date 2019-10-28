
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;

	JTextField input1, input2;
	JToggleButton plus, minus, div, mult;
	JButton equal;
	ButtonGroup mathCalc;
	JPanel inputPanel1, inputPanel2, buttonPanel, outputPanel, painel;
	JLabel inputText1, inputText2, outputText;
	ButtonGroup calculadora;
	
	public GUI() {
		
		calculadora = new ButtonGroup();
		buttonPanel = new JPanel(new GridLayout(2,2));
		
		inputPanel1 = new JPanel();
		inputText1 = new JLabel();
		inputText1.setText("Num 1:");
		inputText1.setFont(new Font("Arial", 1, 30));
		inputPanel1.add(inputText1, BorderLayout.EAST);
		
		input1 = new JTextField();
		input1.setHorizontalAlignment(JTextField.CENTER);
		input1.setFont(new Font("Arial", Font.BOLD, 50));
		input1.setPreferredSize(new Dimension(500,100));
		inputPanel1.add(input1, BorderLayout.CENTER);
		
		inputPanel2 = new JPanel();
		inputText2 = new JLabel();
		inputText2.setText("Num 2:");
		inputText2.setFont(new Font("Arial", 1, 30));
		inputPanel2.add(inputText2, BorderLayout.EAST);
		
		input2 = new JTextField();
		input2.setHorizontalAlignment(JTextField.CENTER);
		input2.setFont(new Font("Arial", Font.BOLD, 50));
		input2.setPreferredSize(new Dimension(500,100));
		inputPanel2.add(input2, BorderLayout.CENTER);
		
		plus = new JToggleButton("+");
		plus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		buttonPanel.add(plus);
		calculadora.add(plus);
		
		minus = new JToggleButton("-");
		minus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		buttonPanel.add(minus);
		calculadora.add(minus);
		
		div = new JToggleButton("/");
		div.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		buttonPanel.add(div);
		calculadora.add(div);
		
		mult = new JToggleButton("*");
		mult.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		buttonPanel.add(mult);
		calculadora.add(mult);
		
		equal = new JButton("=");
		equal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(plus.isSelected()) {
					try{
                        float num1 = Float.parseFloat(input1.getText()); 
                        float num2 = Float.parseFloat(input2.getText());
                        String res = Float.toString(num1 + num2);
                        outputText.setText(res);
                    }catch(Exception e2){
                        JOptionPane.showMessageDialog(null, "Entrada Inválida", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
				}
					
				else if(minus.isSelected()) {
					try{
                        float num1 = Float.parseFloat(input1.getText()); 
                        float num2 = Float.parseFloat(input2.getText());
                        String res = Float.toString(num1 - num2);
                        outputText.setText(res);
                    }catch(Exception e2){
                        JOptionPane.showMessageDialog(null, "Entrada Inválida", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
				}
				
				else if(div.isSelected()) {
					try{
                        float num1 = Float.parseFloat(input1.getText()); 
                        float num2 = Float.parseFloat(input2.getText());
                        if(num2 == 0){
                            JOptionPane.showMessageDialog(null, "Div Zero", "ERROR", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        String res = Float.toString(num1 / num2);
                        outputText.setText(res);
                    }catch(Exception e2){
                        JOptionPane.showMessageDialog(null, "Entrada Inválida", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
				}
				
				else if(mult.isSelected()) {
					try{
                        float num1 = Float.parseFloat(input1.getText()); 
                        float num2 = Float.parseFloat(input2.getText());
                        String res = Float.toString(num1 * num2);
                        outputText.setText(res);
                    }catch(Exception e2){
                        JOptionPane.showMessageDialog(null, "Entrada Inválida", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
				}
			}
		});
		
		outputPanel = new JPanel();
		outputText = new JLabel();
		outputText.setText("Resultado");
		outputText.setFont(new Font("Arial", 1, 30));
		outputText.setHorizontalAlignment(JLabel.CENTER);
		outputPanel.add(outputText, BorderLayout.CENTER);
		
		painel = new JPanel(new GridLayout(5,1));
		painel.add(inputPanel1);
		painel.add(inputPanel2);
		painel.add(buttonPanel);
		painel.add(equal);
		painel.add(outputPanel);
		
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setContentPane(painel);
	}
	
}
