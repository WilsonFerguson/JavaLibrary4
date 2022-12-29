package library.core;

public class MathHelper {
    /**
     * Returns a {@code float} distance between two points given {@code int} x1,
     * {@code int} y1, {@code int} x2, and {@code int} y2.
     * 
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return float
     */
    public static float getDistance(double x1, double y1, double x2, double y2) {
        return (float) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    /**
     * Returns a {@code float} distance between two points given {@code PVector} p1
     * and {@code PVector} p2.
     * 
     * @param p1
     * @param p2
     * @return float
     */
    public static float getDistance(PVector p1, PVector p2) {
        return getDistance(p1.x, p1.y, p2.x, p2.y);
    }

    /**
     * Returns a {@code float} random value between a {@code float} min and
     * {@code float} max.
     * 
     * @param min
     * @param max
     * @return float
     */
    public static float random(double min, double max) {
        return (float) (Math.random() * (max - min) + min);
    }

    /**
     * Returns a {@code float} random value between 0 and {@code float} max.
     * 
     * @param max
     * @return float
     */
    public static float random(double max) {
        return random(0, max);
    }

    /**
     * Returns a {@code float} random value between 0 and 1.
     * 
     * @return float
     */
    public static float random() {
        return random(1.0);
    }

    /**
     * Maps a given {@code float} value from one range to another.
     * 
     * @param value
     * @param start1
     * @param stop1
     * @param start2
     * @param stop2
     * @return float
     */
    public static float map(double value, double start1, double stop1, double start2, double stop2) {
        return (float) (start2 + (stop2 - start2) * ((value - start1) / (stop1 - start1)));
    }

    /**
     * Maps a given {@code int} value from one range to another.
     * 
     * @param value
     * @param start1
     * @param stop1
     * @param start2
     * @param stop2
     * @return int
     */
    public static int map(int value, int start1, int stop1, int start2, int stop2) {
        return (int) map((float) value, (float) start1, (float) stop1, (float) start2, (float) stop2);
    }

    /**
     * Returns a constrained {@code float} value.
     */
    public static float constrain(double value, double min, double max) {
        return (float) Math.min(Math.max(value, min), max);
    }

    /**
     * Returns a constrained {@code int} value.
     */
    public static int constrain(int value, int min, int max) {
        if (value < min)
            return min;
        if (value > max)
            return max;
        return value;
    }

    /**
     * Lerps between two {@code float} values.
     * 
     * @param start
     * @param stop
     * @param amt
     * 
     * @return float
     */
    public static float lerp(double start, double stop, double amt) {
        return (float) (start + (stop - start) * amt);
    }

    /**
     * Normalizes a value from an arbitrary range to a value between 0 and 1.
     * 
     * @param value
     * @param min
     * @param max
     * 
     * @return float
     */
    public static float norm(double value, double min, double max) {
        return (float) ((value - min) / (max - min));
    }

    /**
     * Returns a smooth {@code float} value between 0 and 1 given a {@code float}
     * x and {@code float} y.
     * 
     * @param x
     * @param y
     * @return float
     */
    public static float perlinNoise(double x, double y) {
        float value = (float) PerlinNoise.noise(x, y); // -1 to 1
        value = (value + 1) / 2; // 0 to 1
        return value;
    }

    /**
     * Returns a smooth {@code float} value between 0 and 1 given a {@code float}
     * x, {@code float} y, and {@code float} z.
     * 
     * @param x
     * @param y
     * @param z
     * @return float
     */
    public static float perlinNoise(double x, double y, double z) {
        float value = (float) PerlinNoise.noise(x, y, z); // -1 to 1
        value = (value + 1) / 2; // 0 to 1
        return value;
    }

    /**
     * Returns a smooth {@code float} value between 0 and 1 given a {@code float}
     * x, {@code float} y, {@code float} z, and {@code float} w.
     * 
     * @param x
     * @param y
     * @param z
     * @param w
     * @return float
     */
    public static float perlinNoise(double x, double y, double z, double w) {
        float value = (float) PerlinNoise.noise(x, y, z, w); // -1 to 1
        value = (value + 1) / 2; // 0 to 1
        return value;
    }

    // Math Functions
    /**
     * Returns the absolute value of the given {@code float} value.
     * 
     * @param value
     * @return float
     */
    public static float abs(double value) {
        return (float) Math.abs(value);
    }

    /**
     * Returns the absolute value of the given {@code int} value.
     * 
     * @param value
     * @return int
     */
    public static int abs(int value) {
        return Math.abs(value);
    }

    /**
     * Returns the cosine of the given {@code float} value.
     * 
     * @param value
     * @return float
     */
    public static float cos(double value) {
        return (float) Math.cos(value);
    }

    /**
     * Returns the sine of the given {@code float} value.
     * 
     * @param value
     * @return float
     */
    public static float sin(double value) {
        return (float) Math.sin(value);
    }

    /**
     * Returns the tangent of the given {@code float} value.
     * 
     * @param value
     * @return float
     */
    public static float tan(double value) {
        return (float) Math.tan(value);
    }

    /**
     * Returns the arc cosine of the given {@code float} value.
     * 
     * @param value
     * @return float
     */
    public static float acos(double value) {
        return (float) Math.acos(value);
    }

    /**
     * Returns the arc sine of the given {@code float} value.
     * 
     * @param value
     * @return float
     */
    public static float asin(double value) {
        return (float) Math.asin(value);
    }

    /**
     * Returns the arc tangent of the given {@code float} value.
     * 
     * @param value
     * @return float
     */
    public static float atan(double value) {
        return (float) Math.atan(value);
    }

    /**
     * Returns the arc tangent of the given {@code float} y and {@code float} x.
     * 
     * @param y
     * @param x
     * @return float
     */
    public static float atan2(double y, double x) {
        return (float) Math.atan2(y, x);
    }

    /**
     * Returns the square root of the given {@code float} value.
     * 
     * @param value
     * @return float
     */
    public static float sqrt(double value) {
        return (float) Math.sqrt(value);
    }

    /**
     * Returns the given {@code float} value raised to the given {@code float}
     * 
     * @param value
     * @param power
     * @return float
     */
    public static float pow(double value, double power) {
        return (float) Math.pow(value, power);
    }
}
