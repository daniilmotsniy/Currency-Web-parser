package parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class Main {
    public static String html2text(String html) {
        return Jsoup.parse(html).text();
    }
    public static void main(String[] args){
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.google.com.ua/search?q=%D0%BA%D1%83%D1%80%D1%81+%D0%B4%D0%BE%D0%BB%D0%BB%D0%B0%D1%80%D0%B0&oq=rehc&aqs=chrome.3.69i57j0l5.2739j0j7&sourceid=chrome&ie=UTF-8").get();

        } catch (IOException e) {
            e.printStackTrace();
        }

        String text = doc.toString();
        String a;
        a=html2text(text);
        System.out.println(a.substring(772, 833));

    }
}
