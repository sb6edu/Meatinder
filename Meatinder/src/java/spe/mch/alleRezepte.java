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
import java.util.Collections;
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
@WebServlet(name = "alleRezepte", urlPatterns = {"/allerezepte.do"})
public class alleRezepte extends HttpServlet {

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

        if (CtrlLogin.currentuserisadmin(request, response, getServletContext())) {
            DBConnectionPool pool = (DBConnectionPool) getServletContext().getAttribute("pool");
            Connection conn = pool.getConnection();
            ArrayList<Rezept> rezepte = new ArrayList<>();
            String sql2 = "select id from rezepte";
            int id = 0;
            try {
                PreparedStatement pstm2 = conn.prepareStatement(sql2);
                ResultSet rs2 = pstm2.executeQuery();
                while (rs2.next()) {
                    id++;
                    String sql = "select id, rezeptname, s.artid, artname, menge, einheit, geraetebezeichnung, zubereitungszeit, rezeptbeschreibung from geraete a, artikel s, rezepte d, rezeptartikel f where a.gid = d.gid and s.artid = f.artid and d.id = f.RID and id= " + id;

                    try {
                        PreparedStatement pstm = conn.prepareStatement(sql);
                        ResultSet rs = pstm.executeQuery();
                        String rezeptid = "";
                        String rezeptname = "";
                        int artid = 0;
                        String artname;
                        String menge;
                        String einheit;
                        String geraetebezeichnung = "";
                        String zubereitungszeit = "";
                        String rezeptbeschreibung = "";
                        ArrayList<String> artnamen = new ArrayList<>();
                        ArrayList<String> mengen = new ArrayList<>();
                        ArrayList<String> einheiten = new ArrayList<>();
                        ArrayList<Integer> artids = new ArrayList<>();
                        Boolean eingefuegt = false;

                        while (rs.next()) {
                            rezeptid = rs.getString("id");
                            rezeptname = rs.getString("rezeptname");
                            artid = rs.getInt("artid");
                            artname = rs.getString("artname");
                            menge = rs.getString("menge");
                            einheit = rs.getString("einheit");
                            geraetebezeichnung = rs.getString("geraetebezeichnung");
                            zubereitungszeit = rs.getString("zubereitungszeit");
                            rezeptbeschreibung = rs.getString("rezeptbeschreibung");
                            if (!artname.isEmpty() && !menge.isEmpty() && !einheit.isEmpty() && artid != 0) {
                                artnamen.add(artname);
                                mengen.add(menge);
                                einheiten.add(einheit);
                                artids.add(artid);
                                eingefuegt = true;
                            }
                        }

                        if (eingefuegt) {
                            rezepte.add(new Rezept(rezeptid, rezeptname, artids, artnamen, mengen, einheiten, geraetebezeichnung, zubereitungszeit, rezeptbeschreibung));
                        }
                    } catch (SQLException ex) {
                        response.getWriter().println(ex.getMessage());
                    }
                }
            } catch (Exception ex) {
                response.getWriter().println(ex.getMessage());
            }
            request.setAttribute("rezepte", rezepte);
            pool.releaseConnection(conn);

            for (Rezept rezept : rezepte) {
                System.out.println(rezept);
            }

            ArrayList<Rezept> sortierteRezepte = new ArrayList<>();

            Collections.sort(rezepte);

            ArrayList<Integer> rezid = new ArrayList<>();

            HttpSession session = request.getSession();
            String sid = session.getId();

            if (CtrlLogin.eingeloggt(request, response, getServletContext())) {
                String sql = "select username from kunden where sid = " + "'" + sid + "'";
                String username = "";

                try {
                    PreparedStatement pstm = conn.prepareStatement(sql);
                    ResultSet rs = pstm.executeQuery();
                    while (rs.next()) {
                        username = rs.getString("username");

                        String sql4 = "select rezid from kundenrezepte where username = " + "'" + username + "'";

                        try {
                            PreparedStatement pstm4 = conn.prepareStatement(sql4);
                            ResultSet rs4 = pstm4.executeQuery();

                            while (rs4.next()) {
                                String rezida = rs4.getString("rezid");
                                int rezidaInt = Integer.parseInt(rezida);
                                System.out.println(rezidaInt);
                                rezid.add(rezidaInt);

                            }
                        } catch (SQLException ex) {
                            response.getWriter().println(ex.getMessage());
                        }
                    }
                } catch (SQLException ex) {
                    response.getWriter().println(ex.getMessage());
                }

                ArrayList<Rezept> rezepte2 = new ArrayList<>();
                for (Integer i : rezid) {
                    sql = "select id, rezeptname, s.artid, artname, menge, einheit, geraetebezeichnung, zubereitungszeit, rezeptbeschreibung from geraete a, artikel s, rezepte d, rezeptartikel f where a.gid = d.gid and s.artid = f.artid and d.id = f.RID and id= " + i;

                    try {
                        PreparedStatement pstm = conn.prepareStatement(sql);
                        ResultSet rs = pstm.executeQuery();
                        String rezeptid = "";
                        String rezeptname = "";
                        int artid;
                        String artname;
                        String menge;
                        String einheit;
                        String geraetebezeichnung = "";
                        String zubereitungszeit = "";
                        String rezeptbeschreibung = "";
                        ArrayList<String> artnamen = new ArrayList<>();
                        ArrayList<String> mengen = new ArrayList<>();
                        ArrayList<String> einheiten = new ArrayList<>();
                        ArrayList<Integer> artids = new ArrayList<>();
                        Boolean eingefuegt = false;
                        while (rs.next()) {
                            rezeptid = rs.getString("id");
                            rezeptname = rs.getString("rezeptname");
                            artid = rs.getInt("artid");
                            artname = rs.getString("artname");
                            menge = rs.getString("menge");
                            einheit = rs.getString("einheit");
                            geraetebezeichnung = rs.getString("geraetebezeichnung");
                            zubereitungszeit = rs.getString("zubereitungszeit");
                            rezeptbeschreibung = rs.getString("rezeptbeschreibung");
                            if (!artname.isEmpty() && !menge.isEmpty() && !einheit.isEmpty() && artid != 0) {
                                artnamen.add(artname);
                                mengen.add(menge);
                                einheiten.add(einheit);
                                artids.add(artid);
                                eingefuegt = true;
                            }
                        }
                        if (eingefuegt) {
                            rezepte2.add(new Rezept(rezeptid, rezeptname, artids, artnamen, mengen, einheiten, geraetebezeichnung, zubereitungszeit, rezeptbeschreibung));
                        }
                    } catch (SQLException ex) {
                        response.getWriter().println(ex.getMessage());
                    }
                }
                //response.getWriter().println("test");
                request.setAttribute("rezepte2", rezepte2);

            }
            
            RequestDispatcher view = request.getRequestDispatcher("alleRezepteAdmin.jsp");
            view.forward(request, response);
        } else {
            DBConnectionPool pool = (DBConnectionPool) getServletContext().getAttribute("pool");
            Connection conn = pool.getConnection();
            ArrayList<Rezept> rezepte = new ArrayList<>();
            String sql2 = "select id from rezepte";
            int id = 0;
            try {
                PreparedStatement pstm2 = conn.prepareStatement(sql2);
                ResultSet rs2 = pstm2.executeQuery();
                while (rs2.next()) {
                    id++;
                    String sql = "select id, rezeptname, s.artid, artname, menge, einheit, geraetebezeichnung, zubereitungszeit, rezeptbeschreibung from geraete a, artikel s, rezepte d, rezeptartikel f where a.gid = d.gid and s.artid = f.artid and d.id = f.RID and id= " + id;

                    try {
                        PreparedStatement pstm = conn.prepareStatement(sql);
                        ResultSet rs = pstm.executeQuery();
                        String rezeptid = "";
                        String rezeptname = "";
                        int artid = 0;
                        String artname;
                        String menge;
                        String einheit;
                        String geraetebezeichnung = "";
                        String zubereitungszeit = "";
                        String rezeptbeschreibung = "";
                        ArrayList<String> artnamen = new ArrayList<>();
                        ArrayList<String> mengen = new ArrayList<>();
                        ArrayList<String> einheiten = new ArrayList<>();
                        ArrayList<Integer> artids = new ArrayList<>();
                        Boolean eingefuegt = false;

                        while (rs.next()) {
                            rezeptid = rs.getString("id");
                            rezeptname = rs.getString("rezeptname");
                            artid = rs.getInt("artid");
                            artname = rs.getString("artname");
                            menge = rs.getString("menge");
                            einheit = rs.getString("einheit");
                            geraetebezeichnung = rs.getString("geraetebezeichnung");
                            zubereitungszeit = rs.getString("zubereitungszeit");
                            rezeptbeschreibung = rs.getString("rezeptbeschreibung");
                            if (!artname.isEmpty() && !menge.isEmpty() && !einheit.isEmpty() && artid != 0) {
                                artnamen.add(artname);
                                mengen.add(menge);
                                einheiten.add(einheit);
                                artids.add(artid);
                                eingefuegt = true;
                            }
                        }
                        if (eingefuegt) {
                            rezepte.add(new Rezept(rezeptid, rezeptname, artids, artnamen, mengen, einheiten, geraetebezeichnung, zubereitungszeit, rezeptbeschreibung));
                        }
                    } catch (SQLException ex) {
                        response.getWriter().println(ex.getMessage());
                    }
                }
            } catch (Exception ex) {
                response.getWriter().println(ex.getMessage());
            }
            request.setAttribute("rezepte", rezepte);
            pool.releaseConnection(conn);

            for (Rezept rezept : rezepte) {
                System.out.println(rezept);
            }

            ArrayList<Rezept> sortierteRezepte = new ArrayList<>();

            Collections.sort(rezepte);

            ArrayList<Integer> rezid = new ArrayList<>();

            HttpSession session = request.getSession();
            String sid = session.getId();

            if (CtrlLogin.eingeloggt(request, response, getServletContext())) {
                String sql = "select username from kunden where sid = " + "'" + sid + "'";
                String username = "";

                try {
                    PreparedStatement pstm = conn.prepareStatement(sql);
                    ResultSet rs = pstm.executeQuery();
                    while (rs.next()) {
                        username = rs.getString("username");

                        String sql4 = "select rezid from kundenrezepte where username = " + "'" + username + "'";

                        try {
                            PreparedStatement pstm4 = conn.prepareStatement(sql4);
                            ResultSet rs4 = pstm4.executeQuery();

                            while (rs4.next()) {
                                String rezida = rs4.getString("rezid");
                                int rezidaInt = Integer.parseInt(rezida);
                                System.out.println(rezidaInt);
                                rezid.add(rezidaInt);

                            }
                        } catch (SQLException ex) {
                            response.getWriter().println(ex.getMessage());
                        }
                    }
                } catch (SQLException ex) {
                    response.getWriter().println(ex.getMessage());
                }

                ArrayList<Rezept> rezepte2 = new ArrayList<>();
                for (Integer i : rezid) {
                    sql = "select id, rezeptname, s.artid, artname, menge, einheit, geraetebezeichnung, zubereitungszeit, rezeptbeschreibung from geraete a, artikel s, rezepte d, rezeptartikel f where a.gid = d.gid and s.artid = f.artid and d.id = f.RID and id= " + i;

                    try {
                        PreparedStatement pstm = conn.prepareStatement(sql);
                        ResultSet rs = pstm.executeQuery();
                        String rezeptid = "";
                        String rezeptname = "";
                        int artid;
                        String artname;
                        String menge;
                        String einheit;
                        String geraetebezeichnung = "";
                        String zubereitungszeit = "";
                        String rezeptbeschreibung = "";
                        ArrayList<String> artnamen = new ArrayList<>();
                        ArrayList<String> mengen = new ArrayList<>();
                        ArrayList<String> einheiten = new ArrayList<>();
                        ArrayList<Integer> artids = new ArrayList<>();
                        Boolean eingefuegt = false;
                        while (rs.next()) {
                            rezeptid = rs.getString("id");
                            rezeptname = rs.getString("rezeptname");
                            artid = rs.getInt("artid");
                            artname = rs.getString("artname");
                            menge = rs.getString("menge");
                            einheit = rs.getString("einheit");
                            geraetebezeichnung = rs.getString("geraetebezeichnung");
                            zubereitungszeit = rs.getString("zubereitungszeit");
                            rezeptbeschreibung = rs.getString("rezeptbeschreibung");

                            rezeptname = rezeptname.replaceAll("ä", "&auml;");
                            rezeptname = rezeptname.replaceAll("ö", "&ouml;");
                            rezeptname = rezeptname.replaceAll("ü", "&uuml;");
                            rezeptname = rezeptname.replaceAll("Ä", "&Auml;");
                            rezeptname = rezeptname.replaceAll("Ö", "&Ouml;");
                            rezeptname = rezeptname.replaceAll("Ü", "&Uuml;");

                            artname = artname.replaceAll("ä", "&auml;");
                            artname = artname.replaceAll("ö", "&ouml;");
                            artname = artname.replaceAll("ü", "&uuml;");
                            artname = artname.replaceAll("Ä", "&Auml;");
                            artname = artname.replaceAll("Ö", "&Ouml;");
                            artname = artname.replaceAll("Ü", "&Uuml;");

                            einheit = einheit.replaceAll("ä", "&auml;");
                            einheit = einheit.replaceAll("ö", "&ouml;");
                            einheit = einheit.replaceAll("ü", "&uuml;");
                            einheit = einheit.replaceAll("Ä", "&Auml;");
                            einheit = einheit.replaceAll("Ö", "&Ouml;");
                            einheit = einheit.replaceAll("Ü", "&Uuml;");

                            zubereitungszeit = zubereitungszeit.replaceAll("ä", "&auml;");
                            zubereitungszeit = zubereitungszeit.replaceAll("ö", "&ouml;");
                            zubereitungszeit = zubereitungszeit.replaceAll("ü", "&uuml;");
                            zubereitungszeit = zubereitungszeit.replaceAll("Ä", "&Auml;");
                            zubereitungszeit = zubereitungszeit.replaceAll("Ö", "&Ouml;");
                            zubereitungszeit = zubereitungszeit.replaceAll("Ü", "&Uuml;");

                            rezeptbeschreibung = rezeptbeschreibung.replaceAll("ä", "&auml;");
                            rezeptbeschreibung = rezeptbeschreibung.replaceAll("ö", "&ouml;");
                            rezeptbeschreibung = rezeptbeschreibung.replaceAll("ü", "&uuml;");
                            rezeptbeschreibung = rezeptbeschreibung.replaceAll("Ä", "&Auml;");
                            rezeptbeschreibung = rezeptbeschreibung.replaceAll("Ö", "&Ouml;");
                            rezeptbeschreibung = rezeptbeschreibung.replaceAll("Ü", "&Uuml;");
                            
                            if (!artname.isEmpty() && !menge.isEmpty() && !einheit.isEmpty() && artid != 0) {
                                artnamen.add(artname);
                                mengen.add(menge);
                                einheiten.add(einheit);
                                artids.add(artid);
                                eingefuegt = true;
                            }
                        }
                        if (eingefuegt) {
                            rezepte.add(new Rezept(rezeptid, rezeptname, artids, artnamen, mengen, einheiten, geraetebezeichnung, zubereitungszeit, rezeptbeschreibung));
                        }
                    } catch (SQLException ex) {
                        response.getWriter().println(ex.getMessage());
                    }
                }
                request.setAttribute("rezepte2", rezepte2);
            }
            
            
            RequestDispatcher view = request.getRequestDispatcher("alleRezepte.jsp");
            view.forward(request, response);
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
