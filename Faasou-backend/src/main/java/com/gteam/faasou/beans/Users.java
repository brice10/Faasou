/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gteam.faasou.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ndadj
 */
@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "pseudo" }),
        @UniqueConstraint(columnNames = { "email" }) })
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
        @NamedQuery(name = "Users.findByUserId", query = "SELECT u FROM Users u WHERE u.userId = :userId"),
        @NamedQuery(name = "Users.findByCreatedOn", query = "SELECT u FROM Users u WHERE u.createdOn = :createdOn"),
        @NamedQuery(name = "Users.findByLastUpdateOn", query = "SELECT u FROM Users u WHERE u.lastUpdateOn = :lastUpdateOn"),
        @NamedQuery(name = "Users.findByPseudo", query = "SELECT u FROM Users u WHERE u.pseudo = :pseudo"),
        @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
        @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email") })
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "pseudo", length = 255)
    private String pseudo;

    @Basic(optional = false)
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @Column(name = "last_update_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateOn;

    public Users() {
    }

    public Users(Integer userId) {
        this.userId = userId;
    }

    public Users(String pseudo, String password, String email, Date createdOn) {
        this.pseudo = pseudo;
        this.password = password;
        this.email = email;
        this.createdOn = createdOn;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getLastUpdateOn() {
        return lastUpdateOn;
    }

    public void setLastUpdateOn(Date lastUpdateOn) {
        this.lastUpdateOn = lastUpdateOn;
    }

    @Override
    public String toString() {
        return "Users [createdOn=" + createdOn + ", email=" + email + ", lastUpdateOn=" + lastUpdateOn + ", password="
                + password + ", pseudo=" + pseudo + ", userId=" + userId + "]";
    }
}
