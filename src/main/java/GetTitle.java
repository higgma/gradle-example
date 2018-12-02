import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * A simple example, used on the jsoup website.
 */
public class GetTitle {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("http://hig.hatenablog.com/").get();
        System.out.println(doc.title());

        Elements newsHeadlines = doc.select(".entry-title a");
        for (Element headline : newsHeadlines) {
            String s = String.format("%s\n\t%s", headline.text(), headline.absUrl("href"));
            System.out.println(s);
        }
    }
}
