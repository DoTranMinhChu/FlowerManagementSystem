/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chudtm.DAO;

import chudtm.DTO.Account;
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
public class AccountDAO {

    public static Account getInfoAccount(String emailCheck) {
        Account acc = null;
        try {
            //step 1 : Create connection
            Connection cn = DBUtils.makeConnection();
            //step 2 : SQL query
            if (cn != null) {
                String sqlQuery = "SELECT accID, email,password,fullname,phone,status,role \n"
                        + "FROM Accounts \n"
                        + "WHERE status=1 AND email = ? COLLATE Latin1_General_CS_AS";
                PreparedStatement pst = cn.prepareStatement(sqlQuery);
                pst.setString(1, emailCheck);
                ResultSet rs = pst.executeQuery();
                // step 3 : 
                if (!rs.next()) {
                    return null;
                } else {
                    int accID = rs.getInt("accID");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String fullname = rs.getString("fullname");
                    String phone = rs.getString("phone");
                    int status = rs.getInt("status");
                    int role = rs.getInt("role");
                    acc = new Account(accID, email, password,
                            fullname, phone, status, role);
                    return acc;
                }

            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return acc;
        }
    }

    public static Account getAccount(String emailCheck, String passwordCheck) {
        Account acc = null;
        try {
            //step 1 : Create connection
            Connection cn = DBUtils.makeConnection();
            //step 2 : SQL query
            if (cn != null) {
                String sqlQuery = "SELECT accID, email,password,fullname,phone,status,role \n"
                        + "FROM Accounts \n"
                        + "WHERE status=1 AND email = ? AND password= ? COLLATE Latin1_General_CS_AS";
                PreparedStatement pst = cn.prepareStatement(sqlQuery);
                pst.setString(1, emailCheck);
                pst.setString(2, passwordCheck);
                ResultSet rs = pst.executeQuery();
                // step 3 : 
                if (!rs.next()) {
                    return null;
                } else {
                    int accID = rs.getInt("accID");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String fullname = rs.getString("fullname");
                    String phone = rs.getString("phone");
                    int status = rs.getInt("status");
                    int role = rs.getInt("role");
                    acc = new Account(accID, email, password,
                            fullname, phone, status, role);
                    return acc;
                }

            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return acc;
        }
    }

    public static Account getAccount(String token) {
        Account acc = null;
        try {
            //step 1 : Create connection
            Connection cn = DBUtils.makeConnection();
            //step 2 : SQL query
            if (cn != null) {
                String sqlQuery = "SELECT accID, email,password,fullname,phone,status,role \n"
                        + "FROM Accounts \n"
                        + "WHERE status=1 AND token = ? COLLATE Latin1_General_CS_AS";
                PreparedStatement pst = cn.prepareStatement(sqlQuery);
                pst.setString(1, token);
                ResultSet rs = pst.executeQuery();
                // step 3 : 
                if (!rs.next()) {
                    return null;
                } else {
                    int accID = rs.getInt("accID");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String fullname = rs.getString("fullname");
                    String phone = rs.getString("phone");
                    int status = rs.getInt("status");
                    int role = rs.getInt("role");
                    acc = new Account(accID, email, password,
                            fullname, phone, status, role);
                    return acc;
                }

            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return acc;
        }
    }

    public static ArrayList<Account> getAllAccounts() {
        ArrayList<Account> list = new ArrayList<>();
        try {
            //step 1 : Create connection
            Connection cn = DBUtils.makeConnection();
            //step 2 : SQL query
            if (cn != null) {
                String sqlQuery = "SELECT accID, email,password,fullname,phone,status,role \n"
                        + "FROM Accounts";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sqlQuery);
                // step 3 : 
                while (rs.next()) {
                    int accID = rs.getInt("accID");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String fullname = rs.getString("fullname");
                    String phone = rs.getString("phone");
                    int status = rs.getInt("status");
                    int role = rs.getInt("role");
                    Account acc = new Account(accID, email, password,
                            fullname, phone, status, role);
                    list.add(acc);

                }
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return list;
        }

    }

    public static ArrayList<Account> getAccounts(String keyword, String searchby) {
        ArrayList<Account> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT accID, email,password,fullname,phone,status,role \n"
                        + "FROM Accounts \n";

                if (searchby != "") {
                    sql = sql + "WHERE Accounts." + searchby.trim() + " like ?";
                }
                PreparedStatement pst = cn.prepareStatement(sql);
                if (searchby != "") {
                    pst.setString(1, "%" + keyword + "%");
                }

                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int accID = rs.getInt("accID");
                        String email = rs.getString("email");
                        String password = rs.getString("password");
                        String fullname = rs.getString("fullname");
                        String phone = rs.getString("phone");
                        int status = rs.getInt("status");
                        int role = rs.getInt("role");
                        Account acc = new Account(accID, email, password,
                                fullname, phone, status, role);
                        list.add(acc);
                    }
                }
                return list;
            }

        } catch (Exception e) {
            return null;
        }
        return list;
    }

    public static boolean updateAccountStatus(String emailCheck, int newStatus) {
        try {
            if (newStatus > 1 || newStatus < 0) {
                return false;
            }
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sqlQuery = "UPDATE Accounts\n"
                        + "SET status = ? \n"
                        + "WHERE email= ?";
                PreparedStatement pst = cn.prepareStatement(sqlQuery);
                pst.setInt(1, newStatus);
                pst.setString(2, emailCheck);
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

    public static boolean updateAccount(String email, String newPassword,
            String newFullname, String newPhone) {
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sqlQuery = "UPDATE Accounts\n"
                        + "SET password= ? ,\n"
                        + "fullname= ? ,\n"
                        + "phone= ? \n"
                        + "WHERE email= ? ";
                PreparedStatement pst = cn.prepareStatement(sqlQuery);
                pst.setString(1, newPassword);
                pst.setString(2, newFullname);
                pst.setString(3, newPhone);
                pst.setString(4, email);
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

    public static boolean insertAccount(String newEmail, String newPassword,
            String newFullname, String newPhone, int newSatus, int newRole) {
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sqlQuery = "INSERT INTO Accounts(email,password,fullname,phone,status,role)\n"
                        + "VALUES (?,?,?,?,?,?)";
                PreparedStatement pst = cn.prepareStatement(sqlQuery);
                pst.setString(1, newEmail);
                pst.setString(2, newPassword);
                pst.setString(3, newFullname);
                pst.setString(4, newPhone);
                pst.setInt(5, newSatus);
                pst.setInt(6, newRole);
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

    public static boolean updateToken(String token, String email) {
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sqlQuery = "UPDATE Accounts\n"
                        + "SET token = ?\n"
                        + "WHERE email = ?";
                PreparedStatement pst = cn.prepareStatement(sqlQuery);
                pst.setString(1, token);
                pst.setString(2, email);

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

    public static int getRole(String email) {
        int role = -1;
        try {
            //step 1 : Create connection
            Connection cn = DBUtils.makeConnection();
            //step 2 : SQL query
            if (cn != null) {
                String sqlQuery = "SELECT role \n"
                        + "FROM Accounts \n"
                        + "WHERE email=?  COLLATE Latin1_General_CS_AS";
                PreparedStatement pst = cn.prepareStatement(sqlQuery);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                // step 3 : 
                if (!rs.next()) {
                    role = -1;
                } else {
                    role = rs.getInt("role");
                }
            }
            cn.close();
            return role;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }
}
