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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
@WebServlet(name = "RezeptFinden", urlPatterns = {"/rezeptfinden.do"})
public class RezeptFinden extends HttpServlet {

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
        ArrayList<Rezept> rezeptes = new ArrayList<>();
        ArrayList<String> artnamen = new ArrayList<>();
        ArrayList<String> mengen = new ArrayList<>();
        ArrayList<String> einheiten = new ArrayList<>();
        ArrayList<Integer> artids = new ArrayList<>();
        ArrayList<Zutat> zutaten = new ArrayList<>();
        String rn = request.getParameter("rn");
        
        
        String sql = "select id, rezeptname, s.artid, artname, menge, einheit, geraetebezeichnung, zubereitungszeit, rezeptbeschreibung from geraete a, artikel s, rezepte d, rezeptartikel f where a.gid = d.gid and s.artid = f.artid and d.id = f.RID and rezeptname = "+"'"+rn+"'";

        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            String rezeptid = "";
            
            int artid;
            String artname;
            String menge;
            String einheit;
            String geraetebezeichnung = "";
            String zubereitungszeit = "";
            String rezeptbeschreibung = "";
            

            
                while (rs.next()) {
                    rezeptid = rs.getString("id");
                    
                    artid = rs.getInt("artid");
                    artname = rs.getString("artname");
                    menge = rs.getString("menge");
                    einheit = rs.getString("einheit");
                    geraetebezeichnung = rs.getString("geraetebezeichnung");
                    zubereitungszeit = rs.getString("zubereitungszeit");
                    rezeptbeschreibung = rs.getString("rezeptbeschreibung");

                    Zutat zutat = new Zutat(artid, artname, menge, einheit);
                    
                    artnamen.add(artname);
                    mengen.add(menge);
                    einheiten.add(einheit);
                    artids.add(artid);
                    
                    zutaten.add(zutat);
                }
            
            rezeptes.add(new Rezept(rezeptid, rn, artids, artnamen, mengen, einheiten, geraetebezeichnung, zubereitungszeit, rezeptbeschreibung));

        } catch (SQLException ex) {
            response.getWriter().println(ex.getMessage());
        }
        request.setAttribute("zutaten", zutaten);
        request.setAttribute("rezeptes", rezeptes);
        //request.setAttribute("artnamen", artnamen);
        
        
        
        
        
        
        HttpSession session = request.getSession();
        String sid = session.getId();
        
        
        
        try {
            String sql3 = "select username from kunden where sid = " + "'" + sid + "'";
            String uname = "";
            PreparedStatement pstm = conn.prepareStatement(sql3);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                uname = rs.getString("username");
                String rid = "";
                for(Rezept rezept : rezeptes) {
                    rid = rezept.getId();
                    System.out.println(rid);
                }
                int ridi = Integer.parseInt(rid);
                try{
                   String sql2 = "insert into kundenrezepte (username, datum, rezid) values (" + "'" + uname + "'" + ", current_timestamp, " + ridi + ")";
                   PreparedStatement pstm2 = conn.prepareStatement(sql2);
                   pstm2.executeUpdate();
                } catch(SQLException ex) {
                    
                }
            }
        } catch (SQLException ex) {
            response.getWriter().println(ex.getMessage());
        }
        
        
        pool.releaseConnection(conn);
        
        RequestDispatcher view = request.getRequestDispatcher("rezeptSeite.jsp");
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
