package crawler.zhang.output;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import java.util.List;

public class OutputMain {

	public static void output(List<String> list) {
		try {
			FileOutputStream fos = new FileOutputStream("output.csv", true);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
			BufferedWriter bw = new BufferedWriter(osw);
			for (int i = 0; i < list.size(); i++) {
				bw.write(list.get(i) + "\n");
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
