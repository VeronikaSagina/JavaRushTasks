package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "http://hh.ru/search/vacancy?text=java+%s&page=%d";
    private int page_value = 0;

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> list = new ArrayList<>();
        try {
            while (true) {
                Document doc = getDocument(searchString, page_value);
                Elements elementsByAttribute = doc.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
                if (elementsByAttribute.size() == 0) {
                    page_value = 0;
                    break;
                }
                for (Element element : elementsByAttribute) {
                    Vacancy vacancy = new Vacancy();
                    vacancy.setTitle(element.getElementsByAttributeValueContaining("data-qa", "title")
                            .text());
                    vacancy.setCity(element.getElementsByAttributeValueContaining("data-qa", "address")
                            .text());
                    vacancy.setUrl(element.getElementsByAttributeValueContaining("data-qa", "title")
                            .attr("href"));
                    String salary = element.getElementsByAttributeValueContaining("data-qa", "compensation")
                            .text();
                    vacancy.setSalary(salary.length() == 0 ? "" : salary);
                    vacancy.setCompanyName(element.getElementsByAttributeValueContaining("data-qa", "employer")
                            .text());
                    vacancy.setSiteName("http://hh.ua");
                    list.add(vacancy);
                }
                page_value++;
            }
        } catch (IOException e) {
            //  e.printStackTrace();
        }
        return list;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        return Jsoup.connect(String.format(URL_FORMAT, searchString, page))
                .userAgent("Chrome/68.0.3440.106")
                .referrer("")//hh.ua
                .get();
    }
}
