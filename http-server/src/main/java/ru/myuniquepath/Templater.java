package ru.myuniquepath;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class Templater {

    private static final String HTML_DIR = "templates";

    private static Templater templater;
    private final Configuration cfg;

    public static Templater instance() {
        if (templater == null)
            templater = new Templater();
        return templater;
    }

    public String getPage(String filename, Map<String, Object> pageVariables) {
        Writer stream = new StringWriter();
        try {
            Template template = cfg.getTemplate(HTML_DIR + File.separator + filename);
            template.process(pageVariables, stream);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return stream.toString();
    }

    private Templater() {
        cfg = new Configuration();
    }
}
