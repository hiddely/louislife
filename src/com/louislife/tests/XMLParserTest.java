package com.louislife.tests;

import com.louislife.model.Game;
import com.louislife.util.XMLParser;
import junit.framework.TestCase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class XMLParserTest extends TestCase {

    private String filename = "tests.xml";

    public void testConstructor() throws Exception {
        XMLParser parser = new XMLParser(filename);
        assertNotNull(parser);
    }

    public void testParseGame() throws Exception {
        XMLParser parser = new XMLParser(filename);
        Game g = parser.parseGame();

        assertNotNull(g);
        assertEquals(0, g.getCurrentDay());
        assertEquals("Louis", g.getName());
        assertEquals(2, g.getLeagues().get(0).getTeams().size());
    }

    public void testWriteGame() throws Exception {
        XMLParser parser = new XMLParser(filename);
        Game old = parser.parseGame();

        parser.writeGame(old);

        Game newload = parser.parseGame();

        assertEquals(old, newload);
    }

    public void testCreateGame() throws Exception {
        XMLParser parser = new XMLParser(filename);

        assertTrue(parser.createGame("TEST"+System.currentTimeMillis()));
    }

    public void testGetGames() throws Exception {
        ArrayList<String> names = XMLParser.getGames();

        assertTrue(names.contains("tests"));
    }

    public void testGetChildValue() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); // Open factory
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = factory.newDocumentBuilder(); // Initialize builder
        String input = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><player id=\"412\"><surname>de Vlugt</surname><offensiveRating>58</offensiveRating></player>";

        Document document = builder.parse(new ByteArrayInputStream(input.getBytes()));
        document.normalize();

        Element gameNode = document.getDocumentElement();

        XMLParser parser = new XMLParser(filename);

        assertEquals("58", parser.getChildValue(gameNode, "offensiveRating"));
        assertEquals("de Vlugt", parser.getChildValue(gameNode, "surname"));

    }

    public void testGetAttribute() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); // Open factory
        factory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder builder = factory.newDocumentBuilder(); // Initialize builder
        String input = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><player id=\"412\"><surname>de Vlugt</surname><offensiveRating>58</offensiveRating></player>";

        Document document = builder.parse(new ByteArrayInputStream(input.getBytes()));
        document.normalize();

        Element gameNode = document.getDocumentElement();

        XMLParser parser = new XMLParser(filename);

        assertEquals("412", parser.getAttribute(gameNode.getAttributes(), "id"));

    }
}