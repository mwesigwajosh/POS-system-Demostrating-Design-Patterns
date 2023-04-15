package com.pos.item;
import com.pos.item.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductSingleton {
    private static ProductSingleton obj = new ProductSingleton();
    private ArrayList<Product> products;
    
    //make the constructor private
    private ProductSingleton(){
        products = new ArrayList<>();
        String urlstring = "jdbc:mysql://localhost:3306/pos";
        String driverName = "com.mysql.cj.jdbc.Driver";
        String username = "root";   
        String password = "";
        String sql = "SELECT * FROM Catalog";
        Connection con = null;
        Statement stmt = null;
        try{
            Class.forName(driverName);
            try{
                con = DriverManager.getConnection(urlstring, username, password);
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next()){
                    String name = rs.getString("name");
                    int id = rs.getInt("id");
                    String category = rs.getString("category");
                    int price = rs.getInt("price");
                    int quantity = rs.getInt("quantity");
                    if(quantity >= 1){
                        Product product = new Product(name, id, category, price);
                        products.add(product);
                    }
                }
                rs.close();
                stmt.close();
                con.close();
            }catch(SQLException ex){
                System.out.println("Failed to create the database connection.");
            }
        }catch(ClassNotFoundException ex){
            System.out.println("Driver not found.");
        }
    }
    
    public static ProductSingleton getInstance(){
        return obj;
    }
    
    public ArrayList<Product> getProducts(){
        return products;
    }

}
