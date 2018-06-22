/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spe.mch;

import java.util.ArrayList;



/**
 *
 * @author 103095
 */
public class Rezept {
    private String id;
    private String rezeptname;
    private ArrayList<Integer> artid;
    private ArrayList<String> artname;
    private ArrayList<String> menge;
    private ArrayList<String> einheit;
    private String geraetebezeichnung;
    private String zubereitungszeit;
    private String rezeptbeschreibung;

    public Rezept(String id, String rezeptname, ArrayList<Integer> artid, ArrayList<String> artname, ArrayList<String> menge, ArrayList<String> einheit, String geraetebezeichnung, String zubereitungszeit, String rezeptbeschreibung) {
        this.id = id;
        this.rezeptname = rezeptname;
        this.artid = artid;
        this.artname = artname;
        this.menge = menge;
        this.einheit = einheit;
        this.geraetebezeichnung = geraetebezeichnung;
        this.zubereitungszeit = zubereitungszeit;
        this.rezeptbeschreibung = rezeptbeschreibung;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRezeptname() {
        return rezeptname;
    }

    public void setRezeptname(String rezeptname) {
        this.rezeptname = rezeptname;
    }

    public ArrayList<Integer> getArtid() {
        return artid;
    }

    public void setArtid(ArrayList<Integer> artid) {
        this.artid = artid;
    }

    public ArrayList<String> getArtname() {
        return artname;
    }

    public void setArtname(ArrayList<String> artname) {
        this.artname = artname;
    }

    public ArrayList<String> getMenge() {
        return menge;
    }

    public void setMenge(ArrayList<String> menge) {
        this.menge = menge;
    }

    public ArrayList<String> getEinheit() {
        return einheit;
    }

    public void setEinheit(ArrayList<String> einheit) {
        this.einheit = einheit;
    }

    public String getGeraetebezeichnung() {
        return geraetebezeichnung;
    }

    public void setGeraetebezeichnung(String geraetebezeichnung) {
        this.geraetebezeichnung = geraetebezeichnung;
    }

    public String getZubereitungszeit() {
        return zubereitungszeit;
    }

    public void setZubereitungszeit(String zubereitungszeit) {
        this.zubereitungszeit = zubereitungszeit;
    }

    public String getRezeptbeschreibung() {
        return rezeptbeschreibung;
    }

    public void setRezeptbeschreibung(String rezeptbeschreibung) {
        this.rezeptbeschreibung = rezeptbeschreibung;
    }

    
    
    
}
