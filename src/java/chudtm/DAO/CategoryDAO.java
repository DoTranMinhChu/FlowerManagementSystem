/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chudtm.DAO;

import chudtm.DTO.Category;
import chudtm.UTILS.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ohmin
 */
public class CategoryDAO {

    public static ArrayList<Category> getCategories() {
        ArrayList<Category> list = new ArrayList<>();
        try {
            Connection cn = DBUtils.makeConnection();

            if (cn != null) {
                String sqlQuery = "SELECT cateID,cateName \n"
                        + "FROM Categories";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sqlQuery);
                while (rs.next()) {
                    int cateID = rs.getInt("cateID");
                    String cateName = rs.getString("cateName");
                    Category category = new Category(cateID, cateName);
                    list.add(category);
                }
                cn.close();
                return list;
            }
        } catch (Exception e) {
            return null;
        }
        return list;
    }

    public static ArrayList<Category> getCategories(String keyword, String searchby) {
        ArrayList<Category> list = new ArrayList<>();
        try {
            Connection cn = DBUtils.makeConnection();

            if (cn != null) {
                String sqlQuery = "SELECT cateID,cateName \n"
                        + "FROM Categories";
                if (!keyword.equals("") && searchby.equalsIgnoreCase("cateID")) {
                    sqlQuery = sqlQuery + " where cateID = ?";
                } else if (!keyword.equals("") && searchby != "") {
                    sqlQuery = sqlQuery + " where " + searchby + " like ?";
                }
                PreparedStatement pst = cn.prepareStatement(sqlQuery);
                if (!keyword.equals("") && searchby.equalsIgnoreCase("cateID")) {
                    pst.setString(1, keyword);
                } else if (!keyword.equals("") && searchby != "") {
                    pst.setString(1, "%" + keyword + "%");
                }

                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    int cateID = rs.getInt("cateID");
                    String cateName = rs.getString("cateName");
                    Category category = new Category(cateID, cateName);
                    list.add(category);
                }
                cn.close();
                return list;
            }
        } catch (Exception e) {
            return null;
        }
        return list;
    }

    public static boolean updateCate(int cateID, String cateName) {
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sqlQuery = "UPDATE Categories\n"
                        + "SET cateName=? \n"
                        + "WHERE cateID = ?";
                PreparedStatement pst = cn.prepareStatement(sqlQuery);
                pst.setNString(1, cateName);
                pst.setInt(2, cateID); 
                int rs = pst.executeUpdate();
                if (rs == 0) {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean insertCate(String cateName) {
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sqlQuery = "INSERT INTO Categories(cateName)\n"
                        + "VALUES (?)";
                PreparedStatement pst = cn.prepareStatement(sqlQuery);
                pst.setNString(1, cateName);
               
                int rs = pst.executeUpdate();
                if (rs == 0) {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean removeCate(int cateID) {
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sqlQuery = "DELETE FROM Categories WHERE cateID=?";
                PreparedStatement pst = cn.prepareStatement(sqlQuery);
                pst.setInt(1, cateID);
                int rs = pst.executeUpdate();
                if (rs == 0) {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
