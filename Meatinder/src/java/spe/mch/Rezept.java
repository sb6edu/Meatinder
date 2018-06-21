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
public class Rezept {
    private int id;
    private String rezeptname;
    private String zubereitungszeit;
    private int gid;
    private String rezeptbeschreibung;

    public Rezept(int id, String rezeptname, String zubereitungszeit, int gid, String rezeptbeschreibung) {
        this.id = id;
        this.rezeptname = rezeptname;
        this.zubereitungszeit = zubereitungszeit;
        this.gid = gid;
        this.rezeptbeschreibung = rezeptbeschreibung;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRezeptname() {
        return rezeptname;
    }

    public void setRezeptname(String rezeptname) {
        this.rezeptname = rezeptname;
    }

    public String getZubereitungszeit() {
        return zubereitungszeit;
    }

    public void setZubereitungszeit(String zubereitungszeit) {
        this.zubereitungszeit = zubereitungszeit;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getRezeptbeschreibung() {
        return rezeptbeschreibung;
    }

    public void setRezeptbeschreibung(String rezeptbeschreibung) {
        this.rezeptbeschreibung = rezeptbeschreibung;
    }
    
    
}
