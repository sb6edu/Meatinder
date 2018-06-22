/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spe.mch;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 103098
 */
@WebServlet(name = "Ctrlfinder", urlPatterns = {"/ctrlfinder.do"})
public class Ctrlfinder extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        zwischentest(request, response);
    }

    private ArrayList zutatenliste(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DBConnectionPool pool = (DBConnectionPool) getServletContext().getAttribute("pool");
        Connection conn = pool.getConnection();
        String sql = "select * from artikel";
        ArrayList<Artikel> artikels = new ArrayList<>();
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            String artid;
            String artname;
            while (rs.next()) {
                artid = rs.getString("ARTID");
                artname = rs.getString("ARTNAME");
                artikels.add(new Artikel(artid, artname));
            }
        } catch (SQLException ex) {
            response.getWriter().println(ex.getMessage());
        }
        pool.releaseConnection(conn);
        return artikels;
    }
    private ArrayList geraeteliste(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DBConnectionPool pool = (DBConnectionPool) getServletContext().getAttribute("pool");
        Connection conn = pool.getConnection();
        String sql = "select * from geraete";
        ArrayList<Geraet> geraetes = new ArrayList<>();
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            String geraetid;
            String geraetname;
            while (rs.next()) {
                geraetid = rs.getString("GID");
                geraetname = rs.getString("GERAETEBEZEICHNUNG");
                geraetes.add(new Geraet(geraetid, geraetname));
            }
        } catch (SQLException ex) {
            response.getWriter().println(ex.getMessage());
        }
        pool.releaseConnection(conn);
        return geraetes;
    }

    private ArrayList zinventarliste(HttpServletRequest request, HttpServletResponse response, ArrayList<Artikel> artikels)
            throws ServletException, IOException {
        ArrayList<Artikel> zinventar = new ArrayList<>();
        for (Artikel artikel : artikels) {
            String artikelname = artikel.getArtname();
            String zutat = request.getParameter(artikelname);
            try {
                if (artikelname.equals(zutat)) {
                    zinventar.add(artikel);
                }
            } catch (NullPointerException ex) {
                response.getWriter().println(ex.getMessage());
            }
        }
        return zinventar;
    }
    
    private ArrayList ginventarliste(HttpServletRequest request, HttpServletResponse response, ArrayList<Geraet> geraetes)
            throws ServletException, IOException {
        ArrayList<Geraet> ginventar = new ArrayList<>();
        for (Geraet geraet : geraetes) {
            String geraetname = geraet.getGeraetebezeichnung();
            String ding = request.getParameter(geraetname);
            try {
                if (geraetname.equals(ding)) {
                    ginventar.add(geraet);
                }
            } catch (NullPointerException ex) {
                response.getWriter().println(ex.getMessage());
            }
        }
        return ginventar;
    }
    private ArrayList rezeptartids(){
        String sql = "select ID from Rezepte";
        return null;//platzhalter, um compilerfehler missing return statement zu umgehen
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void johannesdenkmal() {
        /*artikels.forEach((Artikel artikel) -> {
            String bla = artikel.getArtname();
        });
        
        artikels.stream().map((Artikel artikel) ->  artikel.getArtname());*/

    }
    private void zwischentest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Artikel> zinventar = new ArrayList <>();
        zinventar = zinventarliste(request,response, zutatenliste(request, response));
        for (Artikel artikel : zinventar) {
            String artikelname = artikel.getArtname();
            response.getWriter().println(artikelname);
        }
        ArrayList<Geraet> ginventar = new ArrayList <>();
        ginventar = ginventarliste(request,response, geraeteliste(request, response));
        for (Geraet geraet : ginventar) {
            String geraetname = geraet.getGeraetebezeichnung();
            response.getWriter().println(geraetname);
        }
    }
}
