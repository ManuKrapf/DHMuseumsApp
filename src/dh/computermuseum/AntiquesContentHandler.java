package dh.computermuseum;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class AntiquesContentHandler implements ContentHandler {
	private ArrayList<Antique> antiques = new ArrayList<Antique>();
	private String currentValue;
	private Antique antique;
	
	public List<Antique> getAntiques() {
        return antiques;
    }

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		currentValue = new String(ch, start, length);
	}

	// searching for main tag
	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		if (localName.equals("antique")) {
			antique = new Antique();
		}
	}

	// searching for tags and setting Antiques' values in the testfile.xml
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (localName.equals("name")) {
			antique.setName(currentValue);
		}
		if (localName.equals("id")) {
			antique.setId(Integer.parseInt(currentValue));
		}
		if (localName.equals("abstract")) {
			antique.setDescription(currentValue);
		}
		if (localName.equals("date")) {
			antique.setReleaseDate(currentValue);
		}
		if (localName.equals("prod")) {
			antique.setProducer(currentValue);
		}
		if (localName.equals("antique")) {
			antiques.add(antique);
		}
	}

	public void endDocument() throws SAXException {
	}

	public void endPrefixMapping(String arg0) throws SAXException {
	}

	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
	}

	public void processingInstruction(String target, String data)
			throws SAXException {
	}

	public void setDocumentLocator(Locator locator) {
	}

	public void skippedEntity(String name) throws SAXException {
	}

	public void startDocument() throws SAXException {
	}

	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
	}
}
