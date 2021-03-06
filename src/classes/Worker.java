package classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

public class Worker {
	
	public static ArrayList<Commit> commits;

	public static void main(String[] args) {
		commits = new ArrayList<Commit>();
		try {
			parseStatsFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(printC3ChartData());
	}

	public static String printC3ChartData() {
		StringBuilder dateTimeString = new StringBuilder();
		StringBuilder dateString = new StringBuilder();
		StringBuilder additionString = new StringBuilder();
		StringBuilder deletionString = new StringBuilder();
		StringBuilder numFilesChangedString = new StringBuilder();
		String delim = "";
		
		dateTimeString.append("['DateTime', ");
		dateString.append("['Date', ");
		additionString.append("['Total Additions', ");
		deletionString.append("['Total Deletions', ");
		numFilesChangedString.append("['Total Files Changed', ");
		
		for(Commit c : commits) {
			String[] dateTimeArr = c.getDatetime().split(" ");
			
			dateTimeString.append(delim + "'" + c.getDatetime() + "'");
			dateString.append(delim + "'" + dateTimeArr[0] + "'");
			additionString.append(delim + c.totalFileAdditions());
			deletionString.append(delim + c.totalFileDeletions());
			numFilesChangedString.append(delim + c.getChangedFiles().size());
			
			delim = ", ";
		}
		
		dateTimeString.append("]");
		dateString.append("]");
		additionString.append("]");
		deletionString.append("]");
		numFilesChangedString.append("]");
		
		return dateTimeString.toString() + "\n\n" + 
			dateString.toString() + "\n\n" + 
			additionString.toString() + "\n\n" + 
			deletionString.toString() + "\n\n" + 
			numFilesChangedString.toString();
	}

	/**
	 * @throws IOException 
	 */
	public static void parseStatsFile() throws IOException {
		File file = new File("stats.txt");
		ArrayList<Commit> commitsReversed = new ArrayList<Commit>();
		String commitHashPattern = "\\w\\w\\w\\w\\w\\w\\w$";
		
		BufferedReader in = new BufferedReader(new FileReader(file));

		String inputLine = in.readLine();
		while (inputLine != null) {
	    	if(inputLine.trim().equals("")) {
	    		inputLine = in.readLine();
	    	}
	    	
	    	if(Pattern.matches(commitHashPattern, inputLine)) {
	    		String hash = inputLine;
	    		String name = in.readLine();
	    		String datetime = in.readLine().replaceAll(":\\d\\d\\s-\\d\\d\\d\\d$", "");
	    		String message = in.readLine();
	    		Commit c = new Commit(hash, name, datetime, message);
	    		
	    		inputLine = in.readLine();
	    		inputLine = in.readLine();
	    		inputLine = in.readLine();
	    		
	    		ArrayList<FileChange> changedFiles = new ArrayList<FileChange>();
	    		boolean loop = true;
	    		while(loop == true) {
	    			if(inputLine == null || inputLine.trim().equals("") || Pattern.matches(commitHashPattern, inputLine)) {
	    				loop = false;
	    			} else {
	    				String[] arr = inputLine.split("\\t");
	    				String additions = (arr[0].equals("-")) ? "0" : arr[0];
	    				String deletions = (arr[1].equals("-")) ? "0" : arr[1];
	    				FileChange fc = new FileChange(Integer.parseInt(additions),Integer.parseInt(deletions),arr[2]);
	    				c.addFileChange(fc);
	    				inputLine = in.readLine();
	    			}
	    		}
	    		commitsReversed.add(c);
	    	}
	    	
	    	
	    	inputLine = in.readLine();
	    }
		in.close();
		Collections.reverse(commitsReversed);
		commits.addAll(commitsReversed);
	}

}
