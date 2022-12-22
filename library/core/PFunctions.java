package library.core;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.*;
import java.util.*;
import java.awt.Color;

public class PFunctions implements PConstants {
    public void println(Object... args) {
        Helper.println(args);
    }

    public void print(Object... args) {
        Helper.print(args);
    }

    public void printArray(Object[] array) {
        Helper.printArray(array);
    }

    public void printArray(String[] array) {
        Helper.printArray(array);
    }

    public void printArray(int[] array) {
        Helper.printArray(array);
    }

    public void printArray(float[] array) {
        Helper.printArray(array);
    }

    public String[] loadStrings(String fileName) {
        return Helper.loadStrings(fileName);
    }

    public void append(byte[] array, byte value) {
        byte[] newArray = new byte[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        newArray[array.length] = value;
        array = newArray;
    }

    public void append(char[] array, char value) {
        char[] newArray = new char[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        newArray[array.length] = value;
        array = newArray;
    }

    // Array functions
    public void append(int[] array, int value) {
        int[] newArray = new int[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        newArray[array.length] = value;
        array = newArray;
    }

    public void append(float[] array, float value) {
        float[] newArray = new float[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        newArray[array.length] = value;
        array = newArray;
    }

    public void append(String[] array, String value) {
        String[] newArray = new String[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        newArray[array.length] = value;
        array = newArray;
    }

    public void append(Object[] array, Object value) {
        Object[] newArray = new Object[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        newArray[array.length] = value;
        array = newArray;
    }

    public void arrayCopy(Object src, int srcPos, Object dest, int destPos,
            int length) {
        System.arraycopy(src, srcPos, dest, destPos, length);
    }

    public void arrayCopy(Object src, Object dest, int length) {
        System.arraycopy(src, 0, dest, 0, length);
    }

    public void arrayCopy(Object src, Object dest) {
        System.arraycopy(src, 0, dest, 0, Array.getLength(src));
    }

    public boolean[] concat(boolean[] a, boolean[] b) {
        boolean[] c = new boolean[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    public byte[] concat(byte[] a, byte[] b) {
        byte[] c = new byte[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    public char[] concat(char[] a, char[] b) {
        char[] c = new char[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    public int[] concat(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    public float[] concat(float[] a, float[] b) {
        float[] c = new float[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    public String[] concat(String[] a, String[] b) {
        String[] c = new String[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    public Object[] concat(Object[] a, Object[] b) {
        Object[] c = new Object[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    public boolean[] expand(boolean list[]) {
        boolean temp[] = new boolean[list.length + 1];
        System.arraycopy(list, 0, temp, 0, list.length);
        return temp;
    }

    public boolean[] expand(boolean list[], int newSize) {
        boolean temp[] = new boolean[newSize];
        System.arraycopy(list, 0, temp, 0, Math.min(list.length, newSize));
        return temp;
    }

    public byte[] expand(byte list[]) {
        byte temp[] = new byte[list.length + 1];
        System.arraycopy(list, 0, temp, 0, list.length);
        return temp;
    }

    public byte[] expand(byte list[], int newSize) {
        byte temp[] = new byte[newSize];
        System.arraycopy(list, 0, temp, 0, Math.min(list.length, newSize));
        return temp;
    }

    public char[] expand(char list[]) {
        char temp[] = new char[list.length + 1];
        System.arraycopy(list, 0, temp, 0, list.length);
        return temp;
    }

    public char[] expand(char list[], int newSize) {
        char temp[] = new char[newSize];
        System.arraycopy(list, 0, temp, 0, Math.min(list.length, newSize));
        return temp;
    }

    public int[] expand(int list[]) {
        int temp[] = new int[list.length + 1];
        System.arraycopy(list, 0, temp, 0, list.length);
        return temp;
    }

    public int[] expand(int list[], int newSize) {
        int temp[] = new int[newSize];
        System.arraycopy(list, 0, temp, 0, Math.min(list.length, newSize));
        return temp;
    }

    public long[] expand(long list[]) {
        long temp[] = new long[list.length + 1];
        System.arraycopy(list, 0, temp, 0, list.length);
        return temp;
    }

    public long[] expand(long list[], int newSize) {
        long temp[] = new long[newSize];
        System.arraycopy(list, 0, temp, 0, Math.min(list.length, newSize));
        return temp;
    }

    public float[] expand(float list[]) {
        float temp[] = new float[list.length + 1];
        System.arraycopy(list, 0, temp, 0, list.length);
        return temp;
    }

    public float[] expand(float list[], int newSize) {
        float temp[] = new float[newSize];
        System.arraycopy(list, 0, temp, 0, Math.min(list.length, newSize));
        return temp;
    }

    public double[] expand(double list[]) {
        double temp[] = new double[list.length + 1];
        System.arraycopy(list, 0, temp, 0, list.length);
        return temp;
    }

    public double[] expand(double list[], int newSize) {
        double temp[] = new double[newSize];
        System.arraycopy(list, 0, temp, 0, Math.min(list.length, newSize));
        return temp;
    }

    public String[] expand(String list[]) {
        String temp[] = new String[list.length + 1];
        System.arraycopy(list, 0, temp, 0, list.length);
        return temp;
    }

    public String[] expand(String list[], int newSize) {
        String temp[] = new String[newSize];
        System.arraycopy(list, 0, temp, 0, Math.min(list.length, newSize));
        return temp;
    }

    public Object[] expand(Object list[]) {
        Object temp[] = new Object[list.length + 1];
        System.arraycopy(list, 0, temp, 0, list.length);
        return temp;
    }

    public Object[] expand(Object list[], int newSize) {
        Object temp[] = new Object[newSize];
        System.arraycopy(list, 0, temp, 0, Math.min(list.length, newSize));
        return temp;
    }

    public boolean[] shorten(boolean list[]) {
        boolean outgoing[] = new boolean[list.length - 1];
        System.arraycopy(list, 0, outgoing, 0, list.length - 1);
        return outgoing;
    }

    public byte[] shorten(byte list[]) {
        byte outgoing[] = new byte[list.length - 1];
        System.arraycopy(list, 0, outgoing, 0, list.length - 1);
        return outgoing;
    }

    public char[] shorten(char list[]) {
        char outgoing[] = new char[list.length - 1];
        System.arraycopy(list, 0, outgoing, 0, list.length - 1);
        return outgoing;
    }

    public int[] shorten(int list[]) {
        int outgoing[] = new int[list.length - 1];
        System.arraycopy(list, 0, outgoing, 0, list.length - 1);
        return outgoing;
    }

    public long[] shorten(long list[]) {
        long outgoing[] = new long[list.length - 1];
        System.arraycopy(list, 0, outgoing, 0, list.length - 1);
        return outgoing;
    }

    public float[] shorten(float list[]) {
        float outgoing[] = new float[list.length - 1];
        System.arraycopy(list, 0, outgoing, 0, list.length - 1);
        return outgoing;
    }

    public double[] shorten(double list[]) {
        double outgoing[] = new double[list.length - 1];
        System.arraycopy(list, 0, outgoing, 0, list.length - 1);
        return outgoing;
    }

    public String[] shorten(String list[]) {
        String outgoing[] = new String[list.length - 1];
        System.arraycopy(list, 0, outgoing, 0, list.length - 1);
        return outgoing;
    }

    public Object[] shorten(Object list[]) {
        Object outgoing[] = new Object[list.length - 1];
        System.arraycopy(list, 0, outgoing, 0, list.length - 1);
        return outgoing;
    }

    public boolean[] splice(boolean list[], boolean value, int index) {
        list = expand(list);
        System.arraycopy(list, index, list, index + 1, list.length - index - 1);
        list[index] = value;
        return list;
    }

    public byte[] splice(byte list[], byte value, int index) {
        list = expand(list);
        System.arraycopy(list, index, list, index + 1, list.length - index - 1);
        list[index] = value;
        return list;
    }

    public char[] splice(char list[], char value, int index) {
        list = expand(list);
        System.arraycopy(list, index, list, index + 1, list.length - index - 1);
        list[index] = value;
        return list;
    }

    public int[] splice(int list[], int value, int index) {
        list = expand(list);
        System.arraycopy(list, index, list, index + 1, list.length - index - 1);
        list[index] = value;
        return list;
    }

    public long[] splice(long list[], long value, int index) {
        list = expand(list);
        System.arraycopy(list, index, list, index + 1, list.length - index - 1);
        list[index] = value;
        return list;
    }

    public float[] splice(float list[], float value, int index) {
        list = expand(list);
        System.arraycopy(list, index, list, index + 1, list.length - index - 1);
        list[index] = value;
        return list;
    }

    public double[] splice(double list[], double value, int index) {
        list = expand(list);
        System.arraycopy(list, index, list, index + 1, list.length - index - 1);
        list[index] = value;
        return list;
    }

    public String[] splice(String list[], String value, int index) {
        list = expand(list);
        System.arraycopy(list, index, list, index + 1, list.length - index - 1);
        list[index] = value;
        return list;
    }

    public Object[] splice(Object list[], Object value, int index) {
        list = expand(list);
        System.arraycopy(list, index, list, index + 1, list.length - index - 1);
        list[index] = value;
        return list;
    }

    public boolean[] subset(boolean list[], int start) {
        return subset(list, start, list.length);
    }

    public byte[] subset(byte list[], int start) {
        return subset(list, start, list.length);
    }

    public char[] subset(char list[], int start) {
        return subset(list, start, list.length);
    }

    public int[] subset(int list[], int start) {
        return subset(list, start, list.length);
    }

    public long[] subset(long list[], int start) {
        return subset(list, start, list.length);
    }

    public float[] subset(float list[], int start) {
        return subset(list, start, list.length);
    }

    public double[] subset(double list[], int start) {
        return subset(list, start, list.length);
    }

    public String[] subset(String list[], int start) {
        return subset(list, start, list.length);
    }

    public Object[] subset(Object list[], int start) {
        return subset(list, start, list.length);
    }

    public boolean[] subset(boolean list[], int start, int count) {
        boolean outgoing[] = new boolean[count];
        System.arraycopy(list, start, outgoing, 0, count);
        return outgoing;
    }

    public byte[] subset(byte list[], int start, int count) {
        byte outgoing[] = new byte[count];
        System.arraycopy(list, start, outgoing, 0, count);
        return outgoing;
    }

    public char[] subset(char list[], int start, int count) {
        char outgoing[] = new char[count];
        System.arraycopy(list, start, outgoing, 0, count);
        return outgoing;
    }

    public int[] subset(int list[], int start, int count) {
        int outgoing[] = new int[count];
        System.arraycopy(list, start, outgoing, 0, count);
        return outgoing;
    }

    public long[] subset(long list[], int start, int count) {
        long outgoing[] = new long[count];
        System.arraycopy(list, start, outgoing, 0, count);
        return outgoing;
    }

    public float[] subset(float list[], int start, int count) {
        float outgoing[] = new float[count];
        System.arraycopy(list, start, outgoing, 0, count);
        return outgoing;
    }

    public double[] subset(double list[], int start, int count) {
        double outgoing[] = new double[count];
        System.arraycopy(list, start, outgoing, 0, count);
        return outgoing;
    }

    public String[] subset(String list[], int start, int count) {
        String outgoing[] = new String[count];
        System.arraycopy(list, start, outgoing, 0, count);
        return outgoing;
    }

    public Object[] subset(Object list[], int start, int count) {
        Object outgoing[] = new Object[count];
        System.arraycopy(list, start, outgoing, 0, count);
        return outgoing;
    }

    public boolean[] reverse(boolean list[]) {
        boolean outgoing[] = new boolean[list.length];
        for (int i = 0; i < list.length; i++) {
            outgoing[i] = list[list.length - i - 1];
        }
        return outgoing;
    }

    public byte[] reverse(byte list[]) {
        byte outgoing[] = new byte[list.length];
        for (int i = 0; i < list.length; i++) {
            outgoing[i] = list[list.length - i - 1];
        }
        return outgoing;
    }

    public char[] reverse(char list[]) {
        char outgoing[] = new char[list.length];
        for (int i = 0; i < list.length; i++) {
            outgoing[i] = list[list.length - i - 1];
        }
        return outgoing;
    }

    public int[] reverse(int list[]) {
        int outgoing[] = new int[list.length];
        for (int i = 0; i < list.length; i++) {
            outgoing[i] = list[list.length - i - 1];
        }
        return outgoing;
    }

    public long[] reverse(long list[]) {
        long outgoing[] = new long[list.length];
        for (int i = 0; i < list.length; i++) {
            outgoing[i] = list[list.length - i - 1];
        }
        return outgoing;
    }

    public float[] reverse(float list[]) {
        float outgoing[] = new float[list.length];
        for (int i = 0; i < list.length; i++) {
            outgoing[i] = list[list.length - i - 1];
        }
        return outgoing;
    }

    public double[] reverse(double list[]) {
        double outgoing[] = new double[list.length];
        for (int i = 0; i < list.length; i++) {
            outgoing[i] = list[list.length - i - 1];
        }
        return outgoing;
    }

    public String[] reverse(String list[]) {
        String outgoing[] = new String[list.length];
        for (int i = 0; i < list.length; i++) {
            outgoing[i] = list[list.length - i - 1];
        }
        return outgoing;
    }

    public Object[] reverse(Object list[]) {
        Object outgoing[] = new Object[list.length];
        for (int i = 0; i < list.length; i++) {
            outgoing[i] = list[list.length - i - 1];
        }
        return outgoing;
    }

    public byte[] sort(byte list[]) {
        byte outgoing[] = new byte[list.length];
        System.arraycopy(list, 0, outgoing, 0, list.length);
        Arrays.sort(outgoing);
        return outgoing;
    }

    public char[] sort(char list[]) {
        char outgoing[] = new char[list.length];
        System.arraycopy(list, 0, outgoing, 0, list.length);
        Arrays.sort(outgoing);
        return outgoing;
    }

    public int[] sort(int list[]) {
        int outgoing[] = new int[list.length];
        System.arraycopy(list, 0, outgoing, 0, list.length);
        Arrays.sort(outgoing);
        return outgoing;
    }

    public long[] sort(long list[]) {
        long outgoing[] = new long[list.length];
        System.arraycopy(list, 0, outgoing, 0, list.length);
        Arrays.sort(outgoing);
        return outgoing;
    }

    public float[] sort(float list[]) {
        float outgoing[] = new float[list.length];
        System.arraycopy(list, 0, outgoing, 0, list.length);
        Arrays.sort(outgoing);
        return outgoing;
    }

    public double[] sort(double list[]) {
        double outgoing[] = new double[list.length];
        System.arraycopy(list, 0, outgoing, 0, list.length);
        Arrays.sort(outgoing);
        return outgoing;
    }

    public String[] sort(String list[]) {
        String outgoing[] = new String[list.length];
        System.arraycopy(list, 0, outgoing, 0, list.length);
        Arrays.sort(outgoing);
        return outgoing;
    }

    public Object[] sort(Object list[]) {
        Object outgoing[] = new Object[list.length];
        System.arraycopy(list, 0, outgoing, 0, list.length);
        Arrays.sort(outgoing);
        return outgoing;
    }

    // Conversion Functions
    public String binary(int n) {
        return Integer.toBinaryString(n);
    }

    public String binary(byte n) {
        return Integer.toBinaryString(n);
    }

    public String binary(char n) {
        return Integer.toBinaryString(n);
    }

    public String binary(color c) {
        return Integer.toBinaryString(c.getRGB());
    }

    public boolean bool(int n) {
        return n != 0;
    }

    public boolean bool(String s) {
        return s.equals("true");
    }

    public byte parseByte(boolean b) {
        return (byte) (b ? 1 : 0);
    }

    public byte parseByte(int n) {
        return (byte) n;
    }

    public byte parseByte(char n) {
        return (byte) n;
    }

    public byte parseByte(float n) {
        return (byte) n;
    }

    public byte parseByte(String s) {
        return Byte.parseByte(s);
    }

    public byte parseByte(Object o) {
        return Byte.parseByte(o.toString());
    }

    public char parseChar(int n) {
        return (char) n;
    }

    public char parseChar(byte n) {
        return (char) n;
    }

    public char parseChar(float n) {
        return (char) n;
    }

    public char parseChar(String s) {
        return s.charAt(0);
    }

    public char parseChar(Object o) {
        return o.toString().charAt(0);
    }

    public float parseFloat(boolean b) {
        return b ? 1 : 0;
    }

    public float parseFloat(byte n) {
        return n;
    }

    public float parseFloat(char n) {
        return n;
    }

    public float parseFloat(int n) {
        return n;
    }

    public float parseFloat(String s) {
        return Float.parseFloat(s);
    }

    public float parseFloat(Object o) {
        return Float.parseFloat(o.toString());
    }

    public String hex(byte n) {
        return Integer.toHexString(n);
    }

    public String hex(char n) {
        return Integer.toHexString(n);
    }

    public String hex(int n) {
        return Integer.toHexString(n);
    }

    public String hex(color c) {
        return Integer.toHexString(c.getRGB());
    }

    public int parseInt(boolean b) {
        return b ? 1 : 0;
    }

    public int parseInt(byte n) {
        return n;
    }

    public int parseInt(char n) {
        return n;
    }

    public int parseInt(double n) {
        return (int) n;
    }

    public int parseInt(String s) {
        return Integer.parseInt(s);
    }

    public int parseInt(Object o) {
        return Integer.parseInt(o.toString());
    }

    public String str(boolean b) {
        return Boolean.toString(b);
    }

    public String str(byte n) {
        return Byte.toString(n);
    }

    public String str(char n) {
        return Character.toString(n);
    }

    public String str(int n) {
        return Integer.toString(n);
    }

    public String str(float n) {
        return Float.toString(n);
    }

    public String str(color c) {
        return c.toString();
    }

    public String str(Object o) {
        return o.toString();
    }

    public int unbinary(String s) {
        return Integer.parseInt(s, 2);
    }

    public int unhex(String s) {
        return Integer.parseInt(s, 16);
    }

    // String Functions
    public String join(String[] list, String separator) {
        String result = "";
        for (int i = 0; i < list.length; i++) {
            result += list[i];
            if (i < list.length - 1) {
                result += separator;
            }
        }
        return result;
    }

    // match all/match

    // nf
    public String nf(int num) {
        return str(num);
    }

    public String nf(float num) {
        return str(num);
    }

    public String[] nf(int[] nums) {
        String[] outgoing = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            outgoing[i] = str(nums[i]);
        }
        return outgoing;
    }

    public String[] nf(float[] nums) {
        String[] outgoing = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            outgoing[i] = str(nums[i]);
        }
        return outgoing;
    }

    public String nf(int num, int digits) {
        return String.format("%0" + digits + "d", num);
    }

    public String nf(float num, int digits) {
        return String.format("%0" + digits + "f", num);
    }

    public String[] nf(int[] nums, int digits) {
        String[] outgoing = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            outgoing[i] = String.format("%0" + digits + "d", nums[i]);
        }
        return outgoing;
    }

    public String[] nf(float[] nums, int digits) {
        String[] outgoing = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            outgoing[i] = String.format("%0" + digits + "f", nums[i]);
        }
        return outgoing;
    }

    public String nf(int num, int left, int right) {
        String first = left > 0 ? String.format("%0" + left + "d", num) : str(num);
        String second = String.format("%0" + right + "d", (int) (num % 1 * Math.pow(10, right)));
        return first + "." + second;
    }

    public String nf(float num, int left, int right) {
        String first = left > 0 ? String.format("%0" + left + "d", (int) num) : str((int) num);
        String second = String.format("%0" + right + "d", (int) (num % 1 * Math.pow(10, right)));
        return first + "." + second;
    }

    public String[] nf(int[] nums, int left, int right) {
        String[] outgoing = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            String first = left > 0 ? String.format("%0" + left + "d", nums[i]) : str(nums[i]);
            String second = String.format("%0" + right + "d", (int) (nums[i] % 1 * Math.pow(10, right)));
            outgoing[i] = first + "." + second;
        }
        return outgoing;
    }

    public String[] nf(float[] nums, int left, int right) {
        String[] outgoing = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            String first = left > 0 ? String.format("%0" + left + "d", (int) nums[i]) : str((int) nums[i]);
            String second = String.format("%0" + right + "d", (int) (nums[i] % 1 * Math.pow(10, right)));
            outgoing[i] = first + "." + second;
        }
        return outgoing;
    }

    // nfc
    public String nfc(int num) {
        String output = str(num);

        int length = output.length() - 1;
        if (output.charAt(0) == '-') {
            length--;
        }
        int commas = length / 3;
        for (int i = 0; i < commas; i++) {
            output = output.substring(0, output.length() - (i + 1) * 3 - i) + ","
                    + output.substring(output.length() - (i + 1) * 3 - i);
        }
        return output;
    }

    public String nfc(float num) {
        String first = nfc((int) num);
        String second = str(num).substring(str(num).indexOf("."));
        return first + second;
    }

    public String[] nfc(int[] num) {
        String[] output = new String[num.length];
        for (int i = 0; i < num.length; i++) {
            output[i] = nfc(num[i]);
        }
        return output;
    }

    public String[] nfc(float[] num) {
        String[] output = new String[num.length];
        for (int i = 0; i < num.length; i++) {
            output[i] = nfc(num[i]);
        }
        return output;
    }

    public String nfc(float num, int digits) {
        String first = nfc((int) num);
        String second = nf(num, 0, digits);
        second = second.substring(second.indexOf("."));
        return first + second;
    }

    public String[] nfc(int[] num, int digits) {
        String[] output = new String[num.length];
        for (int i = 0; i < num.length; i++) {
            output[i] = nfc(num[i], digits);
        }
        return output;
    }

    public String[] nfc(float[] num, int digits) {
        String[] output = new String[num.length];
        for (int i = 0; i < num.length; i++) {
            output[i] = nfc(num[i], digits);
        }
        return output;
    }

    // nfp
    public String nfp(int num) {
        return num > 0 ? "+" + nf(num) : nf(num);
    }

    public String nfp(float num) {
        return num > 0 ? "+" + nf(num) : nf(num);
    }

    public String[] nfp(int[] num) {
        String[] output = new String[num.length];
        for (int i = 0; i < num.length; i++) {
            output[i] = nfp(num[i]);
        }
        return output;
    }

    public String[] nfp(float[] num) {
        String[] output = new String[num.length];
        for (int i = 0; i < num.length; i++) {
            output[i] = nfp(num[i]);
        }
        return output;
    }

    public String nfp(int num, int digits) {
        return num > 0 ? "+" + nf(num, digits) : nf(num, digits);
    }

    public String nfp(float num, int digits) {
        return num > 0 ? "+" + nf(num, digits) : nf(num, digits);
    }

    public String[] nfp(int[] num, int digits) {
        String[] output = new String[num.length];
        for (int i = 0; i < num.length; i++) {
            output[i] = nfp(num[i], digits);
        }
        return output;
    }

    public String[] nfp(float[] num, int digits) {
        String[] output = new String[num.length];
        for (int i = 0; i < num.length; i++) {
            output[i] = nfp(num[i], digits);
        }
        return output;
    }

    public String nfp(int num, int left, int right) {
        return num > 0 ? "+" + nf(num, left, right) : nf(num, left, right);
    }

    public String nfp(float num, int left, int right) {
        return num > 0 ? "+" + nf(num, left, right) : nf(num, left, right);
    }

    public String[] nfp(int[] num, int left, int right) {
        String[] output = new String[num.length];
        for (int i = 0; i < num.length; i++) {
            output[i] = nfp(num[i], left, right);
        }
        return output;
    }

    public String[] nfp(float[] num, int left, int right) {
        String[] output = new String[num.length];
        for (int i = 0; i < num.length; i++) {
            output[i] = nfp(num[i], left, right);
        }
        return output;
    }

    // nfs
    public String nfs(int num) {
        return num > 0 ? " " + nf(num) : nf(num);
    }

    public String nfs(float num) {
        return num > 0 ? " " + nf(num) : nf(num);
    }

    public String[] nfs(int[] num) {
        String[] output = new String[num.length];
        for (int i = 0; i < num.length; i++) {
            output[i] = nfs(num[i]);
        }
        return output;
    }

    public String[] nfs(float[] num) {
        String[] output = new String[num.length];
        for (int i = 0; i < num.length; i++) {
            output[i] = nfs(num[i]);
        }
        return output;
    }

    public String nfs(int num, int digits) {
        return num > 0 ? " " + nf(num, digits) : nf(num, digits);
    }

    public String nfs(float num, int digits) {
        return num > 0 ? " " + nf(num, digits) : nf(num, digits);
    }

    public String[] nfs(int[] num, int digits) {
        String[] output = new String[num.length];
        for (int i = 0; i < num.length; i++) {
            output[i] = nfs(num[i], digits);
        }
        return output;
    }

    public String[] nfs(float[] num, int digits) {
        String[] output = new String[num.length];
        for (int i = 0; i < num.length; i++) {
            output[i] = nfs(num[i], digits);
        }
        return output;
    }

    public String nfs(int num, int left, int right) {
        return num > 0 ? " " + nf(num, left, right) : nf(num, left, right);
    }

    public String nfs(float num, int left, int right) {
        return num > 0 ? " " + nf(num, left, right) : nf(num, left, right);
    }

    public String[] nfs(int[] num, int left, int right) {
        String[] output = new String[num.length];
        for (int i = 0; i < num.length; i++) {
            output[i] = nfs(num[i], left, right);
        }
        return output;
    }

    public String[] nfs(float[] num, int left, int right) {
        String[] output = new String[num.length];
        for (int i = 0; i < num.length; i++) {
            output[i] = nfs(num[i], left, right);
        }
        return output;
    }

    public String[] split(String str, char separator) {
        return str.split(Character.toString(separator));
    }

    public String[] split(String str, String separator) {
        return str.split(separator);
    }

    public String trim(String str) {
        return str.trim();
    }

    // Time and Date Functions
    public int day() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    public int hour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

    public int minute() {
        return Calendar.getInstance().get(Calendar.MINUTE);
    }

    public int month() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    public int second() {
        return Calendar.getInstance().get(Calendar.SECOND);
    }

    public int year() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    // Math Functions
    public int abs(int n) {
        return Math.abs(n);
    }

    public float abs(double n) {
        return Helper.abs(n);
    }

    public int ceil(double n) {
        return (int) Math.ceil(n);
    }

    public int floor(double n) {
        return (int) Math.floor(n);
    }

    public float constrain(double value, double min, double max) {
        return Helper.constrain(value, min, max);
    }

    public float constrain(int value, int min, int max) {
        return Helper.constrain(value, min, max);
    }

    public float dist(double x1, double y1, double x2, double y2) {
        return PVector.dist(x1, y1, x2, y2);
    }

    public float dist(PVector p1, PVector p2) {
        return PVector.dist(p1, p2);
    }

    public float exp(double n) {
        return (float) Math.exp(n);
    }

    public float lerp(double start, double stop, double amt) {
        return Helper.lerp(start, stop, amt);
    }

    public float ln(double n) {
        return (float) Math.log(n);
    }

    public float log(double n) {
        return (float) Math.log10(n);
    }

    public float mag(double a, double b) {
        return (float) Math.sqrt(a * a + b * b);
    }

    public float mag(double a, double b, double c) {
        return (float) Math.sqrt(a * a + b * b + c * c);
    }

    public float map(double value, double min1, double max1, double min2, double max2) {
        return Helper.map(value, min1, max1, min2, max2);
    }

    public float max(double n1, double n2) {
        return (float) Math.max(n1, n2);
    }

    public int max(int n1, int n2) {
        return Math.max(n1, n2);
    }

    public float max(double n1, double n2, double n3) {
        return (float) Math.max(n1, Math.max(n2, n3));
    }

    public int max(int n1, int n2, int n3) {
        return Math.max(n1, Math.max(n2, n3));
    }

    public float min(double n1, double n2) {
        return (float) Math.min(n1, n2);
    }

    public int min(int n1, int n2) {
        return Math.min(n1, n2);
    }

    public float min(double n1, double n2, double n3) {
        return (float) Math.min(n1, Math.min(n2, n3));
    }

    public int min(int n1, int n2, int n3) {
        return Math.min(n1, Math.min(n2, n3));
    }

    public float norm(double value, double min, double max) {
        return Helper.norm(value, min, max);
    }

    public float pow(double n, double e) {
        return Helper.pow(n, e);
    }

    public int round(double n) {
        return (int) Math.round(n);
    }

    public float sq(double n) {
        return (float) (n * n);
    }

    public float sqrt(double n) {
        return Helper.sqrt(n);
    }

    // Trigonometry Functions
    public float acos(double n) {
        return Helper.acos(n);
    }

    public float asin(double n) {
        return Helper.asin(n);
    }

    public float atan(double n) {
        return Helper.atan(n);
    }

    public float atan2(double y, double x) {
        return Helper.atan2(y, x);
    }

    public float cos(double angle) {
        return Helper.cos(angle);
    }

    public float sin(double angle) {
        return Helper.sin(angle);
    }

    public float tan(double angle) {
        return Helper.tan(angle);
    }

    public float csc(double angle) {
        return 1 / Helper.sin(angle);
    }

    public float sec(double angle) {
        return 1 / Helper.cos(angle);
    }

    public float cot(double angle) {
        return 1 / Helper.tan(angle);
    }

    public float acsc(double n) {
        return 1 / asin(n);
    }

    public float asec(double n) {
        return 1 / acos(n);
    }

    public float acot(double n) {
        return 1 / atan(n);
    }

    public float degrees(double radians) {
        return (float) Math.toDegrees(radians);
    }

    public float radians(double degrees) {
        return (float) Math.toRadians(degrees);
    }

    // Random Functions
    public float random(double high) {
        return Helper.random(high);
    }

    public float random(double low, double high) {
        return Helper.random(low, high);
    }

    public float random() {
        return Helper.random();
    }

    public float randomGaussian() {
        return (float) (new Random().nextGaussian());
    }

    // color
    public color color(Color color) {
        return new color(color);
    }

    public color color(double gray, double alpha) {
        return new color(gray, alpha);
    }

    public color color(double gray) {
        return color(gray, 255);
    }

    public color color(double red, double green, double blue, double alpha) {
        return new color(red, green, blue, alpha);
    }

    public color color(double red, double green, double blue) {
        return color(red, green, blue, 255);
    }

    public int alpha(color color) {
        return color.a;
    }

    public int blue(color color) {
        return color.b;
    }

    public int green(color color) {
        return color.g;
    }

    public int red(color color) {
        return color.r;
    }

    public float brightness(color color) {
        return color.getBrightness();
    }

    public color lerpColor(color c1, color c2, double amt) {
        return new color(
                (int) Helper.lerp(c1.getRed(), c2.getRed(), amt),
                (int) Helper.lerp(c1.getGreen(), c2.getGreen(), amt),
                (int) Helper.lerp(c1.getBlue(), c2.getBlue(), amt),
                (int) Helper.lerp(c1.getAlpha(), c2.getAlpha(), amt));
    }

    public float saturation(color color) {
        float[] hsb = color.RGBtoHSB();
        return hsb[1];
    }

    public float hue(color color) {
        float[] hsb = color.RGBtoHSB();
        return hsb[0];
    }

    public void saveStrings(String[] lines, String filename) {
        try {
            Files.write(Paths.get(filename), Arrays.asList(lines));
        } catch (IOException e) {
        }
    }
}