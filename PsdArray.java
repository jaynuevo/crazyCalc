import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

public class PsdArray extends Thread{
	Link first = null;
	Link firstB = null;
	String buff = "", buffB = "", ansString ="", stack="";
	boolean mathEr=false;
	//JTextArea ////snap;
	final int SIZE = 5;
	int counter=0;
	JTextField[] qu,st,ar,li;
	JTextField post, out;
	int count=0;
	String in, temp="";
	String comp="";
	double a, b, answer=0;
	public PsdArray(String input, JTextField[] queue, JTextField[] stack, JTextField[] array, JTextField[] link,  JTextField postfix,  JTextField output) {
		
		qu = queue;
		st = stack;
		ar = array;
		li = link;
		post = postfix;
		out = output;
		////snap = textarea;
		
		in = input;
		System.out.println("EXPRESSION\t\t\tQUEUE\t\t\tSTACK\t\t\tLINKED LIST\t\t\tCOMMITED");
	}	
		public void run(){
		
		for( int i=0; i<in.length(); i++){
			temp = Character.toString(in.charAt(i));
			//showLink();
			////COMPARES IF OPERATOR
			if(temp.equals("*") || temp.equals("/") || temp.equals("+") || temp.equals("-")){
				
				if(!Character.toString(in.charAt(i-1)).equals(")"))
					buff += ",";
				
				if(first == null && firstB == null){
					//System.out.println("NULL KAMI");
					pushToSecond(temp);
					//showLink();
				}
				else if(temp.equals("+") || temp.equals("-")){
					
					////COMPARE FIRST LINK
					comp = first.getOp();

					if(comp.equals("+") || comp.equals("-")){
						////snap.append("Current postfix expression: " +buff +"\n");
						////snap.append(" Operator being compared:  " +temp +"\n");
						////snap.append("                   Top of the stack:  " +comp +"\n");
						////snap.append("                                      TASK:  commit '" +comp +"' and push '" +temp +"' to stack"+"\n\n");
						buff = buff +comp;
						deleteFirst();
						pushToSecond(temp);
						//showLink();
						dequeueFirst();		
					}	
					
					else if(comp.equals("*") || comp.equals("/")){
						
						if (buff != null && buff.length() > 0 && buff.charAt(buff.length()-1)==',') {
						      buff = buff.substring(0, buff.length()-1);
						    }
						////snap.append("Current postfix expression: " +buff +"\n");
						////snap.append(" Operator being compared:  " +temp +"\n");
						////snap.append("                   Top of the stack:  " +comp +"\n");
						////snap.append("                                      TASK:  commit '" +comp +"' and push '" +temp +"' to stack"+"\n\n");
						
						buff = buff +comp;
						deleteFirst();
						pushToSecond(temp);
						//dequeueFirst();//////////////////////////////////////MADARA AN  5/(5*1)+1
						//showLink();
						
						if(first!=null){
							comp = first.getOp();
						//System.out.println("NULL AK TAS COMP KAY: " + comp);
							if(comp.equals("+") || comp.equals("-")){
								buff = buff +comp;
						//		showLink();
								deleteFirstAgain(temp);
								dequeueFirst();
								//pushToSecond(temp);
							}
						
							else {
								System.out.println("NULL AK TAS COMP KAY: " + comp);
								pushToSecond(temp);	
							//	showLink();
								dequeueFirst();	
							}
						}
						else
							dequeueFirst();
					}		
					else if(comp.equals("(")){
						pushToSecond(temp);
						dequeueFirst();	
					}
				}
				
				
				else if(temp.equals("*") || temp.equals("/")){
					////COMPARE FIRST LINK
					comp = first.getOp();
					
					if(comp.equals("*") || comp.equals("/")){
						////snap.append("Current postfix expression: " +buff +"\n");
						////snap.append(" Operator being compared:  " +temp +"\n");
						////snap.append("                   Top of the stack:  " +comp +"\n");
						////snap.append("                                      TASK:  commit '" +comp +"' and push '" +temp +"' to stack"+"\n\n");
						buff = buff +comp;
						deleteFirst();
						pushToSecond(temp);	
						dequeueFirst();
					//	showLink();
					}	
					
					else if(comp.equals("+") || comp.equals("-")){
						////snap.append("Current postfix expression: " +buff +"\n");
						////snap.append(" Operator being compared:  " +temp +"\n");
						////snap.append("                   Top of the stack:  " +comp +"\n");
						////snap.append("                                      TASK:  push " +temp +"\n\n");
						pushToSecond(temp);
					//	showLink();
						dequeueFirst();
						
					}	
					else if(comp.equals("(")){
						pushToSecond(temp);
						dequeueFirst();	
					}
					
				}
	
			}
			
			
			else if(temp.equals("(")){
				pushToSecond(temp);	
				dequeueFirst();
				
			}
			
			else if(temp.equals(")")){
				firstB = null;
				comp = first.getOp();
			
				while(true){
					comp = first.getOp();
		
					if(comp.equals("(")){
						deleteFirst();
						break;
					}
					
					else{
						buff = buff +comp;
						deleteFirst();
					}					
				}	
				//pushToSecond(temp);	
				if(firstB == null){
					firstB = first;

				}
				else if(first != null){
					dequeueFirst();
				}
			}
			
			
			////IF NOT, ADD TO POSTFIX EXP
			else{
				buff = buff +temp;
			}
			
			if(first == null){
				first = swap(first, firstB);
				
				firstB = null;
			}
			System.out.print(in.substring(i,in.length()) +"\t\t\t\t");
			showLink();
			
		//	System.out.print("\t\t\t" +new StringBuilder(stack).reverse().toString() +"\t\t\t");
		//	showLink();
		//	System.out.print("\t\t\t\t" +buff);
			
			//System.out.println("");
			//stack="";

			//////snap.append("Current postifx expression is: " +buff +"\n\n");
		
		//	else{
			//	ansString = "CANNOT PUSH MORE THAN 5 elements";
				//System.out.println("ARRAY IS FULL");
				//System.exit(1);
			//}
		}
		if(!buff.contains(","))
			buff += ",";
		buff = popAll(buff);
		//showLink();
		
		
		//System.out.print("\t\t\t" +new StringBuilder(stack).reverse().toString());
		System.out.print("\t\t\t\t\t\t\t\t\t\t\t" +buff);
		System.out.println("");
		
		//////snap.append("After popping all elements... \n");
		////snap.append("      FINAL POSTFIX EXPRESSION IS: " +buff +"\n\n");
		
		//showLink();
		System.out.println("YOMAN" +buff);
		System.out.println("COUNTER IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIS   " +counter);
		post.setText(buff);
		////////////////////EVALUATION OF POSTFIX EXPRESSION///////////////////////////////
		for( int i=0; i<buff.length(); i++){
			temp = Character.toString(buff.charAt(i));
		
			if(temp.equals("*") || temp.equals("/") || temp.equals("+") || temp.equals("-")){
				//push previous
				if(!buffB.equals("")){
					pushNumToSecond(buffB);
					dequeueFirst();
					first = swap(first, firstB);
					firstB = null;
					buffB = "";		
				}
				if(temp.equals("+")){
					//pop
					a = first.getNum();
					deleteFirstEval();
					b = first.getNum();
					deleteFirstEval();
					
					//evaluate
					answer = a+b;
					
					//convert
					buffB = Double.toString(answer);
					
					//push again
					pushAgain();
					
				}
				else if(temp.equals("-")){
					b = first.getNum();
					deleteFirstEval();
					a = first.getNum();
					deleteFirstEval();
					
					answer = a-b;
					
					buffB = Double.toString(answer);
					 
					pushAgain();
				}
				
				else if(temp.equals("*")){
					//pop
					a = first.getNum();
					deleteFirstEval();
					b = first.getNum();
					deleteFirstEval();
					
					//evaluate
					answer = a*b;
					
					//convert
					buffB = Double.toString(answer);
					
					//push again
					pushAgain();
					
				}
				
				else if(temp.equals("/")){
			
					b = first.getNum();
					deleteFirstEval();
					a = first.getNum();
					deleteFirstEval();
					
					if(b==0){
						mathEr = true;
						break;
					}
					
					
					answer = a/b;
			
					buffB = Double.toString(answer);
					
					pushAgain();
					
				}
				
				
			}
			
			else if(temp.equals(",")){
				pushNumToSecond(buffB);
				dequeueFirst();
				first = swap(first, firstB);
				firstB = null;
				buffB = "";		
			}
			
			else{
				buffB+= temp;
				
				//if(!buffB.contains("+") && !buffB.contains("-") && !buffB.contains("*") && !buffB.contains("/")){
				//	pushNumToSecond(buffB);
				//	first = swap(first, firstB);
				//	firstB = null;
				//}
			}

		}
		
		if(mathEr){
			ansString = "MATH ERROR";
		}
		
		else{
			try{
			answer = first.getNum();
			}
			catch(NullPointerException n){
				System.out.println("EMPTY");
			}
			ansString = Double.toString(answer);
		}
		out.setText(ansString);
		}
		
	
	
	public void pushToSecond (String op){
		//counter++;
		//showLink();
		Link l = new Link(op);
		l.next = firstB;
		//showLink();
		//System.out.println(op);
		firstB = l;
		//showLink();
		set(op);
		//qu[counter].setText(op);
		//qu[counter].setVisible(true);
	
	}
	
	public void set(String op){

		 try {
	            Thread.sleep(1000);
	        }
	        catch (InterruptedException ie) {
	            // Handle the exception
	        }
		
		//for(int i=4 ; i<=0; i--){
			
		//}
	//	qu[counter].setText(op);
	//	qu[counter].setVisible(true);
				
		if(counter<5){
		ar[counter].setText(op);
		ar[counter].setVisible(true);
		}		
		st[counter].setText(op);
		st[counter].setVisible(true);
				
		li[counter].setText(op);
		li[counter].setVisible(true);
		counter++;
			
	}
	
	
	
	
	public void pushNumToSecond (String op){
		Link l = new Link(op);
		l.setNum();
		l.next = firstB;
		firstB = l;
		set(op);
	}
	
	public void pushAgain(){
		pushNumToSecond(buffB);
		dequeueFirst();
		first = swap(first, firstB);
		firstB = null;
		buffB = "";	
	}
	
	
	public void dequeueFirst (){
		firstB.next = first;
		first = null;
	
	}
	
	public Link swap(Link one, Link two){
		one = two;
		return one;
	}
	
	
	public void deleteFirst (){
		 try {
	            Thread.sleep(1000);
	        }
	        catch (InterruptedException ie) {
	            // Handle the exception
	        }
		
		
		
		
		counter--;
		
		qu[0].setVisible(false);
		qu[1].setVisible(false);
		qu[2].setVisible(false);
		qu[3].setVisible(false);
		qu[4].setVisible(false);
		
		if(counter<5)
		ar[counter].setText(null);
				
		st[counter].setVisible(false);
				
		li[counter].setVisible(false);
		
		Link temp = first;
		first = first.next;
		showLink();
	}
	
	public void deleteFirstAgain(String op){
		 try {
	            Thread.sleep(1000);
	        }
	        catch (InterruptedException ie) {
	            // Handle the exception
	        }
		 counter--;
			//qu[counter].setVisible(false);
					
			
			ar[counter].setText(null);
		
			st[counter].setVisible(false);
					
			li[counter].setVisible(false);
		 
		 counter--;
		 try {
	            Thread.sleep(1000);
	        }
	        catch (InterruptedException ie) {
	            // Handle the exception
	        }
		 
		//qu[counter].setText(op);
		//qu[counter].setVisible(true);
				
		ar[counter].setText(op);
		ar[counter].setVisible(true);
				
		st[counter].setText(op);
		st[counter].setVisible(true);
				
		li[counter].setText(op);
		li[counter].setVisible(true);
		
		counter++;
		Link temp = first;
		first = first.next;
		showLink();
	}
	
	public void deleteFirstEval (){
		
		 try {
	            Thread.sleep(1000);
	        }
	        catch (InterruptedException ie) {
	            // Handle the exception
	        }
		
		
		
		
		counter--;
		
		//qu[counter].setVisible(false);
		
		if(counter<5)
		ar[counter].setText(null);
				
		st[counter].setVisible(false);
				
		li[counter].setVisible(false);
		
		
		
		Link temp = first;
		first = first.next;
	}
	
	
	public void showLink(){
		Link current = first;
		Link currentB = firstB;
		String s;
		int i=0;
		while (current!=null){
			stack+=current.getOp();
			s = current.getOp();
			qu[i].setText(s);
			qu[i].setVisible(true);
			i++;
			current.show();
			current = current.next;
			stack+= " ";
		}
		//System.out.println("END");
		
	//	while (currentB!=null){
	//		currentB.show();
	//		currentB = currentB.next;
	//	}
	}	
	
	public String popAll(String buff){
		Link current = first;
		String comp;
			
		while (current!=null){
			//current.show();
			
			 try {
		            Thread.sleep(1000);
		        }
		        catch (InterruptedException ie) {
		            // Handle the exception
		        }
			
			counter--;
			
			
			//qu[counter].setVisible(false);
			
			ar[counter].setText(null);
					
			st[counter].setVisible(false);
					
			li[counter].setVisible(false);
			
			comp = current.getOp();
			buff += comp;
			
			current = current.next;
		}
		//counter++;
		first = null;
		return buff;
	
	}
	public String getPostfix(){
		return buff;
	
	}
	
	public String getAnswer(){
		return ansString;
	
	}
	
}
