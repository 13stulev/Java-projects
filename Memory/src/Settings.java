import java.io.IOException;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Settings extends JPanel {

	private static final long serialVersionUID = 4568652071397810155L;
	Client cli;
	JTextField widthField = new JTextField(5);
    JTextField heightField = new JTextField(5);
    private String errmsg;
    
	public Settings(Client cli) throws IOException, InterruptedException {

		this.cli = cli;
	      this.add(new JLabel("Введите ширину"));
	      this.add(widthField);
	      this.add(Box.createHorizontalStrut(15)); 
	      this.add(new JLabel("Введите ширину"));
	      this.add(heightField);
	      
	      int result = JOptionPane.showConfirmDialog(null, this, 
	               "Введите высоту и ширину поля", JOptionPane.OK_CANCEL_OPTION);
	      if (result == JOptionPane.OK_OPTION) {
	    	 try {
	    		 ClientMain.height = Integer.parseInt(heightField.getText());
		    	 ClientMain.width = Integer.parseInt(widthField.getText());
	    	 } catch(NumberFormatException e) {
	    		 JOptionPane.showMessageDialog(null, "Введены неверные значения");
	    		 Settings s = new Settings(cli);
	    		 return;
	    	 }
	    	if(validateField()) {
	    		System.out.println("x value: " + widthField.getText());
			    System.out.println("y value: " + heightField.getText());
			    cli.startNewGame(); 
	    	} else {
	    		JOptionPane.showMessageDialog(null, errmsg);
	    		Settings s = new Settings(cli);
	    	}
	    	
	      }
	}
	private boolean validateField() {
		if ((Integer.parseInt(heightField.getText()) * Integer.parseInt(widthField.getText())) % 2 != 0) {
	   		 errmsg = "Поле нечетно";
	   		 return false;
	   	 }
	   	 if ((Integer.parseInt(heightField.getText()) * Integer.parseInt(widthField.getText())) > 64) {
	   		 errmsg = "В поле больше 64 карт";
	   		 return false;
	   	 }
	   	 return true;
		}
	
}


