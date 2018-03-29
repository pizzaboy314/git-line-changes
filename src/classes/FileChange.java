package classes;

public class FileChange {
	

	private Integer additions;
	private Integer deletions;
	private String filepath;
	
	public FileChange(Integer additions, Integer deletions, String filepath) {
		this.additions = additions;
		this.deletions = deletions;
		this.filepath = filepath;
	}

	/**
	 * @return the additions
	 */
	public Integer getAdditions() {
		return additions;
	}

	/**
	 * @param additions the additions to set
	 */
	public void setAdditions(Integer additions) {
		this.additions = additions;
	}

	/**
	 * @return the deletions
	 */
	public Integer getDeletions() {
		return deletions;
	}

	/**
	 * @param deletions the deletions to set
	 */
	public void setDeletions(Integer deletions) {
		this.deletions = deletions;
	}

	/**
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}

	/**
	 * @param filepath the filepath to set
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

}
