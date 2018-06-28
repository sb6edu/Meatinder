/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spe.mch;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
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

/**
 *
 * @author 103098
 */
@WebServlet(name = "Ctrldeleteupdate", urlPatterns = {"/ctrldeleteupdate.do"})
public class Ctrldeleteupdate extends HttpServlet {

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
        
        
        String rid = request.getParameter("id");
        String sql = "select id, rezeptname, s.artid, artname, menge, einheit, geraetebezeichnung, zubereitungszeit, rezeptbeschreibung from geraete a, artikel s, rezepte d, rezeptartikel f where a.gid = d.gid and s.artid = f.artid and d.id = f.RID and id= " + rid;
        ArrayList<Rezept> rezepte = new ArrayList<>();
        
        
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            String rezeptid = "";
            String rezeptname = "";
            int artid = 0;
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
            
            
            Rezept rezept = new Rezept(rezeptid, rezeptname, artids, artnamen, mengen, einheiten, geraetebezeichnung, zubereitungszeit, rezeptbeschreibung);
            String [ ] mengeArr = new String[mengen.size()];
            String [ ] einheitenArr = new String[einheiten.size()];
            int x = 0;
            for(String in : mengen){
                mengeArr[x] = in;
                x++;
            }
            int y = 0;
            for(String in : einheiten){
                einheitenArr[y] = in;
                y++;
            }
            request.setAttribute("rezept", rezept);
            request.setAttribute("mengeArr", mengeArr);
            request.setAttribute("einheitenArr", einheitenArr);
        } catch (SQLException ex) {

        }
        
        
        pool.releaseConnection(conn);
        
        RequestDispatcher view = request.getRequestDispatcher("rezept_bearbeiten.jsp");
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
