package utils;

public class WaitUtils {

    public static void waitFor1Second() {
        sleep(1000);
    }

    public static void waitFor200Milliseconds() {
        sleep(200);
    }

    public static void waitFor500Milliseconds() {
        sleep(500);
    }

    public static void waitFor1000Milliseconds() {
        sleep(1000);
    }

    public static void waitFor1500Milliseconds() {
        sleep(1500);
    }

    public static void waitFor2000Milliseconds() {
        sleep(2000);
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
