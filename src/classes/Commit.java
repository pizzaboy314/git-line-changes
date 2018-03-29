package classes;

import java.util.ArrayList;

public class Commit {
	
	private String hash;
	private String name;
	private String datetime;
	private String message;
	private ArrayList<FileChange> changedFiles;
	
	/**
	 * @param hash
	 * @param name
	 * @param datetime
	 * @param message
	 * @param changedFiles
	 */
	public Commit(String hash, String name, String datetime, String message) {
		this.hash = hash;
		this.name = name;
		this.datetime = datetime;
		this.message = message;
		this.changedFiles = new ArrayList<FileChange>();
	}
	
	public void addFileChange(FileChange fc) {
		changedFiles.add(fc);
	}
	
	public Integer totalFilesWithChanges() {
		return new Integer(changedFiles.size());
	}
	
	public Integer totalFilesWithAdditions() {
		int i = 0;
		for(FileChange fc: changedFiles) {
			if(fc.getAdditions() > 0 && fc.getDeletions() == 0) {
				i++;
			}
		}
		return new Integer(i);
	}
	
	public Integer totalFilesWithDeletions() {
		int i = 0;
		for(FileChange fc: changedFiles) {
			if(fc.getDeletions() > 0 && fc.getAdditions() == 0) {
				i++;
			}
		}
		return new Integer(i);
	}
	
	public Integer totalFileAdditions() {
		int i = 0;
		for(FileChange fc: changedFiles) {
			if(fc.getAdditions() > 0) {
				i = i + fc.getAdditions();
			}
		}
		return new Integer(i);
	}
	
	public Integer totalFileDeletions() {
		int i = 0;
		for(FileChange fc: changedFiles) {
			if(fc.getDeletions() > 0) {
				i = i + fc.getDeletions();
			}
		}
		return new Integer(i);
	}

	/**
	 * @return the hash
	 */
	public String getHash() {
		return hash;
	}

	/**
	 * @param hash the hash to set
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the datetime
	 */
	public String getDatetime() {
		return datetime;
	}

	/**
	 * @param datetime the datetime to set
	 */
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the changedFiles
	 */
	public ArrayList<FileChange> getChangedFiles() {
		return changedFiles;
	}

	/**
	 * @param changedFiles the changedFiles to set
	 */
	public void setChangedFiles(ArrayList<FileChange> changedFiles) {
		this.changedFiles = changedFiles;
	}

}
