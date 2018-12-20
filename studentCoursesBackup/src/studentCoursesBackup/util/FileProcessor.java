package studentCoursesBackup.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import studentCoursesBackup.util.MyLogger.DebugLevel;

public class FileProcessor {
	
	private FileReader fileReader;
	private BufferedReader bufferedReader;


	/**
	 * @param inputFileName
	 * @throws Exception
	 * @return FileProcessor
	 */
	public FileProcessor(String inputFileName) throws FileNotFoundException{
        try {
        	fileReader = new FileReader(inputFileName);		
    		bufferedReader =  new BufferedReader(fileReader);
			MyLogger.writeMessage("Initialized the input file processor constructor \n", DebugLevel.CONSTRUCTOR);
		} catch (FileNotFoundException e) {
			MyLogger.writeMessage("Error occured while opening the file"+ inputFileName, DebugLevel.FILE_PROCESSOR);
			MyLogger.writeMessage("Error occured while opening the file"+ inputFileName, DebugLevel.ERROR);
			MyLogger.writeMessage("Error occured while opening the file"+ inputFileName, DebugLevel.DEBUG);
			throw new FileNotFoundException("Error occured while opening the file"+ inputFileName);
		}finally {
			//nothing implemented
		}
	}

	
	/**
	 * @return
	 * @throws IOException
	 */
	public Object readLine(String inputFileName) throws IOException{
		String line = null;
		try {			
			line = bufferedReader.readLine();
			if(line == null) {
				bufferedReader.close();				
			}
		}catch (IOException e) {
			MyLogger.writeMessage("Error occured while reading the file"+inputFileName, DebugLevel.FILE_PROCESSOR);
			MyLogger.writeMessage("Error occured while reading the file"+inputFileName, DebugLevel.DEBUG);
			MyLogger.writeMessage("Error occured while reading the file"+inputFileName, DebugLevel.ERROR);
			throw new IOException("Error occured while reading the file"+inputFileName);
		}finally {
			//nothing implemented
		}
		return (Object)line;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * @return String
	 */
	@Override
	public String toString(){
		return "";
	}
	
}
