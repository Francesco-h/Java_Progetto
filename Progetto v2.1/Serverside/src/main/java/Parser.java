import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class Parser {

  public final static Document DOCUMENT = Parser.loadDocument("/configuration.xml");

  public static Document loadDocument(String fileName) {
    try (InputStream is = Parser.class.getResourceAsStream(fileName)) {
      if (is == null) {
        throw new IOException("File not found: " + fileName);
      }
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      return builder.parse(is);
    } catch(IOException | ParserConfigurationException | SAXException | DOMException e) {
      e.printStackTrace();
      throw new RuntimeException("Error loading document", e);
    }
  }

  public static String getString(String attributeName) {
    assert Parser.DOCUMENT != null;

    NodeList attributes = Parser.DOCUMENT.getElementsByTagName(attributeName);

    Element attribute = null;

    if(attributes != null && attributes.getLength() > 0) {
      attribute = (Element) attributes.item(0);
    }

    return (attribute != null ? attribute.getTextContent() : "");
  }
}
