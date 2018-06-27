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
import java.sql.SQLException;
import java.util.Random;
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
        String salt = generateSalt();
        System.out.println(salt);
        System.out.println(psw1);
        

        DBConnectionPool pool = (DBConnectionPool) getServletContext().getAttribute("pool");
        Connection conn = pool.getConnection();

        String sql = "Insert into Kunden (vorname, nachname, username, email, passwort, salt) values(?,?,?,?,?,?)";
        //Hat er alle Felder ausgefüllt?
        if (warerbrav(vorname, nachname, uname, psw1, psw2, email)) {
            //Hat er zweimal das gleiche Passwort eingegeben?
            if (psw1.equals(psw2)) {
                try {
                    psw1 = pepperedsaltedhashedpw(request, response, psw1, salt);
                    System.out.println(psw1);
                    PreparedStatement pstm = conn.prepareStatement(sql);
                    pstm.setString(1, vorname);
                    pstm.setString(2, nachname);
                    pstm.setString(3, uname);
                    pstm.setString(4, email);
                    pstm.setString(5, psw1);
                    pstm.setString(6, salt);

                    pstm.executeUpdate();

                    RequestDispatcher view = request.getRequestDispatcher("login.jsp");
                    view.forward(request, response);

                } catch (SQLException ex) {
                    RequestDispatcher view = request.getRequestDispatcher("schonvergeben.jsp");
                    view.forward(request, response);
                }
            } else {
                RequestDispatcher view = request.getRequestDispatcher("failedpwregistrierung.jsp");
                view.forward(request, response);
            }
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
    private static String pepperedsaltedhashedpw(HttpServletRequest request, HttpServletResponse response, String passwort,String salt) throws ServletException, IOException {
        String pepperedpasswort = passwort+"4894415610498408940561234196840456489f4asd9f4das1fg";
        String pepperedsaltedpasswort = pepperedpasswort+salt;
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
    public static String generateSalt() {
    //Nur Buchstaben fürs Salz
    int leftLimit = 65; // Buchstabe A
    int rightLimit = 122; // Buchstabe z
    int targetStringLength = 64;
    Random random = new Random();
    StringBuilder buffer = new StringBuilder(targetStringLength);
    for (int i = 0; i < targetStringLength; i++) {
        int randomLimitedInt = leftLimit + (int) 
          (random.nextFloat() * (rightLimit - leftLimit + 1));
        buffer.append((char) randomLimitedInt);
    }
    String generatedString = buffer.toString();
 
    return generatedString;
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
    /*public static String encrypt(String passwort,String strKey) throws Exception{
	String strData="";
	
	try {
		SecretKeySpec skeyspec=new SecretKeySpec(strKey.getBytes(),"Blowfish");
		Cipher cipher=Cipher.getInstance("Blowfish");
		cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
		byte[] encrypted=cipher.doFinal(strClearText.getBytes());
		strData=new String(encrypted);
		
	} catch (Exception e) {
		e.printStackTrace();
		throw new Exception(e);
	}
	return strData;
}*/
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
