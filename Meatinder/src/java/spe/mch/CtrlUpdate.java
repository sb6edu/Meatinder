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
 * @author 103098
 */
@WebServlet(name = "CtrlUpdate", urlPatterns = {"/ctrlupdate.do"})
public class CtrlUpdate extends HttpServlet {

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
        DBConnectionPool pool = (DBConnectionPool) getServletContext().getAttribute("pool");
        Connection conn = pool.getConnection();
        
        int rid = 0;
        try {
            rid = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException ex) {
            throw new RuntimeException("Ungültiger Löschversuch!");
        }
        String sql = "delete from eigenerezepte where rid = ?";
        String sql2 = "delete from rezeptartikel where rid = ?";
        String sql4 = "delete from kundenrezepte where rezid = ?";
        //String sql3 = "delete from rezepte where id=?";
        

        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, rid);
            pstm.executeUpdate();
            
            PreparedStatement pstm2 = conn.prepareStatement(sql2);
            pstm2.setInt(1, rid);
            pstm2.executeUpdate();
            
            PreparedStatement pstm4 = conn.prepareStatement(sql4);
            pstm4.setInt(1, rid);
            pstm4.executeUpdate();
            
            /*PreparedStatement pstm3 = conn.prepareStatement(sql3);
            pstm3.setInt(1, rid);
            pstm3.executeUpdate();
            */
            

        } catch (SQLException ex) {
            response.getWriter().println(ex.getMessage());
        }
        
        
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
        
        for(Zutat zutat : zutaten) {
            System.out.println(zutat.getArtname());
        }
        
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
        
        sql = "update rezepte set gid = ?, rezeptname = ?, zubereitungszeit = ?, rezeptbeschreibung = ? where id = ?";
        String gid = "";
        for(Geraet geraet : ginventar){
            gid = geraet.getGid();
            System.out.println(gid);
        }
        
        try{
            PreparedStatement pstm = conn.prepareStatement(sql);
            
            pstm.setString(1, gid);
            pstm.setString(2, rezeptname);
            pstm.setString(3, zubereitungszeit);
            pstm.setString(4, rezeptbeschreibung);
            pstm.setInt(5, rid);
            
            pstm.executeUpdate();
            
        } catch(SQLException ex){
        response.getWriter().println(ex.getMessage());
        }
        
        
        
        
        for(Zutat zutat : zutaten) {
            sql = "insert into rezeptartikel (rid, artid, menge, einheit) values (?, ?, ?, ?)";
            int artid = zutat.getArtid();
            String menge = zutat.getMenge();
            String einheit = zutat.getEinheit();
            
            try {
                
                PreparedStatement pstm = conn.prepareStatement(sql);
                pstm.setInt(1, rid);
                pstm.setInt(2, artid);
                pstm.setString(3, menge);
                pstm.setString(4, einheit);
                
                pstm.executeUpdate();
            } catch (SQLException ex) {
                response.getWriter().println(ex.getMessage());
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
        } catch(SQLException ex) {
            response.getWriter().println(ex.getMessage());
        }
        sql4 = "insert into eigenerezepte (rid, username) values (?,?)";
        
        try{
            PreparedStatement pstm = conn.prepareStatement(sql4);
            pstm.setInt(1, rid);
            pstm.setString(2, username);
            pstm.executeUpdate();
            
        } catch(SQLException ex){
            response.getWriter().println(ex.getMessage());
        }
        pool.releaseConnection(conn);
        RequestDispatcher view = request.getRequestDispatcher("ctrlerstellt.do");
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
