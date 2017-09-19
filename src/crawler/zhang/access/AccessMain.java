package crawler.zhang.access;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class AccessMain {

	public static String access(String url) {
		BufferedReader in = null;
		int retry = 0;
		String result = "";
		while (true) {
			try {
				URL realUrl = new URL(url);
				URLConnection connection = realUrl.openConnection();
				in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				while ((line = in.readLine()) != null) {
					result += line + "\n";
				}
				if (result != "")
					break;

			} catch (Exception e) {
				if (retry > 3) {
					throw new RuntimeException("異常終了" + url + e);
				}
				retry++;
			} finally {
				try {
					if (in != null) {
						in.close();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return result;

	}

}
