package function;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reader {
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static int getIntAnswer(String question) {
        System.out.println(question);
        try {
            return Integer.parseInt(in.readLine());
        } catch (Exception e) {
            Print.printError("Ungültige Eingabe");
        }
        return 0;
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
        return matcher.matches() && Operator.isEmailFree(email);
    }

    public static boolean getBooleanAnswer(String question) throws IOException {
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

    public static double getDoubleAnswer(String question) throws IOException {
        System.out.println(question);
        try {
            return Double.parseDouble(in.readLine());
        } catch (Exception e) {
            Print.printError("Ungültige Eingabe");
        }
        return 0;
    }

    public static String getValidPassword() throws IOException {
        while (true) {
            Print.printPasswordRules();
            Print.printAskForPassword();
            String password = in.readLine();
            if (Password.isPasswordValid(password)) {
                return password;
            } else {
                Print.printError("Das eingegebene Passwort entspricht nicht den Anforderungen!");
            }
        }
    }

    static class Password {
        private static boolean isPasswordValid(String password) {
            return isPW8DigitsLong(password) && doesPWContainRightLetters(password) && doesPWContainSpecialCharacters(password);
        }

        private static boolean doesPWContainSpecialCharacters(String password) {
            char[] passwordChar = password.toCharArray();

            for (char letter : passwordChar) {
                if (!Character.isAlphabetic(letter) && !Character.isDigit(letter)) {
                    return true;
                }
            }
            return false;
        }

        private static boolean doesPWContainRightLetters(String password) {
            return (!password.equals(password.toLowerCase()) && !password.equals(password.toUpperCase()));
        }

        private static boolean isPW8DigitsLong(String password) {
            char[] passwordLength = password.toCharArray();
            return passwordLength.length >= 8;
        }

    }

    public static class toBinaryFile {
        BufferedReader fr;
        int MB;
        String fileName;
        Byte[] bytes;

        public toBinaryFile() throws IOException {
            this.MB = 1000000;
            this.fileName = "sharesBinaryDB";
            this.fr = new BufferedReader(new FileReader("binaryfiles/" + fileName + ".bin"));
            this.bytes = getDataFromFile();
            writeBinaryFile();
        }

        private Byte[] getDataFromFile() throws IOException {
            this.bytes = new Byte[this.MB];
            for (Byte bite : this.bytes) {
                bite = (byte) fr.read();
            }
            return this.bytes;
        }

        public void writeBinaryFile() {
            try (FileWriter writer = new FileWriter("binaryfiles/" + fileName + ".bin")) {
                writer.write(Arrays.toString(bytes));
            } catch (Exception e) {
                Print.printError("Beim erstellen des Writers ist ein Fehler unterlaufen!");
            }
        }

        public void writeNewInformation(String information) {
            for (Byte bite : this.bytes) {
                if (bite != null) {
                    bite = Byte.valueOf(information);
                }
            }
        }

        /*private static File createNewFile(String filename) throws IOException {
            File file = new File("binaryfiles/" + filename + ".bin");
            boolean fileWritten = file.createNewFile();
            if (!fileWritten) {
                System.out.println("Datei " + filename + " existiert bereits.");
                return null;
            }
            return file;
        }*/
    }
}

