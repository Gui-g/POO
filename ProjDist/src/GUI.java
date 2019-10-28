
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	JTextField input;
	JToggleButton leguas, milhas_maritimas, milhas,  km, metros, jardas, pes, polegadas, milimetros;
	ButtonGroup medidas;
	JPanel inputPanel, buttonPanel, outputPanel, painel;
	JLabel inputText, outputText;
	
	public GUI() {
		
		medidas = new ButtonGroup();
		
		inputPanel = new JPanel();
		inputText = new JLabel();
		inputText.setText("Distância: ");
		inputText.setFont(new Font("Arial", 1, 30));
		inputPanel.add(inputText, BorderLayout.EAST);
		
		input = new JTextField();
		input.setHorizontalAlignment(JTextField.CENTER);
		input.setFont(new Font("Arial", Font.BOLD, 50));
		input.setPreferredSize(new Dimension(500,100));
		inputPanel.add(input, BorderLayout.CENTER);
		
		buttonPanel = new JPanel(new GridLayout(3,3));
		
		leguas = new JToggleButton("Léguas");
		leguas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String output = String.valueOf(Float.parseFloat(input.getText()) / 6000);
				outputText.setText(output);
			}
		});
		buttonPanel.add(leguas);
		medidas.add(leguas);
		
		milhas_maritimas = new JToggleButton("Milhas Marítimas");
		milhas_maritimas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String output = String.valueOf(Float.parseFloat(input.getText()) / 1852);
				outputText.setText(output);
			}
		});
		buttonPanel.add(milhas_maritimas);
		medidas.add(milhas_maritimas);
		
		milhas = new JToggleButton("Milhas");
		milhas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String output = String.valueOf(Float.parseFloat(input.getText()) / 1609.3);
				outputText.setText(output);
			}
		});
		buttonPanel.add(milhas);
		medidas.add(milhas);
		
		km = new JToggleButton("KM");
		km.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String output = String.valueOf(Float.parseFloat(input.getText()) / 1000);
				outputText.setText(output);
			}
		});
		buttonPanel.add(km);
		medidas.add(km);
		
		metros = new JToggleButton("Metros");
		metros.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String output = String.valueOf(Float.parseFloat(input.getText()));
				outputText.setText(output);
			}
		});
		metros.setSelected(true);
		buttonPanel.add(metros);
		medidas.add(metros);
		
		jardas = new JToggleButton("Jardas");
		jardas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String output = String.valueOf(Float.parseFloat(input.getText()) / 0.914399);
				outputText.setText(output);
			}
		});
		buttonPanel.add(jardas);
		medidas.add(jardas);
		
		pes = new JToggleButton("Pés");
		pes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String output = String.valueOf(Float.parseFloat(input.getText()) / 0.304799);
				outputText.setText(output);
			}
		});
		buttonPanel.add(pes);
		medidas.add(pes);
		
		polegadas = new JToggleButton("Polegadas");
		polegadas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String output = String.valueOf(Float.parseFloat(input.getText()) / 0.0254);
				outputText.setText(output);
			}
		});
		buttonPanel.add(polegadas);
		medidas.add(polegadas);
		
		milimetros = new JToggleButton("Milimetros");
		milimetros.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String output = String.valueOf(Float.parseFloat(input.getText()) / 0.001);
				outputText.setText(output);
			}
		});
		buttonPanel.add(milimetros);
		medidas.add(milimetros);
		
		outputPanel = new JPanel();
		outputText = new JLabel();
		outputText.setFont(new Font("Arial", 1, 50));
		outputText.setHorizontalAlignment(JLabel.CENTER);
		outputPanel.add(outputText, BorderLayout.CENTER);
		
		painel = new JPanel(new GridLayout(3,1));
		painel.add(inputPanel, "North");
		painel.add(buttonPanel);
		painel.add(outputPanel, "South");
		
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setContentPane(painel);
	}

}
