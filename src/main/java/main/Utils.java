package main;

public class Utils {
    public static boolean compareFloats(float f1, float f2) {
        return (Math.abs(f1-f2) < 15 || Math.abs((f1-f2)) > 500);
    }
}
