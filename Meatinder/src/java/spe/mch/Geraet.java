/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spe.mch;
        
public class Geraet {
    private int gid;
    private String geraetebezeichnung;

    public Geraet(int gid, String geraetebezeichnung) {
        this.gid = gid;
        this.geraetebezeichnung = geraetebezeichnung;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getGeraetebezeichnung() {
        return geraetebezeichnung;
    }

    public void setGeraetebezeichnung(String geraetebezeichnung) {
        this.geraetebezeichnung = geraetebezeichnung;
    }
}
