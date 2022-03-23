import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;

public class Parser {
    private static Document getDocument(String url){
        Document doc = null;
        try {
            doc =  Jsoup.parse(new URL(url),3000);
            //doc =  Jsoup.connect(url).get();//в чём разница между этими двумя строками???
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    public static void main(String[] args) {
        System.out.println(getDocument("https://www.sports.ru/"));
    }
}
