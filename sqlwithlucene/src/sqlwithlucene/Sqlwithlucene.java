/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sqlwithlucene;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

/**
 *
 * @author coderjp
 */
public class Sqlwithlucene extends javax.swing.JFrame {
    
     String indexDir = "/home/coderjp/Desktop/index";
    Indexer indexer;
    
    
    

    /**
     * Creates new form Sqlwithlucene
     */
    public Sqlwithlucene() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        usname = new javax.swing.JTextField();
        uspass = new javax.swing.JTextField();
        dbname = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Username");

        jLabel2.setText("Password");

        jLabel3.setText("Db Name");

        jButton1.setText("Connect to Db");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2)))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(usname, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                            .addComponent(uspass)
                            .addComponent(dbname)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(jButton1)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(usname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(uspass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(dbname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(jButton1)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
       
        String name=usname.getText();
        String pass=uspass.getText();
        String db=dbname.getText();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db, name, pass);
            Statement stmt = con.createStatement();
            String sqlc="create table if not exists demo(\n" +
                    "id int primary key auto_increment,\n" +
                    "tname varchar(255),\n" +
                    "tid int,\n" +
                    "ope varchar(255),\n" +
                    "data json,\n" +
                    "date DATETIME\n" +
                    ");";
             stmt.executeUpdate(sqlc);
            con.close();
            TriggerTable t = new TriggerTable();
            t.name=name;
            t.pass=pass;
            t.db=db;
                         
            setVisible(false);
            t.setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,ex);
            Logger.getLogger(Sqlwithlucene.class.getName()).log(Level.SEVERE, null, ex);
        }
        Timer timer = new Timer(); // creating timer
        TimerTask task; // creating timer task
        task = new TimerTask() {
            public void run(){
                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db, name, pass);
            
       PreparedStatement query = con.prepareStatement("select count(*) from demo;");
        ResultSet rs =query.executeQuery();
        int count=0;
        while(rs.next()){    
            count=rs.getInt("count(*)");
            System.out.println(count);
        }
        
            
        if(count>0){
            boolean iscreated=false;
            
            try {
        File indexDirectory = new File("/home/coderjp/Desktop/index/");
            IndexReader reader = IndexReader.open(FSDirectory.open(indexDirectory));
            System.out.println(reader.maxDoc());
            
    } catch (FileNotFoundException ex) {
                System.out.println("file not found");
                iscreated=true;
    }
            
            indexer = new Indexer(indexDir,iscreated);
            long startTime = System.currentTimeMillis();
            indexer.get_data(name, pass, db);
            long endTime = System.currentTimeMillis();
            indexer.close();
            
            

//            Searcher searcher;
//            searcher = new Searcher(indexDir);
//            startTime = System.currentTimeMillis();
//            TopDocs hits = searcher.search("1");
//            endTime = System.currentTimeMillis();
//
//            System.out.println(hits.totalHits +
//                    " documents found. Time :" + (endTime - startTime) +" ms");
//            for(ScoreDoc scoreDoc : hits.scoreDocs) {
//               Document doc = searcher.getDocument(scoreDoc);
//               System.out.println("File: "+ doc.get("id"));
//            }
//            searcher.close();
        }             // TODO add your handling code here:
                } catch (IOException ex) {
                    Logger.getLogger(TriggerTable.class.getName()).log(Level.SEVERE, null, ex);
                }       catch (SQLException ex) {
                    Logger.getLogger(Sqlwithlucene.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        timer.schedule(task, 1000*5, 1000*15);
    }//GEN-LAST:event_jButton1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
         // scheduling the task 1sec
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Sqlwithlucene.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sqlwithlucene.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sqlwithlucene.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sqlwithlucene.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sqlwithlucene().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField dbname;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField usname;
    private javax.swing.JTextField uspass;
    // End of variables declaration//GEN-END:variables
}
