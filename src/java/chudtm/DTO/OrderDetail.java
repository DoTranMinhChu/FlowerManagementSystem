/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chudtm.DTO;

/**
 *
 * @author ohmin
 */
public class OrderDetail {
    private int orderDetailID;
    private int orderID;
    private int PlantID;
    private String plantName;
    private int price;
    private String ImgPath;
    private int quantity;

    public OrderDetail() {
    }

    public OrderDetail(int orderDetailID, int orderID, int PlantID, String plantName, int price, String ImgPath, int quantity) {
        this.orderDetailID = orderDetailID;
        this.orderID = orderID;
        this.PlantID = PlantID;
        this.plantName = plantName;
        this.price = price;
        this.ImgPath = ImgPath;
        this.quantity = quantity;
    }

    public int getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(int orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getPlantID() {
        return PlantID;
    }

    public void setPlantID(int PlantID) {
        this.PlantID = PlantID;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImgPath() {
        return ImgPath;
    }

    public void setImgPath(String ImgPath) {
        this.ImgPath = ImgPath;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    

  
}
