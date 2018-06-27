/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spe.mch;

/**
 *
 * @author 103095
 */
public class User {
    private String rechte;
    private String username;

    public User(String rechte, String username) {
        this.rechte = rechte;
        this.username = username;
    }

    public String getRechte() {
        return rechte;
    }

    public void setRechte(String rechte) {
        this.rechte = rechte;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
}
