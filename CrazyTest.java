import javax.swing.JFrame;

public class CrazyTest {

	public static void main(String[] args){
		CrazyCalc c = new CrazyCalc();
		
		c.setVisible(true);
		c.setSize(400, 150);
		c.setLocationRelativeTo(null);
		c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setResizable(false);
	}
	
}
