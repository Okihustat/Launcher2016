package launcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.List;
import java.util.ArrayList;

public class FileOperation {
	
	static String[] readGameTitle(){
		List<String> list=new ArrayList<String>();
		int i=1;
		String key;
		while(ReadProperties.checkConfigContainsKey((key="GAMENAME_"+i))){
			list.add(new String(ReadProperties.readConfigPropertyString(key)));
			i++;
		}
		
		String[] title = (String[])list.toArray(new String[0]);
		return title;
	}

	static String[][] readScreenShotFolderPath(int screenshotNum){
		String[] title = readGameTitle();
		String[][] screenShot = new String[title.length][screenshotNum];
		
		for(int i=0;i<screenShot.length;i++){
			
			File dir = new File(ReadProperties.readConfigPropertyString("IMAGEFOLDERPATH_"+(i+1)));
			File[] file = dir.listFiles();
			
			for(int j = 0;j < screenShot[0].length;j++){
				screenShot[i][j] = file[j].getPath();
			}
		}
		return screenShot;
	}
	
	static String[] readExplanation(){
		String[] title = readGameTitle();
		String[] address = new String[title.length];
		
		for(int i = 0;i < address.length;i++){
			address[i] = ReadProperties.readConfigPropertyString("EXPLANATIONFILEPATH_"+(i+1));
		}
		return readTextFilePath(address);
	}
	
	static String[] readTextFilePath(String[] str){
		try{
			String[] fileText = new String[str.length];
			for(int i=0;i<str.length;i++){
				int size;
				char[] cbuf = new char[2];
		      
				InputStreamReader inReader = new InputStreamReader(new FileInputStream(str[i]),
																   ReadProperties.readConfigPropertyString("TEXTCHARACTERCODE_"+(i+1)));
				StringWriter sWriter = new StringWriter();
		      
				while( (size = inReader.read(cbuf)) != -1){
				  sWriter.write(cbuf, 0, size);
				}
		      
				fileText[i] = sWriter.toString();
		      
				inReader.close();
			}
			return fileText;
		      
		}catch(FileNotFoundException e){
			e.printStackTrace();
			return null;
		}catch(IOException e){
			e.printStackTrace();
		    return null;
		}
	}
	
	static String[] readExeFilePath(){
		String[] title = readGameTitle();
		String[] address = new String[title.length];
	
		for(int i=0;i<address.length;i++){
			String key = "GAMEFILEPATH_"+(i+1);
			File dir = new File(ReadProperties.readConfigPropertyString(key));
			
			address[i] = dir.getAbsolutePath();
		}
		return address;
	}
	
	static String readExeFolderPath(int i){
		String str = ReadProperties.readConfigPropertyString("GAMEFILEPATH_"+(i+1));
		int index = str.indexOf(ReadProperties.readConfigPropertyString("GAMEFILENAME_"+(i+1)));
		
		File dir = new File(str.substring(0,index));
			
		return dir.getAbsolutePath();
	}
	
	static String[] readExeFileName(){
		String[] title = readGameTitle();
		String[] address = new String[title.length];
		
		for(int i=0;i<address.length;i++){
			address[i] = ReadProperties.readConfigPropertyString("GAMEFILENAME_"+(i+1));
		}
		return address;
	}
}
