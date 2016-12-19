package com.nativapps.arpia.database.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author Cristóbal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
@Entity
@Table(name = "REFERENCE")
@NamedQueries({
    @NamedQuery(name = "reference.getAllBycurriculumVitaeId",
            query = "SELECT a FROM Reference a "
            + "WHERE a.curriculumVitae.id = :curriculumVitaeId")
})
@PrimaryKeyJoinColumn(referencedColumnName = "PERSON_ID")
public class Reference extends Person implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CURRICULUM_VITAE_ID", nullable = false)
    private CurriculumVitae curriculumVitae;

    @Column(name = "TYPE_REFERENCE", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeReference typeReference;

    @Column(name = "RELATIONSHIP", nullable = false)
    @Enumerated(EnumType.STRING)
    private Relationship relationship;

    public Reference() {
    }

    public Reference(TypeReference typeReference,
            Relationship relationship, String name,
            String lastName) {
        super(null, name, lastName, null);
        this.typeReference = typeReference;
        this.relationship = relationship;
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

    public CurriculumVitae getCurriculumVitae() {
        return curriculumVitae;
    }

    public void setCurriculumVitae(CurriculumVitae curriculumVitae) {
        this.curriculumVitae = curriculumVitae;
    }

}
