package com.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import org.jsonschema2pojo.AbstractAnnotator;

import javax.persistence.*;

public class HibernateAnnotator extends AbstractAnnotator {

    @Override
    public void propertyField(JFieldVar field, JDefinedClass clazz, String propertyName, JsonNode propertyNode) {
        super.propertyField(field, clazz, propertyName, propertyNode);

        // Note: does not have to be the propertyName, could be the field or propertyNode that is verified.
        if (propertyName.equals("entity")) {
            clazz.annotate(Entity.class);
            clazz.annotate(Table.class);
        }

        if(field.name().equalsIgnoreCase("id")){
            field.annotate(Id.class);
            field.annotate(GeneratedValue.class);
        }

        if(field.name().equalsIgnoreCase("address")){
            field.annotate(OneToOne.class);
        }
    }
}
