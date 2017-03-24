public class Link {
	public String op;

	public Link next;
	
	
	public Link(String operator) {
		op = operator;
	}
	
	public String getOp(){
		return op;
	}
	
	public void show(){
		System.out.println(op +",");
	}
	
	
}


