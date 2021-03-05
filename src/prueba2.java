
import java.io.*;
import java.sql.*;

public class prueba2 {

    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://192.168.56.101:3306/db_hotels";
        String username = "perepi";
        String password = "pastanaga";

        String csvFilePath = "csv/dades_clients-tab.csv";

        int batchSize = 20;

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(jdbcURL, username, password);
            connection.setAutoCommit(false);

            String sql = "INSERT INTO review (client_id,nom,cognom1,sexe,data_naixament,pais_origen_id) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
            String lineText = null;

            int count = 0;

            lineReader.readLine(); // skip header line

            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                String client_id = data[0];
                String nom = data[1];
                String cognom1 = data[2];
                String sexe = data[3];
                String data_contractacio = data[4];
                String pais_origen_id = data[5];

                statement.setInt(1, Integer.parseInt(client_id));

                statement.setString(2, nom);

                statement.setString(3, cognom1);

                statement.setString(4, sexe);

                Date sqldate = Date.valueOf(data_contractacio);
                statement.setString(5, String.valueOf(sqldate));

                statement.setInt(6,Integer.parseInt(pais_origen_id));

                statement.addBatch();

                if (count % batchSize == 0) {
                    statement.executeBatch();
                }
            }

            lineReader.close();

            // execute the remaining queries
            statement.executeBatch();

            connection.commit();
            connection.close();

        } catch (IOException ex) {
            System.err.println(ex);
        } catch (SQLException ex) {
            ex.printStackTrace();

            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
