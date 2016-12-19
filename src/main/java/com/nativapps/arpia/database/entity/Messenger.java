package com.nativapps.arpia.database.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 *
 * @version 2.3.0
 */
@Entity
@Table(name = "MESSENGER")
@PrimaryKeyJoinColumn(referencedColumnName = "PERSON_ID")
@NamedQueries({
    @NamedQuery(name = "messenger.findAll", 
            query = "SELECT m FROM Messenger m")
})
public class Messenger extends Person {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "WORKSHIFT_ID")
    private WorkshiftParam workshiftParam;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "RELIABILITY_ID")
    private Reliability reliability;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CURRICULUM_VITAE_ID")
    private CurriculumVitae curriculumVitae;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "messenger")
    private List<MessengerInventory> inventory;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "messenger", cascade = CascadeType.ALL)
    private List<MessengerAction> actions;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "messenger")
    private List<Vehicle> vehicles;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "messenger", cascade = CascadeType.ALL)
    private List<Fault> faults;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "messenger", cascade = CascadeType.ALL)
    private List<Disease> diseases;

    @Column(name = "DISMISSAL", nullable = false)
    private boolean dismissal;

    @Column(name = "OBSERVATION", columnDefinition = "TEXT")
    private String observations;

    @Column(name = "PHOTO", nullable = false)
    private String photo;

    @Column(name = "CATEGORY")
    private int category;

    public Messenger() {
        this.workshiftParam = new WorkshiftParam();
        this.reliability = new Reliability();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public WorkshiftParam getWorkshiftParam() {
        return workshiftParam;
    }

    public void setWorkshiftParam(WorkshiftParam workshiftParam) {
        this.workshiftParam = workshiftParam;
    }

    public Reliability getReliability() {
        return reliability;
    }

    public void setReliability(Reliability reliability) {
        this.reliability = reliability;
    }

    public CurriculumVitae getCurriculumVitae() {
        return curriculumVitae;
    }

    public void setCurriculumVitae(CurriculumVitae curriculumVitae) {
        this.curriculumVitae = curriculumVitae;
    }

    public List<MessengerInventory> getInventory() {
        return inventory;
    }

    public void setInventory(List<MessengerInventory> inventory) {
        this.inventory = inventory;
    }

    public List<MessengerAction> getActions() {
        return actions;
    }

    public void setActions(List<MessengerAction> actions) {
        this.actions = actions;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Fault> getFaults() {
        return faults;
    }

    public void setFaults(List<Fault> faults) {
        this.faults = faults;
    }

    public List<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<Disease> diseases) {
        this.diseases = diseases;
    }

    public boolean isDismissal() {
        return dismissal;
    }

    public void setDismissal(boolean dismissal) {
        this.dismissal = dismissal;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

}
