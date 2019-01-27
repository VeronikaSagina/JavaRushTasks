package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HtmlView implements View {
    private Controller controller;
    private final String filePath = "4.JavaCollections/src/" + this.getClass().getPackage().getName().replaceAll("\\.", "/") + "/vacancies.html";

    @Override
    public void update(List<Vacancy> vacancies) {
        try {
            String updatedFileContent = getUpdatedFileContent(vacancies);
            assert updatedFileContent != null;
            updateFile(updatedFileContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        Document document;
        try {
            document = getDocument();
            Element template = document.getElementsByClass("template").first();
            Element clone = template.clone();
            clone.removeAttr("style");
            clone.removeClass("template");
            document.select("tr[class=vacancy]")
                    .remove()
                    .not("tr[class=vacancy template]");
            for (Vacancy vacancy : vacancies) {
                Element subClone = clone.clone();
                subClone.getElementsByClass("city").first().text(vacancy.getCity());
                subClone.getElementsByClass("companyName").first().text(vacancy.getCompanyName());
                subClone.getElementsByClass("salary").first().text(vacancy.getSalary());
                subClone.getElementsByTag("a").first()
                        .text(vacancy.getTitle())
                        .attr("href", vacancy.getUrl());
                template.before(subClone.outerHtml());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Some exception occurred";
        }
        return document.html();
    }

    private void updateFile(String string) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }
}
