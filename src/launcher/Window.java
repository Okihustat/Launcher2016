package launcher;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;;

class Window extends JFrame implements ActionListener{
	
	static Timer time;
	
	JPanel p1;
	JPanel p2;
	
	GameList list;
	Image image;
	Explanation explanation;
	StartButton button;
	
	@SuppressWarnings("deprecation")
	Window(){
		p1 = new JPanel();
		p1.setPreferredSize(new Dimension(ReadProperties.readConstantPropertyInteger("LIST_SIZE_X"),this.size().height));
		p1.setBackground(ReadProperties.readConstantPropertyColor("WINDOW_LEFTPANEL_COLOR"));
		add(p1,BorderLayout.WEST);
		
		initGameList();
		initStartButton();
		
		p2 = new JPanel();
		p2.setBackground(ReadProperties.readConstantPropertyColor("WINDOW_RIGHTPANEL_COLOR"));
		add(p2,BorderLayout.CENTER);
		
		initImage();
		initExplanation();
		
		setSize(ReadProperties.readConstantPropertyInteger("FRAME_SIZE_X"),
				ReadProperties.readConstantPropertyInteger("FRAME_SIZE_Y"));
		setTitle(ReadProperties.readConstantPropertyString("TITLE"));
		setBackground(ReadProperties.readConstantPropertyColor("WINDOW_COLOR"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		time = new Timer(100,this);
        time.start();
	}
	
	private void initGameList(){
		list = new GameList(FileOperation.readGameTitle());
		p1.add(list,BorderLayout.NORTH);
		list.setVisible(true);
	}
	
	private void initImage(){
		int screenShotNum = ReadProperties.readConstantPropertyInteger("SCREENSHOT_NUM");
		image = new Image(FileOperation.readScreenShotFolderPath(screenShotNum));
		p2.add(image, BorderLayout.NORTH);
		image.setVisible(true);
	}
	
	private void initExplanation(){
		explanation = new Explanation(FileOperation.readExplanation());
		p2.add(explanation, BorderLayout.SOUTH);
		explanation.setVisible(true);
	}
	
	private void initStartButton(){
		button = new StartButton(FileOperation.readExeFilePath());
		p1.add(button, BorderLayout.SOUTH);
		button.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		Object source = e.getSource();
        if (source == time){   
        	if (isShowing()){   
        		image.changeImageWithSelectedIndex(list.getSelectedIndex());
        		explanation.setSelectedIndex(list.getSelectedIndex());
        		button.setSelectedIndex(list.getSelectedIndex());
            }
        }
	}
}
