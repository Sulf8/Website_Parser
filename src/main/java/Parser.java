import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Parser {
    private static Document getDocument(String url) {
        Document doc = null;
        try {
            doc = Jsoup.parse(new URL(url), 5000);
            //doc =  Jsoup.connect(url).get();//в чём разница между этими двумя строками???
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    public static void main(String[] args) {
        Document document = getDocument("https://www.sports.ru/");
        Element mainNewsTable = document.select("ul[class=\"aside-news-list list-reset aside-news-block__list\"]").first();
        Elements footballNews = mainNewsTable.select("a[title^=\"Футбол\"]");//прописать действие при null
        footballNews.stream()
                .map(element -> {
                    String[] splitted = element.toString().split("\"");

                    String linkToString = Arrays.stream(splitted)
                            .filter(s-> s.contains("https://"))
                            .findFirst()
                            .orElse("Нет ссылки");

                    return "<a href=\"" + linkToString + "\">" + element.text() + "</a>";
                })
                .forEach(System.out::println);//заместо печати это надо класть в Kafka

    }
}
