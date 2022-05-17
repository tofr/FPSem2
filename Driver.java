import javax.swing.*;

public class Driver extends JFrame{

	DriverRunner panel;
	
	Driver(){
		panel = new DriverRunner();
		this.add(panel);
		this.setTitle("Apple Test");
		this.setResizable(true);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
    public static void main(String[] args) {
        Driver driver = new Driver();
    }
}