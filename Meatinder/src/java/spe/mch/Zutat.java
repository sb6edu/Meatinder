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
    
    private String artname;
    private String menge;
    private String einheit;

    public Zutat(String artname, String menge, String einheit) {
        this.artname = artname;
        this.menge = menge;
        this.einheit = einheit;
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
