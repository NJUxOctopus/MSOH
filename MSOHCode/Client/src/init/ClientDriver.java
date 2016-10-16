package init;

import javax.swing.JFrame;

public class ClientDriver {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		frame.setVisible(true);
		
		frame.setBounds(200, 50, 1000, 800);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
