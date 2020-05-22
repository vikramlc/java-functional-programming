package com.reactivespring.practice;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LeadSquared {
    private static final String POST_DATA = "{\"userId\": \"Rohit123&\"}";
    private static final String POST_URL = "https://www.leadsquared.com/globalapis/v1/isanewuserid";
    private static final String USERID_REGEX = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[,_#?!@$^*-]).{8,}$";
    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{10,}$";


    public static void main(String[] args) {

        JSONObject isUserValid = new JSONObject(POST_DATA);
        String userId = isUserValid.getString("userId");

        // Rules: UserId -
        // 1. UserId should be atleast 8 characters long
        // 2. UserId should have atleast one uppercase letter, lowercase letter and number
        // 3. UserId not have special characters like <&%\: -> can lead to SQL injections

        /*
        Regex:
        ^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$

        This regex will enforce these rules:
        1. At least one upper case English letter, (?=.*?[A-Z])
        2. At least one lower case English letter, (?=.*?[a-z])
        3. At least one digit, (?=.*?[0-9])
        4. At least one special character, (?=.*?[,_#?!@$^*-])
        5. Minimum eight in length .{8,} (with the anchors)
        */

        if(!userId.matches(USERID_REGEX)) {
            throw new IllegalStateException("Please provide a valid userId.");
        }
        getIsUserIdValidData();

        System.out.println("******* PASSWORD CHECK **********");

        // Password check rules: One Letter, One Numeral, minimum length 8 characters
        // Password check fails for Regex: ^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$
        // 1. Minimum number of characters will be 8 according to regex.
        // 2. The regex should have an escape character before '\d'.
        // It should be: ^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$

        // Below code to show the correctness of the regex
        String password = "Rohit12345";
        if(password.matches(PASSWORD_REGEX)) {
            System.out.println("Correct Password!");
        } else {
            System.out.println("Incorrect Password");
        }

    }

    private static void getIsUserIdValidData() {
        try {
            URL url = new URL(POST_URL);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");

            OutputStream os = urlConnection.getOutputStream();
            os.write(POST_DATA.getBytes());
            os.flush();
            os.close();

            int responseCode = urlConnection.getResponseCode();
            System.out.println("POST Response Code :  " + responseCode);

            printResponseDataToConsole(urlConnection, responseCode);

        } catch (MalformedURLException e) {
            throw new IllegalStateException("Please provide a valid URL.");
        } catch (IOException e) {
            throw new IllegalStateException("There was an error while opening connection to the URL.");
        }
    }

    private static void printResponseDataToConsole(HttpURLConnection urlConnection, int responseCode) throws IOException {
        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in .readLine()) != null) {
                response.append(inputLine);
            } in .close();
            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("POST NOT WORKED");
        }
    }
}
