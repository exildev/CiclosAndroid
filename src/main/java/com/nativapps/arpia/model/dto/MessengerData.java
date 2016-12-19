package com.nativapps.arpia.model.dto;

import java.util.Calendar;
import com.nativapps.arpia.database.entity.Gender;

/**
 * @author Cristóbal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.2.0
 */
public class MessengerData {

    protected String identification;

    protected String name;

    protected String lastName;

    protected Gender gender;

    protected Calendar birthday;

    protected Boolean dismissal;

    protected String observations;

    protected String photo;

    protected Integer category;

    public MessengerData() {
    }

    public MessengerData(String identification, String name, String lastName,
            Gender gender, Calendar birthday, Boolean dismissal,
            String observations, String photo, Integer category) {
        this.identification = identification;
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.birthday = birthday;
        this.dismissal = dismissal;
        this.observations = observations;
        this.photo = photo;
        this.category = category;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public Boolean getDismissal() {
        return dismissal;
    }

    public void setDismissal(Boolean dismissal) {
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

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

}
