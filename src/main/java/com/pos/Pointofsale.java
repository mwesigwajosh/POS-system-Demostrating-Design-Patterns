package com.pos;
import com.pos.barcodescanner.Barcodescanner;
import com.pos.barcodescanner.Observercart;
import com.pos.item.Cart;
import com.pos.item.ProductSingleton;
import com.pos.item.Product;
import com.pos.item.SalesPerson;
import com.pos.payment.CashPaymentStrategy;
import com.pos.payment.CreditCardPaymentStrategy;
import com.pos.payment.PaymentContext;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import com.pos.ReceiptPrinter;


public class Pointofsale extends javax.swing.JFrame {
    String urlstring = "jdbc:mysql://localhost:3306/pos";
    String driverName = "com.mysql.cj.jdbc.Driver";
    String username = "root";   
    String password = "";
    Connection con = null;
    PreparedStatement stmt = null;
    
    public Pointofsale() {
        initComponents();
        updateTable();
    }

    @SuppressWarnings("unchecked")
    
    private void updateTable(){
        ProductSingleton product = ProductSingleton.getInstance();
        ArrayList<Product> products = product.getProducts();
        DefaultTableModel DF = (DefaultTableModel)productsTable.getModel();
        DF.setRowCount(0);
        for (Product pdt : products) {
            Vector v = new Vector();
            v.add(pdt.getID());
            v.add(pdt.getName());
            v.add(pdt.getPrice());
            v.add(pdt.getCategory());
            DF.addRow(v);
        }
    }
    
    private void clearCart(){
        DefaultTableModel model = (DefaultTableModel)cartTable.getModel();
        model.setRowCount(0);
        cartTotal();
    }
    
    private void cartTotal(){
        int total = 0;
        int quantityColIndex = 1;
        int priceColIndex = 2;
        for (int i = 0; i <cartTable.getRowCount(); i++) {
            int quantity = Integer.parseInt(cartTable.getValueAt(i, quantityColIndex).toString());
            int price = Integer.parseInt(cartTable.getValueAt(i, priceColIndex).toString());
            int product_of_one_item = quantity * price;
            total += product_of_one_item;
        }
        calculated_price.setText(String.valueOf(total));
    }
    
    //adding product to cart via barcode scanner
    private void barcodescanner(int itemID){
        Barcodescanner bscanner = new Barcodescanner();
        Observercart cart = new Observercart();
        bscanner.registerObserver(cart);
        bscanner.notifyObserver(itemID);
        int itemcode = cart.getproductCode();
        int rows = productsTable.getRowCount();
        DefaultTableModel DF = (DefaultTableModel)cartTable.getModel();
        Vector v = new Vector();
        
        if(rows >0){
            for(int i = 0; i<rows; i++){
                int itemcode_in_catalog = parseInt(productsTable.getValueAt(i, 0).toString());
                if(itemcode == itemcode_in_catalog){
                    String itemName = productsTable.getValueAt(i, 1).toString();
                    int itemPrice = parseInt(productsTable.getValueAt(i, 2).toString());
                    v.add(itemName);
                    v.add(1);
                    v.add(itemPrice);
                    DF.addRow(v);
                    
                    cartTotal();
                    
                    // notify salesperson
                    Cart notification_cart = new Cart();
                    SalesPerson salesperson = new SalesPerson("Cashier");
                    notification_cart.registerObserver(salesperson);
                    String notification = notification_cart.notifyObserver();
                    JOptionPane.showMessageDialog(this,notification);
                    break;
                }
            }
            
        }else{
            JOptionPane.showMessageDialog(this, "   No items");
        }
        
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        productsTable = new javax.swing.JTable();
        addSelectedItemsToCart = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        addtocart = new javax.swing.JButton();
        itemname = new javax.swing.JTextField();
        item_id = new javax.swing.JTextField();
        ScanBarcode = new javax.swing.JButton();
        cartPrice = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        cartTable = new javax.swing.JTable();
        removeItemFromCart = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        calculated_price = new javax.swing.JLabel();
        PaymentOption = new javax.swing.JComboBox<>();
        payButton = new javax.swing.JButton();
        increaseQuantity = new javax.swing.JButton();
        decreaseQuantity = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        gotoPOS = new javax.swing.JButton();
        gotoCatalog = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("BENJI SUPERMARKET");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(375, 14, 150, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Products available", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        productsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Name", "PRICE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(productsTable);

        addSelectedItemsToCart.setBackground(new java.awt.Color(153, 204, 255));
        addSelectedItemsToCart.setText("Add selected items to cart");
        addSelectedItemsToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSelectedItemsToCartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(addSelectedItemsToCart)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(addSelectedItemsToCart)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 110, -1, 510));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Find Item", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel2.setText("Item Name");

        jLabel3.setText("Item ID");

        addtocart.setBackground(new java.awt.Color(153, 204, 255));
        addtocart.setText("Add to cart");
        addtocart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addtocartActionPerformed(evt);
            }
        });

        item_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_idActionPerformed(evt);
            }
        });

        ScanBarcode.setBackground(new java.awt.Color(153, 204, 255));
        ScanBarcode.setText("Scan Barcode");
        ScanBarcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ScanBarcodeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addtocart)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(itemname, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                        .addComponent(item_id))
                    .addComponent(ScanBarcode))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(itemname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(item_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addtocart)
                    .addComponent(ScanBarcode))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 401, 150));

        cartPrice.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cart", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        cartTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ITEM", "QUANTITY", "PRICE @"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(cartTable);

        removeItemFromCart.setBackground(new java.awt.Color(255, 51, 102));
        removeItemFromCart.setText("Remove from cart");
        removeItemFromCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeItemFromCartActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("TOTAL");

        calculated_price.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        calculated_price.setText("0");

        PaymentOption.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CashPayment", "CreditCardPayment" }));
        PaymentOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PaymentOptionActionPerformed(evt);
            }
        });

        payButton.setBackground(new java.awt.Color(153, 204, 255));
        payButton.setText("Pay");
        payButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                payButtonMouseClicked(evt);
            }
        });

        increaseQuantity.setBackground(new java.awt.Color(102, 255, 51));
        increaseQuantity.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        increaseQuantity.setText("+");
        increaseQuantity.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increaseQuantityMouseClicked(evt);
            }
        });
        increaseQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                increaseQuantityActionPerformed(evt);
            }
        });

        decreaseQuantity.setBackground(new java.awt.Color(255, 51, 102));
        decreaseQuantity.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        decreaseQuantity.setText("-");
        decreaseQuantity.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreaseQuantityMouseClicked(evt);
            }
        });
        decreaseQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decreaseQuantityActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cartPriceLayout = new javax.swing.GroupLayout(cartPrice);
        cartPrice.setLayout(cartPriceLayout);
        cartPriceLayout.setHorizontalGroup(
            cartPriceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cartPriceLayout.createSequentialGroup()
                .addGroup(cartPriceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cartPriceLayout.createSequentialGroup()
                        .addComponent(removeItemFromCart)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(calculated_price, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cartPriceLayout.createSequentialGroup()
                        .addGroup(cartPriceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cartPriceLayout.createSequentialGroup()
                                .addComponent(PaymentOption, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(payButton))
                            .addGroup(cartPriceLayout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(cartPriceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(increaseQuantity)
                                    .addComponent(decreaseQuantity))))
                        .addGap(0, 21, Short.MAX_VALUE)))
                .addContainerGap())
        );
        cartPriceLayout.setVerticalGroup(
            cartPriceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cartPriceLayout.createSequentialGroup()
                .addGroup(cartPriceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(cartPriceLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(increaseQuantity)
                        .addGap(18, 18, 18)
                        .addComponent(decreaseQuantity)))
                .addGroup(cartPriceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cartPriceLayout.createSequentialGroup()
                        .addGroup(cartPriceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(calculated_price)
                            .addComponent(jLabel4))
                        .addGap(366, 366, 366))
                    .addGroup(cartPriceLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(removeItemFromCart)
                        .addGap(18, 18, 18)
                        .addGroup(cartPriceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PaymentOption, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(payButton))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        getContentPane().add(cartPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 400, 350));

        gotoPOS.setBackground(new java.awt.Color(153, 204, 255));
        gotoPOS.setText("POS");
        gotoPOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gotoPOSActionPerformed(evt);
            }
        });

        gotoCatalog.setBackground(new java.awt.Color(153, 204, 255));
        gotoCatalog.setText("Catalog");
        gotoCatalog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gotoCatalogActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(gotoPOS)
                .addGap(18, 18, 18)
                .addComponent(gotoCatalog)
                .addGap(90, 90, 90))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gotoPOS)
                    .addComponent(gotoCatalog))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 180, -1));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 20, 50, 100));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 580, 390, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addtocartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addtocartActionPerformed
        String itemName = itemname.getText();
        int itemID = parseInt(item_id.getText());
        int found = 0;
        Product p = null;
        ProductSingleton product = ProductSingleton.getInstance();
        ArrayList<Product> products = product.getProducts();
        DefaultTableModel DF = (DefaultTableModel)cartTable.getModel();
        for (Product pdt : products) {
            if(pdt.getName().equalsIgnoreCase(itemName) && pdt.getID() == itemID){
                found = 1;
                p = pdt;
                break;
            }
        }
        if(found == 1){
            Vector v = new Vector();
            v.add(p.getName());
            v.add(1);
            v.add(p.getPrice());
            DF.addRow(v);
            
            // notify salesperson
            Cart cart = new Cart();
            SalesPerson salesperson = new SalesPerson("Cashier");
            cart.registerObserver(salesperson);
            String notification = cart.notifyObserver();
            JOptionPane.showMessageDialog(this,notification);
        }else{
            JOptionPane.showMessageDialog(this, "Item not found");
        }
        cartTotal();
    }//GEN-LAST:event_addtocartActionPerformed
    
    private void item_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_idActionPerformed
    }//GEN-LAST:event_item_idActionPerformed

    private void gotoPOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gotoPOSActionPerformed

    }//GEN-LAST:event_gotoPOSActionPerformed

    private void gotoCatalogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gotoCatalogActionPerformed
        this.dispose();
        Catalog catalog = new Catalog();
        catalog.setVisible(true);
    }//GEN-LAST:event_gotoCatalogActionPerformed

    private void removeItemFromCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeItemFromCartActionPerformed
        DefaultTableModel model = (DefaultTableModel) cartTable.getModel();
        int selectedrow = cartTable.getSelectedRow();
        if(selectedrow >= 0){
            model.removeRow(selectedrow);
        }else{
            //if cart is empty
            if(cartTable.getRowCount() == 0){
                JOptionPane.showMessageDialog(this, "cart is empty");
            }else{
             // if cart is not empty but no item row selected
                JOptionPane.showMessageDialog(this, "Please select a single item to remove"); 
            }    
        }
        cartTotal(); 
    }//GEN-LAST:event_removeItemFromCartActionPerformed

    private void addSelectedItemsToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSelectedItemsToCartActionPerformed
        DefaultTableModel model = (DefaultTableModel) cartTable.getModel();
        int selectedRow = productsTable.getSelectedRow();
        if(selectedRow >= 0){
            Vector v = new Vector();
            v.add(productsTable.getValueAt(selectedRow,1));
            v.add(1);
            v.add(productsTable.getValueAt(selectedRow,2));
            model.addRow(v);
            
            // notify salesperson
            Cart cart = new Cart();
            SalesPerson salesperson = new SalesPerson("Cashier");
            cart.registerObserver(salesperson);
            String notification = cart.notifyObserver();
            JOptionPane.showMessageDialog(this,notification);
        }
        cartTotal();
    }//GEN-LAST:event_addSelectedItemsToCartActionPerformed

    private void PaymentOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PaymentOptionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PaymentOptionActionPerformed

    private void updateCatalog(){
        int rows = cartTable.getRowCount();
        if(rows >= 1){
            String item_name;
            int item_quantity;
            for(int i=0; i<rows; i++){
                item_name = cartTable.getValueAt(i, 0).toString();
                item_quantity = parseInt(cartTable.getValueAt(i, 1).toString());
                try {
                    Class.forName(driverName);
                    try {
                        con = DriverManager.getConnection(urlstring, username, password);
                        String sql = "UPDATE catalog SET quantity = quantity - ? WHERE name = ?";
                        stmt = con.prepareStatement(sql);
                        stmt.setInt(1, item_quantity);
                        stmt.setString(2, item_name);
                        stmt.executeUpdate();
                        con.close();
                    }catch (SQLException ex) {
                        System.out.println("Failed to create the database connection.");
                    }
                }catch (ClassNotFoundException ex) {
                    System.out.println("Driver not found.");  
                }
            }
        }
    }
    
    private void generateReceipt(){
        // Get the data from the cart table
        StringBuilder receipt = new StringBuilder();
        receipt.append("Receipt\n\n");
        for (int row = 0; row < cartTable.getRowCount(); row++) {
            String item = cartTable.getValueAt(row, 0).toString();
            String quantity = cartTable.getValueAt(row, 1).toString();
            String price = cartTable.getValueAt(row, 2).toString();
            receipt.append("Item: ").append(item).append("\n");
            receipt.append("Quantity: ").append(quantity).append("\n");
            receipt.append("Price: $").append(price).append("\n\n");
        }
        receipt.append("5000\n");
        receipt.append("\nThank you for your purchase!");
        new ReceiptPrinter(receipt.toString());
    }
    
    private void payButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_payButtonMouseClicked
        int items_available = cartTable.getRowCount();
        if(items_available < 1){
            JOptionPane.showMessageDialog(this, "cart is empty"); 
        }else{
            //Temporary cart data storage models for easy insertion into the database
            int countRows = cartTable.getRowCount();
            String[] cartItems = new String[countRows];
            String[] cartItemsQuantity = new String[countRows];
            String[] cartItemsPrices = new String[countRows];
            int price_to_pay = parseInt(calculated_price.getText());
            
            for(int i=0; i < countRows; i++){
                cartItems[i] = cartTable.getValueAt(i,0).toString();
                cartItemsQuantity[i] = cartTable.getValueAt(i, 1).toString();
                cartItemsPrices[i] = cartTable.getValueAt(i, 2).toString();
            }
            
            //now we use the strategy design pattern
            PaymentContext paymentContext = new PaymentContext(new CashPaymentStrategy());
            String selectedPaymentOption = PaymentOption.getSelectedItem().toString();
            if(selectedPaymentOption.equalsIgnoreCase("CashPayment")){
                //by default its cash payment
                String paidWith = paymentContext.pay();
                try {
                    Class.forName(driverName);
                    try {
                        con = DriverManager.getConnection(urlstring, username, password);
                        String sql = "INSERT INTO cart(item, quantity, prices, TotalPaid, paidwith) VALUES(?,?,?,?,?)";
                        stmt = con.prepareStatement(sql);
                        stmt.setString(1,Arrays.toString(cartItems));
                        stmt.setString(2, Arrays.toString(cartItemsQuantity));
                        stmt.setString(3, Arrays.toString(cartItemsPrices));
                        stmt.setInt(4,price_to_pay);
                        stmt.setString(5, paidWith);
                        stmt.executeUpdate();
                        con.close();
                    }catch (SQLException ex) {
                        System.out.println("Failed to create the database connection.");
                    }
                }catch (ClassNotFoundException ex) {
                    System.out.println("Driver not found.");  
                }
                JOptionPane.showMessageDialog(this, "Paid");
                updateCatalog();
                generateReceipt();
                clearCart();
            }
            if(selectedPaymentOption.equalsIgnoreCase("CreditCardPayment")){
                String cardNumber = JOptionPane.showInputDialog("Card Number");
                String expirationDate = JOptionPane.showInputDialog("Expiration Date");
                String cvv = JOptionPane.showInputDialog("cvv");
                //we dynamically change the context to creaditcard payment
                CreditCardPaymentStrategy cardpay = new CreditCardPaymentStrategy(cardNumber,expirationDate, cvv);
                paymentContext.setPaymentStrategy(cardpay);
                String paidWith = paymentContext.pay();
                                try {
                    Class.forName(driverName);
                    try {
                        con = DriverManager.getConnection(urlstring, username, password);
                        String sql = "INSERT INTO cart(item, quantity, prices, TotalPaid, paidwith) VALUES(?,?,?,?,?)";
                        stmt = con.prepareStatement(sql);
                        stmt.setString(1,Arrays.toString(cartItems));
                        stmt.setString(2, Arrays.toString(cartItemsQuantity));
                        stmt.setString(3, Arrays.toString(cartItemsPrices));
                        stmt.setInt(4,price_to_pay);
                        stmt.setString(5, paidWith);
                        stmt.executeUpdate();
                        con.close();
                    }catch (SQLException ex) {
                        System.out.println("Failed to create the database connection.");
                    }
                }catch (ClassNotFoundException ex) {
                    System.out.println("Driver not found.");  
                }
                JOptionPane.showMessageDialog(this, "Paid");
                generateReceipt();
                clearCart();
            }
        }
    }//GEN-LAST:event_payButtonMouseClicked

    private void increaseQuantityMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_increaseQuantityMouseClicked
        int selectedRow = cartTable.getSelectedRow();
        int currentValue;
        if(selectedRow>=0){
            currentValue = parseInt(cartTable.getValueAt(selectedRow, 1).toString());
            currentValue= currentValue + 1;
            cartTable.setValueAt(currentValue, selectedRow, 1);
        }
        cartTotal();
    }//GEN-LAST:event_increaseQuantityMouseClicked

    private void increaseQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_increaseQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_increaseQuantityActionPerformed

    private void decreaseQuantityMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decreaseQuantityMouseClicked
        int selectedRow = cartTable.getSelectedRow();
        int currentValue;
        if(selectedRow>=0){
            currentValue = parseInt(cartTable.getValueAt(selectedRow, 1).toString());
            if(currentValue>=2){
                currentValue= currentValue - 1;
                cartTable.setValueAt(currentValue, selectedRow, 1);
            }
        }
        cartTotal();
    }//GEN-LAST:event_decreaseQuantityMouseClicked

    private void decreaseQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decreaseQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_decreaseQuantityActionPerformed

    private void ScanBarcodeActionPerformed(java.awt.event.ActionEvent evt) {
        int productCode = parseInt(JOptionPane.showInputDialog("Product Code"));
        barcodescanner(productCode);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pointofsale().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> PaymentOption;
    private javax.swing.JButton ScanBarcode;
    private javax.swing.JButton addSelectedItemsToCart;
    private javax.swing.JButton addtocart;
    private javax.swing.JLabel calculated_price;
    private javax.swing.JPanel cartPrice;
    private javax.swing.JTable cartTable;
    private javax.swing.JButton decreaseQuantity;
    private javax.swing.JButton gotoCatalog;
    private javax.swing.JButton gotoPOS;
    private javax.swing.JButton increaseQuantity;
    private javax.swing.JTextField item_id;
    private javax.swing.JTextField itemname;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton payButton;
    private javax.swing.JTable productsTable;
    private javax.swing.JButton removeItemFromCart;
    // End of variables declaration//GEN-END:variables
}
