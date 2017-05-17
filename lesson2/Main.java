//1.  Сформировать таблицу товаров (id, prodid, title, cost) запросом из Java приложения.
//        id - порядковый номер записи, первичный ключ
//        prodid - уникальный номер товара
//        title - название товара
//        cost - стоимость
//2.  При запуске приложения очистить таблицу и заполнить 10.000 товаров вида:
//        id_товара 1 товар1 10
//        id_товара 2 товар2 20
//        id_товара 3 товар3 30
//        ...
//        id_товара 10000 товар10000 100000
//3.  Написать консольное приложение, которое позволяет узнать цену товара по его имени, либо вывести сообщение "Такого товара нет",
//    если товара нет в базе.
//    Консольная команда: "/cost product545"
//4.  Добавить возможность изменения цены товара (указываем имя товара и новую цену).
//    Консольная команда: "/changecost product10 10000"
//5.  Вывести товары в заданном ценовом диапазоне.
//    Консольная команда: "/productsbycost 100 600"

package lesson2;

import java.sql.*;

public class Main {
    private static Connection connection;
    private static PreparedStatement ps;

    public static void main(String[] args) {
        try {
            connect();
            createTable();
            fill();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    private static void connect() throws Exception {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:main.db");
        connection.setAutoCommit(false);
    }

    private static void createTable() throws SQLException {
        ps = connection.prepareStatement("CREATE TABLE IF NOT EXISTS products (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "prodid INTEGER NOT NULL UNIQUE, " +
                "title TEXT NOT NULL, " +
                "cost INTEGER NOT NULL);");
        ps.execute();
        connection.commit();
    }

    private static void fill() throws SQLException {
        ps = connection.prepareStatement("DELETE FROM products;");
        ps.execute();
        ps = connection.prepareStatement("INSERT INTO products (prodid, title, cost) VALUES (?, ?, ?);");
        for (int i = 1; i <= 10000; i++) {
            ps.setInt(1, i);
            ps.setString(2, "product" + i);
            ps.setInt(3, i * 10);
            ps.addBatch();
        }
        ps.executeBatch();
        connection.commit();
    }

    private static void disconnect() {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}