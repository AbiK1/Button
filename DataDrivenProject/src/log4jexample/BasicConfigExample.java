package log4jexample;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class BasicConfigExample {

	static Logger logger = Logger.getLogger(BasicConfigExample.class);
	public static void main(String[] args) {
	
		BasicConfigurator.configure();
		logger.debug("This is a debug message");
		logger.info("This is a info");
		logger.warn("This is a warning");
		logger.error("This is an error");
	}
}
