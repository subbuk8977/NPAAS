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
import jakarta.servlet.http.HttpSession;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apiiit-rkv
 */
@WebServlet(urlPatterns = {"/subscribe"})
public class subscribe extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException, SQLException, SQLException, SQLException, SQLException {
        
        
        
        response.setContentType("text/html;charset=UTF-8");
        //
        
        
             
             

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
        
        
        String name="";
        String address=request.getParameter("address");
        String news[]=request.getParameterValues("newspaper");
        String newspapers="";
        for(int i=0;i<news.length;i++){
            newspapers+=news[i]+",";
        }
        
        Connection conn=null;
        int status=0;
        ResultSet rs=null;
        Statement st=null;
        try {   
                HttpSession session=request.getSession(false);
                name=(String)session.getAttribute("username");
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/npaas","root","@Bieber8977");
                //String query="select * from user_info where uname=";
                st=conn.createStatement();
                status=st.executeUpdate("insert into subscribe values('"+name+"','"+address+"','"+newspapers+"','"+news.length*1000+"');");
                String query="select * from deliverers where address=?;";
                PreparedStatement ps=conn.prepareStatement(query);
                ps.setString(1,address);
                rs=ps.executeQuery();
                
        }
        catch(Exception ex){
            Logger.getLogger(subscribe.class.getName()).log(Level.SEVERE, null, ex);
                    
        }
//                Logger.getLogger(subscribe.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (ClassNotFoundException ex) {
//            Logger.getLogger(subscribe.class.getName()).log(Level.SEVERE, null, ex);
        try (PrintWriter out = response.getWriter()) {
            
            
            
            int status2=0;
            if(rs.next()){
                    
                
                String id=rs.getString("d_id");
                status2=st.executeUpdate("insert into customerDetails values('"+name+"','"+id+"','"+newspapers+"','"+address+"');");
                out.println("Deliverer Details Found");
                out.println("Deliverer Id :"+id);
            }
            
             if(status == 1){
                out.println("Subscription Added");
                   
             }
             else
                 out.println("Subscription Does not added");
             
             if(status2 ==1)
                 out.println("Details Found");
             else
                 out.println("Details Not Found");
            
            out.println("Name is : "+name);
            out.println("Newspapers are : "+newspapers);
    }   catch (Exception ex) {
            Logger.getLogger(subscribe.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(subscribe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(subscribe.class.getName()).log(Level.SEVERE, null, ex);
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
