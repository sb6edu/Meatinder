/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spe.mch;

/**
 *
 * @author 103098
 */
import java.sql.*;
import java.util.Stack;

/**
 *
 * @author 103098
 */
public class DBConnectionPool {
    private static final int MAX_CONNECTIONS = 5;
    private static DBConnectionPool pool = null;
    private final Stack<Connection> myDBStack = new Stack<>();
    
    //Singleton ==> Konstruktor muss private sein!
    private DBConnectionPool(){}
    
    public synchronized Connection getConnection(){
        Connection conn = null;
        if(myDBStack.empty()) {
            conn = createConnection();
        } else {
            conn = myDBStack.pop();
        }
        
        return conn;
    }
    
    private synchronized Connection createConnection(){
        String url = "jdbc:derby://localhost:1527/Meatinder";
        String user = "admin11";
        String passwort = "admin";
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url, user, passwort);
        } 
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return conn;
    }
    
    public synchronized void releaseConnection(Connection conn){
        try{
            if(conn != null && !conn.isClosed() && myDBStack.size() < MAX_CONNECTIONS){
                conn.commit();
                myDBStack.push(conn);
            }
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public static synchronized DBConnectionPool createConnectionPool(){
        if(pool==null){
            pool = new DBConnectionPool();
        }
        
        return pool;
    }
}

