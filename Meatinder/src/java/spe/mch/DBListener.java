/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spe.mch;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author 103098
 */
@WebListener 
public class DBListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
       DBConnectionPool pool = DBConnectionPool.createConnectionPool();
       ServletContext sc = sce.getServletContext();
       sc.setAttribute("pool", pool);
       
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
    
}
