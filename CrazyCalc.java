import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CrazyCalc extends JFrame{
	private JTextField output, postfix, inputBox;
	private JLabel inputL, postL, outL,snapshot, stackL, queueL, linkedL, arrayL;
	private JTextArea textArea;
	private JScrollPane scroll;
	private JTextField[] queue, stack, array, link;
	
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
		
		queue = new JTextField[8];
		array = new JTextField[5];
		stack = new JTextField[8];
		link = new JTextField[8];
		
		for(int i=0; i<8; i++){
			queue[i] = new JTextField(null);
			queue[i].setEditable(false);
			queue[i].setBackground(Color.PINK);
			queue[i].setBounds(90+((30*i)),140,30,30);
			add(queue[i]);
		}
		queueL = new JLabel("Queue");
		queueL.setBounds(192,172,50,20);
		add(queueL);
		
		for(int i=0; i<8; i++){
			stack[i] = new JTextField(null);
			stack[i].setEditable(false);
			stack[i].setBackground(Color.CYAN);
			stack[i].setBounds(40,350-((30*i)),30,30);
			add(stack[i]);
		}
		stackL = new JLabel("Stack");
		stackL.setBounds(37,382,50,20);
		add(stackL);
		
		
		for(int i=0; i<5; i++){
			array[i] = new JTextField(null);
			array[i].setEditable(false);
			array[i].setBackground(Color.WHITE);
			array[i].setBounds(90+((30*i)),245,30,30);
			add(array[i]);
		}
		arrayL = new JLabel("Array");
		arrayL.setBounds(150,277,50,20);
		add(arrayL);
		
		for(int i=0; i<8; i++){
			link[i] = new JTextField(null);
			link[i].setEditable(false);
			link[i].setBackground(Color.lightGray);
			link[i].setBounds(90+((30*i)),350,30,30);
			add(link[i]);
		}
		linkedL = new JLabel("Linked List");
		linkedL.setBounds(178,382,100,20);
		add(linkedL);
		
		
		
		//snapshot = new JLabel("Snapshots:");
		//snapshot.setBounds(10,100,70,20);
		//add(snapshot);
		
		
		//textArea = new JTextArea();
		//textArea.setEditable(false);
				
		//scroll = new JScrollPane(textArea);
		//scroll.setBounds(10,125,355,320);
		
		//add(scroll);
		
		inputBox.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						String input = e.getActionCommand();
						
						if(input.length() == 0)
							JOptionPane.showMessageDialog(null, "Input is empty.");
						
						else{
							//textArea.setText(null);
							PsdArray ps = new PsdArray(input, textArea);
							postfix.setText(ps.getPostfix());
							output.setText(ps.getAnswer());
						}
					}					
				}				
		);
		
		
		
		
	}
	
}
