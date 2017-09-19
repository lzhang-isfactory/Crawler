package crawler.zhang.replace;

public class ReplaceMain {

	public static String replacezwmc(String data) {
		data = data.replace(">", "");
		data = data.replace("</a", "");
		return data;
	}

	public static String replacegsmc(String data) {
		data = data.replaceAll("<td class=\"gsmc\">.* target=\"_blank\">", "");
		data = data.replaceAll("</a>", "");
		data = data.replaceAll("<a.*>$", "");

		return data;
	}

	public static String replacezwyx(String data) {
		data = data.replaceAll("<td class=\"zwyx\">", "");
		data = data.replaceAll("</td>", "");
		return data;

	}

	public static String replacegzdd(String data) {
		data = data.replaceAll("td class=\"gzdd\">", "");
		data = data.replaceAll("</td>", "");
		return data;

	}

	public static String replacegxsj(String data) {
		data = data.replaceAll("<td class=\"gxsj\"><span>", "");
		data = data.replaceAll("</tspan>", "");
		return data;

	}

	public static String replacegshy(String data) {
		data = data.replaceAll("<span>", "");
		data = data.replaceAll("</span>", "");
		return data;
	}
}
