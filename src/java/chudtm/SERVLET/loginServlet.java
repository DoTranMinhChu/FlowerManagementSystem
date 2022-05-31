/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chudtm.SERVLET;

import chudtm.DAO.AccountDAO;
import chudtm.DTO.Account;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author o
 */
public class loginServlet extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            String save = request.getParameter("saveLogin");

            Account acc = null;
            try {
                if (email == null || email.equals("") || password == null || password.equals("")) {
                    Cookie[] cookies = request.getCookies();
                    String token = "";
                    if (cookies != null) {
                        for (Cookie cookie : cookies) {
                            if (cookie.getName().equals("selector")) {
                                token = cookie.getValue();
                            }
                        }
                    }
                    if (!token.equals("")) {
                        acc = AccountDAO.getAccount(token);
                    } else {
                        response.sendRedirect("invalid.html");
                    }
                } else {
                    acc = AccountDAO.getAccount(email, password);
                }
                if (acc == null) {
                    response.sendRedirect("invalid.html");
                } else {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("account", acc);
                    if (save != null) {
                        String token = String.format("%x", (int) (Math.random() * 20000000)) + email.hashCode();
                        AccountDAO.updateToken(token, email);
                        Cookie cookie = new Cookie("selector", token);
                        cookie.setMaxAge(60 * 2);
                        response.addCookie(cookie);
                    }
                    switch (acc.getRole()) {
                        case 1:
                            response.sendRedirect("admin/adminIndex");
                            break;
                        case 0:
                            response.sendRedirect("user/personalPage");
                            break;
                        default:
                            System.out.println("invalid.html");
                            response.sendRedirect("invalid.html");
                            break;
                    }
                }
            } catch (Exception e) {
                response.sendRedirect("invalid.html");
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
