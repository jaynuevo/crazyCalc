import java.util.Arrays;

import javax.swing.JOptionPane;

public class PsdArray{
	Link first = null;
	Link firstB = null;
	String buff = "", buffB = "", ansString ="";
	boolean mathEr=false;
	
	
	public PsdArray(String input) {
		int[] nums = new int[20];
		int num, count=0;
		String in, temp="";
		String comp="";
		double number, a, b, answer=0;
		
		in = input;
		
		for( int i=0; i<in.length(); i++){
			temp = Character.toString(in.charAt(i));
			
			////COMPARES IF OPERATOR
			if(temp.equals("*") || temp.equals("/") || temp.equals("+") || temp.equals("-")){
				if(!Character.toString(in.charAt(i-1)).equals(")"))
					buff += ",";
				
				if(first == null && firstB == null)
					pushToSecond(temp);
				
				else if(temp.equals("+") || temp.equals("-")){
					
					////COMPARE FIRST LINK
					comp = first.getOp();
					//System.out.println("TEMP " +temp);
					//System.out.println("AKO" +comp);
					
					if(comp.equals("+") || comp.equals("-")){
						buff = buff +comp;
						deleteFirst();
						pushToSecond(temp);
						dequeueFirst();
						//push(temp);			
					}	
					
					else if(comp.equals("*") || comp.equals("/")){
						
						if (buff != null && buff.length() > 0 && buff.charAt(buff.length()-1)==',') {
							System.out.println("HOHO");
						      buff = buff.substring(0, buff.length()-1);
						    }
						
						
						buff = buff +comp;
						deleteFirst();
						pushToSecond(temp);
					///////SHOULD HAVE SOMETHING NGA MACOMPARE PA HIT PREVIOUS LINK
				//		System.out.println(temp);
						
				//		if(first == null)
				//			push(temp);

						if(first!=null){
							comp = first.getOp();
						
							if(comp.equals("+") || comp.equals("-")){
								buff = buff +comp;
								deleteFirst();
								dequeueFirst();
								//pushToSecond(temp);
							}
						//	else if(comp.equals("(")){
								//pushToSecond(temp);	//System.out.println(temp);
							//	System.out.println("ASYA INI IT LINK ");
							//	showLink();
							//	deleteFirst();
							//	System.out.println(firstB.getOp());
								
								/////COMPARE AGAIN
							//	comp = first.getOp();
								///if(comp.equals("*") || comp.equals("/")){
								//	System.out.println("BEFORE");
								//	showLink();
								//	buff = buff +comp;
								//	deleteFirst();
								///	System.out.println(buff);
							//		//pushToSecond(temp);
							//		System.out.println("DIZ IZ LINK");
							//		showLink();
							//	}
							//	dequeueFirst();	
							//}
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
						buff = buff +comp;
						deleteFirst();
						pushToSecond(temp);	
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
				//System.out.println(buff);
				firstB = null;
				comp = first.getOp();
				//System.out.println(comp);
				showLink();
				while(true){
					comp = first.getOp();
					System.out.println("COMP IS: " +comp);
					if(comp.equals("(")){
						deleteFirst();
						break;
					}
					
					else{
						buff = buff +comp;
						System.out.println(buff);
						deleteFirst();
					}					
				}	
				//pushToSecond(temp);	
				if(firstB == null){
					firstB = first;
					System.out.println("I WAS EMPTY");
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
		}
		buff = popAll(buff);
		
		showLink();
		System.out.println("NUM ARRAY: " +Arrays.toString(nums));
		System.out.println(buff);

		
		
		////////////////////EVALUATION OF POSTFIX EXPRESSION///////////////////////////////
		for( int i=0; i<buff.length(); i++){
			temp = Character.toString(buff.charAt(i));
		
			if(temp.equals("*") || temp.equals("/") || temp.equals("+") || temp.equals("-")){
				//push previous
				System.out.println("PREVIOUS IS" +buffB);
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
					
					System.out.println("DIDI KAY " +a);
					System.out.println("DIDI KAY " +b);
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
					System.out.println("A is: " +a);
					System.out.println("B is: " +b);
					
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
		
			//System.out.println(buffB);
			
			
	
			
		
		
		}
		
		if(mathEr){
			ansString = "MATH ERROR";
		}
		
		else{
			answer = first.getNum();
			System.out.println("ANSWER IS: " +answer);
				
			ansString = Double.toString(answer);
		}
		
		
	}
	
	public void pushToSecond (String op){
		Link l = new Link(op);
		l.next = firstB;
		firstB = l;
	
	}
	
	public void pushNumToSecond (String op){
		System.out.println("ANSWER IS " +op);
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
