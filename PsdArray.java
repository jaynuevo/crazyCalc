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
	JTextField[] quA, quB, st,ar,li;
	JTextField post, out, ch;
	int count=0;
	String in, temp="";
	String comp="";
	double a, b, answer=0;
	public PsdArray(String input, JTextField[] queueA, JTextField[] queueB, JTextField[] stack, JTextField[] array, JTextField[] link,  JTextField postfix,  JTextField output, JTextField c) {
		ch = c;
		quA = queueA;
		quB = queueB;
		st = stack;
		ar = array;
		li = link;
		post = postfix;
		out = output;
		
		in = input;
	}	
		public void run(){
		
		for( int i=0; i<in.length(); i++){
			temp = Character.toString(in.charAt(i));
			ch.setText(temp);
			

			 try {
		            Thread.sleep(500);
		        }
		        catch (InterruptedException ie) {
		            // Handle the exception
		        }
			
			////COMPARES IF OPERATOR
			if(temp.equals("*") || temp.equals("/") || temp.equals("+") || temp.equals("-")){
				
				if(!Character.toString(in.charAt(i-1)).equals(")"))
					buff += ",";
				
				if(first == null && firstB == null){
					pushToSecond(temp);
				}
				else if(temp.equals("+") || temp.equals("-")){
					
					////COMPARE FIRST LINK
					comp = first.getOp();

					if(comp.equals("+") || comp.equals("-")){
						buff = buff +comp;
						deleteFirst();
						pushToSecond(temp);
						dequeueFirst();		
					}	
					
					else if(comp.equals("*") || comp.equals("/")){
						
						if (buff != null && buff.length() > 0 && buff.charAt(buff.length()-1)==',') {
						      buff = buff.substring(0, buff.length()-1);
						    }
					
						
						buff = buff +comp;
						deleteFirst();
						pushToSecond(temp);
					
						if(first!=null){
							comp = first.getOp();
				
							if(comp.equals("+") || comp.equals("-")){
								buff = buff +comp;
								deleteFirstAgain(temp);
								dequeueFirst();
							}
						
							else {
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
						buff = buff +comp;
						deleteFirst();
						pushToSecond(temp);	
						dequeueFirst();
					}	
					
					else if(comp.equals("+") || comp.equals("-")){
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
			showLink();
			for(int j=0; j <8; j++){

				quB[j].setVisible(false);
				}
			
		}
		if(!buff.contains(","))
			buff += ",";
		
		buff = popAll(buff);
		post.setText(buff);
		
		////////////////////EVALUATION OF POSTFIX EXPRESSION///////////////////////////////
		for( int i=0; i<buff.length(); i++){
			temp = Character.toString(buff.charAt(i));
		
			ch.setText(temp);
			
			 try {
		            Thread.sleep(500);
		        }
		        catch (InterruptedException ie) {
		            // Handle the exception
		        }
			
			if(temp.equals("*") || temp.equals("/") || temp.equals("+") || temp.equals("-")){
				//push previous
				if(!buffB.equals("")){
					pushNumToSecond(buffB);
					dequeueFirst();
					first = swap(first, firstB);
					firstB = null;
					showLink();
					for(int j=0; j <8; j++){
						quB[j].setVisible(false);
						}
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
					
					 try {
				            Thread.sleep(500);
				        }
				        catch (InterruptedException ie) {
				            // Handle the exception
				        }
					
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
			}
			showLink();
			for(int j=0; j <8; j++){
				quB[j].setVisible(false);
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
			}
			ansString = Double.toString(answer);
		}
		
		quA[0].setVisible(false);

		ar[0].setText(null);
				
		st[0].setVisible(false);
				
		li[0].setVisible(false);
		
		ch.setText(null);
		
		 try {
	            Thread.sleep(500);
	        }
	        catch (InterruptedException ie) {
	            // Handle the exception
	        }
		
		
		
		
		out.setText(ansString);
		}
		
	
	
	public void pushToSecond (String op){
		Link l = new Link(op);
		l.next = firstB;
		firstB = l;
		set(op);
	
	}
	
	public void set(String op){

		 try {
	            Thread.sleep(500);
	        }
	        catch (InterruptedException ie) {
	            // Handle the exception
	        }
			
		if(counter<5){
		ar[counter].setText(op);
		ar[counter].setVisible(true);
		}		

		for(int i=0; i <8; i++){
			quB[i].setVisible(false);
		}
		showLinkB();
			
		st[counter].setText(op);
		st[counter].setVisible(true);
				
		li[counter].setText(op);
		li[counter].setVisible(true);
		counter++;
		
		 	
	}
	
	public void setNum(String op){

		 try {
	            Thread.sleep(500);
	        }
	        catch (InterruptedException ie) {
	            // Handle the exception
	        }
		
			
		if(counter<5){
		ar[counter].setText(op);
		ar[counter].setVisible(true);
		}		
		
		for(int i=0; i <8; i++){
		quB[i].setVisible(false);
		}
		
		showLinkB();
		
	
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
		setNum(op);
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
		
		 try {
	            Thread.sleep(500);
	        }
	        catch (InterruptedException ie) {
	            // Handle the exception
	        }
		showLinkB();
		for(int j=0; j <8; j++){
			//quA[i].setVisible(false);
			quA[j].setVisible(false);
			}
		 try {
	            Thread.sleep(500);
	        }
	        catch (InterruptedException ie) {
	            // Handle the exception
	        }
	
	}
	
	public Link swap(Link one, Link two){
		one = two;
		return one;
	}
	
	
	public void deleteFirst (){
		 try {
	            Thread.sleep(500);
	        }
	        catch (InterruptedException ie) {
	            // Handle the exception
	        }
	
		counter--;
		
		quA[0].setVisible(false);
		quA[1].setVisible(false);
		quA[2].setVisible(false);
		quA[3].setVisible(false);
		quA[4].setVisible(false);
		quA[5].setVisible(false);
		quA[6].setVisible(false);
		quA[7].setVisible(false);
		
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
	            Thread.sleep(500);
	        }
	        catch (InterruptedException ie) {
	            // Handle the exception
	        }
		 counter--;

			ar[counter].setText(null);
		
			st[counter].setVisible(false);
					
			li[counter].setVisible(false);
		 
		 counter--;
		 try {
	            Thread.sleep(500);
	        }
	        catch (InterruptedException ie) {
	            // Handle the exception
	        }

				
		ar[counter].setText(op);
		ar[counter].setVisible(true);
				
		st[counter].setText(op);
		st[counter].setVisible(true);
				
		li[counter].setText(op);
		li[counter].setVisible(true);
		
		counter++;
		Link temp = first;
		first = first.next;
	}
	
	public void deleteFirstEval (){
		
		 try {
	            Thread.sleep(500);
	        }
	        catch (InterruptedException ie) {
	            // Handle the exception
	        }
		
		
		
		
		counter--;
		
		quA[0].setVisible(false);
		quA[1].setVisible(false);
		quA[2].setVisible(false);
		quA[3].setVisible(false);
		quA[4].setVisible(false);
		
		if(counter<5)
		ar[counter].setText(null);
				
		st[counter].setVisible(false);
				
		li[counter].setVisible(false);
		
		Link temp = first;
		first = first.next;
		showLink();
	}
	
	
	public void showLink(){
		Link current = first;
		Link currentB = firstB;
		String s;
		int i=0;
		while (current!=null){
			stack+=current.getOp();
			s = current.getOp();
			quA[i].setText(s);
			quA[i].setVisible(true);
			i++;
			current.show();
			current = current.next;
			stack+= " ";
		}

	}	
	
	
	
	public void showLinkB(){
		Link current = first;
		Link currentB = firstB;
		String s;
		int i=0;
		while (currentB!=null){
			stack+=currentB.getOp();
			s = currentB.getOp();
			quB[i].setText(s);
			quB[i].setVisible(true);
			i++;
			currentB.show();
			currentB = currentB.next;
			stack+= " ";
		}

	}	
	
	
	public String popAll(String buff){
		Link current = first;
		String comp;
			
		while (current!=null){
			
			 try {
		            Thread.sleep(500);
		        }
		        catch (InterruptedException ie) {
		            // Handle the exception
		        }
			
			counter--;
			
			ar[counter].setText(null);
			quA[counter].setVisible(false);
			
			st[counter].setVisible(false);
					
			li[counter].setVisible(false);
			
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
