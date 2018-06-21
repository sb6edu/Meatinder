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
public class Rezept {
    private int rid;
    private String rezeptname;
    private String zubereitungszeit;
    private int gid;
    private String rezeptbeschreibung;

    public Rezept(int rid, String rezeptname, String zubereitungszeit, int gid, String rezeptbeschreibung) {
        this.rid = rid;
        this.rezeptname = rezeptname;
        this.zubereitungszeit = zubereitungszeit;
        this.gid = gid;
        this.rezeptbeschreibung = rezeptbeschreibung;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
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
