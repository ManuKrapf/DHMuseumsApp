package dh.computermuseum;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class AntiquesContentHandler implements ContentHandler {
	
	//private ArrayList<Antique> antiques = new ArrayList<Antique>();
	Data data;
	private String currentValue;
	private Antique antique;
	private Computer computer;
	private Component component;
	private Storage storage;
	private InnerlifeComponent inner;
	private String type;
	private int typeid;
	private boolean element;
	
	public AntiquesContentHandler(Data d) {
		data = d;
		type = "";
	}
	
	/*
	public List<Antique> getAntiques() {
        return antiques;
    }*/

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if(element) {
			currentValue = new String(ch, start, length);
			element = false;
		}
	}

	// searching for main tag
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		
		element = true;
		
		if (localName.equals("computer")) {
			type = "computer";
			typeid = 1;
		}
		
		if (localName.equals("components")) {
			type = "components";
			typeid = 2;
		}
		
		if (localName.equals("storages")) {
			type = "storages";
			typeid = 3;
		}
		if(localName.equals("innerlifecomponents")) {
			type = "innerlifecomponents";
			typeid = 4;
		}
		
		if (localName.equals("item")) {
			switch(typeid) {
				case 1: computer = new Computer();
					break;
				case 2: component = new Component();
					break;
				case 3: storage = new Storage();
					break;
				case 4: inner = new InnerlifeComponent();
					break;
				default: antique = new Antique("unknown");
			}
		}
	}

	// searching for tags and setting Antiques' values in the testfile.xml
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		
		element = false;
		if(type.equalsIgnoreCase("computer")) {
			if (localName.equals("name")) {
				computer.setName(currentValue);
			}
			if (localName.equals("id")) {
				computer.setId(Integer.parseInt(currentValue));
			}
			if (localName.equals("note")) {
				computer.setDescription(currentValue);
			}
			if (localName.equals("date")) {
				computer.setReleaseDate(currentValue);
			}
			if (localName.equals("comp")) {
				computer.setProducer(currentValue);
			}
			if (localName.equals("os")) {
				computer.setOs(currentValue);
			}
			if (localName.equals("memory")) {
				computer.setMemory(currentValue);
			}
			if (localName.equals("ram")) {
				computer.setRam(currentValue);
			}
			if (localName.equals("processor")) {
				computer.setCpu(currentValue);
			}
			if (localName.equals("special")) {
				computer.setSpecial(currentValue);
			}
			if (localName.equals("img")) {
				computer.setImg(currentValue);
			}
			if (localName.equals("item")) {
				data.addComupter(computer);
			}
		}
		if(type.equalsIgnoreCase("components")) {
			if (localName.equals("name")) {
				component.setName(currentValue);
			}
			if (localName.equals("id")) {
				component.setId(Integer.parseInt(currentValue));
			}
			if (localName.equals("date")) {
				component.setReleaseDate(currentValue);
			}
			if (localName.equals("comp")) {
				component.setProducer(currentValue);
			}
			if (localName.equals("special")) {
				component.setSpecial(currentValue);
			}
			if (localName.equals("img")) {
				component.setImg(currentValue);
			}
			if (localName.equals("note")) {
				component.setDescription(currentValue);
			}
			if (localName.equals("item")) {
				data.addComponents(component);
			}
		}
		if(type.equalsIgnoreCase("storages")) {
			if (localName.equals("name")) {
				storage.setName(currentValue);
			}
			if (localName.equals("id")) {
				storage.setId(Integer.parseInt(currentValue));
			}
			if (localName.equals("date")) {
				storage.setReleaseDate(currentValue);
			}
			if (localName.equals("type")) {
				storage.setType(currentValue);
			}
			if (localName.equals("capacity")) {
				storage.setCapacity(currentValue);
			}
			if (localName.equals("material")) {
				storage.setMaterial(currentValue);
			}
			if (localName.equals("endurance")) {
				storage.setEndurance(currentValue);
			}
			if (localName.equals("size")) {
				storage.setSize(currentValue);
			}
			if (localName.equals("developer")) {
				storage.setDeveloper(currentValue);
			}
			if (localName.equals("rw-speed")) {
				storage.setRwSpeed(currentValue);
			}
			if (localName.equals("img")) {
				storage.setImg(currentValue);
			}
			if (localName.equals("item")) {
				data.addStorage(storage);
			}
		}
		if(type.equalsIgnoreCase("innerlifecomponents")) {
			if (localName.equals("name")) {
				inner.setName(currentValue);
			}
			if (localName.equals("id")) {
				inner.setId(Integer.parseInt(currentValue));
			}
			if (localName.equals("parentid")) {
				inner.setParentId(Integer.parseInt(currentValue));
			}
			if (localName.equals("date")) {
				inner.setReleaseDate(currentValue);
			}
			if (localName.equals("developer")) {
				inner.setDeveloper(currentValue);
			}
			if (localName.equals("desc")) {
				inner.setDescription(currentValue);
			}
			if (localName.equals("img")) {
				inner.setImg(currentValue);
			}
			if (localName.equals("item")) {
				data.addInnerlifeComponent(inner);
			}
		}
	}

	@Override
	public void endDocument() throws SAXException {
	}

	@Override
	public void endPrefixMapping(String arg0) throws SAXException {
	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
	}

	@Override
	public void processingInstruction(String target, String data)
			throws SAXException {
	}

	@Override
	public void setDocumentLocator(Locator locator) {
	}

	@Override
	public void skippedEntity(String name) throws SAXException {
	}

	@Override
	public void startDocument() throws SAXException {
		element = false;
	}

	@Override
	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
	
	}
}