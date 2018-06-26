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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 103095
 */
@WebServlet(name = "CtrlLogin", urlPatterns = {"/ctrllogin.do"})
public class CtrlLogin extends HttpServlet {

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
        /*To do: Funktion für zufällige Salts schreiben
        java hashing lib sha3 oder besser finden
        hier implementieren
        Datenbank für Salt erweitern
        Token anstatt cookie verwenden
        */
        String uname = request.getParameter("uname");
        String psw = request.getParameter("psw");
        String vorname = request.getParameter("vorname");
        String passwort = "Wird weiter unten aus der Datenbank geholt, hier nur initialisiert";
        DBConnectionPool pool = (DBConnectionPool) getServletContext().getAttribute("pool");
        Connection conn = pool.getConnection();

        String sql = "select PASSWORT from KUNDEN where USERNAME="+"'"+uname+"'";

        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                passwort = rs.getString("PASSWORT");
            } else {
                RequestDispatcher view = request.getRequestDispatcher("login.jsp");
                view.forward(request, response);
            }
            if (passwort.equals(psw)) {
                Cookie u = new Cookie("User", uname);
                u.setMaxAge(120);
                response.addCookie(u);
                HttpSession session = request.getSession();
                String sid = session.getId(); 
                session.setMaxInactiveInterval(2 * 60 * 60);//2 Stunden
                sql = "update kunden set sid  = " + "'" + sid + "'" + " where username = " + "'" + uname + "'";
                try {
                    pstm = conn.prepareStatement(sql);
                    pstm.executeUpdate();
                } catch (Exception ex) {
                    response.getWriter().println(ex.getMessage());
                }
                RequestDispatcher view = request.getRequestDispatcher("ctrlselect.do");
                view.forward(request, response);
            } else {
                uname = "";
                psw = "";
                RequestDispatcher view = request.getRequestDispatcher("failedlogin.jsp");
                view.forward(request, response);
            }

        } catch (SQLException ex) {
            response.getWriter().println(ex.getMessage());
        }

        //RequestDispatcher view = request.getRequestDispatcher("ctrlselect.do");
        //view.forward(request, response);
        pool.releaseConnection(conn);
    }

    public boolean eingeloggt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String sid = session.getId();
        try {
            DBConnectionPool pool = (DBConnectionPool) getServletContext().getAttribute("pool");
            Connection conn = pool.getConnection();
            String sql = "select sid from kunden where sid = " + "'" + sid + "'";
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            pool.releaseConnection(conn);
            if (rs.next()) {
                String dsid = rs.getString("sid");
                if(dsid.equals(sid)){
                    return true;
                }
        }}
        catch(SQLException ex){
            response.getWriter().println(ex.getMessage());
        }
        return false;
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
