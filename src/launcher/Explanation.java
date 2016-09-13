package launcher;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Explanation extends JPanel{
	static JTextArea textArea;
	static JScrollPane scrollPane;
	static String[] explanation;
	static Dimension textAreaSize;
	
	static int currentIndex;
	
	Explanation(String[] str){
        textArea = new JTextArea();
        
        textAreaSize = new Dimension();
		textAreaSize.width = ReadProperties.readConstantPropertyInteger("EXPLANATION_SIZE_X");
		textAreaSize.height = ReadProperties.readConstantPropertyInteger("EXPLANATION_SIZE_Y");
        
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setBackground(ReadProperties.readConstantPropertyColor("TEXTAREA_COLOR"));
        
        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(textAreaSize);
        add(scrollPane);
        
		explanation = str;
		
		currentIndex = 0;
		textArea.setText(explanation[0]);
		
		setBackground(ReadProperties.readConstantPropertyColor("TEXTAREA_FRAME_COLOR"));
		setSize(textAreaSize);
	}
	
	void setSelectedIndex(int i){
		if(currentIndex != i){
			currentIndex = i;
			textArea.setText(explanation[i]);
			textArea.setCaretPosition(0);
		}
	}
}
