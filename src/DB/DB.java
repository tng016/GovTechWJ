package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by tzeyangng on 4/2/17.
 */
public class DB {
    private String DbUrl = "jdbc:mysql://localhost:3306/GovTech";
    private String user = "root";
    private String pass = "MyMacb00k";
    private Connection myConn;
    private Statement myStmt;

    public DB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            myConn = DriverManager.getConnection(DbUrl, user, pass);
            myStmt = myConn.createStatement();
        } catch (Exception e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
        }
    }

    public boolean DBQuery() {
        try {
            ResultSet myRs = myStmt.executeQuery("select * from testing");

            while (myRs.next()) {
                System.out.println(myRs.getString("username"));
                System.out.println(myRs.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean DBPut(ArrayList<String> list) {
        try{
            while(!list.isEmpty()){
                String occupation = list.remove(0);
                String skill = list.remove(0);
                String sql = "insert into occupationskill" + " (occupation, skill)" + " values ('"+occupation+"', '"+skill+"')";
                myStmt.executeUpdate(sql);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean saveProfile(String name, int age, Boolean isMale, String location, String photoPath,String[]hobbies,
                               String[]jobs) {
        try{
            String sql = "insert into users" + " (name, age, gender, location, photopath)" + " values ('"+name+"', " +
                    "'"+age+"', '"+(isMale?1:0)+"', '"+location+"', '"+photoPath+"')";
            myStmt.executeUpdate(sql);
            int id = getLastID("users");
            for(String s:hobbies){
                sql = "insert into userhobby" + " (userid, hobby)" + " values ('"+id+"', '"+s+"')";
                myStmt.executeUpdate(sql);
            }
            for(String s:jobs){
                sql = "insert into userjob" + " (userid, job)" + " values ('"+id+"', '"+s+"')";
                myStmt.executeUpdate(sql);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean saveJob(String title, String description, int price, String date, String[]skills) {
        try{
            String sql = "insert into job" + " (title, description, price, duedate)" + " values ('"+title+"', " +
                    "'"+description+"', '"+price+"', STR_TO_DATE('"+date+"', '%d/%m/%Y'))";
            myStmt.executeUpdate(sql);
            int id = getLastID("job");
            for(String s:skills){
                sql = "insert into jobskill" + " (jobid, skill)" + " values ('"+id+"', '"+s+"')";
                myStmt.executeUpdate(sql);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean saveService(String title, String service, String description, int priceLower, int priceUpper,Period days) {
        try{
            String sql = "insert into service" + " (title, service, description, pricelower, priceupper, days)" +
                    " values ('"+title+"', '"+service+"', '"+description+"', '"+priceLower+"', " +
                    "'"+priceUpper+"', "+days.getDays()+")";
            myStmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private int getLastID(String tableName){
        int count=0;
        try {
            ResultSet myRs = myStmt.executeQuery("select id from "+tableName);

            while (myRs.next()) {
                count = myRs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}
