package launcher;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class StartButton extends JPanel implements ActionListener{
	
	static String[] address;
	static int currentIndex;
	static Dimension buttonSize;
	static Font buttonFont;
	JButton button;
	
	StartButton(String[] str){
		buttonSize = new Dimension();
		buttonSize.width = ReadProperties.readConstantPropertyInteger("BUTTON_SIZE_X");
		buttonSize.height = ReadProperties.readConstantPropertyInteger("BUTTON_SIZE_Y");
		
		buttonFont = new Font(ReadProperties.readConstantPropertyString("LABEL_FONT"),Font.BOLD,
							  ReadProperties.readConstantPropertyInteger("BUTTON_LABEL_SIZE"));
		
		button = new JButton();
		button.setText(ReadProperties.readConstantPropertyString("BUTTON_TEXT"));
		button.setForeground(ReadProperties.readConstantPropertyColor("BUTTON_LABEL_COLOR"));
		button.setFont(buttonFont);
	    button.setPreferredSize(buttonSize);
	    button.addActionListener(this);
	    add(button);
	    
	    setBackground(ReadProperties.readConstantPropertyColor("BUTTON_PANEL_COLOR"));
		setSize(buttonSize);
	    
	    currentIndex = 0;
	    address = str;
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == button) {
			String path = System.getProperty("user.dir");
	        try{
	        	String[] str = new String[]{path+"/"+ReadProperties.readConstantPropertyString("AUXILIIARY_NAME"),
											FileOperation.readExeFolderPath(currentIndex),
											FileOperation.readExeFileName()[currentIndex]};
	        	
	        	Process p = Runtime.getRuntime().exec(str);
	        	p.waitFor();
	        	p.destroy();
		   }catch(IOException e1){
			   	JOptionPane.showMessageDialog(this.getParent().getParent(),
			   								  e1.getLocalizedMessage(),
			   								  ReadProperties.readConstantPropertyString("ERROR_TEXT"),
			   								  JOptionPane.ERROR_MESSAGE);
			   	e1.printStackTrace();
		   }catch (InterruptedException e1) {
			   	JOptionPane.showMessageDialog(this.getParent().getParent(),
			   								  e1.getLocalizedMessage(),
			   								  ReadProperties.readConstantPropertyString("ERROR_TEXT"),
			   								  JOptionPane.ERROR_MESSAGE);
			   	e1.printStackTrace();
		   }
		}
	}
	
	void setSelectedIndex(int i){
		if(currentIndex != i){
			currentIndex = i;
		}
	}

}
