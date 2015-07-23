package de.saces.markdowntool;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;

public class MarkdownTool {

	// taken from node
	public static StringBuilder readUTF(InputStream stream) throws IOException {
	    StringBuilder result = new StringBuilder();
	    InputStreamReader reader = null;
	    try {
	        reader = new InputStreamReader(stream, "UTF-8");
	        char[] buf = new char[4096];
	        int length = 0;
	        while((length = reader.read(buf)) > 0) {
	            result.append(buf, 0, length);
	        }
	    } finally {
	        if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// ignore
				}
	        }
	    }
	    return result;
	}

	static Pattern pat1 = Pattern.compile("(.*)(<!--[ ]*MarkdownInclude:[ ]*)(.*)([ ]*-->)");

	static private void hello() {
		System.out.println("MarkdownTool ©2014-2015 saces@freenetproject.org");
	}

	static private void version() {
		System.out.println("Version: " + Version.longVersionString);
	}

	static private void usage() {
		System.out.println("A simple tool for generating html files from (github flavored) markdown.");
		System.out.println("    java -jar MarkdownTool.jar [option] template.html generated.html");
		System.out.println("Options:");
		System.out.println("    --help       show this help");
		System.out.println("    --version    show version info");
		System.out.println("    --no-loop    do not do recursive processing");
		System.out.println();
	}

	public static void main(String[] args) throws IOException {

		boolean noloop = false;
		boolean error = false;

		if (args.length < 2 && args.length > 3) {
			hello();
			usage();
			System.exit(1);
		}

		int argIndex = 0;
		if (!args[0].equals("-") && args[0].startsWith("--")) {
			argIndex++;
			switch (args[0]) {
				case "--help": hello(); usage(); System.exit(0); break;
				case "--version": hello(); version(); System.exit(0); break;
				case "--no-loop": noloop = true; break;
				default : System.err.println("Dont know what to do with '" + args[0] + "'."); hello(); usage(); System.exit(1);
			}
		}


		InputStream is;
		if (args[argIndex].equals("-")) {
			is = System.in;
		} else {
			is = new FileInputStream(args[argIndex]);
		}

		argIndex++;

		PrintStream os;
		if (args[argIndex].equals("-")) {
			os = System.out;
		} else {
			os = new PrintStream(new FileOutputStream(args[argIndex]));
		}

		StringBuilder sb = readUTF(is);
		is.close();
		String s = sb.toString();
		String res = findOne(s, noloop);
		os.println(res);
		os.close();
	}

	private static String findOne(String s, boolean dontrecurse) throws IOException {
		Matcher matcher = pat1.matcher(s);
		if (matcher.find()) {
			StringBuilder result = new StringBuilder();
	    	result.append(s.substring(0, matcher.start()));
	    	result.append(process(matcher.group(3).trim()));
	    	result.append(s.substring(matcher.end()));
	    	if (dontrecurse) {
	    		return result.toString();
	    	}
	    	return findOne(result.toString(), dontrecurse);
	    }
	    return s;
	}

	private static String process(String filename) throws IOException {
		InputStream is = new FileInputStream(filename);
		StringBuilder sb = readUTF(is);
		is.close();
		PegDownProcessor pdp = new PegDownProcessor(Extensions.ANCHORLINKS | Extensions.AUTOLINKS | Extensions.FENCED_CODE_BLOCKS | Extensions.STRIKETHROUGH | Extensions.TABLES);
		String result = pdp.markdownToHtml(sb.toString());
		return result;
	}

}
