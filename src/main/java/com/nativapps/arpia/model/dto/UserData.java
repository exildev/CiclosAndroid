package com.nativapps.arpia.model.dto;

import com.nativapps.arpia.database.entity.UserType;
import java.util.Calendar;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
@XmlRootElement
public class UserData {

    private String username;

    private String email;

    private String displayName;

    private Long roleId;

    @XmlElement(name = "userType")
    private UserType type;

    private List<Long> operations;

    private Boolean enabled;

    private Calendar created;

    private Calendar updated;

    public UserData() {
        this.enabled = true;
    }

    public UserData(String username, String email, String displayName,
            Long roleId, UserType type, List<Long> operations) {
        this.username = username;
        this.email = email;
        this.displayName = displayName;
        this.roleId = roleId;
        this.type = type;
        this.operations = operations;
        this.enabled = true;
    }

    public UserData(String username, String email, String displayName,
            Long roleId, UserType type, List<Long> operations, Boolean enabled,
            Calendar created, Calendar updated) {
        this.username = username;
        this.email = email;
        this.displayName = displayName;
        this.roleId = roleId;
        this.type = type;
        this.operations = operations;
        this.enabled = enabled;
        this.created = created;
        this.updated = updated;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public List<Long> getOperations() {
        return operations;
    }

    public void setOperations(List<Long> operations) {
        this.operations = operations;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public Calendar getUpdated() {
        return updated;
    }

    public void setUpdated(Calendar updated) {
        this.updated = updated;
    }

}
