package com.louislife.util;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.louislife.model.Game;

public class XMLParser {
	
	private DocumentBuilder builder;
	private String filename;
	
	public XMLParser(String filename) throws ParserConfigurationException {
		this.filename = filename;
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); // Open factory
		builder = factory.newDocumentBuilder(); // Initialize builder
		
	}
	
	public Game parseGame() throws SAXException, IOException {
		Document document = builder.parse(ClassLoader.getSystemResourceAsStream(filename));

	    //List<Player> empList = new ArrayList<>();
		
		return null;
	}
	
	
}
