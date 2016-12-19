package com.nativapps.arpia.database.entity;

import com.nativapps.arpia.database.converter.PasswordConverter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
@Entity
@Table(name = "`USER`")
@NamedQueries({
    @NamedQuery(name = "user.findByUsername",
            query = "select u from User u where u.username = :username"),
    @NamedQuery(name = "user.findByEmail",
            query = "select u from User u where u.email = :email"),
    @NamedQuery(name = "user.findByType",
            query = "select u from User u where u.type = :type"),
    @NamedQuery(name = "user.login",
            query = "select u from User u where (u.username = :dataUser or "
            + "u.email = :dataUser) and u.password = :password")
})
public class User implements Serializable {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "USERNAME", unique = true, length = 12)
    private String username;

    @Column(name = "EMAIL", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "`PASSWORD`", nullable = false)
    @Convert(converter = PasswordConverter.class)
    private String password;

    @Column(name = "DISPLAY_NAME", nullable = false, length = 45)
    private String displayName;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    private Role role;

    @Column(name = "USER_TYPE", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private UserType type;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USR_CMP",
            joinColumns = @JoinColumn(name = "USR_ID",
                    referencedColumnName = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "CMP_ID",
                    referencedColumnName = "OPERATION_ID"))
    private List<Operation> operations;

    @Column(name = "ENABLED", nullable = false)
    private boolean enabled;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED", nullable = false, updatable = false)
    private Calendar created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED", nullable = false)
    private Calendar updated;

    public User() {
        this.enabled = true;
        this.operations = new ArrayList<>();
        this.created = GregorianCalendar.getInstance();
        this.updated = GregorianCalendar.getInstance();
    }

    public User(String email, String password, String displayName,
            UserType type) {
        this.email = email;
        this.password = password;
        this.displayName = displayName;
        this.type = type;
        this.enabled = true;
        this.operations = new ArrayList<>();
        this.created = GregorianCalendar.getInstance();
        this.updated = GregorianCalendar.getInstance();
    }

    public User(long id, String username, String email, String password,
            String displayName, Role role, UserType type, boolean enabled,
            Calendar updated) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.displayName = displayName;
        this.role = role;
        this.type = type;
        this.operations = new ArrayList<>();
        this.enabled = enabled;
        this.updated = updated;
        this.created = GregorianCalendar.getInstance();
    }

    public User(long id, String username, String email, String password,
            String displayName, Role role, UserType type,
            List<Operation> operations, boolean enabled, Calendar updated) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.displayName = displayName;
        this.role = role;
        this.type = type;
        this.operations = operations;
        this.enabled = enabled;
        this.updated = updated;
        this.created = GregorianCalendar.getInstance();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
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
