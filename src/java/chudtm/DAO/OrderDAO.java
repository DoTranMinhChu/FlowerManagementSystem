/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chudtm.DAO;

import chudtm.DTO.Order;
import chudtm.DTO.OrderDetail;
import chudtm.UTILS.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author ohmin
 */
public class OrderDAO {

    public static ArrayList<Order> getAllOrder() {
        ArrayList<Order> list = new ArrayList<>();
        try {
            Connection cn = DBUtils.makeConnection();

            if (cn != null) {
                String sqlQuery = "SELECT Orders.orderID,Orders.orderDate,Orders.shipdate,Orders.status,Orders.accID,Orders.total\n"
                        + "FROM Orders";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sqlQuery);
                while (rs.next()) {
                    int orderID = rs.getInt("orderID");
                    String orderDate = rs.getString("orderDate");
                    String shipDate = rs.getString("shipdate");
                    int status = rs.getInt("status");
                    int accID = rs.getInt("accID");
                    int total = rs.getInt("total");
                    Order order = new Order(orderID, orderDate, shipDate, status, accID, total);

                    list.add(order);
                }
                cn.close();
                return list;
            }
        } catch (Exception e) {
            return null;
        }
        return list;
    }

    public static ArrayList<Order> getOrders(String email) {
        ArrayList<Order> list = new ArrayList<>();
        try {
            Connection cn = DBUtils.makeConnection();

            if (cn != null) {
                String sqlQuery = "SELECT Orders.orderID,Orders.orderDate,Orders.shipdate,Orders.status,Orders.accID,Orders.total \n"
                        + "FROM Orders INNER JOIN Accounts \n"
                        + "ON Orders.accID=Accounts.accID \n"
                        + "WHERE Accounts.email= ?";
                PreparedStatement pst = cn.prepareStatement(sqlQuery);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("orderID");
                    String orderDate = rs.getString("orderDate");
                    String shipDate = rs.getString("shipdate");
                    int status = rs.getInt("status");
                    int accID = rs.getInt("accID");
                    int total = rs.getInt("total");
                    Order order = new Order(orderID, orderDate, shipDate, status, accID, total);
                    list.add(order);
                }
                cn.close();
                return list;
            }
        } catch (Exception e) {
            return null;
        }
        return list;
    }

    public static ArrayList<Order> getOrders(String keyword, String searchby) {
        ArrayList<Order> list = new ArrayList<>();
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sqlQuery = "SELECT Orders.orderID, Orders.orderDate, Orders.shipdate, Orders.status, Orders.accID, Orders.total \n"
                        + "FROM Orders\n";

                if (!"".equals(searchby) && !searchby.isEmpty()) {
                    if ("orderDate".equals(searchby) || "shipdate".equals(searchby)) {
                        sqlQuery = sqlQuery + "WHERE Orders." + searchby.trim() + " = ?";

                    } else {
                        sqlQuery = sqlQuery + "WHERE Orders." + searchby.trim() + " like ?";
                    }
                }

                PreparedStatement pst = cn.prepareStatement(sqlQuery);
                pst.setString(1, "%" + keyword + "%");
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("orderID");
                    String orderDate = rs.getString("orderDate");
                    String shipDate = rs.getString("shipdate");
                    int status = rs.getInt("status");
                    int accID = rs.getInt("accID");
                    int total = rs.getInt("total");
                    Order order = new Order(orderID, orderDate, shipDate, status, accID, total);
                    list.add(order);
                }
                cn.close();
                return list;
            }

        } catch (Exception e) {
            return null;
        }
        return list;
    }

    public static ArrayList<Order> getOrders(String keyword, String searchby, String dateFrom, String dateTo, String searchdateby) {
        ArrayList<Order> list = new ArrayList<>();
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sqlQuery = "SELECT Orders.orderID, Orders.orderDate, Orders.shipdate, Orders.status, Orders.accID, Orders.total \n"
                        + "FROM Orders\n";

                if (!dateFrom.isEmpty() && !dateTo.isEmpty()) {
                    sqlQuery = sqlQuery + "WHERE Orders." + searchby.trim() + " like ? AND Orders." + searchdateby + " BETWEEN ? AND ?";
                } else if (!dateFrom.isEmpty() && dateTo.isEmpty()) {
                    sqlQuery = sqlQuery + "WHERE Orders." + searchby.trim() + " like ? AND Orders." + searchdateby + " >= ?";
                } else if (dateFrom.isEmpty() && !dateTo.isEmpty()) {
                    sqlQuery = sqlQuery + "WHERE Orders." + searchby.trim() + " like ? AND Orders." + searchdateby + " <= ? ";
                } else {
                    sqlQuery = sqlQuery + "WHERE Orders." + searchby.trim() + " like ?";
                }
                PreparedStatement pst = cn.prepareStatement(sqlQuery);
                if (!dateFrom.isEmpty() && !dateTo.isEmpty()) {
                    pst.setString(1, "%" + keyword + "%");
                    pst.setString(2, dateFrom);
                    pst.setString(3, dateTo);
                } else if (!dateFrom.isEmpty() && dateTo.isEmpty()) {
                    pst.setString(1, "%" + keyword + "%");
                    pst.setString(2, dateFrom);
                } else if (dateFrom.isEmpty() && !dateTo.isEmpty()) {
                    pst.setString(1, "%" + keyword + "%");
                    pst.setString(2, dateTo);
                } else {
                    pst.setString(1, "%" + keyword + "%");
                }
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    int orderID = rs.getInt("orderID");
                    String orderDate = rs.getString("orderDate");
                    String shipDate = rs.getString("shipdate");
                    int status = rs.getInt("status");
                    int accID = rs.getInt("accID");
                    int total = rs.getInt("total");
                    Order order = new Order(orderID, orderDate, shipDate, status, accID, total);
                    list.add(order);
                }
                cn.close();
                return list;
            }

        } catch (Exception e) {

            return null;
        }
        return list;
    }

    public static ArrayList<Order> getOrders(String accountID, String keyword, String searchby, String dateFrom, String dateTo, String searchdateby) {
        ArrayList<Order> list = new ArrayList<>();
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {

                String sqlQuery = "SELECT Orders.orderID, Orders.orderDate, Orders.shipdate, Orders.status, Orders.accID, Orders.total \n"
                        + "FROM Orders\n"
                        + "WHERE accID = '" + accountID + "' \n";

                if (!dateFrom.isEmpty() && !dateTo.isEmpty()) {
                    sqlQuery = sqlQuery + "AND Orders." + searchby.trim() + " like ? AND Orders." + searchdateby + " BETWEEN ? AND ?";
                } else if (!dateFrom.isEmpty() && dateTo.isEmpty()) {
                    sqlQuery = sqlQuery + "AND Orders." + searchby.trim() + " like ? AND Orders." + searchdateby + " >= ?";
                } else if (dateFrom.isEmpty() && !dateTo.isEmpty()) {
                    sqlQuery = sqlQuery + "AND Orders." + searchby.trim() + " like ? AND Orders." + searchdateby + " <= ? ";
                } else {
                    sqlQuery = sqlQuery + "AND Orders." + searchby.trim() + " like ?";
                }
                PreparedStatement pst = cn.prepareStatement(sqlQuery);
                if (!dateFrom.isEmpty() && !dateTo.isEmpty()) {
                    pst.setString(1, "%" + keyword + "%");
                    pst.setString(2, dateFrom);
                    pst.setString(3, dateTo);
                } else if (!dateFrom.isEmpty() && dateTo.isEmpty()) {
                    pst.setString(1, "%" + keyword + "%");
                    pst.setString(2, dateFrom);
                } else if (dateFrom.isEmpty() && !dateTo.isEmpty()) {
                    pst.setString(1, "%" + keyword + "%");
                    pst.setString(2, dateTo);
                } else {
                    pst.setString(1, "%" + keyword + "%");
                }
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    int orderID = rs.getInt("orderID");
                    String orderDate = rs.getString("orderDate");
                    String shipDate = rs.getString("shipdate");
                    int status = rs.getInt("status");
                    int accID = rs.getInt("accID");
                    int total = rs.getInt("total");
                    Order order = new Order(orderID, orderDate, shipDate, status, accID, total);
                    list.add(order);
                }
                cn.close();
                return list;
            }

        } catch (Exception e) {

            return null;
        }
        return list;
    }

    public static ArrayList<Order> getOrdersByDate(String email, String dateFrom, String dateTo, String searchby) {
        ArrayList<Order> list = new ArrayList<>();
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sqlQuery = "SELECT Orders.orderID,Orders.orderDate,Orders.shipdate,Orders.status,Orders.accID,Orders.total \n"
                        + "FROM Orders INNER JOIN Accounts \n"
                        + "ON Orders.accID=Accounts.accID \n";
                if (!dateFrom.isEmpty() && !dateTo.isEmpty()) {
                    sqlQuery = sqlQuery + "WHERE Accounts.email= ? AND Orders." + searchby + " BETWEEN ? AND ?";
                } else if (!dateFrom.isEmpty() && dateTo.isEmpty()) {
                    sqlQuery = sqlQuery + "WHERE Accounts.email= ? AND Orders." + searchby + " >= ?";
                } else if (dateFrom.isEmpty() && !dateTo.isEmpty()) {
                    sqlQuery = sqlQuery + "WHERE Accounts.email= ? AND Orders." + searchby + " <= ? ";
                } else {
                    sqlQuery = sqlQuery + "WHERE Accounts.email= ?";
                }
                PreparedStatement pst = cn.prepareStatement(sqlQuery);

                if (!dateFrom.isEmpty() && !dateTo.isEmpty()) {
                    pst.setString(1, email);
                    pst.setString(2, dateFrom);
                    pst.setString(3, dateTo);
                } else if (!dateFrom.isEmpty() && dateTo.isEmpty()) {
                    pst.setString(1, email);
                    pst.setString(2, dateFrom);
                } else if (dateFrom.isEmpty() && !dateTo.isEmpty()) {
                    pst.setString(1, email);
                    pst.setString(2, dateTo);
                } else {
                    pst.setString(1, email);
                }
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("orderID");
                    String orderDate = rs.getString("orderDate");
                    String shipDate = rs.getString("shipdate");
                    int status = rs.getInt("status");
                    int accID = rs.getInt("accID");
                    int total = rs.getInt("total");
                    Order order = new Order(orderID, orderDate, shipDate, status, accID, total);
                    list.add(order);
                }
                cn.close();
                return list;
            }

        } catch (Exception e) {
            return null;
        }
        return list;
    }

    public static ArrayList<Order> getOrdersByDate(String dateFrom, String dateTo) {
        ArrayList<Order> list = new ArrayList<>();
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sqlQuery = "SELECT Orders.orderID, Orders.orderDate, Orders.shipdate, Orders.status, Orders.accID, Orders.total\n"
                        + "FROM Orders\n";
                if (!dateFrom.isEmpty() && !dateTo.isEmpty()) {
                    sqlQuery = sqlQuery + "WHERE Orders.orderDate BETWEEN ? AND ?";
                } else if (!dateFrom.isEmpty() && dateTo.isEmpty()) {
                    sqlQuery = sqlQuery + "WHERE Orders.orderDate >= ?";
                } else if (dateFrom.isEmpty() && !dateTo.isEmpty()) {
                    sqlQuery = sqlQuery + "WHERE Orders.orderDate <= ? ";
                }
                PreparedStatement pst = cn.prepareStatement(sqlQuery);

                if (!dateFrom.isEmpty() && !dateTo.isEmpty()) {
                    pst.setString(1, "%" + dateFrom + "%");
                    pst.setString(2, "%" + dateTo + "%");
                } else if (!dateFrom.isEmpty() && dateTo.isEmpty()) {
                    pst.setString(1, "%" + dateFrom + "%");
                } else if (dateFrom.isEmpty() && !dateTo.isEmpty()) {
                    pst.setString(1, "%" + dateTo + "%");
                }
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("orderID");
                    String orderDate = rs.getString("orderDate");
                    String shipDate = rs.getString("shipdate");
                    int status = rs.getInt("status");
                    int accID = rs.getInt("accID");
                    int total = rs.getInt("total");
                    Order order = new Order(orderID, orderDate, shipDate, status, accID, total);
                    list.add(order);
                }
                cn.close();
                return list;
            }

        } catch (Exception e) {
            return null;
        }
        return list;
    }

    public static ArrayList<OrderDetail> getOrderDetail(int orderID) {
        ArrayList<OrderDetail> list = new ArrayList<>();
        try {
            Connection cn = DBUtils.makeConnection();

            if (cn != null) {
                String sqlQuery = "SELECT OrderDetails.detailID,OrderDetails.orderID,OrderDetails.PID,Plants.PName,Plants.price,Plants.imgPath,OrderDetails.quantity \n"
                        + "FROM OrderDetails, Plants\n"
                        + "WHERE orderID=? AND OrderDetails.PID=Plants.PID";
                PreparedStatement pst = cn.prepareStatement(sqlQuery);
                pst.setInt(1, orderID);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    int orderDetailID = rs.getInt("detailID");
                    int orderid = rs.getInt("orderID");
                    int PlantID = rs.getInt("PID");
                    String plantName = rs.getString("PName");
                    int price = rs.getInt("price");
                    String ImgPath = rs.getString("imgPath");
                    int quantity = rs.getInt("quantity");
                    OrderDetail orderDetail = new OrderDetail(orderDetailID, orderid, PlantID, plantName, price, ImgPath, quantity);
                    list.add(orderDetail);
                }
                cn.close();
                return list;
            }
        } catch (Exception e) {
            return null;
        }
        return list;
    }

    public static boolean insertOrder(String email, HashMap<String, Integer> cart) {
        Connection cn = null;
        boolean result = false;
        System.out.println("cart "+ cart.size());
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                int accid = 0, orderid = 0;
                cn.setAutoCommit(false);
                // get accID by Email
                String sql = "select AccID from Accounts where Accounts.email=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    accid = rs.getInt("accID");
                }
                System.out.println("accid : " + accid);
                
                // insert Order
                Date d = new Date(System.currentTimeMillis());
                sql = "insert Orders(OrderDate,status,AccID) values(?,?,?)";
                pst = cn.prepareStatement(sql);
                pst.setDate(1, d);
                pst.setInt(2, 1);
                pst.setInt(3, accid);
                pst.executeUpdate();

                // Get latest orderID
                sql = "select top 1 orderID from Orders order by orderID desc";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    orderid = rs.getInt("orderID");
                }

                System.out.println("orderid : " + orderid);
                
                // Insert items in order to orderDetail
                Set<String> pids = cart.keySet();
                for (String pid : pids) {
                    sql = "insert OrderDetails values(?,?,?)";
                    pst = cn.prepareStatement(sql);
                    pst.setInt(1, orderid);
                    pst.setInt(2, Integer.parseInt(pid.trim()));
                    pst.setInt(3, cart.get(pid));
                    pst.executeUpdate();
                    cn.commit();
                    cn.setAutoCommit(true);
                }

                // Update total price for order
                sql = "UPDATE Orders \n"
                        + "SET  total = (SELECT SUM(Plants.price*OrderDetails.quantity)\n"
                        + "FROM OrderDetails,Plants\n"
                        + "WHERE OrderDetails.PID = Plants.PID AND OrderDetails.orderID=?)\n"
                        + "WHERE orderID = ?;";
                pst = cn.prepareStatement(sql);
                pst.setInt(1, orderid);
                pst.setInt(2, orderid);

                pst.executeUpdate();
                return true;
            } else {
                System.out.println("Ko chen order dc");
            }
        } catch (Exception e) {
            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (SQLException ex) {
            }
            result = false;
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
            }
        }
        return result;
    }

    public static boolean updateOrderStatus(int accID, int orderID, int newStatus) {
        try {
            if (newStatus < 1 || newStatus > 3) {
                return false;
            }
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sqlQuery = "UPDATE Orders \n"
                        + "SET status = ? \n"
                        + "WHERE accID= ? \n"
                        + "AND orderID = ?";
                PreparedStatement pst = cn.prepareStatement(sqlQuery);
                pst.setInt(1, newStatus);
                pst.setInt(2, accID);
                pst.setInt(3, orderID);
                int rs = pst.executeUpdate();
                if (rs == 0) {
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
