import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CrazyCalc extends JFrame{
	private JTextField output, postfix, inputBox;
	private JLabel inputL, postL, outL;
	
	public CrazyCalc(){
		super("Crazy Calculator");
		setLayout(null);
		
		inputL = new JLabel("Input:");
		inputL.setBounds(10,10,50,20);
		add(inputL);
		
		inputBox = new JTextField("");
		inputBox.setEditable(true);
		inputBox.setBounds(60,10,300,20);
		add(inputBox);
		
		postL = new JLabel("Postfix:");
		postL.setBounds(10,40,50,20);
		add(postL);
		
		postfix = new JTextField("empty");
		postfix.setEditable(false);
		postfix.setBounds(60,40,300,20);
		add(postfix);
		
		outL = new JLabel("Result:");
		outL.setBounds(10,70,50,20);
		add(outL);
		
		output = new JTextField("0");
		output.setEditable(false);
		output.setBounds(60,70,300,20);
		add(output);
		
		inputBox.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						String input = e.getActionCommand();
						
						if(input.length() == 0)
							JOptionPane.showMessageDialog(null, "Input is empty.");
						
						else{
							new PsdArray(input);
							output.setText(input);
						}
						//send(e.getActionCommand(), link);
					}					
				}				
		);
		
		
		
		
	}
	
}
