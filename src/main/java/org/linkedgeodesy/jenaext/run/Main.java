package org.linkedgeodesy.jenaext.run;

import org.linkedgeodesy.jenaext.config.POM_jenaext;
import org.linkedgeodesy.jenaext.log.Logging;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.jena.riot.Lang;
import org.linkedgeodesy.jenaext.model.JenaModel;

/**
 * main class for running
 */
public class Main {

    /**
     * main method
     *
     * @param args
     * @throws IOException
     * @throws SQLException
     */
    public static void main(String[] args) throws IOException, SQLException {
        List<String> o = new ArrayList();
        try {
            // get POM Info
            o.add(POM_jenaext.getInfo().toJSONString());
            // read in turtle, write json-ld, read in json-ld, write N-Triples
            String turtle = "<http://example.org/#spiderman> <http://www.perceive.net/schemas/relationship/enemyOf> <http://example.org/#green-goblin> ; <http://xmlns.com/foaf/0.1/name> \"Spiderman\" .";
            JenaModel jm = new JenaModel();
            JenaModel jm2 = new JenaModel();
            jm.readRDF(turtle, Lang.TURTLE);
            o.add(jm.getModelAsRDFFormatedString("JSON-LD"));
            String turtleld = jm.getModelAsRDFFormatedString("JSON-LD");
            jm2.readRDF(turtleld, Lang.JSONLD);
            o.add(jm2.getModelAsRDFXML());
            // test with ls json-ld
            JenaModel jm3 = new JenaModel();
            String ld = "{\n"
                    + "  \"@id\" : \"http://143.93.114.135/item/label/9ExDWbZEPMOP\",\n"
                    + "  \"ls:thumbnail\" : {\n"
                    + "    \"@language\" : \"de\",\n"
                    + "    \"@value\" : \"Wrack\"\n"
                    + "  },\n"
                    + "  \"dc:created\" : \"2017-05-15T13:44:38.177+0200\",\n"
                    + "  \"dc:creator\" : \"thiery\",\n"
                    + "  \"dc:identifier\" : \"9ExDWbZEPMOP\",\n"
                    + "  \"dc:language\" : \"de\",\n"
                    + "  \"dc:modified\" : [ \"2017-05-15T13:44:38.177+0200\", \"2017-05-15T13:44:46.606+0200\" ],\n"
                    + "  \"creator\" : \"http://143.93.114.135/item/agent/thiery\",\n"
                    + "  \"rdf:type\" : [ {\n"
                    + "    \"@id\" : \"skos:Concept\"\n"
                    + "  }, {\n"
                    + "    \"@id\" : \"ls:Label\"\n"
                    + "  } ],\n"
                    + "  \"changeNote\" : \"http://143.93.114.135/item/revision/j76Bx3b5WqLR\",\n"
                    + "  \"inScheme\" : \"http://143.93.114.135/item/vocabulary/R85M7YgBEeg2\",\n"
                    + "  \"skos:prefLabel\" : [ {\n"
                    + "    \"@language\" : \"de\",\n"
                    + "    \"@value\" : \"Wrack\"\n"
                    + "  }, {\n"
                    + "    \"@language\" : \"en\",\n"
                    + "    \"@value\" : \"wreck\"\n"
                    + "  } ],\n"
                    + "  \"@context\" : {\n"
                    + "    \"prefLabel\" : {\n"
                    + "      \"@id\" : \"http://www.w3.org/2004/02/skos/core#prefLabel\",\n"
                    + "      \"@type\" : \"@id\"\n"
                    + "    },\n"
                    + "    \"language\" : {\n"
                    + "      \"@id\" : \"http://purl.org/dc/elements/1.1/language\",\n"
                    + "      \"@type\" : \"@id\"\n"
                    + "    },\n"
                    + "    \"thumbnail\" : {\n"
                    + "      \"@id\" : \"http://labeling.link/docs/ls/core#thumbnail\",\n"
                    + "      \"@type\" : \"@id\"\n"
                    + "    },\n"
                    + "    \"creator\" : {\n"
                    + "      \"@id\" : \"http://purl.org/dc/terms/creator\",\n"
                    + "      \"@type\" : \"@id\"\n"
                    + "    },\n"
                    + "    \"identifier\" : {\n"
                    + "      \"@id\" : \"http://purl.org/dc/elements/1.1/identifier\",\n"
                    + "      \"@type\" : \"@id\"\n"
                    + "    },\n"
                    + "    \"changeNote\" : {\n"
                    + "      \"@id\" : \"http://www.w3.org/2004/02/skos/core#changeNote\",\n"
                    + "      \"@type\" : \"@id\"\n"
                    + "    },\n"
                    + "    \"modified\" : {\n"
                    + "      \"@id\" : \"http://purl.org/dc/elements/1.1/modified\",\n"
                    + "      \"@type\" : \"@id\"\n"
                    + "    },\n"
                    + "    \"created\" : {\n"
                    + "      \"@id\" : \"http://purl.org/dc/elements/1.1/created\",\n"
                    + "      \"@type\" : \"@id\"\n"
                    + "    },\n"
                    + "    \"inScheme\" : {\n"
                    + "      \"@id\" : \"http://www.w3.org/2004/02/skos/core#inScheme\",\n"
                    + "      \"@type\" : \"@id\"\n"
                    + "    },\n"
                    + "    \"geo\" : \"http://www.w3.org/2003/01/geo/wgs84_pos#\",\n"
                    + "    \"dct\" : \"http://purl.org/dc/terms/\",\n"
                    + "    \"owl\" : \"http://www.w3.org/2002/07/owl#\",\n"
                    + "    \"rdf\" : \"http://www.w3.org/1999/02/22-rdf-syntax-ns#\",\n"
                    + "    \"ls\" : \"http://labeling.link/docs/ls/core#\",\n"
                    + "    \"xsd\" : \"http://www.w3.org/2001/XMLSchema#\",\n"
                    + "    \"skos\" : \"http://www.w3.org/2004/02/skos/core#\",\n"
                    + "    \"rdfs\" : \"http://www.w3.org/2000/01/rdf-schema#\",\n"
                    + "    \"dcat\" : \"http://www.w3.org/ns/dcat#\",\n"
                    + "    \"prov\" : \"http://www.w3.org/ns/prov#\",\n"
                    + "    \"foaf\" : \"http://xmlns.com/foaf/0.1/\",\n"
                    + "    \"dc\" : \"http://purl.org/dc/elements/1.1/\"\n"
                    + "  }\n"
                    + "}";
            jm3.readJSONLD(ld);
            o.add(jm3.getModelAsRDFFormatedString("N-Triples"));
            // context json objects
            o.add(JenaModel.getJSONLDContextByURL("https://raw.githubusercontent.com/geojson/geojson-ld/gh-pages/geojson-context.jsonld").toJSONString());
            o.add(JenaModel.getJSONLDContextByURL("https://raw.githubusercontent.com/florianthiery/geojson-ld-lg/master/geojson-context-lg.jsonld").toJSONString());
            // write output to file
            FileOutput.writeFile(o);
        } catch (Exception e) {
            System.out.println(Logging.getMessageJSON(e, "org.linkedgeodesy.jenaext.run.Main").toJSONString());
            o.add(Logging.getMessageJSON(e, "org.linkedgeodesy.jenaext.run.Main").toJSONString());
            FileOutput.writeFile(o);
        }
    }

}
