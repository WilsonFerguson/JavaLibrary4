package library.core;

import java.util.ArrayList;

public class PComponent extends PFunctions {

    // static arraylist of all components that will be added to and then called for
    // things like mousePressed
    public static ArrayList<PComponent> components = new ArrayList<PComponent>();

    // static variables for all of the sim stuff
    public static Applet applet;

    // All variables
    public static color[] pixels;

    public static int mouseX = 0;
    public static int mouseY = 0;
    public static PVector mouse = PVector.zero();
    public static int pmouseX = 0;
    public static int pmouseY = 0;
    public static PVector pmouse = PVector.zero();

    public static int width;
    public static int height;
    public static int displayWidth;
    public static int displayHeight;

    public static boolean mousePressed = false;
    public static boolean keyPressed = false;
    public static int mouseButton = CENTER;
    public static String key = "";
    public static int keyCode = 0;

    // FPS
    public static float frameRate = 0;
    public static int frameCount = 0;

    public PComponent() {
        components.add(this);
    }

    public static void updateVariables() {
        pixels = applet.pixels;

        mouseX = applet.mouseX;
        mouseY = applet.mouseY;
        mouse = new PVector(mouseX, mouseY);
        pmouseX = applet.pmouseX;
        pmouseY = applet.pmouseY;
        pmouse = new PVector(pmouseX, pmouseY);

        mousePressed = applet.mousePressed;
        keyPressed = applet.keyPressed;
        mouseButton = applet.mouseButton;
        key = applet.key;
        keyCode = applet.keyCode;

        frameRate = applet.frameRate;
        frameCount = applet.frameCount;
    }

    protected void mousePressed() {

    }

    protected void mouseReleased() {

    }

    protected void mouseClicked() {

    }

    protected void mouseDragged() {

    }

    protected void mouseScrolled(int amount) {

    }

    protected void mouseMoved() {

    }

    protected void keyPressed() {

    }

    protected void keyReleased() {

    }

    /**
     * Warning: This method is not called when the user presses any modifier (e.g.
     * shift, ctrl, alt, backspace).
     * 
     * @param key
     */
    protected void keyTyped() {

    }

    public void smooth() {
        applet.smooth();
    }

    public void noSmooth() {
        applet.noSmooth();
    }

    public void exitOnEscape(boolean exitOnEscape) {
        applet.exitOnEscape(exitOnEscape);
    }

    public void frameRate(double targetFrameRate) {
        applet.frameRate(targetFrameRate);
    }

    public void displayFrameRate() {
        applet.displayFrameRate();
    }

    public void background(color color) {
        applet.background(color);
    }

    public void background(double r, double g, double b, double a) {
        applet.background(r, g, b, a);
    }

    public void background(double r, double g, double b) {
        applet.background(r, g, b);
    }

    public void background(double gray, double a) {
        applet.background(gray, a);
    }

    public void background(double gray) {
        applet.background(gray);
    }

    public void rectMode(int mode) {
        applet.rectMode(mode);
    }

    public void ellipseMode(int mode) {
        applet.ellipseMode(mode);
    }

    // Pixels
    public void loadPixels() {
        applet.loadPixels();
    }

    public void updatePixels() {
        applet.updatePixels();
    }

    public color get(int x, int y) {
        return applet.get(x, y);
    }

    public color get(double x, double y) {
        return applet.get(x, y);
    }

    public void set(int x, int y, color color) {
        applet.set(x, y, color);
    }

    public void set(double x, double y, color color) {
        applet.set(x, y, color);
    }

    public void set(int x, int y, int c) {
        applet.set(x, y, c);
    }

    public void set(double x, double y, int c) {
        applet.set(x, y, c);
    }

    // Push/Pop
    public void push() {
        applet.push();
    }

    public void pop() {
        applet.pop();
    }

    // Fill
    public void fill(color color, double a) {
        applet.fill(color, a);
    }

    public void fill(color color) {
        applet.fill(color);
    }

    public void fill(double r, double g, double b, double a) {
        applet.fill(r, g, b, a);
    }

    public void fill(double r, double g, double b) {
        applet.fill(r, g, b);
    }

    public void fill(double gray, double a) {
        applet.fill(gray, a);
    }

    public void fill(double gray) {
        applet.fill(gray);
    }

    public void noFill() {
        applet.noFill();
    }

    // Stroke
    public void stroke(color color, double a) {
        applet.stroke(color, a);
    }

    public void stroke(color color) {
        applet.stroke(color);
    }

    public void stroke(double r, double g, double b, double a) {
        applet.stroke(r, g, b, a);
    }

    public void stroke(double r, double g, double b) {
        applet.stroke(r, g, b);
    }

    public void stroke(double gray, double a) {
        applet.stroke(gray, a);
    }

    public void stroke(double gray) {
        applet.stroke(gray);
    }

    public void noStroke() {
        applet.noStroke();
    }

    // Stroke Weight
    public void strokeWeight(double weight) {
        applet.strokeWeight(weight);
    }

    // Text Size
    public void textSize(double size) {
        applet.textSize(size);
    }

    // Text Align
    public void textAlign(int align) {
        applet.textAlign(align);
    }

    // Text Font
    public void textFont(String font) {
        applet.textFont(font);
    }

    // Transformations
    public void translate(double x, double y) {
        applet.translate(x, y);
    }

    public void translate(PVector p) {
        applet.translate(p);
    }

    public void rotate(double angle) {
        applet.rotate(angle);
    }

    public void scale(double scalar) {
        applet.scale(scalar);
    }

    // Returns the width of the text
    public int textWidth(String text) {
        return applet.textWidth(text);
    }

    // Returns the height of the text
    public int textHeight(String text) {
        return applet.textHeight(text);
    }

    // Ellipse
    public void drawEllipse(double x, double y, double w, double h) {
        applet.drawEllipse(x, y, w, h);

    }

    public void ellipse(double x, double y, double w, double h) {
        applet.ellipse(x, y, w, h);
    }

    public void ellipse(PVector p, double w, double h) {
        applet.ellipse(p, w, h);
    }

    public void ellipse(PVector p, PVector s) {
        applet.ellipse(p, s);
    }

    // Circle
    public void circle(double x, double y, double r) {
        applet.circle(x, y, r);
    }

    public void circle(PVector p, double r) {
        applet.circle(p, r);
    }

    // Line
    public void drawLine(double x1, double y1, double x2, double y2) {
        applet.drawLine(x1, y1, x2, y2);
    }

    public void line(double x1, double y1, double x2, double y2) {
        applet.line(x1, y1, x2, y2);
    }

    public void line(PVector p1, PVector p2) {
        applet.line(p1, p2);
    }

    // PVector
    public void point(double x, double y) {
        applet.point(x, y);
    }

    public void point(PVector p) {
        applet.point(p);
    }

    // Quad
    public void drawQuad(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        applet.drawQuad(x1, y1, x2, y2, x3, y3, x4, y4);
    }

    public void quad(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        applet.quad(x1, y1, x2, y2, x3, y3, x4, y4);
    }

    public void quad(PVector p1, PVector p2, PVector p3, PVector p4) {
        applet.quad(p1, p2, p3, p4);
    }

    // Rect
    public void drawRect(double x, double y, double w, double h, double r) {
        applet.drawRect(x, y, w, h, r);
    }

    public void rect(double x, double y, double w, double h, double r) {
        applet.rect(x, y, w, h, r);
    }

    public void rect(double x, double y, double w, double h) {
        applet.rect(x, y, w, h);
    }

    public void rect(PVector p, double w, double h, double r) {
        applet.rect(p, w, h, r);
    }

    public void rect(PVector p, double w, double h) {
        applet.rect(p, w, h);
    }

    public void rect(PVector p, PVector s, double r) {
        applet.rect(p, s, r);
    }

    public void rect(PVector p, PVector s) {
        applet.rect(p, s);
    }

    // Square
    public void square(double x, double y, double s) {
        applet.square(x, y, s);
    }

    public void square(PVector p, double s) {
        applet.square(p, s);
    }

    // Triangle
    public void drawTriangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        applet.drawTriangle(x1, y1, x2, y2, x3, y3);
    }

    public void triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        applet.triangle(x1, y1, x2, y2, x3, y3);
    }

    public void triangle(PVector p1, PVector p2, PVector p3) {
        applet.triangle(p1, p2, p3);
    }

    // Text
    public void drawText(String text, double x, double y) {
        applet.drawText(text, x, y);

    }

    public void text(String text, double x, double y) {
        applet.text(text, x, y);
    }

    public void text(Object text, double x, double y) {
        applet.text(text, x, y);
    }

    public void text(String text, PVector p) {
        applet.text(text, p);
    }

    public void text(Object text, PVector p) {
        applet.text(text, p);
    }

    // Shape
    public void drawShape() {
        applet.drawShape();
    }

    public void beginShape() {
        applet.beginShape();
    }

    public void beginShape(int mode) {
        applet.beginShape(mode);
    }

    public void endShape() {
        applet.endShape();
    }

    public void endShape(int endBehavior) {
        applet.endShape(endBehavior);
    }

    public void vertex(double x, double y) {
        applet.vertex(x, y);
    }

    public void vertex(PVector p) {
        applet.vertex(p);
    }

    public void delay(int millis) {
        applet.delay(millis);
    }

    public void exit() {
        applet.exit();
    }

    // Noise
    public void noiseSeed(int seed) {
        applet.noiseSeed(seed);
    }

    public int millis() {
        return applet.millis();
    }

    public float noise(double x, double y, double z, double w) {
        return applet.noise(x, y, z, w);
    }

    public float noise(double x, double y, double z) {
        return applet.noise(x, y, z);
    }

    public float noise(double x, double y) {
        return applet.noise(x, y);
    }

    public float noise(double x) {
        return applet.noise(x);
    }

    /**
     * Saves the frame to a .png file.
     * 
     * @param filename
     */
    public void save(String filename) {
        applet.save(filename);
    }

    public void saveStrings(String[] lines, String filename) {
        applet.saveStrings(lines, filename);
    }

    public void shaderTexture(Shader shader) {
        applet.shaderTexture(shader);
    }

    public void shaderArray(Shader shader, int arrayLength) {
        applet.shaderArray(shader, arrayLength);
    }

}