import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Database {


    private static String JDBC_DRIVER;
    private static String DB_CONNECTION;
    private static String DB_USER;
    private static String DB_PASSWORD;


    public Database() {
        Config config = new Config();
        DB_CONNECTION = config.getProp().getProperty("DB_CONNECTION");
        System.out.println(DB_CONNECTION);
        DB_USER = config.getProp().getProperty("DB_USER");
        DB_PASSWORD = config.getProp().getProperty("DB_PASSWORD");
        JDBC_DRIVER = config.getProp().getProperty("DB_DRIVER");
    }

    public void createExamTable() throws SQLException, ClassNotFoundException {
        Connection connection = getDBConnection();
        Statement statement = null;

        statement = connection.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS exam( id INTEGER  PRIMARY KEY auto_increment, subject INTEGER  , question VARCHAR(255)  FOREIGN KEY (subject) REFERENCES subject(id)";
        statement.executeUpdate(sql);


    }

    public void createSubjectTable() throws SQLException, ClassNotFoundException {
        Connection connection = getDBConnection();
        Statement statement = null;

        statement = connection.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS subject( id INTEGER  PRIMARY KEY auto_increment , name VARCHAR(255) PRIMARY KEY  )";
        statement.executeUpdate(sql);


    }

    public void insertExam(String question , int subject) throws SQLException, ClassNotFoundException {

        createExamTable();

        Connection connection = getDBConnection();
        Statement statement = null;
        statement = connection.createStatement();
        String insertSql = "INSERT INTO exam (subject , question) VALUES (" + subject + ", "+ question + ") ";
        statement.execute(insertSql);

    }

    private static Connection getDBConnection() throws ClassNotFoundException {
        Class.forName("org.h2.Driver");
        Connection dbConnection = null;
        try {
            dbConnection = DriverManager.getConnection("jdbc:h2:~/test", "sa",
                    "123");
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    public int selectSubjectId(String subject){

    }
}
