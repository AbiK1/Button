package log4jexample;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class PropertyConfigure {

	static Logger logger = Logger.getLogger(PropertyConfigure.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//PropertyConfigurator.configure("log4j.properties");
		
		PropertyConfigurator.configure("C:\\Users\\dvkar\\eclipse-workspace\\DataDrivenProject\\src\\log4jexample\\log4j.properties");
		System.out.println("enter");
		logger.debug("This is a debug message");
		logger.info("This is a info");
		logger.warn("This is a warning");
		logger.error("This is an error");
		logger.fatal("This is an fatal");
	}

}
