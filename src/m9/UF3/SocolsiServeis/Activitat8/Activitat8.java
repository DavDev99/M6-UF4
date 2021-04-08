/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m9.UF3.SocolsiServeis.Activitat8;

/**
 *
 * @author David
 */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import javax.mail.Header;
import javax.net.ssl.HttpsURLConnection;

public class Activitat8 {

    private final String USER_AGENT = "Mozilla/5.0";
    String url = "http://www.insbaixcamp.org/";
    static String[] headers = new String[2];
    static String[] headersValues = new String[2];

    public static void main(String[] args) throws Exception {

        Activitat8 http = new Activitat8();
        Scanner teclado = new Scanner(System.in);

        String type = "";

        System.out.println("Quin tipus de peticio vols fer?");
        type = teclado.nextLine();

        System.out.println("Especifica " + headers.length + " headers:");

        for (int i = 0; i < headers.length; i++) {
            System.out.println("Header num " + (i + 1) + ":");
            headers[i] = teclado.nextLine();

            System.out.println("Header value: ");
            headers[i] = teclado.nextLine();
        }

        if (type.equalsIgnoreCase("GET")) {
            System.out.println("Testing 1 - Send Http GET request");
            http.sendGet();
        } else {
            System.out.println("\nTesting 2 - Send Http POST request");
            http.sendPost();
        }

    }

    // HTTP GET request
    private void sendGet() throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        for (int i = 0; i < headers.length; i++) {
            con.setRequestProperty(headers[i], headersValues[i]);
        }
//        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }

    // HTTP POST request
    private void sendPost() throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
              for (int i = 0; i < headers.length; i++) {
            con.setRequestProperty(headers[i], headersValues[i]);
        }
//        con.setRequestProperty("User-Agent", USER_AGENT);
//        con.setRequestProperty("Accept-Language", "ca-es");

        //Query string
        String urlParameters = "user_id=207";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }

}