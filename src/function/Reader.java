package function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reader {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static int getIntAnswer(String question) throws IOException {
        System.out.println(question);
        return Integer.parseInt(in.readLine());
    }

    public static String getValidEmailAnswer(String question) throws IOException {
        while (true) {
            System.out.println(question);
            String email = in.readLine();
            if (isValidEmailAddress(email)) {
                return email;
            }
        }
    }

    public static String getStringAnswer(String question) throws IOException {
        System.out.println(question);
        return in.readLine();
    }

    public static boolean isValidEmailAddress(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean getBooleanAnswer(String question) throws IOException{
        while (true) {
            System.out.println(question);
            String answer = in.readLine();
            if (answer.equalsIgnoreCase("ja")) {
                return true;
            } else if (answer.equalsIgnoreCase("nein")) {
                return false;
            }
        }
    }

}

