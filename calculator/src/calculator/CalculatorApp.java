package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

public class CalculatorApp {
	
	String resultStr = "";
	
	public static void changeFont(Component c, Font f) {
		c.setFont(f);
		if(c instanceof Container) {
			for(Component child : ((Container) c).getComponents()) {
				changeFont(child, f);
			}
		}
	}
	
	public static String calculate(String s) {
		int operand1 = 0;
		int operand2 = 0;
		char currentOperator = ' ';
		boolean storedOperand = false;
		
		if(!Character.isDigit(s.charAt(0))) {
			return "Syntax Error";
		}
		
		for(int i=0 ; i<s.length()-1 ; i++) {
			if(!Character.isDigit(s.charAt(i)) && !Character.isDigit(s.charAt(i+1)))
				return "Syntax Error";
		}
		
		for(char c : s.toCharArray()) {
			if(Character.isDigit(c) && storedOperand == false) {
				operand1 = operand1*10;
				operand1 += Character.getNumericValue(c);
				continue;
			}
			
			if(!Character.isDigit(c) && currentOperator == ' ') {
				storedOperand = true;
				currentOperator = c;
				continue;
			}
			
			if(Character.isDigit(c)) {
				operand2 = operand2*10;
				operand2 += Character.getNumericValue(c);
				continue;
			}
			
			switch(currentOperator) {
				case '+' :
					operand1 = operand1 + operand2;
					break;
				case '-' :
					operand1 = operand1 - operand2;
					break;
				case 'x' :
					operand1 = operand1 * operand2;
					break;
				case '/' :
					operand1 = operand1 / operand2;
					break;
				default:
					return "Syntax Error";
			}
			currentOperator = c;
			operand2 = 0;
		}
		
		switch(currentOperator) {
			case '+' :
				operand1 = operand1 + operand2;
				break;
			case '-' :
				operand1 = operand1 - operand2;
				break;
			case 'x' :
				operand1 = operand1 * operand2;
				break;
			case '/' :
				operand1 = operand1 / operand2;
				break;
			default:
				return "Syntax Error";
		}	
		return String.valueOf(operand1);
	}
	
	public CalculatorApp() {
		//result
		JTextArea resultTextArea = new JTextArea();
		resultTextArea.setBackground(new Color(255, 255, 255));
		resultTextArea.setEditable(false);
		resultTextArea.setFont(new Font("Serif", Font.PLAIN, 60));
		
		JScrollPane resultPanel = new JScrollPane(resultTextArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//numbers
		JPanel numbersPanel = new JPanel(new GridLayout(4, 3));
		
		for(int i=9 ; i>=0 ; i--) {
			for(int j=2 ; j>=0 ; j--) {
				if(i-j >= 0) {
					final Integer innerI = new Integer(i-j);
					JButton numberBtn = new JButton(String.valueOf(i-j));
					numberBtn.addMouseListener(new MouseListener() {
						
						@Override
						public void mouseReleased(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void mousePressed(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void mouseExited(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void mouseEntered(MouseEvent e) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void mouseClicked(MouseEvent e) {
							// TODO Auto-generated method stub
							resultStr += String.valueOf(innerI);
							resultTextArea.setText(resultStr);
						}
					});
					numbersPanel.add(numberBtn);
				}
			}
			i = i-2;
		}
		
		JButton clearBtn = new JButton("Clear");
		JButton equalBtn = new JButton("=");
		numbersPanel.add(clearBtn);
		numbersPanel.add(equalBtn);
		changeFont(numbersPanel, new Font("Serif", 0, 20));
		
		//operators
		JPanel operatorsPanel = new JPanel(new GridLayout(4, 1));
		
		JButton addBtn = new JButton("+");
		JButton subBtn = new JButton("-");
		JButton mulBtn = new JButton("x");
		JButton divBtn = new JButton("/");
		
		operatorsPanel.add(addBtn);
		operatorsPanel.add(subBtn);
		operatorsPanel.add(mulBtn);
		operatorsPanel.add(divBtn);
		changeFont(operatorsPanel, new Font("Serif", 0, 35));
		
		//main
		JPanel controlPanel = new JPanel(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.5;
		gbc.weighty = 1.0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		controlPanel.add(numbersPanel, gbc);
		
		gbc.weightx = 0.5;
		gbc.gridx = 1;
		controlPanel.add(operatorsPanel, gbc);
		
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(resultPanel, BorderLayout.NORTH);
		mainPanel.add(controlPanel, BorderLayout.CENTER);
		
		JFrame frame = new JFrame();
		frame.setTitle("Calculator");
		frame.setLayout(new BorderLayout());
		frame.add(mainPanel, BorderLayout.CENTER);
		frame.setSize(new Dimension(400, 600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		//action listener
		clearBtn.addActionListener((e) -> {
			resultStr = "";
			resultTextArea.setText(resultStr);
		});
		
		equalBtn.addActionListener((e) -> {
			resultStr = calculate(resultStr);
			resultTextArea.setText(resultStr);
		});
		
		addBtn.addActionListener((e) -> {
			resultStr += "+";
			resultTextArea.setText(resultStr);
		});
		
		subBtn.addActionListener((e) -> {
			resultStr += "-";
			resultTextArea.setText(resultStr);
		});
		
		mulBtn.addActionListener((e) -> {
			resultStr += "x";
			resultTextArea.setText(resultStr);
		});
		
		divBtn.addActionListener((e) -> {
			resultStr += "/";
			resultTextArea.setText(resultStr);
		});
	}
	
	public static void main (String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(() -> {new CalculatorApp();});
	}
}
