package yose;

import static org.junit.Assert.*;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class HtmlDocumentTest {

    @Test
	public void testGetParsAnElement() throws Exception {
		String text = "<span>allo</span>";
		Element elem = HTMLDocument.toElement(text);
		
		assertNotNull(elem);
		assertEquals("allo", elem.getTextContent());
	}

  @Test
	public void testGetAccesElementByTag() throws Exception {
		String text = "<html><div><span id='allo'>allo</span><span id='bye'>salut</span></div></html>";
		Element elem = HTMLDocument.toElement(text);
		
		NodeList list = elem.getElementsByTagName("span");
		assertEquals(2, list.getLength());
	}

  @Test
	public void testCanAccesAnElementById() throws Exception {
		String text = "<html><div><span id='allo'>allo</span><span id='bye'>salut</span></div></html>";
		Document doc = HTMLDocument.from(text);
		
		Element elem = doc.getElementById("bye");
		assertEquals("salut", elem.getTextContent());
	}
}
