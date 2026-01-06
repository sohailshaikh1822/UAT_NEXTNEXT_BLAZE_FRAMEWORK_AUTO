package utils;

public class WaitUtils {

    public static void waitFor100Milliseconds() {
        sleep(100);
    }

    public static void waitFor200Milliseconds() {
        sleep(200);
    }

    public static void waitFor300Milliseconds() {
        sleep(300);
    }

    public static void waitFor400Milliseconds() {
        sleep(400);
    }

    public static void waitFor500Milliseconds() {
        sleep(500);
    }

    public static void waitFor600Milliseconds() {
        sleep(600);
    }

    public static void waitFor700Milliseconds() {
        sleep(700);
    }

    public static void waitFor800Milliseconds() {
        sleep(800);
    }

    public static void waitFor900Milliseconds() {
        sleep(900);
    }

    public static void waitFor1000Milliseconds() {
        sleep(1000);
    }

    public static void waitFor1200Milliseconds() {
        sleep(1200);
    }

    public static void waitFor1400Milliseconds() {
        sleep(1400);
    }

    public static void waitFor2000Milliseconds() {
        sleep(2000);
    }

    public static void waitFor3000Milliseconds()
    {
        sleep(3000);
    }
    public static void waitFor9000Milliseconds()
    {
        sleep(9000);
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Wait interrupted: " + e.getMessage());
        }
    }
}
