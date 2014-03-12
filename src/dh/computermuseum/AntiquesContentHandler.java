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
	private String type;
	private boolean element;
	
	public List<Antique> getAntiques() {
        return antiques;
    }

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if(element) {
			currentValue = new String(ch, start, length);
			element = false;
		}
	}

	// searching for main tag
	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		
		element = true;
		
		if (localName.equals("computer")) {
			type = "computer";
		}
		
		if (localName.equals("components")) {
			type = "components";
		}
		
		if (localName.equals("storages")) {
			type = "storages";
		}
		
		if (localName.equals("item")) {
			antique = new Antique();
			antique.setType(type);
		}
	}

	// searching for tags and setting Antiques' values in the testfile.xml
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		
		element = false;
		if(type.equalsIgnoreCase("computer")) {
			if (localName.equals("name")) {
				antique.setName(currentValue);
			}
			if (localName.equals("id")) {
				antique.setId(Integer.parseInt(currentValue));
			}
			if (localName.equals("note")) {
				antique.setDescription(currentValue);
			}
			if (localName.equals("date")) {
				antique.setReleaseDate(currentValue);
			}
			if (localName.equals("comp")) {
				antique.setProducer(currentValue);
			}
			if (localName.equals("os")) {
				antique.setOs(currentValue);
			}
			if (localName.equals("memory")) {
				antique.setMemory(currentValue);
			}
			if (localName.equals("ram")) {
				antique.setRam(currentValue);
			}
			if (localName.equals("processor")) {
				antique.setCpu(currentValue);
			}
			if (localName.equals("special")) {
				antique.setSpecial(currentValue);
			}
			if (localName.equals("img")) {
				antique.setImg(currentValue);
			}
			if (localName.equals("item")) {
				antiques.add(antique);
			}
		}
		if(type.equalsIgnoreCase("components")) {
			if (localName.equals("name")) {
				antique.setName(currentValue);
			}
			if (localName.equals("id")) {
				antique.setName(currentValue);
			}
			if (localName.equals("date")) {
				antique.setName(currentValue);
			}
			if (localName.equals("comp")) {
				antique.setName(currentValue);
			}
			if (localName.equals("special")) {
				antique.setName(currentValue);
			}
			if (localName.equals("note")) {
				antique.setName(currentValue);
			}
			if (localName.equals("item")) {
				antiques.add(antique);
			}
		}
		if(type.equalsIgnoreCase("storages")) {
			if (localName.equals("name")) {
				antique.setName(currentValue);
			}
			if (localName.equals("id")) {
				antique.setName(currentValue);
			}
			if (localName.equals("date")) {
				antique.setName(currentValue);
			}
			if (localName.equals("type")) {
				antique.setName(currentValue);
			}
			if (localName.equals("capacity")) {
				antique.setName(currentValue);
			}
			if (localName.equals("material")) {
				antique.setName(currentValue);
			}
			if (localName.equals("endurance")) {
				antique.setName(currentValue);
			}
			if (localName.equals("size")) {
				antique.setName(currentValue);
			}
			if (localName.equals("developer")) {
				antique.setName(currentValue);
			}
			if (localName.equals("rw-speed")) {
				antique.setName(currentValue);
			}
			if (localName.equals("item")) {
				antiques.add(antique);
			}
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
		element = false;
	}

	@Override
	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
	
	}
}