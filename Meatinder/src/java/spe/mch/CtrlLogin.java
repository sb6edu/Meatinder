/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spe.mch;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
        DBConnectionPool pool = (DBConnectionPool) getServletContext().getAttribute("pool");
        Connection conn = pool.getConnection();
        String salt = "bla";

        String uname = request.getParameter("uname");

        String sql = "select salt from kunden where username = " + "'" + uname + "'";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                salt = rs.getString("salt");
                System.out.println("Salt: "+salt);
            }
        } catch (Exception ex) {
            response.getWriter().println(ex.getMessage());
        }
        String psw = pepperedsaltedhashedpw(request, response, request.getParameter("psw"), salt);
        System.out.println("psw:"+psw);
        String vorname = request.getParameter("vorname");
        String passwort = "Wird weiter unten aus der Datenbank geholt, hier nur initialisiert";

        sql = "select PASSWORT from KUNDEN where USERNAME=" + "'" + uname + "'";

        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                passwort = rs.getString("PASSWORT");
                System.out.println("Passwort aus DB: "+passwort);
            } else {
                RequestDispatcher view = request.getRequestDispatcher("login.jsp");
                view.forward(request, response);
            }
            if (passwort.equals(psw)) {
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
                System.out.println("psw: "+psw);
                System.out.println("passwort: "+passwort);
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

    public static boolean eingeloggt(HttpServletRequest request, HttpServletResponse response, ServletContext sc) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String sid = session.getId();

        try {
            DBConnectionPool pool = (DBConnectionPool) sc.getAttribute("pool");
            Connection conn = pool.getConnection();
            String sql = "select sid from kunden where sid = " + "'" + sid + "'";
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            pool.releaseConnection(conn);
            if (rs.next()) {
                String dsid = rs.getString("sid");
                if (dsid.equals(sid)) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            response.getWriter().println(ex.getMessage());
        }
        return false;
    }

    private static String pepperedsaltedhashedpw(HttpServletRequest request, HttpServletResponse response, String passwort, String salt) throws ServletException, IOException {
        String pepperedpasswort = passwort + "4894415610498408940561234196840456489f4asd9f4das1fg";
        String pepperedsaltedpasswort = pepperedpasswort + salt;
        byte[] pepperedsaltedhash = "fjsaoi".getBytes();//nur initialisieren
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            pepperedsaltedhash = digest.digest(
                    pepperedsaltedpasswort.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException no) {
            response.getWriter().println(no.getMessage());
        }

        return bytesToHex(pepperedsaltedhash);
    }
    private static String pepperedhashedpw(HttpServletRequest request, HttpServletResponse response, String passwort) throws ServletException, IOException {
        String pepperedpasswort = passwort + "4894415610498408940561234196840456489f4asd9f4das1fg";
        byte[] pepperedhash = "fjsaoi".getBytes();//nur initialisieren
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            pepperedhash = digest.digest(
                    pepperedpasswort.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException no) {
            response.getWriter().println(no.getMessage());
        }

        return bytesToHex(pepperedhash);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /* Funktionsaufruf:
    if(CtrlLogin.eingeloggt(request, response, getServletContext())){
            
        }
    
     */
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
