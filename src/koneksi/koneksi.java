package koneksi;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class koneksi {
    private static Connection koneksi;
    public static Connection getkoneksi(){
    try{
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Berhasil Koneksi ke database");
    }catch (ClassNotFoundException ex){
        System.out.println("Gagal Koneksi ke Database" +ex);
    }
    String url = "jdbc:mysql://localhost/yellowcarwash";
    try{
        koneksi = DriverManager.getConnection(url,"root","");
        System.out.println("Berhasil Koneksi ke database");
    }catch(SQLException ex){
        System.out.println("Gagal Koneksi ke database" +ex);
    }
    return koneksi;
    }
}
        