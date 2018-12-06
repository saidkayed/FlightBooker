/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author sidad
 */
@Entity
public class Data implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String departure;
    private String destination;
    private String depTime;
    @ManyToOne(cascade = CascadeType.PERSIST)
    User user;

    
    
    public Data(){
    
    }
    
       public Data(String userName, String departure, String destination, String depTime) {
        this.userName = userName;
        this.departure = departure;
        this.destination = destination;
        this.depTime = depTime;
    }
       
    public Data(int id,String userName, String departure, String destination, String depTime) {
        this.id = id;
        this.userName = userName;
        this.departure = departure;
        this.destination = destination;
        this.depTime = depTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

   

   
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepTime() {
        return depTime;
    }

    public void setDepTime(String depTime) {
        this.depTime = depTime;
    }
    
    public int getid(){
      return id;
    }

    @Override
    public String toString() {
        return "Data{" + "id=" + id + ", userName=" + userName + ", departure=" + departure + ", destination=" + destination + ", depTime=" + depTime + '}';
    }
    


   

   
    
    
    
}
