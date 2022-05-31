/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chudtm.DAO;

import chudtm.DTO.Plant;
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
public class PlantDAO {

    public static Plant getPlant(int pid) {
        Plant plant = null;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();

            if (cn != null) {
                String sql = "select PID,PName,price,imgPath,description,status,Plants.CateID as 'CateID',CateName\n"
                        + "from Plants join Categories on Plants.CateID=Categories.CateID\n"
                        + "where Plants.PID=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, pid);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {

                    int id = rs.getInt("PID");
                    String name = rs.getString("PName");
                    int price = rs.getInt("price");
                    String imgpath = rs.getString("imgPath");
                    String description = rs.getString("description");
                    int status = rs.getInt("status");
                    int cateid = rs.getInt("CateID");
                    String catename = rs.getString("CateName");
                    plant = new Plant(id, name, price, imgpath, description, status, cateid, catename);

                }
                return plant;
            }

        } catch (Exception e) {
            return null;
        }
        return plant;
    }

    public static ArrayList<Plant> getPlants(String keyword, String searchby) {
        ArrayList<Plant> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select PID,PName,price,imgPath,description,status,Plants.CateID as 'CateID',CateName\n"
                        + "from Plants join Categories on Plants.CateID=Categories.CateID\n";
                if (!keyword.equals("") && searchby.equalsIgnoreCase("status")) {
                    sql = sql + " where status = ?";
                } else if (!keyword.equals("") && searchby != "") {
                    sql = sql + " where " + searchby + " like ?";
                }
                PreparedStatement pst = cn.prepareStatement(sql);
                if (!keyword.equals("") && searchby.equalsIgnoreCase("status")) {
                    pst.setString(1, keyword);
                } else if (!keyword.equals("") && searchby != "") {
                    pst.setString(1, "%" + keyword + "%");
                }

                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt("PID");
                        String name = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgpath = rs.getString("imgPath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateid = rs.getInt("CateID");
                        String catename = rs.getString("CateName");
                        Plant plant = new Plant(id, name, price, imgpath, description, status, cateid, catename);
                        list.add(plant);
                    }
                }
                return list;
            }

        } catch (Exception e) {
            return null;
        }
        return list;
    }

    public static boolean updatePlant(int plantID, String plantName, int price, String imgPath, String description, int status, int cateID) {
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sqlQuery = "UPDATE Plants\n"
                        + "SET PName=?,price=?,imgPath=?,description=?,status=?,cateID=? \n"
                        + "WHERE PID = ?";
                PreparedStatement pst = cn.prepareStatement(sqlQuery);
                pst.setNString(1, plantName);
                pst.setInt(2, price);
                pst.setString(3, imgPath);
                pst.setNString(4, description);
                pst.setInt(5, status);
                pst.setInt(6, cateID);
                pst.setInt(7, plantID);
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

    public static boolean insertPlant(String PName, int price,
            String imgPath, String description, int status, int cateID) {
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sqlQuery = "INSERT INTO Plants(PName, price, imgPath, description,status,cateID)\n"
                        + "VALUES (?,?,?,?,?,?)";
                PreparedStatement pst = cn.prepareStatement(sqlQuery);
                pst.setNString(1, PName);
                pst.setInt(2, price);
                pst.setString(3, imgPath);
                pst.setNString(4, description);
                pst.setInt(5, status);
                pst.setInt(6, cateID);
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

    public static boolean removePlant(int plantID) {
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sqlQuery = "DELETE FROM Plants WHERE PID=?";
                PreparedStatement pst = cn.prepareStatement(sqlQuery);
                pst.setInt(1, plantID);
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
