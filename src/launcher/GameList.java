package launcher;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JList;
import javax.swing.JPanel;

class GameList extends JPanel{
	@SuppressWarnings("rawtypes")
	static JList list;
	
	static Dimension listSize;
	static Dimension cellSize;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	GameList(String[] title){
		Dimension listSize = new Dimension(ReadProperties.readConstantPropertyInteger("LIST_SIZE_X"),ReadProperties.readConstantPropertyInteger("LIST_SIZE_Y"));
		Dimension cellSize = new Dimension(ReadProperties.readConstantPropertyInteger("CELL_SIZE_X"),ReadProperties.readConstantPropertyInteger("CELL_SIZE_Y"));;
		setBackground(ReadProperties.readConstantPropertyColor("LIST_FRAME_COLOR"));
		setPreferredSize(listSize);
		
	    list = new JList(title);
	    
	    list.setBackground(ReadProperties.readConstantPropertyColor("CELL_COLOR"));
	    list.setForeground(ReadProperties.readConstantPropertyColor("CELL_LABEL_COLOR"));
	    list.setSelectionForeground(ReadProperties.readConstantPropertyColor("CELL_SELECTION_LABEL_COLOR"));
	    list.setSelectionBackground(ReadProperties.readConstantPropertyColor("CELL_SELECTION_COLOR"));
	    
	    list.setFont(new Font(ReadProperties.readConstantPropertyString("LABEL_FONT"),Font.BOLD,ReadProperties.readConstantPropertyInteger("LIST_LABEL_SIZE")));
	    
	    list.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    
	    list.setFixedCellWidth(cellSize.width);
	    list.setFixedCellHeight(cellSize.height);
		
		add(list);
	}
	
	int getSelectedIndex(){
		int index =0;
		if(list.getSelectedIndex()!=-1){
			index = list.getSelectedIndex();
		}
		return index;
	}
}
