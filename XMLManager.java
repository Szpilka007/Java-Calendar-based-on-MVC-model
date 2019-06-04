import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLManager {
	
	private String getValueOfNode(String tag, Element element) {
		NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodeList.item(0);
		return node.getNodeValue();
	}
	
	private Event getEvent(Node node) {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			Event event = new Event(
					Integer.parseInt(getValueOfNode("id", element)),
					Integer.parseInt(getValueOfNode("dayNumber", element)),
					Integer.parseInt(getValueOfNode("monthNumber", element)),
					Integer.parseInt(getValueOfNode("yearNumber", element)),
					getValueOfNode("description", element));
					return event;
		}
		return null;
		
		
		
	}
	
	public void writeToXMLFile(Vector<Event> eventsList, String xmlFilePath) {
		try {
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();
			
			Element root = document.createElement("Events");
			document.appendChild(root);
			
			for (Event e: eventsList) {
				Element event = document.createElement("Event");
				root.appendChild(event);
				
				Element id = document.createElement("id");
				id.appendChild(document.createTextNode(String.valueOf(e.getID())));
				event.appendChild(id);
				
				Element dayNumber = document.createElement("dayNumber");
				dayNumber.appendChild(document.createTextNode(String.valueOf(e.getDayNumber())));
				event.appendChild(dayNumber);
				
				Element monthNumber = document.createElement("monthNumber");
				monthNumber.appendChild(document.createTextNode(String.valueOf(e.getMonthNumber())));
				event.appendChild(monthNumber);
				
				Element yearNumber = document.createElement("yearNumber");
				yearNumber.appendChild(document.createTextNode(String.valueOf(e.getYearNumber())));
				event.appendChild(yearNumber);
				
				Element description = document.createElement("description");
				description.appendChild(document.createTextNode(e.getDescription()));
				event.appendChild(description);
				
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource domSource = new DOMSource(document);
				StreamResult streamResult = new StreamResult(xmlFilePath);
				transformer.transform(domSource, streamResult);
			}
		}
		catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } 
		catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
		
	}
	
	public Vector<Event> loadFromXMLFile(Vector<Event> eventsList, String xmlFilePath) {
		
		try {
			File file = new File (xmlFilePath);
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			document.getDocumentElement().normalize();
			System.out.println("Czy dzia³a: " + document.getDocumentElement().getNodeName());
			NodeList nodeList = document.getElementsByTagName("Event");
			Vector<Event> loadedEvents = new Vector<Event>();
			for (int i = 0; i < nodeList.getLength(); i++)
				loadedEvents.add(getEvent(nodeList.item(i)));
			
			return loadedEvents;
						
		} 
		catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	
}
