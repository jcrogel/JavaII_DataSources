import java.sql.*;

public class DataSourceDB {
    public static void DBTest(){
        String conn_url = "jdbc:mysql://localhost:3306/employees?user=root&password=&serverTimezone=UTC";
        Connection conn = null;
        try {
            String employee = "499998";
            String cmd = "update salaries set salary=90000 where emp_no=? and to_date>curdate()";

            conn = DriverManager.getConnection(conn_url);
            PreparedStatement stmt = conn.prepareStatement(cmd);
            stmt.setString(1, employee);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main(String args[]){
        DataSourceDB.DBTest();
    }
}
