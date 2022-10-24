import java.sql.*;

public class Main {
        public static final String DB_NAME = "mydb";
        public static final String USER = "User1";
        public static final String PASSWORD = "Mypassword123";
        public static final String CONNECTION_STRING = "jdbc:mysql://localhost/" + DB_NAME;
        public static final String USER_TABLE = "user_tbl";

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_EMAIL = "email";

        public static void main(String[] args) {
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
                Statement statement = conn.createStatement();
                statement.execute("DROP TABLE IF EXISTS " + USER_TABLE);

                statement.execute("CREATE TABLE IF NOT EXISTS " + USER_TABLE
                        + " (" + COLUMN_NAME + " text, "
                        + COLUMN_PHONE + " integer, "
                        + COLUMN_EMAIL + " text" + ")");

                insertContact(statement, "Mary", 155645, "mary@email.com");
                insertContact(statement, "David", 5674852, "david@email.com");
                insertContact(statement, "ken", 701586, "ken@email.com");
                insertContact(statement, "Jerad", 113500, "jerad@email.com");




                statement.execute("UPDATE " + USER_TABLE + " SET "
                        + COLUMN_PHONE + "=824124"
                        + " WHERE " + COLUMN_NAME + "='Mary'");

                statement.execute("DELETE FROM " + USER_TABLE
                        + " WHERE " + COLUMN_NAME + "='ken'");
                //statement.execute("SELECT * FROM user_tbl");
                //ResultSet results = statement.getResultSet();
                ResultSet results = statement.executeQuery("SELECT * FROM USER_TBL");
                while (results.next()) {

                    System.out.println(results.getString(COLUMN_NAME) + " "
                            + results.getInt(COLUMN_PHONE) + " "
                            + results.getString(COLUMN_EMAIL));

                }

                results.close();
                statement.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println("Something when wrong " + e.getMessage());
                e.printStackTrace();
            }

            System.out.println("");
            System.out.println("Done.....");
        }

        private static void insertContact(Statement statement, String name, int phone, String email) throws SQLException
        {
            statement.execute("INSERT INTO " + USER_TABLE
                    + "(" + COLUMN_NAME + ", "
                    + COLUMN_PHONE + ", "
                    + COLUMN_EMAIL + ")"
                    + "VALUES('" + name + "', " + phone + ", '" + email + "')");

        }
}