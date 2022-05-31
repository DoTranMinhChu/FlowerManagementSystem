/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chudtm.SERVLET;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author o
 */
public class searchServlet extends HttpServlet {

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
        try {

            String keyword = request.getParameter("keyword");
            String searchby = request.getParameter("searchby");
            String page = request.getParameter("page");
            String searchdateby = request.getParameter("searchdateby");
            String fromDate = request.getParameter("fromDate");
            String toDate = request.getParameter("toDate");
            String changeIndex = request.getParameter("changeIndex");

            int index = Integer.parseInt(request.getParameter("index") == null ? "1" : request.getParameter("index"));

            index = "-".equals(changeIndex) ? index - 1 : ("+".equals(changeIndex) ? index + 1 : index);

            request.setAttribute("keyword", keyword.trim() == "" ? null : keyword);
            request.setAttribute("searchby", searchby == "" ? null : searchby);
            request.setAttribute("searchdateby", searchdateby == "" ? null : searchdateby);
            request.setAttribute("fromDate", fromDate == "" ? null : fromDate);
            request.setAttribute("toDate", toDate == "" ? null : toDate);
            request.setAttribute("index", index);

            request.getRequestDispatcher(page).forward(request, response);
        } catch (Exception ex) {
            System.out.println("loi roi ne");
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
