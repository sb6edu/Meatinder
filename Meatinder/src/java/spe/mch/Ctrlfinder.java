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

        String nudeln = request.getParameter("Tomaten");
        response.getWriter().println(nudeln);
        try {
            if (nudeln.equals("Tomaten")) {
                RequestDispatcher view = request.getRequestDispatcher("login.jsp");
                view.forward(request, response);
            } else {
                RequestDispatcher view = request.getRequestDispatcher("registrierung.jsp");
                view.forward(request, response);
            }
        } catch (NullPointerException ex) {
            response.getWriter().println(ex.getMessage());
        }
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

    private ArrayList inventarliste(HttpServletRequest request, HttpServletResponse response, ArrayList<Artikel> artikels)
            throws ServletException, IOException {
        ArrayList<Artikel> inventar = new ArrayList<>();
        for (Artikel artikel : artikels) {
            String artikelname = artikel.getArtname();
            String zutat = request.getParameter(artikelname);
            try {
                if (artikelname.equals(zutat)) {
                    inventar.add(artikel);
                }
            } catch (NullPointerException ex) {
                response.getWriter().println(ex.getMessage());
            }
        }
        return inventar;
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
}
