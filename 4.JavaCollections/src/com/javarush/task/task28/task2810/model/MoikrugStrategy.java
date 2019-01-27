package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoikrugStrategy implements Strategy {
    //private static final String URL_FORMAT = "https://moikrug.ru/vacancies?page=%d&q=java+%s";
    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%d";

    int page_value = 0;

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> listV = new ArrayList<>();
        try {
            while (true) {
                Document document = getDocument(searchString, page_value);
                Elements elementsByAttribute = document.getElementsByClass("job");
                if (elementsByAttribute.size() == 0) {
                    page_value = 0;
                    break;
                }
                for (Element element : elementsByAttribute) {
                    Vacancy vacancy = new Vacancy();
                    vacancy.setTitle(element.select("div.title").first()
                            .getElementsByAttribute("title").text());
                    String city = element.select("span.location > a").text();
                    vacancy.setCity(city.length() == 0 ? "" : city);
                    String salary = element.select("div.salary").text();
                    vacancy.setSalary(salary.length() == 0 ? "" : salary);
                   // vacancy.setCompanyName(element.select("div.company_name > a").text());
                   // vacancy.setUrl(element.select("div.inner > a").first().attr("href"));
                    vacancy.setCompanyName(element.getElementsByAttributeValue("class", "company_name").text());
                    vacancy.setUrl("https://moikrug.ru" + element.select("a[class=job_icon]").attr("href"));
                    vacancy.setSiteName("https://moikrug.ru");
                    listV.add(vacancy);
                }
                page_value++;
            }
        } catch (IOException e) {
            //
        }
        return listV;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        return Jsoup.connect(String.format(URL_FORMAT,searchString, page))
                .userAgent("Chrome/68.0.3440.106")
                .referrer("")//hh.ua
                .get();
    }
}
