import java.sql.Date;
import java.util.Arrays;

public class prueba {
    public static void main(String[] args) {
        String[] campos =  { "27930	Lillian	Weedon	F	17/05/1993	3"};
        String datos = campos[0];
        String[] dades = datos.split("\t");
        int client_id = Integer.parseInt(dades[0]);
        String nom = dades[1];
        String cognom1 = dades[2];
        char sexe  = String.valueOf(dades[3]).charAt(0);
        String data_naixament = dades[4];
        int pais_origen_id = Integer.parseInt(dades[5]);
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s \n",client_id,nom,cognom1,sexe,data_naixament,pais_origen_id);
    }
}
