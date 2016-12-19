package com.nativapps.arpia.database.entity;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
@Entity
@Table(name = "ADMINISTRATOR")
@PrimaryKeyJoinColumn(referencedColumnName = "PERSON_ID")
@NamedQueries({
    @NamedQuery(name = "findByEstablishmentId",
            query = "SELECT a FROM Administrator a WHERE "
            + "a.establishment.id = :establishmentId AND "
            + "a.id = :administratorId"),
    @NamedQuery(name = "findAllByEstablishmentId",
            query = "SELECT a FROM Administrator a WHERE "
            + "a.establishment.id = :establishmentId")
})
public class Administrator extends Person {

    @Column(name = "OBSERVATIONS", columnDefinition = "TEXT")
    private String observations;

    @Column(name = "LINKED", nullable = false)
    private boolean linked;

    @Column(name = "NDAY_REPORT")
    private Integer nDayReport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ESTABLISHMENT_ID", nullable = false)
    private Establishment establishment;

    public Administrator() {
    }

    public Administrator(String observations, boolean linked,
            String identification, String name, String lastName,
            Gender gender) {
        super(identification, name, lastName, gender);
        this.observations = observations;
        this.linked = linked;
    }

    public Administrator(String observations, boolean linked,
            Integer nDayReport, String identification,
            String name, String lastName, Gender gender, Calendar birthday) {
        super(identification, name, lastName, gender, birthday);
        this.observations = observations;
        this.linked = linked;
        this.nDayReport = nDayReport;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public boolean isLinked() {
        return linked;
    }

    public void setLinked(boolean linked) {
        this.linked = linked;
    }

    public Integer getnDayReport() {
        return nDayReport;
    }

    public void setnDayReport(Integer nDayReport) {
        this.nDayReport = nDayReport;
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;

        if (!establishment.getAdministrators().contains(this)) {
            establishment.addAdministrator(this);
        }
    }
}
