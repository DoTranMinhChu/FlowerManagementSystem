/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chudtm.SERVLET;

import chudtm.DAO.AccountDAO;
import chudtm.DTO.Notification;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author o
 */
public class registerServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            ArrayList<Notification> listNotifications = new ArrayList<>();
            /* TODO output your page here. You may use following sample code. */
            String newEmail = request.getParameter("txtEmail");
            String newFullname = request.getParameter("txtFullname");
            String newPassword = request.getParameter("txtPassword");
            String newPhone = request.getParameter("txtPhone");

            if (newPhone.contains("[a-zA-Z]+")) {
                request.setAttribute("txtEmail", newEmail);
                request.setAttribute("txtFullname", newFullname);
                request.setAttribute("txtPhone", newPhone);
                listNotifications.add(new Notification("error", "Register", "The phone is invalid"));
                request.setAttribute("listNotifications", listNotifications);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                int status = 1;
                int role = 0;
                if (AccountDAO.insertAccount(newEmail, newPassword, newFullname, newPhone, status, role)) {
                    request.setAttribute("email_newAccount", newEmail);
                    listNotifications.add(new Notification("success", "Register", "Successfully register"));
                    request.setAttribute("listNotifications", listNotifications);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } else {
                    listNotifications.add(new Notification("success", "Register", "Unsuccessfully register"));
                    request.setAttribute("listNotifications", listNotifications);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                  
                }
            }

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
