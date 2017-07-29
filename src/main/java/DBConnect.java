import java.sql.*;

/*
 * Class for  DBConnect
 * @author dpopov93 (mailto:d.popov93@mail.ru)
 * @since 28.07.17 19:02
 * @version 1.0
 */
public class DBConnect {
    /**
     * JDBC driver name
     */
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    /**
     * JDBC database URL
     */
    private static String DB_URL = "jdbc:mysql://localhost/somebase";

    /**
     * Database's table for manipulation
     */
    private static String DB_TABLE = "Persons";

    /**
     * Database user's name
     */
    private static String USER = "User";

    /**
     * Database user's password
     */
    private static String PASS = "password";

    /**
     * Initialize DBConnect, create new object for connect to db
     * @param DB_URL Database URL, e.g. "jdbc:mysql://localhost/somebase"
     * @param DB_TABLE Name of table in selected database
     * @param USER MySQL User name
     * @param PASSWORD MySQL User password
     */
    public DBConnect(String DB_URL, String DB_TABLE, String USER, String PASSWORD) {
        this.DB_URL = DB_URL;
        this.DB_TABLE = DB_TABLE;
        this.USER = USER;
        this.PASS = PASSWORD;
    }

    public String getData() {
        Connection conn = null;
        Statement stmt = null;
        String result = "";

        try{
            /**
             * Register JDBC driver
             */
            Class.forName(JDBC_DRIVER);

            /**
             * Open a connection
             */
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            /**
             * Execute a query
             */
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM " + DB_TABLE;
            ResultSet rs = stmt.executeQuery(sql);

            /**
             * Extract data from result set
             */
            while (rs.next()) {
                /**
                 * Extract data from result set
                 */
                int id  = rs.getInt("P_Id");
                String first = rs.getString("FirstName");
                String last = rs.getString("LastName");
                String address = rs.getString("Address");
                String city = rs.getString("City");

                /**
                 * Display values
                 */
                result += "ID: " + id + ", First: " + first +
                        ", Last: " + last + ", Address: " + address +
                        ", City: " + city + "\n";
            }
            /**
             * Clean-up environment
             */
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            /**
             * Handle errors for JDBC
             */
            se.printStackTrace();
        }catch (Exception e) {
            /**
             * Handle errors for Class.forName
             */
            e.printStackTrace();
        } finally {
            /**
             * Closing resources
             */
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {

            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return result;
    }
}
