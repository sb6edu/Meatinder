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
@WebServlet(name = "SturkopfCtrluserverwaltung", urlPatterns = {"/sturkopfctrluserverwaltung.do"})
public class SturkopfCtrluserverwaltung extends HttpServlet {

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
        ArrayList<User> users = new ArrayList<>();
        String sql = "select username from kunden";
        String username = "bla";
        String rechte = "ois";
        String loeschen = "null";
        String a="";
        String b="";
        String bearbeiten="";
        DBConnectionPool pool = (DBConnectionPool) getServletContext().getAttribute("pool");
        Connection conn = pool.getConnection();
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                username = rs.getString("username");
                loeschen = request.getParameter(username + "loeschen");
                if (loeschen != null) {
                    if (loeschen.equals("Löschen")) {
                    } else {
                        String sql2 = "select berechtigung from kunden where username = " + "'" + username + "'";
                        try {
                            PreparedStatement pstm2 = conn.prepareStatement(sql2);
                            ResultSet rs2 = pstm2.executeQuery();
                            rs2.next();
                            rechte = rs2.getString("BERECHTIGUNG");
                            users.add(new User(rechte, username,bearbeiten,a,b));
                        } catch (SQLException ex) {
                            response.getWriter().println(ex.getMessage());
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            response.getWriter().println(ex.getMessage());
        }
        pool.releaseConnection(conn);
        request.setAttribute("users", users);
        RequestDispatcher view = request.getRequestDispatcher("userverwaltung.jsp");
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
