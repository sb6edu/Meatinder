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
@WebServlet(name = "CtrlFavoriten", urlPatterns = {"/ctrlfavoriten.do"})
public class CtrlFavoriten extends HttpServlet {

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
        ArrayList<Rezept> rezepte = new ArrayList<>();

        String id = request.getParameter("id");
        int idi = Integer.parseInt(id);
        HttpSession session = request.getSession();
        String sid = session.getId();
        
        String sql = "select username from kunden where sid = " + "'" + sid + "'";
        String username = "";
            
            try {
                PreparedStatement pstm = conn.prepareStatement(sql);
                ResultSet rs = pstm.executeQuery();
                while(rs.next()){
                username = rs.getString("username");
                }
                sql = "insert into favoriten (rid, username) values (?, ?)";
                PreparedStatement pstm2 = conn.prepareStatement(sql);
                
                try {
                    pstm2 = conn.prepareStatement(sql);
                    pstm2.setInt(1, idi);
                    pstm2.setString(2, username);
                    pstm2.executeUpdate();
                } catch (SQLException ex){
                    
                }
            } catch (SQLException ex) {
                
            }
            
            
        sql = "select rezeptname from rezepte where id = " + idi;
        String rezeptname = "";
        try{
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                rezeptname = rs.getString("rezeptname");
            }
        } catch(SQLException ex){
            
        }
        
        
        RequestDispatcher view = request.getRequestDispatcher("rezeptfinden.do?rn="+rezeptname);
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
