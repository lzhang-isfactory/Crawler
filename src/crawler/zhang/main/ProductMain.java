package crawler.zhang.main;

import java.util.ArrayList;
import java.util.List;

import crawler.zhang.access.AccessMain;
import crawler.zhang.output.OutputMain;
import crawler.zhang.pattern.PatternMain;
import crawler.zhang.replace.ReplaceMain;

public class ProductMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("begin");
		String url = "http://sou.zhaopin.com/jobs/searchresult.ashx?jl=%E4%B8%8A%E6%B5%B7&sm=0&p=1";
		List<String> list = new ArrayList<String>();
		List<String> resultlist = new ArrayList<String>();
		do {
			String regx = "<td class=\"zwmc\"[\\s\\S]*?</tr>";
			String result = AccessMain.access(url);
			list = PatternMain.patternlist(regx, result);
			for (int i = 0; i < list.size(); i++) {
				String gzurl = PatternMain.patternString("[a-zA-z]+://company[^\\s]*", list.get(i));
				String gshy = null;
				if (!gzurl.equals("")) {
					gzurl = gzurl.replace("\n", "");
					String[] gzurllist = gzurl.split("\"");
					String companypage = AccessMain.access(gzurllist[0].replace("\n", gzurllist[0]));
					gshy = PatternMain.patternString(">公司行业[\\s\\S]*?</tr>", companypage);
					gshy = PatternMain.patternString("<span>.*</span>", gshy);
					gshy = ReplaceMain.replacegshy(gshy);
				}
				String zwmc = PatternMain.patternString(">.*</a>\n", list.get(i));
				zwmc = ReplaceMain.replacezwmc(zwmc);
				zwmc = zwmc.replace("\n", "");
				String gsmc = PatternMain.patternString("<td class=\"gsmc\".*</a>", list.get(i));
				gsmc = ReplaceMain.replacegsmc(gsmc);
				gsmc = gsmc.replace("\n", "");
				String zwyx = PatternMain.patternString("<td class=\"zwyx\">.*</td>", list.get(i));
				zwyx = PatternMain.patternString("[\u4e00-\u9fa5]{2,}|[1-9]\\d*-[1-9]\\d*", zwyx);
				zwyx = zwyx.replace("\n", "");
				String gzdd = PatternMain.patternString("<td class=\"gzdd\">.*</td>", list.get(i));
				gzdd = PatternMain.patternString("[\u4e00-\u9fa5]{2,}|[1-9]\\d*-[1-9]\\d*", gzdd);
				gzdd = gzdd.replace("\n", "");
				String gxsj = PatternMain.patternString("<td class=\"gxsj\">.*</span>", list.get(i));
				gxsj = PatternMain.patternString("[\u4e00-\u9fa5]{2,}|[1-9]\\d*-[1-9]\\d*", gxsj);
				gxsj = gxsj.replace("\n", "");
				resultlist.add(zwmc + "," + gsmc + "," + zwyx + "," + gzdd + "," + "," + gxsj + "," + gshy);
			}
			OutputMain.output(resultlist);
			resultlist.clear();
			url = null;
			String nextPage = PatternMain.patternString("<li class=\"pagesDown-pos\">((?:.|\r|\n)*?)</li>", result);
			url = PatternMain.patternString("[a-zA-z]+://[^\\s]*", nextPage);
			if (url != null)
				url = url.replace("\"", "");
			System.out.println(url);
		} while (url != null && !url.equals(""));

		System.out.println("Finish Normal.");
	}
}
