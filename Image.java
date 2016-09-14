package launcher;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

class Image extends JPanel implements ActionListener {
	static ImageIcon[][] images;
	static JLabel label;
	static Timer time;
	static Dimension screenshotSize;
	static int screenshotNum;
	static double currentFrame;
	static double changeFrame;
	static int selectedIndex;
	
	Image(String[][] str){
		screenshotSize = new Dimension(ReadProperties.readConstantPropertyInteger("IMAGE_SIZE_X"),
										ReadProperties.readConstantPropertyInteger("IMAGE_SIZE_Y"));
		screenshotNum = ReadProperties.readConstantPropertyInteger("SCREENSHOT_NUM");
		
		setBackground(ReadProperties.readConstantPropertyColor("IMAGE_FRAME_COLOR"));
		setSize(screenshotSize);
		
		images = new ImageIcon[str.length][str[0].length];
		label = new JLabel();

		for(int i=0;i<images.length;i++){
			for(int j=0;j<images[0].length;j++){
				images[i][j] = new ImageIcon(str[i][j]);
			}
		}
		
		currentFrame = 0;
		changeFrame = ReadProperties.readConstantPropertyDouble("IMAGE_CHANGE_FRAME");
		
		time = new Timer(100, this);
        time.start();
        
		label.setIcon(new ImageIcon(images[0][0].getImage().getScaledInstance(screenshotSize.width,screenshotSize.height, 0)));
		label.setSize(screenshotSize);
		
		add(label);
	}
	
	public void actionPerformed(ActionEvent e){
		Object source = e.getSource();
        if (source == time){   
        	if (isShowing()){   
        		currentFrame = (currentFrame+changeFrame)%screenshotNum;
        		label.setIcon(new ImageIcon(images[selectedIndex][(int)currentFrame].getImage().getScaledInstance(screenshotSize.width,screenshotSize.height,0)));
                repaint();
            }
        }
	}
	
	void changeImageWithSelectedIndex(int i){
		selectedIndex = i;
	}
}
