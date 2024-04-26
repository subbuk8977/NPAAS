/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apiiit-rkv
 */
@WebServlet(urlPatterns = {"/printSubscribe"})
public class printSubscribe extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        Connection conn=null;
        ResultSet rs=null;
        Statement st=null;
        ResultSetMetaData rsmd=null;
        try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/npaas","root","@Bieber8977");
                //String query="select * from user_info where uname=";
                st=conn.createStatement();
                
                String query="select * from subscribe;";
                rs=st.executeQuery(query); 
                rsmd=rs.getMetaData();
            } catch (SQLException ex) {
                Logger.getLogger(subscribe.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Subscription Details</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<center>");
            out.println("<table border=1 cellpadding=30 cellspacing=5>");
                out.println("<tr><td><b>"+rsmd.getColumnName(1)+"</b></td><td><b>");
                    out.println(rsmd.getColumnName(2)+"</b></td><td><b>");
                    out.println(rsmd.getColumnName(3)+"</b></td><td><b>");
                    out.println(rsmd.getColumnName(4)+"</b></td></tr> ");
                while(rs.next()){
                    
                    out.println("<tr><td>"+rs.getString("username")+"</td><td>");
                    
                    out.println(rs.getString("address")+"</td><td>");
                    
                    out.println(rs.getString("newspaper")+"</td><td>");
                     
                    out.println(rs.getString("cost")+"</td></tr>");
                    
                    
                }
            out.println("</table>");
            out.println("</center>");
            
            out.println("</body>");
            out.println("</html>");
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(printSubscribe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(printSubscribe.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(printSubscribe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(printSubscribe.class.getName()).log(Level.SEVERE, null, ex);
        }
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
