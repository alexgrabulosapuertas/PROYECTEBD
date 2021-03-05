import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Arrays;
import java.util.Calendar;

public class Insert {
    public static final String SEPARADOR = "/t";


    /*public static String[] lectura(String[] args){
        BufferedReader bufferLectura = null;
        try {
            // Abrir el .csv en buffer de lectura
            bufferLectura = new BufferedReader(new FileReader("csv/dades_clients-tab.csv"));

            // Leer una linea del archivo
            String linea = bufferLectura.readLine();

            while (linea != null) {
                // Sepapar la linea leída con el separador definido previamente
                String[] campos = linea.split(SEPARADOR);

                System.out.println(Arrays.toString(campos));
                return campos;
                // Volver a leer otra línea del fichero
                linea = bufferLectura.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            // Cierro el buffer de lectura
            if (bufferLectura != null) {
                try {
                    bufferLectura.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/


    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con= DriverManager.getConnection("jdbc:mysql://<IP>:3306/db_hotels","perepi","pastanaga");


            //Preparem el Date
            Calendar calendar = Calendar.getInstance();
            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

            //splits prueba
            String[] campos = new String[0];
            String datos = campos[0];
            String[] dades = datos.split("\t");



            // the mysql insert statement
            String query = " INSERT INTO clients (client_id,nom,cognom1,sexe,data_naixament,pais_origen_id)"
                    + " values (?, ?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt    (1, 10001);
            preparedStmt.setString (2, "Geo'rgi");
            preparedStmt.setString (3, "Facello");
            preparedStmt.setString (4, "M"); // M F
            preparedStmt.setDate   (5, Date.valueOf("26/06/1986"));
            preparedStmt.setInt    (6, 16);

            // execute the preparedstatement
            preparedStmt.execute();

            //Tanquem la connexió
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
