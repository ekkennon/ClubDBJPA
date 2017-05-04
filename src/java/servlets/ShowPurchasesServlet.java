/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import business.Member;
import business.Purchase;
import business.PurchaseDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author raefo
 */
public class ShowPurchasesServlet extends HttpServlet {

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
        
        String url = "/MemberScreen.jsp";
        List<Purchase> p;
        String msg = "";
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
        Date pd = null;
        String mo = "";
        String day = "";
        String year = "";
        
        try {
            Member m = (Member) request.getSession().getAttribute("m");
            
            mo = request.getParameter("month");
            day = request.getParameter("day");
            year = request.getParameter("year");
            
            if (!mo.isEmpty() && !day.isEmpty() && !year.isEmpty()) {
                try {
                pd = formatter.parse(mo + "-" + day + "-" + year);
                } catch (ParseException e) {
                    pd = null;
                }
            }
            
            if (pd == null) {
                p = PurchaseDB.getPurchases(m.getMemID());
            } else {
                p = PurchaseDB.getPurchases(m.getMemID(), pd);
            }
            
            if (p == null) {
                msg = "Purchases list returned null<br/>";
            } else {
                msg = p.size() + " purchase records.";
                url = "/Purchases.jsp";
                request.setAttribute("purchases", p);
            }
        } catch (Exception e) {
            msg = "Servlet Exception: " + e.getMessage() + "<br/>";
        }
        
        request.setAttribute("msg", msg);
        RequestDispatcher disp = getServletContext().getRequestDispatcher(url);
        disp.forward(request, response);
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
