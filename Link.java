public class Link {
	public String op;
	public double num;
	public Link next;
	String c,d;
	
	public Link(String string) {
		
			op = string;
		
	}
	
	public String getOp(){
		return op;
	}
	
	public double getNum(){
		return num;
	}
	
	public void setNum(){
		num = Double.parseDouble(op);
	}
	
	public void show(){
		System.out.println(op +",");
	}
	
	
}


