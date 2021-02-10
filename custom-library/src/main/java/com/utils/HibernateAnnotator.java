package com.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import org.jsonschema2pojo.AbstractAnnotator;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.persistence.*;

public class HibernateAnnotator extends AbstractAnnotator {

    @Override
    public void propertyField(JFieldVar field, JDefinedClass clazz, String propertyName, JsonNode propertyNode) {
        super.propertyField(field, clazz, propertyName, propertyNode);

        if(field.name().equalsIgnoreCase("address")){
            field.annotate(OneToOne.class).param("cascade", CascadeType.ALL);
        }

        if (propertyName.equalsIgnoreCase("id")) {
            field.annotate(Id.class);
            field.annotate(GeneratedValue.class).param("strategy", GenerationType.AUTO);
        }
        super.propertyField(field, clazz, propertyName, propertyNode);
    }

    @Override
    public void propertyInclusion(JDefinedClass clazz, JsonNode propertyNode) {

      if (propertyNode.get("properties").has("entity")) {

            clazz.annotate(Entity.class);
            clazz.annotate(Table.class);
            ((ObjectNode) propertyNode.get("properties")).remove("entity");
            if (!propertyNode.get("properties").has("id")) {
                ((ObjectNode) propertyNode.get("properties"))
                        .putObject("id")
                        .put("type", "integer")
                        .put("description", "The ID of " + clazz.name())
                        .put("minLength", "1")
                        .put("minimum", "1")
                        .put("required", "1");
            }
      }
    }
}
