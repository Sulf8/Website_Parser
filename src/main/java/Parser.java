import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class Parser {
    private static Document getDocument(String url) {
        Document doc = null;
        try {
            doc = Jsoup.parse(new URL(url), 3000);
            //doc =  Jsoup.connect(url).get();//в чём разница между этими двумя строками???
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    public static void main(String[] args) {
        Document document = getDocument("https://www.sports.ru/");
        Element mainNewsTable = document.select("ul[class=\"aside-news-list list-reset aside-news-block__list\"]").first();
        Elements footballNews = mainNewsTable.select("a[title^=\"Футбол\"]");

        //System.out.println(document);
        //System.out.println(footballNews);
    }
}
