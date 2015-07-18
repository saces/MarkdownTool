package de.saces.markdowntool;

/**
 * @author saces
 *
 */
public class Version {

	public static final String gitRevision = "@custom@";

	public static final long version = 1;

	public static final String longVersionString = "1 " + gitRevision;

	/**
	 * just prints the version info to standard out.
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Version: " + version);
		System.out.println("gitRevision: " + gitRevision);
		System.out.println("longVersion: " + longVersionString);
	}
}
