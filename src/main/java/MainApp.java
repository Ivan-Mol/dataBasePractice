import java.sql.*;

public class MainApp {
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;


    public static void main(String[] args) throws SQLException {
        Worker worker1 = new Worker("Natalia", "programmer", 26, "female");
        printBase();
    }


    public static void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:workers.db");
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("INSERT INTO workers (id, name, specialisation, age, gender) VALUES (?, ?, ?, ? , ?);");
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Unable to connect");
        }
    }

    public static void disconnect() {
        try {
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void printBase() {
        try {
            connect();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try (ResultSet rs = statement.executeQuery("SELECT * FROM workers;")) {
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString("name") + " " + rs.getString("specialisation") + " " + rs.getInt("age") + " " + rs.getString("gender"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
    }

    public static void workerToBase(Worker worker) {
        try {
            connect();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            statement.executeUpdate("INSERT INTO workers (id, name, specialisation, age, gender) VALUES (" + worker.toString() + ");");
        } catch (SQLException throwables) {
            System.out.println("Кажется что-то пошло не так");
        }
        disconnect();
    }

}
