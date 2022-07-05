/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sqlwithlucene;

/**
 *
 * @author coderjp
 */
import com.mysql.cj.xdevapi.Statement;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.print.DocFlavor;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public class Indexer {

   private IndexWriter writer;



   public Indexer(String indexDirectoryPath,boolean iscreated) throws IOException {
      //this directory will contain the indexes
      Directory indexDirectory = 
         FSDirectory.open(new File(indexDirectoryPath));

      //create the indexer
      writer = new IndexWriter(indexDirectory, 
         new StandardAnalyzer(Version.LUCENE_30),iscreated,
         IndexWriter.MaxFieldLength.UNLIMITED);
   }
   
   public void close() throws CorruptIndexException, IOException {
       writer.optimize();
       writer.commit();
      writer.close();
   }
   
   public void get_data(String name, String pass, String db)throws IOException, SQLException{
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db, name, pass); 
        
                PreparedStatement query1 = con.prepareStatement("select * from demo;");
                ResultSet rs2 =query1.executeQuery();

                while (rs2.next()) {
//                    System.out.println(rs1.getString("ope").equals("Delete"));
                    String s = rs2.getString("data");
                    System.out.println(s);
                    Object obj = JSONValue.parse(s);
                    JSONObject jsonObject = (JSONObject) obj;
                    String tid=rs2.getString("id");
System.out.println("sqlwithlucene.Indexer.get_data()1");

                    if(rs2.getString("ope").equals("Insert")){
                        
                        long id = (long) jsonObject.get("id");
                        String sname = (String) jsonObject.get("name");
                        String regno = (String) jsonObject.get("regno");
                        String city = (String) jsonObject.get("city");
                        long age = (long) jsonObject.get("age");
                        createIndex(Long.toString(id), sname, regno, city,Long.toString(age));
System.out.println("sqlwithlucene.Indexer.get_data()2");

                    }else if(rs2.getString("ope").equals("Update")){
                        long id = (long) jsonObject.get("id");
                        String sname = (String) jsonObject.get("name");
                        String regno = (String) jsonObject.get("regno");
                        String city = (String) jsonObject.get("city");
                        long age = (long) jsonObject.get("age");
                        updateDocument(Long.toString(id), sname, regno, city,Long.toString(age));

                    }else if(rs2.getString("ope").equals("Delete")){
                        long id = (long) jsonObject.get("id");
                        deleteDocument(Long.toString(id));
                    }
                    System.out.println("sqlwithlucene.Indexer.get_data()1");

                    
                    String d_query = "delete from demo where id = ?";
                    PreparedStatement preparedStmt = con.prepareStatement(d_query);
                    preparedStmt.setInt(1, Integer.parseInt(tid));

                    // execute the preparedstatement
                    preparedStmt.execute();
      
                 
            
                }
                con.close();
               
            
        }
   
   public void createIndex(String id, String name, String regno, String city, String age) 
      throws IOException {
//       System.out.println(age);
       
            Document doc = new Document();
            doc.add(new Field("id",id,Field.Store.YES,Field.Index.ANALYZED));
            doc.add(new Field("name",name,Field.Store.YES,Field.Index.ANALYZED));
            doc.add(new Field("regno",regno,Field.Store.YES,Field.Index.ANALYZED));
            doc.add(new Field("city",city,Field.Store.YES,Field.Index.ANALYZED));
            doc.add(new Field("age",age,Field.Store.YES,Field.Index.ANALYZED));

            writer.addDocument(doc);
            System.out.println(id + " Indexed");
                                

   }
   
   private void updateDocument(String id, String name, String regno, String city,String age) throws IOException {
       Document doc = new Document();
        doc.add(new Field("id",id,Field.Store.YES,Field.Index.ANALYZED));
        doc.add(new Field("name",name,Field.Store.YES,Field.Index.ANALYZED));
        doc.add(new Field("regno",regno,Field.Store.YES,Field.Index.ANALYZED));
        doc.add(new Field("city",city,Field.Store.YES,Field.Index.ANALYZED));
        doc.add(new Field("age",age,Field.Store.YES,Field.Index.ANALYZED));       
        writer.updateDocument(new Term ("id",id ),doc); 
        System.out.println(id+" Updated");
    } 
   
    private void deleteDocument(String id) throws IOException {
        writer.deleteDocuments(new Term("id",id));         
        System.out.println(id+" Deleted");
        writer.commit();
    }
}