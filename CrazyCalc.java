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
	private JTextField output, postfix, inputBox, c;
	private JLabel inputL, postL, outL,snapshot, stackL, queueL, linkedL, arrayL, character;
	private JTextArea textArea;
	private JScrollPane scroll;
	JTextField[] queueA, queueB;
	private JTextField[] stack;
	private JTextField[] array;
	private JTextField[] link;
	
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
		
		queueA = new JTextField[8];
		queueB = new JTextField[8];
		array = new JTextField[5];
		stack = new JTextField[8];
		link = new JTextField[8];
		
		c= new JTextField(null);
		c.setEditable(false);
		c.setVisible(true);
		c.setBounds(155,408,30,30);
		c.setBackground(Color.GRAY);
		add(c);
		
		character = new JLabel("Character being compared");
		character.setBounds(100,441,160,20);
		add(character);
		
		for(int i=0; i<8; i++){
			queueA[i] = new JTextField(null);
			queueA[i].setEditable(false);
			queueA[i].setVisible(false);
			queueA[i].setBackground(Color.PINK);
			queueA[i].setBounds(90+((30*i)),125,30,30);
			add(queueA[i]);
		}
		
		
		for(int i=0; i<8; i++){
			queueB[i] = new JTextField(null);
			queueB[i].setEditable(false);
			queueB[i].setVisible(false);
			queueB[i].setBackground(Color.PINK);
			queueB[i].setBounds(90+((30*i)),155,30,30);
			add(queueB[i]);
		}
		queueL = new JLabel("Queue");
		queueL.setBounds(192,187,50,20);
		add(queueL);
		
		for(int i=0; i<8; i++){
			stack[i] = new JTextField(null);
			stack[i].setEditable(false);
			stack[i].setVisible(false);
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
			link[i].setVisible(false);
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
							int i;
							for ( i=0; i<input.length();i++)
							{
								if (input.charAt(i)=='1' || input.charAt(i)=='0' || input.charAt(i)=='2' || input.charAt(i)=='3' || input.charAt(i)=='4' || input.charAt(i)=='5' || input.charAt(i)=='6' || input.charAt(i)=='7' || input.charAt(i)=='8' || input.charAt(i)=='9' )
								{
									if(i==0 && input.length()>1)
									{
										if(input.charAt(i+1)=='(' || input.charAt(i+1)==')')
											break;
									}
									else if(i>0 && i<(input.length()-1))
									{
										if(input.charAt(i-1)==')')
											break;
										if(input.charAt(i+1)=='(')
											break;
									}
									else if(i==(input.length()-1))
									{
										if(input.charAt(i-1)=='(' || input.charAt(i-1)==')')
											break;
									}
									
								}
								else if(input.charAt(i)=='+' || input.charAt(i)=='-' || input.charAt(i)=='*' || input.charAt(i)=='/')
								{
									if(i==0)
									{
										break;
									}
									else if(i>0 && i<(input.length()-1))
									{
										if(input.charAt(i+1)=='+' || input.charAt(i+1)=='-' || input.charAt(i+1)=='*' || input.charAt(i+1)=='/' || input.charAt(i+1)==')')
											break;
										if(input.charAt(i-1)=='+' || input.charAt(i-1)=='-' || input.charAt(i-1)=='*' || input.charAt(i-1)=='/' || input.charAt(i-1)=='(')
											break;
									}
									else if(i==input.length()-1)
									{
										break;
									}
								}
								else if(input.charAt(i)=='(')
								{
									openCounter++;
									if(i==0 && input.length()>1)
									{
										if(input.charAt(i+1)=='+' || input.charAt(i+1)=='-' || input.charAt(i+1)=='*' || input.charAt(i+1)=='/' || input.charAt(i+1)==')')
											break;
									}
									else if(i>0 && i<(input.length()-1))
									{
										if(input.charAt(i+1)=='+' || input.charAt(i+1)=='-' || input.charAt(i+1)=='*' || input.charAt(i+1)=='/' || input.charAt(i+1)==')')
											break;
										if(input.charAt(i-1)==')' || input.charAt(i-1)=='1' || input.charAt(i-1)=='0' || input.charAt(i-1)=='2' || input.charAt(i-1)=='3' || input.charAt(i-1)=='4' || input.charAt(i-1)=='5' || input.charAt(i-1)=='6' || input.charAt(i-1)=='7' || input.charAt(i-1)=='8' || input.charAt(i-1)=='9')
											break;
									}
									//else if()
								}
								else if(input.charAt(i)==')')
								{
									closeCounter++;
									if(i==0)
									{
										break;
									}
									else if(i>0 && i<(input.length()-1))
									{
										if(input.charAt(i+1)=='(' ||input.charAt(i+1)=='1' || input.charAt(i+1)=='0' || input.charAt(i+1)=='2' || input.charAt(i+1)=='3' || input.charAt(i+1)=='4' || input.charAt(i+1)=='5' || input.charAt(i+1)=='6' || input.charAt(i+1)=='7' || input.charAt(i+1)=='8' || input.charAt(i+1)=='9')
											break;
										if(input.charAt(i-1)=='+' || input.charAt(i-1)=='-' || input.charAt(i-1)=='*' || input.charAt(i-1)=='/' || input.charAt(i-1)=='(')
											break;
									}
								}
								else
									break;
							}
							System.out.println("OpenCounter: "+openCounter +" CloseCounter: "+ closeCounter);
							if (i<input.length() || openCounter!=closeCounter)
							{
								JOptionPane.showMessageDialog(null, "Input is invalid.");
							}
							else
							{
								PsdArray ps = new PsdArray(input, queueA, queueB, stack, array, link, postfix, output, c);
								ps.start();
							}
							//postfix.setText(ps.getPostfix());
							//1+output.setText(ps.getAnswer());
						}
					}					
				}				
		);
		
		
		
		
	}
	
}
