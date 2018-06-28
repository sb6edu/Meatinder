/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spe.mch;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 103097
 */
@WebServlet(name = "CtrlEinfuegen", urlPatterns = {"/ctrleinfuegen.do"})
public class CtrlEinfuegen extends HttpServlet {

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
        
        
        //Alle Artikel auswählen
        DBConnectionPool pool = (DBConnectionPool) getServletContext().getAttribute("pool");
        Connection conn = pool.getConnection();
        String sqlartikels = "select * from artikel";
        ArrayList<Artikel> artikels = new ArrayList<>();
        try {
            PreparedStatement pstm = conn.prepareStatement(sqlartikels);
            ResultSet rs = pstm.executeQuery();
            int artid = 0;
            String artname;
            while (rs.next()) {
                artid = rs.getInt("ARTID");
                artname = rs.getString("ARTNAME");
                artikels.add(new Artikel(artid, artname));
                //System.out.println(artid);
                //System.out.println(artname);
            }
        } catch (SQLException ex) {
            response.getWriter().println(ex.getMessage());
        }
        /*for(Artikel artikel : artikels) {
            System.out.println(artikel);
        }*/
        
        request.setAttribute("artikels", artikels);
        
        //Geraete auswählen
        String sqlgeraete = "select * from geraete";
        ArrayList<Geraet> geraetes = new ArrayList<>();
        try {
            PreparedStatement pstm = conn.prepareStatement(sqlgeraete);
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
        request.setAttribute("geraetes", geraetes);
        
        
        //Ausgewählte Artikel
        ArrayList<Zutat> zutaten = new ArrayList<>();
        for (Artikel artikel : artikels) {
            int artid = artikel.getArtid();
            String artikelname = artikel.getArtname();
            String zutat = request.getParameter(artikelname);
            String menge = request.getParameter(artikelname + "-Menge");
            String einheit = request.getParameter(artikelname + "-Einheit");
            try {
                if (artikelname.equals(zutat)) {
                    zutaten.add(new Zutat(artid, artikelname, menge, einheit));
                }
            } catch (NullPointerException ex) {
                response.getWriter().println(ex.getMessage());
            }
        }
        request.setAttribute("zutaten", zutaten);
        
        //Ausgewählte Geräte
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
        request.setAttribute("ginventar", ginventar);
        
        
        
        String rezeptname = request.getParameter("rezeptname");
        String zubereitungszeit = request.getParameter("zubereitungszeit");
        String rezeptbeschreibung = request.getParameter("rezeptbeschreibung");
        //ArrayList ginventar
        //ArrayList zutaten (artname, menge, einheit)
        
        String sql = "insert into rezepte (gid, rezeptname, zubereitungszeit, rezeptbeschreibung) values (?,?,?,?)";
        String gid = "";
        for(Geraet geraet : ginventar){
            gid = geraet.getGid();
        }
        
        try{
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, gid);
            pstm.setString(2, rezeptname);
            pstm.setString(3, zubereitungszeit);
            pstm.setString(4, rezeptbeschreibung);
            
            pstm.executeUpdate();
            
        } catch(SQLException ex){}
        
        
        String sql2 = "select id from rezepte where rezeptname = " + "'" + rezeptname + "'";
        String rid = "";
        try{
            PreparedStatement pstm = conn.prepareStatement(sql2);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                rid = rs.getString("id");
            }
        } catch (SQLException ex) {
            response.getWriter().println(ex.getMessage());
        }
        
        for(Zutat zutat : zutaten) {
            sql = "insert into rezeptartikel (rid, artid, menge, einheit) values (?, ?, ?, ?)";
            int artid = zutat.getArtid();
            String menge = zutat.getMenge();
            String einheit = zutat.getEinheit();
            
            try {
                PreparedStatement pstm = conn.prepareStatement(sql);
                pstm.setString(1, rid);
                pstm.setInt(2, artid);
                pstm.setString(3, menge);
                pstm.setString(4, einheit);
                
                pstm.executeUpdate();
            } catch (SQLException ex) {
                
            }
        }
        
        HttpSession session = request.getSession();
        String sid = session.getId();
        
        String sql3 = "select username from kunden where sid = " + "'" + sid + "'";
        
        String username ="";
        try {
                PreparedStatement pstm = conn.prepareStatement(sql3);
                ResultSet rs = pstm.executeQuery();
                while(rs.next()){
                username = rs.getString("username");
                    System.out.println(username);
                }
        } catch(SQLException SQL) {
            
        }
        String sql4 = "insert into eigenerezepte (rid, username) values (?,?)";
        
        try{
            PreparedStatement pstm = conn.prepareStatement(sql4);
            pstm.setString(1, rid);
            pstm.setString(2, username);
            pstm.executeUpdate();
            
        } catch(SQLException ex){
            
        }
        pool.releaseConnection(conn);
        RequestDispatcher view = request.getRequestDispatcher("eigeneRezepte.jsp");
        view.forward(request,response); 
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

}
