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

/**
 *
 * @author 103095
 */
@WebServlet(name = "Ctrlsearch", urlPatterns = {"/ctrlsearch.do"})
public class Ctrlsearch extends HttpServlet {

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
        String suche = request.getParameter("search");
        System.out.println(suche);
        String sql1 = "select username from kunden where username = " + "'" + suche + "'";
        String sql2 = "select rezeptname from rezepte where rezeptname = " + "'" + suche + "'";
        DBConnectionPool pool = (DBConnectionPool) getServletContext().getAttribute("pool");
        Connection conn = pool.getConnection();
        ArrayList<ProfilRezept> profilrezepte = new ArrayList<>();
        ArrayList<ProfilUser> profiluser = new ArrayList<>();
        try {
            PreparedStatement pstm = conn.prepareStatement(sql1);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String username = rs.getString("username");
                profiluser.add(new ProfilUser(username));
            }
        } catch (SQLException ex) {
            response.getWriter().println(ex.getMessage());
        }
        try {
            PreparedStatement pstm2 = conn.prepareStatement(sql2);
            ResultSet rs2 = pstm2.executeQuery();
            while (rs2.next()) {
                String rezeptname = rs2.getString("rezeptname");
                profilrezepte.add(new ProfilRezept(rezeptname));
            }
        } catch (SQLException ex) {
            response.getWriter().println(ex.getMessage());
        }
        pool.releaseConnection(conn);
        request.setAttribute("profilrezepte", profilrezepte);
        request.setAttribute("profiluser", profiluser);
        RequestDispatcher view = request.getRequestDispatcher("suchanzeige.jsp");
        view.forward(request, response);
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
