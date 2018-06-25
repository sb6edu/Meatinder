/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spe.mch;

/**
 *
 * @author 103098
 */
public class Zutat {
    
    private int artid;
    private String artname;
    private String menge;
    private String einheit;

    public Zutat(int artid, String artname, String menge, String einheit) {
        this.artid = artid;
        this.artname = artname;
        this.menge = menge;
        this.einheit = einheit;
    }

    public int getArtid() {
        return artid;
    }

    public void setArtid(int artid) {
        this.artid = artid;
    }

    public String getArtname() {
        return artname;
    }

    public void setArtname(String artname) {
        this.artname = artname;
    }

    public String getMenge() {
        return menge;
    }

    public void setMenge(String menge) {
        this.menge = menge;
    }

    public String getEinheit() {
        return einheit;
    }

    public void setEinheit(String einheit) {
        this.einheit = einheit;
    }

    

    
    
    
}
