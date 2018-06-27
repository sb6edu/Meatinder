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
import java.sql.SQLException;
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
@WebServlet(name = "Ctrldelete", urlPatterns = {"/ctrldelete.do"})
public class Ctrldelete extends HttpServlet {

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
        int rid = 0;
        try {
            rid = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException ex) {
            throw new RuntimeException("Ungültiger Löschversuch!");
        }
        String sql3 = "delete from rezepte where id=?";
        String sql2 = "delete from rezeptartikel where rid = ?";
        String sql = "delete from eigenerezepte where rid = ?";
        String sql4 = "delete from kundenrezepte where rezid = ?";
        DBConnectionPool pool = (DBConnectionPool) getServletContext().getAttribute("pool");
        Connection conn = pool.getConnection();

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
            
            PreparedStatement pstm3 = conn.prepareStatement(sql3);
            pstm3.setInt(1, rid);
            pstm3.executeUpdate();
            
            

        } catch (SQLException ex) {

        }

        pool.releaseConnection(conn);

        RequestDispatcher view = request.getRequestDispatcher("ctrlerstellt.do");
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
