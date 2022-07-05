/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sqlwithlucene;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import org.json.simple.JSONObject;  
import org.json.simple.JSONValue;  

import org.json.simple.parser.*;

/**
 *
 * @author coderjp
 */
public class demo {
    
    public static void create(String id,String name,String regno,String city,String age) throws IOException{
       
        
    }
    
}


//File indexDirectory = new File("/home/coderjp/Desktop/index/");
//IndexReader reader = IndexReader.open(FSDirectory.open(indexDirectory));
//System.out.println(reader);



// try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "sql@2020");
//         Statement stmt = conn.createStatement();
//      ) {
//            String table_name="student";
//             ResultSet rs=stmt.executeQuery("select * from demo");
//        
//            while(rs.next())  {
//                
//                String s = rs.getString("data");
//                System.out.println(s);
//                Object obj = JSONValue.parse(s);
//                JSONObject jsonObject = (JSONObject) obj;
//
//                long id = (long) jsonObject.get("id");
//                String name = (String) jsonObject.get("name");
//                String regno = (String) jsonObject.get("regno");
//                String city = (String) jsonObject.get("city");
//                long age = (long) jsonObject.get("age");
//                
//
//                System.out.println(id+" "+name+" "+regno+" "+city+"  "+age);
//            } 
//        
//       
//        conn.close();
//         
//      } catch (SQLException e) {
//         e.printStackTrace();
//      } 