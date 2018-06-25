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
 * @author 103097
 */
@WebServlet(name = "CtrlEinfuegen", urlPatterns = {"/CtrlEinfuegen.do"})
public class CtrlEinfuegen extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        zutatenliste(request, response);
        geraeteliste(request, response);
        
            RequestDispatcher view = request.getRequestDispatcher("eigeneRezepte.jsp");
        view.forward(request,response);
        
        
          
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
        pool.releaseConnection(conn);
        request.setAttribute("artikels", artikels);
        return artikels;
    }
     
     private ArrayList geraeteliste(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DBConnectionPool pool = (DBConnectionPool) getServletContext().getAttribute("pool");
        Connection conn = pool.getConnection();
        String sql = "select * from geraete";
        ArrayList<Geraet> geraetes = new ArrayList<>();
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
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
         
        pool.releaseConnection(conn);
        request.setAttribute("geraetes", geraetes);
        return geraetes;
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
