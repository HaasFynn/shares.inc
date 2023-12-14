package shares.main_class;

import shares.function.Operator;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        start();
    }

    private static void start() throws IOException {
        Operator operator = new Operator();
        while (true) {
            boolean loginSucceed;
            do {
                loginSucceed = operator.loginOperator();
            } while (!loginSucceed);
            boolean backToLogin;
            do {
                backToLogin = operator.menuOperator();
            } while (!backToLogin);
        }
    }
}