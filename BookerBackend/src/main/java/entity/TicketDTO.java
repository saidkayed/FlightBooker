/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author sidad
 */
public class TicketDTO {
    private Integer id;
    private String userName;
    private String departure;
    private String destination;
    private String depTime;

    public TicketDTO(Integer id, String userName, String departure, String destination, String depTime) {
        this.id = id;
        this.userName = userName;
        this.departure = departure;
        this.destination = destination;
        this.depTime = depTime;
    }

    public TicketDTO(String userName, String departure, String destination, String depTime) {
        this.userName = userName;
        this.departure = departure;
        this.destination = destination;
        this.depTime = depTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "TicketDTO{" + "id=" + id + ", userName=" + userName + ", departure=" + departure + ", destination=" + destination + ", depTime=" + depTime + '}';
    }

   

    
    

    
}
