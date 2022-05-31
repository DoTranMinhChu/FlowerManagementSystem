/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chudtm.SERVLET;

import chudtm.DAO.ParameterDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;

/**
 *
 * @author o
 */
public class mainController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String url = "errorpage.html";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String action = null;
            if (ParameterDAO.checkMultipart(request)) {
                request.setAttribute("Parameters", ParameterDAO.getMultipartParameters(request));
                action = ((HashMap<String, FileItem>) request.getAttribute("Parameters")).get("action").getString();

            } else {
                request.setAttribute("Parameters", ParameterDAO.getParameters(request));
                action = ((HashMap<String, String>) request.getAttribute("Parameters")).get("action");
            }

            System.out.println("action : " + action);
            
            if (action == null) {
                url = "index.jsp";
            } else {
                switch (action) {
                    case "":
                        url = "index.jsp";
                        break;
                    case "search":
                        url = "searchServlet";
                        System.out.println("searhc");
                        break;
                    case "login": 
                        url = "loginServlet";
                        break;
                    case "register":
                        url = "registerServlet";
                        break;
                    case "logout":
                        url = "index.jsp";
                        HttpSession session = request.getSession(true);
                        session.invalidate();
                        break;
                    case "viewcart":
                        url = "viewCart.jsp";
                        break;
                    case "addtocart":
                        url = "addToCartServlet";
                        break;
                    case "updateCart":
                        url = "updateCartServlet";
                        break;
                    case "saveOrder":
                        url = "saveShoppingCartServlet";
                        break;
                    case "updateCartServlet":
                        url = "index";
                        break;
                    case "manageAccounts":
                        url = "manageAccountsServlet";
                        break;
                    case "updateStatusAccount":
                        url = "updateStatusAccountServlet";
                        break;
                    case "updateStatusOrder":
                        url = "updateStatusOrderServlet";
                        break;
                    case "updatePlant":
                        url = "updatePlantServlet";
                        break;
                    case "updateCategory":
                        url = "updateCategoryServlet";
                        break;
                    case "save":
                        url = "updateAccountServlet";
                        break;
                    case "cancel":
                        url = "personalPage.jsp";
                        break;
                    case "manageOrders":
                        url = "manageOrdersServlet";
                        break;
                    case "managePlants":
                        url = "managePlants.jsp";
                        break;
                    case "manageCategories":
                        url = "manageCategories.jsp";
                        break;
                    case "personalPage":
                        url = "personalPage.jsp";
                        break;
                    default:
                        url = "index.jsp";
                        break;
                }
            }
            //action = updatePlant + "Servlet" => 
            // path = path + ".jsp"

           
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
