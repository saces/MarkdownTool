package de.saces.markdowntool;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	public static void main(String[] args) throws IOException {
		InputStream is = new FileInputStream(args[0]);
		StringBuilder sb = readUTF(is);
		is.close();
		String s = sb.toString();
		String res = findOne(s);
		System.out.println(res);
	}

	private static String findOne(String s) throws IOException {
		Matcher matcher = pat1.matcher(s);
		if (matcher.find()) {
			StringBuilder result = new StringBuilder();
	    	result.append(s.substring(0, matcher.start()));
	      result.append(process(matcher.group(3).trim()));
	      result.append(s.substring(matcher.end()));
	      return findOne(result.toString());
	    }
	    return s;
	}

	private static String process(String filename) throws IOException {
		InputStream is = new FileInputStream(filename);
		StringBuilder sb = readUTF(is);
		is.close();
		PegDownProcessor pdp = new PegDownProcessor();
		String result = pdp.markdownToHtml(sb.toString());
		return result;
	}

}
