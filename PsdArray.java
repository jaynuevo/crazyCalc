import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class PsdArray{
	Link first = null;
	Link firstB = null;
	String buff = "", buffB = "", ansString ="";
	boolean mathEr=false;
	JTextArea snap;
	
	public PsdArray(String input, JTextArea textarea) {
		int[] nums = new int[20];
		int num, count=0;
		String in, temp="";
		String comp="";
		double number, a, b, answer=0;
		snap = textarea;
		
		in = input;
		
		for( int i=0; i<in.length(); i++){
			temp = Character.toString(in.charAt(i));
			
			////COMPARES IF OPERATOR
			if(temp.equals("*") || temp.equals("/") || temp.equals("+") || temp.equals("-")){
				if(!Character.toString(in.charAt(i-1)).equals(")"))
					buff += ",";
				
				if(first == null && firstB == null){
					System.out.println("NULL KAMI");
					pushToSecond(temp);
				}
				else if(temp.equals("+") || temp.equals("-")){
					
					////COMPARE FIRST LINK
					comp = first.getOp();

					if(comp.equals("+") || comp.equals("-")){
						snap.append("Current postfix expression: " +buff +"\n");
						snap.append(" Operator being compared:  " +temp +"\n");
						snap.append("                   Top of the stack:  " +comp +"\n");
						snap.append("                                      TASK:  commit '" +comp +"' and push '" +temp +"' to stack"+"\n\n");
						buff = buff +comp;
						deleteFirst();
						pushToSecond(temp);
						dequeueFirst();
						//push(temp);			
					}	
					
					else if(comp.equals("*") || comp.equals("/")){
						
						if (buff != null && buff.length() > 0 && buff.charAt(buff.length()-1)==',') {
						      buff = buff.substring(0, buff.length()-1);
						    }
						snap.append("Current postfix expression: " +buff +"\n");
						snap.append(" Operator being compared:  " +temp +"\n");
						snap.append("                   Top of the stack:  " +comp +"\n");
						snap.append("                                      TASK:  commit '" +comp +"' and push '" +temp +"' to stack"+"\n\n");
						
						buff = buff +comp;
						deleteFirst();
						pushToSecond(temp);
						
						if(first!=null){
							comp = first.getOp();
						
							if(comp.equals("+") || comp.equals("-")){
								buff = buff +comp;
								deleteFirst();
								dequeueFirst();
								//pushToSecond(temp);
							}
						
							else {
								pushToSecond(temp);		
								dequeueFirst();	
							}
						}
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
						snap.append("Current postfix expression: " +buff +"\n");
						snap.append(" Operator being compared:  " +temp +"\n");
						snap.append("                   Top of the stack:  " +comp +"\n");
						snap.append("                                      TASK:  commit '" +comp +"' and push '" +temp +"' to stack"+"\n\n");
						buff = buff +comp;
						deleteFirst();
						pushToSecond(temp);	
					}	
					
					else if(comp.equals("+") || comp.equals("-")){
						snap.append("Current postfix expression: " +buff +"\n");
						snap.append(" Operator being compared:  " +temp +"\n");
						snap.append("                   Top of the stack:  " +comp +"\n");
						snap.append("                                      TASK:  push " +temp +"\n\n");
						pushToSecond(temp);		
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
				showLink();
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
					showLink();
				}
				else if(first != null){
					showLink();
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
			snap.append("Current postifx expression is: " +buff +"\n\n");
		}
		showLink();
		buff = popAll(buff);
		//showLink();
		snap.append("After popping all elements... \n");
		snap.append("      FINAL POSTFIX EXPRESSION IS: " +buff +"\n\n");
		
		showLink();
		
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
					deleteFirst();
					b = first.getNum();
					deleteFirst();
					
					//evaluate
					answer = a+b;
					
					//convert
					buffB = Double.toString(answer);
					
					//push again
					pushAgain();
					
				}
				else if(temp.equals("-")){
					b = first.getNum();
					deleteFirst();
					a = first.getNum();
					deleteFirst();
					
					answer = a-b;
					
					buffB = Double.toString(answer);
					 
					pushAgain();
				}
				
				if(temp.equals("*")){
					//pop
					a = first.getNum();
					deleteFirst();
					b = first.getNum();
					deleteFirst();
					
					//evaluate
					answer = a*b;
					
					//convert
					buffB = Double.toString(answer);
					
					//push again
					pushAgain();
					
				}
				
				else if(temp.equals("/")){
			
					b = first.getNum();
					deleteFirst();
					a = first.getNum();
					deleteFirst();
					
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
			
			else
				buffB+= temp;

		}
		
		if(mathEr){
			ansString = "MATH ERROR";
		}
		
		else{
			answer = first.getNum();
				
			ansString = Double.toString(answer);
		}
		
		
	}
	
	public void pushToSecond (String op){
		Link l = new Link(op);
		l.next = firstB;
		firstB = l;
	
	}
	
	public void pushNumToSecond (String op){
		Link l = new Link(op);
		l.setNum();
		l.next = firstB;
		firstB = l;
	
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
		Link temp = first;
		first = first.next;
	}
	
	
	public void showLink(){
	Link current = first;
	Link currentB = firstB;
	
		while (current!=null){
			current.show();
			current = current.next;
		}
		System.out.println("END");
		
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
			comp = current.getOp();
			buff += comp;
			
			current = current.next;
		}
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
