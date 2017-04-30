/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.Member;
import business.MemberDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author raefo
 */
public class ClubLogonServlet extends HttpServlet {

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
        
        String url = "/Logon.jsp";
        String msg = "";
        Member m;
        String userId = "";
        long pwAttempt;
        
        /*
        String sql = "";
        
        String dbUrl = "jdbc:mysql://localhost:3306/club?useSSL=false";
        String dbUser = "root";
        String dbPw = "sesame";
        */
        try {
            userId = request.getParameter("userid").trim();
            m = MemberDB.getMemberByID(userId);
            if (m == null) {
                msg = "No member record retrieved<br/>";
            } else {
                //msg = "Member " + m.getLname() + " found.";
                pwAttempt = Long.parseLong(request.getParameter("password").trim());
                m.setPwAttempt(pwAttempt);
                if (m.isAuthenticated()) {
                    url = "/MemberScreen.jsp";
                    request.getSession().setAttribute("m", m);
                } else {
                    msg = "unable to authenticate<br/>";
                }
            }
            //pwAttempt = Long.parseLong(request.getParameter("password").trim());
            /*
            sql = "SELECT * FROM tblMembers WHERE MemId = '" + userId + "'";
            Connection conn = DriverManager.getConnection(dbUrl,dbUser,dbPw);
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ResultSet r = ps.executeQuery(sql);
            if (r.next()) {
                m = new Member();
                m.setMemID(userId);
                m.setPwAttempt(pwAttempt);
                m.setPassword(r.getLong("Password"));
                if (m.isAuthenticated()) {
                    msg = "Member Authenticated!<br/>";
                    m.setLname(r.getString("LastName"));
                    m.setFname(r.getString("FirstName"));
                    m.setMidName(r.getString("MiddleName"));
                    m.setStatus(r.getString("status"));
                    m.setMemDate(r.getString("MemDt"));
                    url = "/MemberScreen.jsp";
                } else {
                    msg = "Member failed to authenticate<br/>";
                }
                request.getSession().setAttribute("m", m);
            } else {
                msg = "User not found in DB<br/>";
            }
            */
        } catch (Exception e) {/*(NumberFormatException e) {
            msg = "Illegal password: "  + e.getMessage() + "<br/>";
        } catch (SQLException e) {
                */
            msg = "Exception: " + e.getMessage() + "<br/>";
        }
        request.setAttribute("msg", msg);
        RequestDispatcher disp = getServletContext().getRequestDispatcher(url);
        disp.forward(request,response);
        /*
        
        Cookie uid = new Cookie("userid",userId);
        uid.setMaxAge(60*10);
        uid.setPath("/");
        response.addCookie(uid);
        
        */
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
