package com.nativapps.arpia.model.dto;

import com.nativapps.arpia.database.entity.Relationship;
import com.nativapps.arpia.database.entity.TypeReference;

/**
 * @author Cristóbal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
public class ReferenceData {

    private String name;

    private String lastName;

    private TypeReference typeReference;

    private Relationship relationship;

    public ReferenceData() {
    }

    public ReferenceData(String name, String lastName,
            TypeReference typeReference,
            Relationship relationship) {
        this.name = name;
        this.lastName = lastName;
        this.typeReference = typeReference;
        this.relationship = relationship;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public TypeReference getTypeReference() {
        return typeReference;
    }

    public void setTypeReference(TypeReference typeReference) {
        this.typeReference = typeReference;
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }
}
