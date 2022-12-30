package library.core;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Helper {

    /**
     * Returns a {@code String} rounded number given a {@code float} number and
     * {@code int} decimal places.
     * 
     * @param num
     * @param places
     * @return String
     */
    public static String roundString(float num, int places) {
        float factor = (float) Math.pow(10, places);
        float rounded = Math.round(num * factor) / factor;
        if (rounded % 1 == 0)
            return Integer.toString((int) rounded);

        return Float.toString(rounded);
    }

    /**
     * Waits a given {@code float} milliseconds. {@code float} milliseconds are
     * converted to {@code long} milliseconds.
     * 
     * @param millis
     */
    public static void wait(float millis) {
        try {
            Thread.sleep((long) millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints every {@code Object} argument given with a space in between. Prints a
     * new line at the end.
     * 
     * @param args
     */
    public static void println(Object... args) {
        print(args);
        System.out.println();
    }

    /**
     * Prints every {@code Object} argument given with a space in between.
     * 
     * @param args
     */
    public static void print(Object... args) {
        for (int i = 0; i < args.length; i++) {
            if (i == args.length - 1)
                System.out.print(args[i]);
            else
                System.out.print(args[i] + " ");
        }
    }

    /**
     * Prints a given {@code Object[]}. <br>
     * <br>
     * Example: <br>
     * <br>
     * [0] 0.3 <br>
     * <br>
     * [1] 0.4 <br>
     * <br>
     * [2] 0.5 <br>
     * 
     * @param args
     */
    public static void printArray(Object[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println("[" + i + "] " + args[i]);
        }
    }

    /**
     * Prints a given {@code String[]}.
     * 
     * @param args
     */
    public static void printArray(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println("[" + i + "] " + args[i]);
        }
    }

    /**
     * Prints a given {@code int[]}.
     * 
     * @param args
     */
    public static void printArray(int[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println("[" + i + "] " + args[i]);
        }
    }

    /**
     * Prints a given {@code float[]}.
     * 
     * @param args
     */
    public static void printArray(float[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println("[" + i + "] " + args[i]);
        }
    }

    /**
     * Loads a {@code String} array from a given {@code String} file name.
     * 
     * @param fileName
     * @return String[]
     */
    public static String[] loadStrings(String fileName) {
        ArrayList<String> list = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null) {
                list.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list.toArray(new String[list.size()]);
    }

    /**
     * Returns true if the given {@code String} is an integer.
     * 
     * @param str
     * @return boolean
     */
    public static boolean isInt(String str) {
        int decimalIndex = str.indexOf('.');
        if (decimalIndex == -1) {
            try {
                Integer.parseInt(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        String afterDecimal = str.substring(decimalIndex + 1);
        return afterDecimal.equals("0");
    }

    /**
     * Returns true if the given {@code Object} is an int. Based on
     * {@link #isInt(String)}.
     * 
     * @param obj
     * @return
     */
    public static boolean isInt(Object obj) {
        return isInt(obj.toString());
    }

    /**
     * Returns a String representation of the given {@code Object} array. The String
     * will not contain brackets, each element is separated by a space.
     * 
     * @param arr
     * @return String
     */
    public static String toString(Object[] arr) {
        String str = "";
        for (int i = 0; i < arr.length; i++) {
            str += arr[i];
            if (i < arr.length - 1)
                str += " ";
        }
        return str + "";
    }

    /**
     * Converts an {@code ArrayList<Character>} to a {@code char[]}.
     * 
     * @param arr
     * @return char[]
     */
    public static char[] toCharArray(ArrayList<Character> arr) {
        char[] charArr = new char[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            charArr[i] = arr.get(i);
        }
        return charArr;
    }

    /**
     * Converts an {@code ArrayList<Byte>} to a {@code byte[]}.
     * 
     * @param arr
     * @return byte[]
     */
    public static byte[] toByteArray(ArrayList<Byte> arr) {
        byte[] byteArr = new byte[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            byteArr[i] = arr.get(i);
        }
        return byteArr;
    }

    /**
     * Converts an {@code ArrayList<Integer>} to an {@code int[]}.
     * 
     * @param arr
     * @return int[]
     */
    public static int[] toIntArray(ArrayList<Integer> arr) {
        int[] intArr = new int[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            intArr[i] = arr.get(i);
        }
        return intArr;
    }

    /**
     * Converts an {@code ArrayList<Long>} to a {@code long[]}.
     * 
     * @param arr
     * @return long[]
     */
    public static long[] toLongArray(ArrayList<Long> arr) {
        long[] longArr = new long[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            longArr[i] = arr.get(i);
        }
        return longArr;
    }

    /**
     * Converts an {@code ArrayList<Float>} to a {@code float[]}.
     * 
     * @param arr
     * @return float[]
     */
    public static float[] toFloatArray(ArrayList<Float> arr) {
        float[] floatArr = new float[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            floatArr[i] = arr.get(i);
        }
        return floatArr;
    }

    /**
     * Converts an {@code ArrayList<Double>} to a {@code double[]}.
     * 
     * @param arr
     * @return double[]
     */
    public static double[] toDoubleArray(ArrayList<Double> arr) {
        double[] doubleArr = new double[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            doubleArr[i] = arr.get(i);
        }
        return doubleArr;
    }

    /**
     * Converts an {@code ArrayList<Boolean>} to a {@code boolean[]}.
     * 
     * @param arr
     * @return boolean[]
     */
    public static boolean[] toBooleanArray(ArrayList<Boolean> arr) {
        boolean[] booleanArr = new boolean[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            booleanArr[i] = arr.get(i);
        }
        return booleanArr;
    }

    /**
     * Converts an {@code ArrayList<String>} to a {@code String[]}.
     * 
     * @param arr
     * @return String[]
     */
    public static String[] toStringArray(ArrayList<String> arr) {
        String[] stringArr = new String[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            stringArr[i] = arr.get(i);
        }
        return stringArr;
    }

    /**
     * Converts an {@code ArrayList<PVector>} to a {@code PVector[]}.
     * 
     * @param arr
     * @return PVector[]
     */
    public static PVector[] toPVectorArray(ArrayList<PVector> arr) {
        PVector[] pvectorArr = new PVector[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            pvectorArr[i] = arr.get(i);
        }
        return pvectorArr;
    }

    /**
     * Converts an {@code ArrayList<Object>} to an {@code Object[]}.
     * 
     * @param arr
     * @return Object[]
     */
    public static Object[] toObjectArray(ArrayList<Object> arr) {
        Object[] objectArr = new Object[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            objectArr[i] = arr.get(i);
        }
        return objectArr;
    }

    /**
     * Converts an {@code ArrayList<color>} to a {@code color[]}.
     * 
     * @param arr
     * @return color[]
     */
    public static color[] toColorArray(ArrayList<color> arr) {
        color[] colorArr = new color[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            colorArr[i] = arr.get(i);
        }
        return colorArr;
    }

    /**
     * Returns true if the given {@code String[]} array contains the given
     * {@code String} element.
     * 
     * @param arr
     * @param element
     * @return boolean
     */
    public static boolean contains(String[] arr, String element) {
        for (String str : arr) {
            if (str.equals(element))
                return true;
        }
        return false;
    }

    /**
     * Returns true if the given {@code int[]} array contains the given {@code int}
     * element.
     * 
     * @param arr
     * @param element
     * @return boolean
     */
    public static boolean contains(int[] arr, int element) {
        for (int i : arr) {
            if (i == element)
                return true;
        }
        return false;
    }

    /**
     * Returns true if the given {@code float[]} array contains the given
     * {@code float} element.
     * 
     * @param arr
     * @param element
     * @return boolean
     */
    public static boolean contains(float[] arr, float element) {
        for (float d : arr) {
            if (d == element)
                return true;
        }
        return false;
    }

    /**
     * Returns true if the given {@code Object} classObject contains a
     * {@code String} method.
     * <p>
     * Note: This will only work with methods that do not have parameters.
     * </font>
     * 
     * @param classObject
     * @param methodName
     * @return boolean
     */
    public static boolean containsClass(Object classObject, String methodName) {
        Class<?> class1 = classObject.getClass();
        try {
            class1.getDeclaredMethod(methodName, (Class<?>[]) null);
            return true;
        } catch (NoSuchMethodException | SecurityException e) {
            // Doesn't have class
            return false;
        }
    }

    /**
     * Returns a {@code String} of the given {@code KeyEvent}. Examples of non-alpha
     * keys: "Backspace" "Control" "Space" "Up" "F5"
     * 
     * @param evt
     * @return String
     */
    public static String keyEventToString(KeyEvent evt) {
        String key = String.valueOf(evt.getKeyChar());
        int keyCode = evt.getKeyCode();
        int[] possibleKeyCodes = { 8, 10, 16, 17, 18, 27, 32, 37, 38, 39, 40, 112, 113, 114, 115, 116, 117, 118, 119,
                120, 121, 122, 123 };
        String[] possibleKeys = { "Backspace", "Enter", "Shift", "Control", "Alt", "Escape", "Space", "Left", "Up",
                "Right", "Down", "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10", "F11", "F12" };
        for (int i = 0; i < possibleKeyCodes.length; i++) {
            if (keyCode == possibleKeyCodes[i]) {
                key = possibleKeys[i];
                break;
            }
        }
        return key;
    }

    /**
     * Returns an {@code int} keycode of the given {@code KeyEvent}.
     * 
     * @param evt
     * @return int
     */
    public static int keyEventToKeyCode(KeyEvent evt) {
        return evt.getKeyCode();
    }
}