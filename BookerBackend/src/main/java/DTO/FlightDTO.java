/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author tobbe
 */
public class FlightDTO {

    private Integer id;
    private String airline;
    private String departure;
    private String destination;
    private String depTime;
    private String arrTime;
    private int duration;
    private String airplane;
    private String model;
    private String username;

    public FlightDTO(Integer id, String airline, String departure, String destination, String depTime, String arrTime, int duration, String airplane, String model, String username) {
        this.id = id;
        this.airline = airline;
        this.departure = departure;
        this.destination = destination;
        this.depTime = depTime;
        this.arrTime = arrTime;
        this.duration = duration;
        this.airplane = airplane;
        this.model = model;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
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

    public String getArrTime() {
        return arrTime;
    }

    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getAirplane() {
        return airplane;
    }

    public void setAirplane(String airplane) {
        this.airplane = airplane;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "FlightDTO{" + "id=" + id + ", airline=" + airline + ", departure=" + departure + ", destination=" + destination + ", depTime=" + depTime + ", arrTime=" + arrTime + ", duration=" + duration + ", airplane=" + airplane + ", model=" + model + ", username=" + username + '}';
    }
    
    

}
