package com.newwork.idservice.controller;
import com.newwork.idservice.model.User;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.text.MessageFormat;
import java.util.UUID;


@RestController
public class UserController {

    // JDBC driver name and database URL
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:mem:testdb";

    //  Database credentials
    final String USER = "sa";
    final String PASS = "";

    boolean isTableExist = false;
    String result;


    Connection conn = null;
    Statement stmt = null;



    @GetMapping("/user/ExternalIDs/{internal_user_id}")
    public User getUserExternalIDs (@PathVariable("internal_user_id") long internal_user_id) {

        if (internal_user_id == 0) {
            return new User(internal_user_id, "none","none");
        }
        else{
            result = null;
            OpenDB();
            if (!IsTableExists()) {
                CreateAndInitializeTable();
            }

            result = FetchExternalIDs(internal_user_id);
            if(result == null){
                CreateDate(internal_user_id);
                result = FetchExternalIDs(internal_user_id);
            }

            //DropTable();
            CloseDB();
            return new User(internal_user_id,result.split(",")[0],result.split(",")[1]);
        }
    }

    public void OpenDB () {


        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();

        }catch(Exception e) {
            e.printStackTrace();
        }

    }

    public void CloseDB () {

        try {
            if(stmt!=null) stmt.close();
        } catch(SQLException se2) {
        }
        try {
            if(conn!=null) conn.close();
        } catch(SQLException se) {
            se.printStackTrace();
        }

    }

    public void CreateAndInitializeTable () {

        try {
            String sql =  "CREATE TABLE   ID_SERVICE " +
                    "(internal_user_id INTEGER not NULL, " +
                    "tool_a_user_id VARCHAR(255), " +
                    "tool_b_user_id VARCHAR(255))";
            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");

            sql = "INSERT INTO ID_SERVICE VALUES (1, 'random-id-123', 'random-id-456')";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO ID_SERVICE VALUES (2, 'random-id-789', 'random-id-123')";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO ID_SERVICE VALUES (3, 'random-id-456', 'random-id-789')";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO ID_SERVICE VALUES (4, 'random-id-123', 'random-id-456')";
            stmt.executeUpdate(sql);

        } catch(SQLException se) {
            se.printStackTrace();
        }
    }

    public void CreateDate (long internal_user_id) {

        UUID toolAUserId = UUID.randomUUID();
        UUID toolBUserId = UUID.randomUUID();
        try {
            String sql =  "INSERT INTO ID_SERVICE VALUES ( " + internal_user_id + ", '" + toolAUserId + "', '" + toolBUserId + "')";
            System.out.println(sql);
            stmt.executeUpdate(sql);

        } catch(SQLException se) {
            se.printStackTrace();
        }
    }

    public String FetchExternalIDs (long internal_user_id) {

        try {

            String sql = MessageFormat.format("SELECT tool_a_user_id, tool_b_user_id FROM ID_SERVICE WHERE internal_user_id = {0}", internal_user_id);
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                String tool_a_user_id = rs.getString("tool_a_user_id");
                String tool_b_user_id = rs.getString("tool_b_user_id");

                // Display values
                System.out.print("tool_a_user_id: " + tool_a_user_id);
                System.out.println(", tool_b_user_id: " + tool_b_user_id);

                result = tool_a_user_id + "," + tool_b_user_id;
            }
            rs.close();
        } catch(SQLException se) {
            se.printStackTrace();
        }

        return result;
    }

    public boolean IsTableExists () {

        try {
            String sql = "SHOW TABLES";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                if(rs.getString(rs.getRow()).equals("ID_SERVICE")) {
                    isTableExist = true;
                    break;
                }
            }
            rs.close();
        } catch(SQLException se) {
            se.printStackTrace();
        }

        return isTableExist;
    }

    public void DropTable () {

        try {
            String sql =  "DROP TABLE ID_SERVICE";
            stmt.executeUpdate(sql);
            System.out.println("Deleted table from given database...");

        } catch(SQLException se) {
            se.printStackTrace();
        }
        isTableExist = false;
    }
    }
