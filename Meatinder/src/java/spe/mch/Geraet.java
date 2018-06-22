/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spe.mch;
        
public class Geraet {
    private String gid;
    private String geraetebezeichnung;

    public Geraet(String gid, String geraetebezeichnung) {
        this.gid = gid;
        this.geraetebezeichnung = geraetebezeichnung;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getGeraetebezeichnung() {
        return geraetebezeichnung;
    }

    public void setGeraetebezeichnung(String geraetebezeichnung) {
        this.geraetebezeichnung = geraetebezeichnung;
    }
}
