import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "root";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/example";

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("Выберите колонку: ");
            System.out.println("1. Показать таблицу; ");
            System.out.println("2. Изменить таблицу; ");
            System.out.println("3. Добавить файл; ");
            int command = scanner.nextInt();

            if (command == 1) {
                System.out.println("");
                System.out.println("Show the table");
                Statement statement = connection.createStatement();
                String SQL_SELECT_TASKS = "select * from cars order by id";
                ResultSet resultSet = statement.executeQuery(SQL_SELECT_TASKS);
                while (resultSet.next()) {
                    System.out.println(resultSet.getInt("id") + " " + resultSet.getString("model")
                            + " " + resultSet.getInt("price"));
                }
                System.out.println("");
            } else if (command == 5) {
                flag = false;
            } else if (command == 2) {
                System.out.println("Выберите колонку: ");
                System.out.println("1. Модель; ");
                System.out.println("2. Цена; ");
                int chooseColumn = scanner.nextInt();
                if (chooseColumn == 1) {
                    String sql = "update cars set model = ? where id = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    System.out.println("Enter model: ");
                    String modelName = scanner.next();
                    System.out.println("Enter identificator: ");
                    int taskID = scanner.nextInt();
                    preparedStatement.setString(1, modelName);
                    preparedStatement.setInt(2, taskID);
                    preparedStatement.executeUpdate();
                } else if (chooseColumn == 2) {
                    String sql = "update cars set price = ? where id = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    System.out.println("Введите цену:");
                    int priceSet = scanner.nextInt();
                    System.out.println("Enter identificator: ");
                    int taskID = scanner.nextInt();
                    preparedStatement.setInt(1, priceSet);
                    preparedStatement.setInt(2, taskID);
                    preparedStatement.executeUpdate();
                }
            } else if (command == 3) {
                System.out.println("Enter id of file: ");
                int interID = scanner.nextInt();
                System.out.println("Enter model of car: ");
                scanner.nextLine();
                String interModel = scanner.nextLine();
                System.out.println("Enter price of a car: ");
                int interPrice = scanner.nextInt();
                String sql = "insert into cars (id, model, price) values (?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, interID);
                preparedStatement.setString(2, interModel);
                preparedStatement.setInt(3, interPrice);
                preparedStatement.executeUpdate();

            }
        }
    }
}
