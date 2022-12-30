package library.core;

public interface PConstants {
    // Drawing modes
    public final int CENTER = 0;
    public final int CORNER = 4;
    public final int LEFT = 1;
    public final int RIGHT = 3;

    // Shape modes
    public final int SMOOTH = 1;
    public final int RIGID = 2;
    public final int CLOSE = 0;

    // Math
    public final float PI = (float) Math.PI;
    public final float HALF_PI = (float) Math.PI / 2;
    public final float QUARTER_PI = (float) Math.PI / 4;
    public final float TWO_PI = (float) Math.PI * 2;
    public final float TAU = (float) Math.PI * 2;
    public final float DEG_TO_RAD = (float) Math.PI / 180;
    public final float RAD_TO_DEG = 180 / (float) Math.PI;
}