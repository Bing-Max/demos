package com.example.esclidemojd.util;

import com.example.esclidemojd.entity.Content;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class HtmlParseUtil {

    public static List<Content> parseRequest(String keywords) throws IOException {
        ArrayList<Content> list = new ArrayList<>();
        String url = "https://search.jd.com/search?keyword="+keywords;

        Connection connect = Jsoup.connect(url);

        connect.header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36");
        Document document = connect.get();

        Element jGoodsList = document.getElementById("J_goodsList");
        Elements elements = jGoodsList.getElementsByTag("li");

        for (Element element : elements) {
            String img = element.getElementsByTag("img").eq(0).attr("data-lazy-img");
            String text = element.getElementsByClass("p-price").eq(0).text();
            String tittle = element.getElementsByClass("p-name").eq(0).text();

            list.add(new Content(img,text,tittle));

        }

        return list;
    }
}
