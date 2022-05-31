/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chudtm.SERVLET;

import chudtm.DAO.ParameterDAO;
import chudtm.DAO.PlantDAO;
import chudtm.DTO.Notification;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;

/**
 *
 * @author o
 */
public class updatePlantServlet extends HttpServlet {

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
        ArrayList<Notification> listNotifications = new ArrayList<>();
        ServletContext context = getServletContext();
        String virtualPath = "/images";
        String UPLOAD_DIRECTORY = context.getRealPath(virtualPath);
        HashMap<String, FileItem> paramters = null;
        String plantId = null, fullNameChange = null, categoryChange = null, pathImgChange = null, oldPathImg = null, priceChange = null, statusChange = null, descriptionChange = null, name = null;
        if (ParameterDAO.checkMultipart(request)) {
            paramters = (HashMap<String, FileItem>) request.getAttribute("Parameters");
            String actionUpdate = paramters.get("actionUpdate").getString();
            System.out.println("actionUpdate : " + actionUpdate);
            switch (actionUpdate) {
                case "save":
                    plantId = paramters.get("plantId").getString();
                    fullNameChange = paramters.get("fullNameChange").getString();
                    categoryChange = paramters.get("categoryChange").getString();
                    oldPathImg = paramters.get("oldPathImg").getString();
                    name = paramters.get("imgUpload").getName();

                    if (!(name == null || name == "")) {
                        int indexPoint = name.lastIndexOf(".");
                        name = name.substring(0, indexPoint) + "_" + (new Date().getTime()) + name.substring(indexPoint);

                        try {
                            String path = UPLOAD_DIRECTORY + File.separator + name;
                            try {
                                System.out.println("Old : " + context.getRealPath(oldPathImg));
                                (new File(context.getRealPath(oldPathImg))).delete();
                            } catch (Exception e) {
                                System.out.println("Fail delte old file");
                            }
                            System.out.println("Path : " + path);
                            paramters.get("imgUpload").write(new File(path));

                            pathImgChange = "images/" + name;
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("Fail save inmg");
                        }
                    } else if (!(oldPathImg == null || oldPathImg == "")) {
                        pathImgChange = oldPathImg;
                        pathImgChange = "images/" + oldPathImg.substring(oldPathImg.lastIndexOf("/"));
                    }
                    System.out.println("patjh  : " + pathImgChange);
                    priceChange = paramters.get("priceChange").getString();
                    statusChange = paramters.get("statusChange").getString();
                    descriptionChange = paramters.get("descriptionChange").getString();
                    PlantDAO.updatePlant(Integer.parseInt(plantId), fullNameChange, Integer.parseInt(priceChange), pathImgChange, descriptionChange, Integer.parseInt(statusChange), Integer.parseInt(categoryChange));
                    listNotifications.add(new Notification("success", "Update plant", "Successfully updated plant's information"));
                    break;
                case "remove":
                    plantId = paramters.get("plantId").getString();
                    if (PlantDAO.removePlant(Integer.parseInt(plantId))) {
                        listNotifications.add(new Notification("success", "Remove plant", "Successfully removed plant"));
                    } else {
                        listNotifications.add(new Notification("error", "Remove plant", "Successfully removed plant"));
                        listNotifications.add(new Notification("warning", "Can not remove", "There are some users who are already buying this plant"));
                    }
                    break;
                case "create":

                    fullNameChange = paramters.get("fullNameChange").getString();
                    categoryChange = paramters.get("categoryChange").getString();
                    name = paramters.get("imgUpload").getName();
                    System.out.println("fullNameChange : " + fullNameChange);
                    System.out.println("categoryChange : " + categoryChange);
                    System.out.println("name : " + name);
//                    int indexPoint = name.lastIndexOf(".");
//                    name = name.substring(0, indexPoint) + "_" + (new Date().getTime()) + name.substring(indexPoint);
                    name = "123" + name;
                    try {
                        String path = UPLOAD_DIRECTORY + File.separator + name;
                        paramters.get("imgUpload").write(new File(path));
                    } catch (Exception ex) {

                    }
                    pathImgChange = "images/" + name;
                    priceChange = paramters.get("priceChange").getString();
                    statusChange = paramters.get("statusChange").getString();
                    descriptionChange = paramters.get("descriptionChange").getString();
                    PlantDAO.insertPlant(fullNameChange, Integer.parseInt(priceChange), pathImgChange, descriptionChange, Integer.parseInt(statusChange), Integer.parseInt(categoryChange));
                    listNotifications.add(new Notification("success", "Create plant", "Successfully created plant"));
                    break;
                default:
                    break;
            }
        }
        request.setAttribute("listNotifications", listNotifications);
        System.out.println("123");
        request.getRequestDispatcher("admin/managePlants.jsp").forward(request, response);
        // request.getRequestDispatcher("mainController?action=managePlants").forward(request, response);
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
