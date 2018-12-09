import java.sql.*;
import java.util.ArrayList;


public class Database {


    private static String DB_DRIVER;
    private static String DB_CONNECTION;
    private static String DB_USER;
    private static String DB_PASSWORD;
    private static Config config;

    public Database() {
        config = new Config();
        DB_CONNECTION = config.getProp().getProperty("DB_CONNECTION");
        System.out.println(DB_CONNECTION);
        DB_USER = config.getProp().getProperty("DB_USER");
        DB_PASSWORD = config.getProp().getProperty("DB_PASSWORD");
        DB_DRIVER = config.getProp().getProperty("DB_DRIVER");
    }

    public void createExamTable() throws SQLException, ClassNotFoundException {
        Connection connection = getDBConnection();
        Statement statement = null;

        statement = connection.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS exam( id INTEGER  PRIMARY KEY auto_increment, subject INTEGER  , question VARCHAR(255) , FOREIGN KEY (subject) REFERENCES subject(id))";
        statement.executeUpdate(sql);


    }

    public void createSubjectTable() throws SQLException, ClassNotFoundException {
        Connection connection = getDBConnection();
        Statement statement = null;

        statement = connection.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS subject( id INTEGER  PRIMARY KEY auto_increment , name VARCHAR(255) , UNIQUE(name)  )";
        statement.executeUpdate(sql);


    }

    public void insertExam(String question, int subject) throws SQLException, ClassNotFoundException {

        createExamTable();

        Connection connection = getDBConnection();
        Statement statement = null;
        statement = connection.createStatement();
        String insertSql = "INSERT INTO exam (subject , question) VALUES (" + subject + ", '" + question + "') ";
        statement.execute(insertSql);

    }

    private static Connection getDBConnection() throws ClassNotFoundException {
        Class.forName(DB_DRIVER);
        Connection dbConnection = null;
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
                    DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    public int selectSubjectId(String subject) throws SQLException, ClassNotFoundException {
        Connection connection = getDBConnection();
        Statement statement = null;

        statement = connection.createStatement();
        String sql = "SELECT id from subject where name = '" + subject + "'";
        int id = 0;
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            id = rs.getInt(1);
        }

        return id;
    }

    public void insertSubject(String name) throws ClassNotFoundException, SQLException {
        Connection connection = getDBConnection();
        Statement statement = null;

        statement = connection.createStatement();
        String sql = "INSERT INTO subject (name) VALUES ('" + name + "') ";
        statement.executeUpdate(sql);
    }

    public ArrayList<String> getAllSubject() throws ClassNotFoundException, SQLException {
        ArrayList<String> allSub = new ArrayList<>();
        Connection connection = getDBConnection();
        Statement statement = null;

        statement = connection.createStatement();
        String sql = "SELECT name FROM subject";
        try {
            ResultSet rs = statement.executeQuery(sql);
            // Array array = rs.getArray("name");
            while (rs.next()) {
                allSub.add(rs.getString(1));
            }
        } catch (SQLException e) {
            allSub.add("There is no subject! ");
        }
        return allSub;
    }

    public ResultSet getAllQuesion() throws ClassNotFoundException, SQLException {
        Connection connection = getDBConnection();
        Statement statement = null;
        ResultSet rs = null;

        statement = connection.createStatement();
        String sql = "SELECT * FROM exam";
        try {
            rs = statement.executeQuery(sql);
            return rs;
        } catch (SQLException e) {

        }
        return rs;
    }

    public String getSubjectById(int id) throws ClassNotFoundException, SQLException {
        Connection connection = getDBConnection();
        Statement statement = null;
        String subject = "";
        statement = connection.createStatement();
        String sql = "SELECT name FROM subject where id = " + id;
        try {
            ResultSet rs = statement.executeQuery(sql);
            // Array array = rs.getArray("name");
            while (rs.next()) {
                subject = rs.getString(1);
            }
        } catch (SQLException e) {

        }
        return subject;
    }
}


