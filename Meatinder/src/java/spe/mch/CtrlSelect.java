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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
@WebServlet(name = "CtrlSelect", urlPatterns = {"/ctrlselect.do"})
public class CtrlSelect extends HttpServlet {

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
        DBConnectionPool pool = (DBConnectionPool)getServletContext().getAttribute("pool");
        Connection conn = pool.getConnection();
        
        String sql = "select * from artikel";
        String sql2 = "select * from geraete";
        
        ArrayList<Artikel> artikels = new ArrayList<>();
        ArrayList<Geraet> geraete = new ArrayList<>();
        
        try{
            PreparedStatement pstm = conn.prepareStatement(sql);
            
            ResultSet rs = pstm.executeQuery();
            
            String artid;
            String artname;
            
            while(rs.next()){
                artid = rs.getString("ARTID");
                artname = rs.getString("ARTNAME");               
                
                artikels.add(new Artikel(artid, artname));
            }
            pool.releaseConnection(conn);
            
            request.setAttribute("artikels", artikels);
            
            PreparedStatement pstm2 = conn.prepareStatement(sql2);
            
            ResultSet rs2 = pstm2.executeQuery();
            
            String gid;
            String geraetebezeichnung;
            
            while(rs2.next()){
                gid = rs2.getString("gid");
                geraetebezeichnung = rs2.getString("geraetebezeichnung");               
                
                geraete.add(new Geraet(gid, geraetebezeichnung));
            }
            pool.releaseConnection(conn);
            
            request.setAttribute("geraete", geraete);
            
            RequestDispatcher view = request.getRequestDispatcher("select_rezepte.jsp");
            view.forward(request,response);
            
        }
        catch(SQLException ex){
            
        }
        
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
