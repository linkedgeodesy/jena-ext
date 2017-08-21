package org.linkedgeodesy.jenaext.model;

import com.github.jsonldjava.jena.JenaJSONLD;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.linkedgeodesy.jenaext.log.JenaModelException;

/**
 * CLASS for set up a JenaModel graph and export it
 *
 */
public class JenaModel {

    private Model model;

    public JenaModel() {
        model = ModelFactory.createDefaultModel();
    }

    public void readRDF(String rdf, Lang format) throws JenaModelException {
        try {
            RDFDataMgr.read(model, new ByteArrayInputStream(rdf.getBytes()), null, format);
        } catch (Exception e) {
            throw new JenaModelException(e.getMessage());
        }
    }
    
    public void readJSONLD(String jsonld) throws JenaModelException {
        try {
            RDFDataMgr.read(model, new ByteArrayInputStream(jsonld.getBytes()), null, Lang.JSONLD);
        } catch (Exception e) {
            throw new JenaModelException(e.getMessage());
        }
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setModelLiteral(String subject, String predicate, String object) throws JenaModelException {
        try {
            Resource s = model.createResource(subject);
            Property p = model.createProperty(predicate);
            Literal o = model.createLiteral(object);
            model.add(s, p, o);
        } catch (Exception e) {
            throw new JenaModelException(e.getMessage());
        }
    }

    public void setModelLiteralLanguage(String subject, String predicate, String object, String lang) throws JenaModelException {
        try {
            Resource s = model.createResource(subject);
            Property p = model.createProperty(predicate);
            Literal o = model.createLiteral(object, lang);
            model.add(s, p, o);
        } catch (Exception e) {
            throw new JenaModelException(e.getMessage());
        }
    }

    public void setModelURI(String subject, String predicate, String object) throws JenaModelException {
        try {
            Resource s = model.createResource(subject);
            Property p = model.createProperty(predicate);
            Resource o = model.createResource(object);
            model.add(s, p, o);
        } catch (Exception e) {
            throw new JenaModelException(e.getMessage());
        }
    }

    public void setModelTriple(String subject, String predicate, String object) throws JenaModelException {
        try {
            if (object.startsWith("http://") || object.contains("mailto")) {
                setModelURI(subject, predicate, object);
            } else if (object.contains("@")) {
                String literalLanguage[] = object.split("@");
                setModelLiteralLanguage(subject, predicate, literalLanguage[0].replaceAll("\"", ""), literalLanguage[1]);
            } else {
                setModelLiteral(subject, predicate, object.replaceAll("\"", ""));
            }
        } catch (Exception e) {
            throw new JenaModelException(e.getMessage());
        }
    }

    public Model getModelObject() {
        return model;
    }

    public String getModel() throws JenaModelException {
        try {
            JenaJSONLD.init();
            ByteArrayOutputStream o = new ByteArrayOutputStream();
            model.write(o, "RDF/XML");
            return o.toString("UTF-8");
        } catch (Exception e) {
            throw new JenaModelException(e.getMessage());
        }
    }

    public String getModel(String format) throws UnsupportedEncodingException, JenaModelException {
        // https://jena.apache.org/documentation/io/rdf-output.html#jena_model_write_formats
        try {
            JenaJSONLD.init();
            ByteArrayOutputStream o = new ByteArrayOutputStream();
            model.write(o, format);
            return o.toString("UTF-8");
        } catch (Exception e) {
            throw new JenaModelException(e.getMessage());
        }
    }
    
    public String getModelAsJSONLD() throws UnsupportedEncodingException, JenaModelException {
        try {
            JenaJSONLD.init();
            ByteArrayOutputStream o = new ByteArrayOutputStream();
            model.write(o, "JSON-LD");
            return o.toString("UTF-8");
        } catch (Exception e) {
            throw new JenaModelException(e.getMessage());
        }
    }

}
