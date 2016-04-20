package yose.worlds;

import com.vtence.molecule.testing.http.HttpRequest;
import com.vtence.molecule.testing.http.HttpResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import yose.HTMLDocument;
import yose.YoseDriver;

import java.io.IOException;

import static com.vtence.molecule.testing.http.HttpResponseAssert.assertThat;
import static org.junit.Assert.*;

public class StartWorld {

    YoseDriver yose = new YoseDriver(9999);

    HttpRequest request = new HttpRequest(9999);
    HttpResponse response;

    @Before
    public void startGame() throws Exception {
        yose.start();
    }

    @After
    public void stopGame() throws Exception {
        yose.stop();
    }

    @Test
    public void firstWebPageChallenge() throws IOException {
        yose.home().displaysGreeting("Hello Yose");
    }

    @Test
    public void firstWebServiceChallenge() throws IOException {
        response = request.get("/ping");

        assertThat(response).isOK()
                            .hasContentType("application/json")
                            .hasBodyText("{\"alive\":true}");
    }
    
    
    // This test and the elementExist method should probably not be here. 
    // They are there to give an example
    @Test
    public void dom() throws IOException, SAXException {
        response = request.get("/");
        assertResponseContainsElementWithId(response, "greetings");
    }

	private void assertResponseContainsElementWithId(HttpResponse responseToTest, String id) throws IOException, SAXException {
		Document doc = HTMLDocument.from(responseToTest.bodyText());
        assertNotNull("element with id " + id + " does not exists", doc.getElementById(id));
	}

}
