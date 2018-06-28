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
@WebServlet(name = "Ctrlhistory", urlPatterns = {"/ctrlhistory.do"})
public class Ctrlhistory extends HttpServlet {

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
        
        ArrayList<Integer> rezid = new ArrayList<>();
        
        HttpSession session = request.getSession();
        String sid = session.getId();

        if (CtrlLogin.eingeloggt(request, response, getServletContext())) {
            DBConnectionPool pool = (DBConnectionPool) getServletContext().getAttribute("pool");
            Connection conn = pool.getConnection();
            String sql = "select username from kunden where sid = " + "'" + sid + "'";
            String username = "";
            
            try {
                PreparedStatement pstm = conn.prepareStatement(sql);
                ResultSet rs = pstm.executeQuery();
                while(rs.next()){
                username = rs.getString("username");
                    
                String sql2 = "select rezid from kundenrezepte where username = " + "'" + username + "'";
                    
                try {
                    PreparedStatement pstm2 = conn.prepareStatement(sql2);
                    ResultSet rs2 = pstm2.executeQuery();
                    
                    while (rs2.next()) {
                        String rezida = rs2.getString("rezid");
                        int rezidaInt = Integer.parseInt(rezida);
                        
                        rezid.add(rezidaInt);
                        
                    }
                } catch (SQLException ex) {
                    response.getWriter().println(ex.getMessage());
                }
            }
            } catch (SQLException ex) {
                response.getWriter().println(ex.getMessage());
            }
            
            ArrayList<Rezept> rezepte = new ArrayList<>();
            for (Integer i : rezid) {
                sql = "select id, rezeptname, s.artid, artname, menge, einheit, geraetebezeichnung, zubereitungszeit, rezeptbeschreibung from geraete a, artikel s, rezepte d, rezeptartikel f where a.gid = d.gid and s.artid = f.artid and d.id = f.RID and id= " + i;
                
                try {
                    PreparedStatement pstm = conn.prepareStatement(sql);
                    ResultSet rs = pstm.executeQuery();
                    String rezeptid = "";
                    String rezeptname = "";
                    int artid;
                    String artname;
                    String menge;
                    String einheit;
                    String geraetebezeichnung = "";
                    String zubereitungszeit = "";
                    String rezeptbeschreibung = "";
                    ArrayList<String> artnamen = new ArrayList<>();
                    ArrayList<String> mengen = new ArrayList<>();
                    ArrayList<String> einheiten = new ArrayList<>();
                    ArrayList<Integer> artids = new ArrayList<>();

                    while (rs.next()) {
                        rezeptid = rs.getString("id");
                        rezeptname = rs.getString("rezeptname");
                        artid = rs.getInt("artid");
                        artname = rs.getString("artname");
                        menge = rs.getString("menge");
                        einheit = rs.getString("einheit");
                        geraetebezeichnung = rs.getString("geraetebezeichnung");
                        zubereitungszeit = rs.getString("zubereitungszeit");
                        rezeptbeschreibung = rs.getString("rezeptbeschreibung");
                        
                        
                        artnamen.add(artname);
                        mengen.add(menge);
                        einheiten.add(einheit);
                        artids.add(artid);
                    }

                    rezepte.add(new Rezept(rezeptid, rezeptname, artids, artnamen, mengen, einheiten, geraetebezeichnung, zubereitungszeit, rezeptbeschreibung));

                } catch (SQLException ex) {
                    response.getWriter().println(ex.getMessage());
                }
            }
            
            request.setAttribute("rezepte", rezepte);
            pool.releaseConnection(conn);
            }
        RequestDispatcher view = request.getRequestDispatcher("history.jsp");
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
