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
    private String bearbeiten;
    private String a;
    private String b;

    public User(String rechte, String username, String bearbeiten, String a, String b) {
        this.rechte = rechte;
        this.username = username;
        this.bearbeiten = bearbeiten;
        this.a = a;
        this.b = b;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    

    public String getBearbeiten() {
        return bearbeiten;
    }

    public void setBearbeiten(String bearbeiten) {
        this.bearbeiten = bearbeiten;
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
