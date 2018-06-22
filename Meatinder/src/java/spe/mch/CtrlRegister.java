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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 103095
 */
@WebServlet(name = "Ctrlregister", urlPatterns = {"/ctrlregister.do"})
public class CtrlRegister extends HttpServlet {

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
        String uname = request.getParameter("uname");
        String vorname = request.getParameter("vorname");
        String nachname = request.getParameter("nachname");
        String email = request.getParameter("email");
        String psw1 = request.getParameter("psw1");
        String psw2 = request.getParameter("psw2");

        DBConnectionPool pool = (DBConnectionPool) getServletContext().getAttribute("pool");
        Connection conn = pool.getConnection();

        String sql = "Insert into Kunden (vorname, nachname, username, email, passwort) values(?,?,?,?,?)";
        //Hat er alle Felder ausgefüllt?
        if (warerbrav(vorname, nachname, uname, psw1, psw2, email)) {
            //Ist der Benutzername bereits vergeben?
            if (unamenochfrei(uname)) {
                //Hat er zweimal das gleiche Passwort eingegeben?
                if (psw1.equals(psw2)) {
                    try {
                        PreparedStatement pstm = conn.prepareStatement(sql);
                        pstm.setString(1, vorname);
                        pstm.setString(2, nachname);
                        pstm.setString(3, uname);
                        pstm.setString(4, email);
                        pstm.setString(5, psw1);

                        pstm.executeUpdate();

                    } catch (SQLException ex) {
                    }

                    RequestDispatcher view = request.getRequestDispatcher("login.jsp");
                    view.forward(request, response);
                } else {
                    RequestDispatcher view = request.getRequestDispatcher("failedpwregistrierung.jsp");
                    view.forward(request, response);
                }
            }
            else {
            RequestDispatcher view = request.getRequestDispatcher("select_rezepte.jsp");//hier noch eine jspf für den fall, das es besetzt ist erstellen
            view.forward(request, response);}
        } else {
            RequestDispatcher view = request.getRequestDispatcher("plsallregistrierung.jsp");
            view.forward(request, response);
        }
        pool.releaseConnection(conn);
    }

    public static boolean warerbrav(String... strings) {
        for (String s : strings) {
            if (s == null || s.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public boolean unamenochfrei(String uname) {
        DBConnectionPool pool = (DBConnectionPool) getServletContext().getAttribute("pool");
        Connection conn = pool.getConnection();
        String bla = "";
            try {
                String sql = "select username from kunden where username="+uname;
                PreparedStatement pstm = conn.prepareStatement(sql);
                ResultSet rs = pstm.executeQuery();
                while(rs.next()) {
                    bla = rs.getString("username");
                }
                if(bla.equals(uname)){
                    return false;
                }
                
            } catch (SQLException ex) {
            }
        pool.releaseConnection(conn);
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
