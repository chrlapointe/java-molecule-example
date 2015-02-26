package yose;

import com.vtence.molecule.templating.JMustacheRenderer;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import static yose.HTMLDocument.toElement;

public class ViewRenderer {

    private final JMustacheRenderer renderer =
            new JMustacheRenderer().encoding("utf-8").extension("html").defaultValue("");

    private final String template;
    private Object context;

    private ViewRenderer(String template) {
        this.template = template;
    }

    public static ViewRenderer render(String template) {
        return new ViewRenderer(template);
    }

    public ViewRenderer from(File location) {
        renderer.fromDir(location);
        return this;
    }

    public ViewRenderer with(Object context) {
        this.context = context;
        return this;
    }

    public String asString() throws IOException {
        StringWriter buffer = new StringWriter();
        renderer.render(buffer, template, context);
        return buffer.toString();
    }

    public Element asDom() throws IOException, SAXException {
        return toElement(asString());
    }
}