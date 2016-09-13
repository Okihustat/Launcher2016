package launcher;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {
	
	static Properties prop;
	static InputStream inStream ;
	
	static String readPropertyString(String key,String propertiesFileName){
		prop = new Properties();
		inStream = null;
		try{
			inStream = new BufferedInputStream(new FileInputStream(propertiesFileName));
			prop.load(inStream);
			return prop.getProperty(key);
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}finally{
			try {
                if(inStream != null){
                    inStream.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
		}
	}
	
	static boolean checkContainsKey(String key,String propertiesFileName){
		prop = new Properties();
		inStream = null;
		try{
			inStream = new BufferedInputStream(new FileInputStream(propertiesFileName));
			prop.load(inStream);
			return prop.containsKey(key);
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}finally{
			try {
                if(inStream != null){
                    inStream.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
		}
	}
	
	static String readConfigPropertyString(String key){
		return readPropertyString(key,"config.properties");
	}
	
	static boolean checkConfigContainsKey(String key){
		return checkContainsKey(key,"config.properties");
	}
	
	static String readConstantPropertyString(String key){
		return readPropertyString(key,"constantValue.properties");
	}
	
	static boolean checkConstantContainsKey(String key){
		return checkContainsKey(key,"constantValue.properties");
	}
	
	static int readConstantPropertyInteger(String key){
		return Integer.parseInt(readConstantPropertyString(key));
	}
	
	static double readConstantPropertyDouble(String key){
		return Double.parseDouble(readConstantPropertyString(key));
	}
	
	static Color readConstantPropertyColor(String key){
		return ColorCreator.createColorWithId(readConstantPropertyString(key));
	}
}
