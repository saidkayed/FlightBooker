/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;


import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author sidad
 */
@Entity

public class FlightTicket implements Serializable,Comparable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String airline;
    private String departure;
    private String destination;
    private String  depTime;
    private String  arrTime;
    private int duration;
    private int price;
    private int cancelInsurance;
    private String airplane;
    private String model;
    private int capacity;
    //@ManyToOne
    //private Account account;
    
    public FlightTicket(){
        
    }

    public FlightTicket(String airline, String departure, String destination, String depTime, String arrTime, int duration, int price, int cancelInsurance, String airplane, String model, int capacity) {
        this.airline = airline;
        this.departure = departure;
        this.destination = destination;
        this.depTime = depTime;
        this.arrTime = arrTime;
        this.duration = duration;
        this.price = price;
        this.cancelInsurance = cancelInsurance;
        this.airplane = airplane;
        this.model = model;
        this.capacity = capacity;
    }
    

    public Integer getId() {
        return id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String desination) {
        this.destination = desination;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCancelInsurance() {
        return cancelInsurance;
    }

    public void setCancelInsurance(int cancelInsurance) {
        this.cancelInsurance = cancelInsurance;
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

    @Override
    public int compareTo(Object t) {
       int price= ((FlightTicket)t).getPrice();
        /* For Ascending order*/
        return this.price-price;
    }
    
    @Override
    public String toString() {
        return "FlightTicket{" + "id=" + id + ", airline=" + airline + ", departure=" + departure + ", destination=" + destination + ", depTime=" + depTime + ", arrTime=" + arrTime + ", duration=" + duration + ", price=" + price + ", cancelInsurance=" + cancelInsurance + ", airplane=" + airplane + ", model=" + model + ", capacity=" + capacity + '}';
    }

    

   

    
}
