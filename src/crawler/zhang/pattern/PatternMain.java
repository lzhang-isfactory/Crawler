package crawler.zhang.pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMain {

	public static List<String> patternlist(String compile, String text) {
		List<String> list = new ArrayList<String>();
		Pattern pattern = Pattern.compile(compile);
		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			list.add(matcher.group(0));
		}
		return list;
	}

	public static String patternString(String compile, String text) {
		String str = "";
		Pattern pattern = Pattern.compile(compile);
		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			str += matcher.group(0);
		}

		return str.replace(",", "");
	}

}
