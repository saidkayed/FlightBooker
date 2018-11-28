/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author tobbe
 */
@Entity
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    
   
    //@OneToMany(mappedBy = "account")
    //private List<FlightTicket> ft;
    
    public Account(){
        
    }
    
    public Account(int id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }
    
    public Account(String username, String password){
        this.username = username;
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<FlightTicket> getFt() {
        return ft;
    }

    public void setFt(List<FlightTicket> ft) {
        this.ft = ft;
    }
    
        public boolean verifyPassword(String pw){
            return BCrypt.checkpw(pw, password);
        }
    

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", username=" + username + ", password=" + password + '}';
    }

    
    
}
