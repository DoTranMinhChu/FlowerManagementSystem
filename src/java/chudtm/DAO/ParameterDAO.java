/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chudtm.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author ohmin
 */
public class ParameterDAO {

    public static HashMap<String, FileItem> getMultipartParameters(HttpServletRequest request) {

        HashMap<String, FileItem> parameters = new HashMap<>();
        try {
            List<FileItem> multiparts = new ServletFileUpload(
                    new DiskFileItemFactory()).parseRequest(request);
            multiparts.forEach((item) -> {
                System.out.println("item : " + item);
                parameters.put(item.getFieldName(), item);
            });
        } catch (FileUploadException e) {
            System.out.println("ex : " + e);
        } finally {
            return parameters;
        }
    }

    public static HashMap<String, String> getParameters(HttpServletRequest request) {

        Set<String> paramNames = request.getParameterMap().keySet();
        HashMap<String, String> parameters = new HashMap<>();
        for (String name : paramNames) {
            String param = name;
            String value = request.getParameter(param);
            parameters.put(param, value);
        }
        return parameters;
    }

    public static void showMultipartParameters(HashMap<String, FileItem> hashMap) {
        Set<String> keySet = hashMap.keySet();
        keySet.forEach((key) -> {
            System.out.println(key + " - " + hashMap.get(key).getString());
        });
    }

    public static void showParameters(HashMap<String, String> hashMap) {
        Set<String> keySet = hashMap.keySet();
        keySet.forEach((key) -> {
            System.out.println(key + " - " + hashMap.get(key));
        });
    }

    public static boolean checkMultipart(HttpServletRequest request) {
        return ServletFileUpload.isMultipartContent(request);
    }

}
