package library.core;

import java.awt.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.*;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.Arrays;
import java.lang.reflect.Array;

public class Applet extends JPanel implements PConstants {

    private JFrame frame;
    private BufferedImage img;
    private Graphics2D g2d;

    public color[] pixels;

    private int rectMode = CORNER;
    private int ellipseMode = CENTER;
    private int textAlign = LEFT;

    private color fillColor = color(0);
    private color strokeColor = color(0);

    private float strokeWeight = 1;
    private float textSize = 12;
    private String textFont = "Arial";

    private float rotation = 0;
    private PVector translation = PVector.zero();
    private float scale = 1;

    public float mouseX = 0;
    public float mouseY = 0;
    public PVector mouse = PVector.zero();
    public float pmouseX = 0;
    public float pmouseY = 0;
    public PVector pmouse = PVector.zero();

    public int width;
    public int height;
    public int displayWidth;
    public int displayHeight;
    private double universalScale = 1;

    public boolean mousePressed = false;
    public boolean keyPressed = false;
    public int mouseButton = CENTER;
    public String key = "";
    public int keyCode = 0;

    // Shape
    private ArrayList<PVector> points = new ArrayList<PVector>();
    private int shapeMode = RIGID;

    // FPS
    private ArrayList<Double> frameRates = new ArrayList<Double>();
    public float frameRate = 0;
    private double lastTime = 0;
    public int frameCount = 0;
    private double startTime = 0;

    // Random noise offsets
    private float noiseXOffset = 0;
    private float noiseYOffset = 0;
    private float noiseZOffset = 0;
    private float noiseWOffset = 0;

    private boolean exitOnEscape = true;

    private boolean fullScreen = false;

    // Push/Pop
    private int previousRectMode = CORNER;
    private int previousEllipseMode = CORNER;
    private int previousTextAlign = LEFT;

    private color previousFillColor = color(0);
    private color previousStrokeColor = color(0);

    private float previousStrokeWeight = 1;
    private float previousTextSize = 12;
    private String previousTextFont = "Arial";

    private float previousRotation = 0;
    private PVector previousTranslation = PVector.zero();
    private float previousScale = 1;

    public void size(int width, int height) {
        Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize();
        displayWidth = (int) displaySize.getWidth();
        displayHeight = (int) displaySize.getHeight();
        universalScale = 1;

        setDoubleBuffered(true);

        this.width = (int) width;
        this.height = (int) height;

        PComponent.width = width;
        PComponent.height = height;

        Dimension size = new Dimension(this.width, this.height);
        setMinimumSize(size);

        frame = new JFrame("Sketch");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);

        frame.pack();
        frame.setResizable(false);

        startTime = System.currentTimeMillis();
        lastTime = System.currentTimeMillis();

        addListeners();

        noiseXOffset = Helper.random(1000.0f);
        noiseYOffset = Helper.random(1000.0f);
        noiseZOffset = Helper.random(1000.0f);
        noiseWOffset = Helper.random(1000.0f);

        frame.setVisible(true);

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g2d = img.createGraphics();
        pixels = new color[width * height];

        smooth();
    }

    /**
     * Note: Automatically sets width to 1920, and height to the same ratio as your
     * display.
     */
    public void fullScreen() {
        Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize();
        displayWidth = (int) displaySize.getWidth();
        displayHeight = (int) displaySize.getHeight();
        universalScale = displayWidth / 1920.0;

        setDoubleBuffered(true);

        width = 1920;
        height = (int) (displayHeight * (1920.0 / displayWidth));

        PComponent.width = width;
        PComponent.height = height;

        Dimension size = new Dimension(displayWidth, displayHeight);
        setMinimumSize(size);

        frame = new JFrame("Sketch");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);

        frame.getContentPane().add(this);

        frame.pack();
        frame.setResizable(false);

        startTime = System.currentTimeMillis();
        lastTime = System.currentTimeMillis();

        addListeners();

        noiseXOffset = Helper.random(1000.0f);
        noiseYOffset = Helper.random(1000.0f);
        noiseZOffset = Helper.random(1000.0f);
        noiseWOffset = Helper.random(1000.0f);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

        fullScreen = true;

        // img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        img = new BufferedImage(displayWidth, displayHeight, BufferedImage.TYPE_INT_ARGB);
        g2d = img.createGraphics();
        // pixels = new color[width * height];
        pixels = new color[displayWidth * displayHeight];

        smooth();
    }

    public JFrame getFrame() {
        return frame;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(width, height);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(width, height);
    }

    public Graphics2D getGraphics2D() {
        return g2d;
    }

    private void addListeners() {
        frame.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                mousePressed = true;
                mouseButton = evt.getButton();
                mousePress();
            }

            public void mouseReleased(MouseEvent evt) {
                mousePressed = false;
                mouseButton = CENTER;
                mouseRelease();
            }

            public void mouseClicked(MouseEvent evt) {
                mouseButton = evt.getButton();
                mouseClick();
            }
        });

        frame.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent evt) {
                mouseX = evt.getX();
                mouseY = evt.getY();
                mouseX /= universalScale;
                mouseY /= universalScale;
                if (!fullScreen) {
                    mouseX -= 8 / universalScale;
                    mouseY -= 31 / universalScale;
                }
                mouse = new PVector(mouseX, mouseY);
                mouseMove();
            }

            public void mouseDragged(MouseEvent evt) {
                mouseX = evt.getX();
                mouseY = evt.getY();
                mouseX /= universalScale;
                mouseY /= universalScale;
                if (!fullScreen) {
                    mouseX -= 8 / universalScale;
                    mouseY -= 31 / universalScale;
                }
                mouse = new PVector(mouseX, mouseY);
                mouseDrag();
            }
        });

        frame.addMouseWheelListener(new MouseWheelListener() {
            public void mouseWheelMoved(MouseWheelEvent evt) {
                int notches = evt.getWheelRotation();
                mouseScroll(notches);
            }
        });

        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                keyCode = evt.getKeyCode();
                String keyText = KeyEvent.getKeyText(evt.getKeyCode());
                key = keyText;

                keyPressed = true;

                keyPress();

                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE && exitOnEscape) {
                    exit();
                }
            }

            public void keyReleased(KeyEvent evt) {
                keyCode = evt.getKeyCode();
                String keyText = KeyEvent.getKeyText(evt.getKeyCode());
                key = keyText;

                keyPressed = false;

                keyRelease();
            }

            public void keyTyped(KeyEvent evt) {
                keyType();
            }
        });
    }

    // Mouse inputs (protected so that they can be overridden)
    protected void mousePress() {
        mousePressed();
        for (int i = 0; i < PComponent.components.size(); i++) {
            PComponent.components.get(i).mousePressed();
        }
    }

    protected void mousePressed() {

    }

    protected void mouseRelease() {
        mouseReleased();
        for (int i = 0; i < PComponent.components.size(); i++) {
            PComponent.components.get(i).mouseReleased();
        }
    }

    protected void mouseReleased() {

    }

    protected void mouseClick() {
        mouseClicked();
        for (int i = 0; i < PComponent.components.size(); i++) {
            PComponent.components.get(i).mouseClicked();
        }
    }

    protected void mouseClicked() {

    }

    protected void mouseDrag() {
        mouseDragged();
        for (int i = 0; i < PComponent.components.size(); i++) {
            PComponent.components.get(i).mouseDragged();
        }
    }

    protected void mouseDragged() {

    }

    protected void mouseScroll(int amount) {
        mouseScrolled(amount);
        for (int i = 0; i < PComponent.components.size(); i++) {
            PComponent.components.get(i).mouseScrolled(amount);
        }
    }

    protected void mouseScrolled(int amount) {

    }

    protected void mouseMove() {
        mouseMoved();
        for (int i = 0; i < PComponent.components.size(); i++) {
            PComponent.components.get(i).mouseMoved();
        }
    }

    protected void mouseMoved() {

    }

    // Keyboard inputs
    protected void keyPress() {
        keyPressed();
        for (int i = 0; i < PComponent.components.size(); i++) {
            PComponent.components.get(i).keyPressed();
        }
    }

    protected void keyPressed() {

    }

    protected void keyRelease() {
        keyReleased();
        for (int i = 0; i < PComponent.components.size(); i++) {
            PComponent.components.get(i).keyReleased();
        }
    }

    protected void keyReleased() {

    }

    protected void keyType() {
        keyTyped();
        for (int i = 0; i < PComponent.components.size(); i++) {
            PComponent.components.get(i).keyTyped();
        }
    }

    /**
     * Warning: This method is not called when the user presses any modifier (e.g.
     * shift, ctrl, alt, backspace).
     * 
     * @param key
     */
    protected void keyTyped() {

    }

    public void setup() {
        // Empty
    }

    public void draw() {
        // Empty
    }

    public void noSmooth() {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
    }

    public void smooth() {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    }

    public void backendUpdate() {
        // invalidate();
        repaint();

        rotation = 0;
        translation = PVector.zero();
        scale = 1;

        double nextFrameRate = 1000.0 / (millis() - lastTime);
        if (frameRates.size() < 10) {
            frameRates.add(nextFrameRate);
        } else {
            frameRates.remove(0);
            frameRates.add(nextFrameRate);
        }
        frameRate = 0;
        for (double frameRate : frameRates) {
            this.frameRate += frameRate;
        }
        this.frameRate /= frameRates.size();
        lastTime = millis();
        frameCount++;

        PComponent.updateVariables();

        draw();

        // Update mouse
        pmouseX = mouseX;
        pmouseY = mouseY;
        pmouse = mouse.copy();
    }

    public void exitOnEscape(boolean exitOnEscape) {
        this.exitOnEscape = exitOnEscape;
    }

    public void frameRate(double targetFrameRate) {
        Window.frameRate = (int) targetFrameRate;
        if (Window.frameRate <= 0) {
            Window.frameRate = 60;
        }
    }

    public void displayFrameRate() {
        String fps = "FPS: " + Helper.roundString((float) frameRate, 0);

        push();
        noStroke();

        rectMode(CENTER);
        fill(0, 0, 0, 100);
        rect(width - 50, 20, 100, 40);

        fill(255);
        textAlign(CENTER);
        textSize(20);
        textFont("Arial");
        text(fps, width - 50, 20);
        pop();
    }

    public void background(color color) {
        color previousColor = color(g2d.getColor());

        g2d.setColor(color.toColor());
        g2d.scale(universalScale, universalScale);
        g2d.fillRect(0, 0, width, height);
        g2d.scale(1 / universalScale, 1 / universalScale);

        g2d.setColor(previousColor.toColor());
    }

    public void background(double r, double g, double b, double a) {
        background(color(r, g, b, a));
    }

    public void background(double r, double g, double b) {
        background(r, g, b, 255);
    }

    public void background(double gray, double a) {
        background(gray, gray, gray, a);
    }

    public void background(double gray) {
        background(gray, 255);
    }

    // Drawing Modes
    public void rectMode(int mode) {
        rectMode = mode;
    }

    public void ellipseMode(int mode) {
        ellipseMode = mode;
    }

    // Pixels
    public void loadPixels() {
        WritableRaster raster = img.getRaster();
        int[] pixelsInt = new int[width * height];
        raster.getDataElements(0, 0, width, height, pixelsInt);
        for (int i = 0; i < pixelsInt.length; i++) {
            pixels[i] = color.fromInt(pixelsInt[i]);
        }
    }

    public void updatePixels() {
        int[] pixelsInt = new int[pixels.length];
        for (int i = 0; i < pixels.length; i++) {
            pixelsInt[i] = pixels[i].getRGB();
        }

        // img.setRGB(0, 0, width, height, pixelsInt, 0, width);
        img.getRaster().setDataElements(0, 0, width, height, pixelsInt); // Much faster
    }

    public color get(int x, int y) {
        return pixels[y * width + x];
    }

    public color get(double x, double y) {
        return get((int) x, (int) y);
    }

    public void set(int x, int y, color color) {
        pixels[y * width + x] = color;
    }

    public void set(double x, double y, color color) {
        set((int) x, (int) y, color);
    }

    public void set(int x, int y, int c) {
        set(x, y, new color(c));
    }

    public void set(double x, double y, int c) {
        set(x, y, new color(c));
    }

    // Push/Pop
    public void push() {
        previousRectMode = rectMode;
        previousEllipseMode = ellipseMode;
        previousTextAlign = textAlign;

        previousFillColor = fillColor;
        previousStrokeColor = strokeColor;

        previousStrokeWeight = strokeWeight;
        previousTextSize = textSize;
        previousTextFont = textFont;

        previousRotation = rotation;
        previousTranslation = translation;
        previousScale = scale;
    }

    public void pop() {
        rectMode = previousRectMode;
        ellipseMode = previousEllipseMode;
        textAlign = previousTextAlign;

        fillColor = previousFillColor;
        strokeColor = previousStrokeColor;

        strokeWeight = previousStrokeWeight;
        textSize = previousTextSize;
        textFont = previousTextFont;

        rotation = previousRotation;
        translation = previousTranslation;
        scale = previousScale;
    }

    // Fill
    public void fill(color color) {
        fillColor = color;
    }

    public void fill(double r, double g, double b, double a) {
        fill(color(r, g, b, a));
    }

    public void fill(double r, double g, double b) {
        fill(r, g, b, 255);
    }

    public void fill(double gray, double a) {
        fill(gray, gray, gray, a);
    }

    public void fill(double gray) {
        fill(gray, gray, gray);
    }

    public void noFill() {
        fill(0, 0);
    }

    // Stroke
    public void stroke(color color, int a) {
        a = Helper.constrain(a, 0, 255);
        strokeColor = new color(color.getRed(), color.getGreen(), color.getBlue(), a);
    }

    public void stroke(color color) {
        strokeColor = color;
    }

    public void stroke(double r, double g, double b, double a) {
        r = Helper.constrain(r, 0, 255);
        g = Helper.constrain(g, 0, 255);
        b = Helper.constrain(b, 0, 255);
        a = Helper.constrain(a, 0, 255);
        stroke(new color((int) r, (int) g, (int) b, (int) a));
    }

    public void stroke(double r, double g, double b) {
        stroke(r, g, b, 255);
    }

    public void stroke(double gray, double a) {
        stroke(gray, gray, gray, a);
    }

    public void stroke(double gray) {
        stroke(gray, gray, gray);
    }

    public void noStroke() {
        stroke(0, 0);
    }

    // Stroke Weight
    public void strokeWeight(double weight) {
        strokeWeight = Helper.constrain(weight, 0, weight);
    }

    // Text Size
    public void textSize(double size) {
        textSize = Helper.constrain(size, 0, size);
    }

    // Text Align
    public void textAlign(int align) {
        textAlign = align;
    }

    // Text Font
    public void textFont(String font) {
        textFont = font;
    }

    // Transformations
    public void translate(double x, double y) {
        translation = new PVector(x, y);
    }

    public void translate(PVector p) {
        translation = p.copy();
    }

    public void rotate(double angle) {
        rotation = (float) angle;
    }

    public void scale(double scalar) {
        scale = (float) scalar;
    }

    // Returns the width of the text
    public int textWidth(String text) {
        return frame.getGraphics().getFontMetrics().stringWidth(text);
    }

    // Returns the height of the text
    public int textHeight(String text) {
        return frame.getGraphics().getFontMetrics().getHeight();
    }

    public void drawGenericShape(Shape shape) {
        AffineTransform old = g2d.getTransform();

        g2d.translate(translation.x, translation.y);
        g2d.scale(scale, scale);
        g2d.rotate(rotation);

        // Universal scale
        g2d.scale(universalScale, universalScale);

        // Fill
        g2d.setColor(fillColor.toColor());
        g2d.fill(shape);

        // Stroke
        g2d.setColor(strokeColor.toColor());
        g2d.setStroke(new BasicStroke((float) strokeWeight));
        g2d.draw(shape);

        g2d.setTransform(old);
    }

    // Ellipse
    public void drawEllipse(double x, double y, double w, double h) {
        if (ellipseMode == CENTER) {
            x -= w / 2;
            y -= h / 2;
        }

        // AffineTransform old = g2d.getTransform();

        // g2d.translate(translation.x, translation.y);
        // g2d.scale(scale, scale);
        // g2d.rotate(rotation);

        // Ellipse2D.Double ellipse = new Ellipse2D.Double(x, y, w, h);
        // ellipse.setFrame(x, y, w, h);

        // // Fill
        // g2d.setColor(fillColor.toColor());
        // g2d.fill(ellipse);

        // // Stroke
        // g2d.setColor(strokeColor.toColor());
        // g2d.setStroke(new BasicStroke(strokeWeight));
        // g2d.draw(ellipse);

        // g2d.setTransform(old);

        drawGenericShape(new Ellipse2D.Double(x, y, w, h));

    }

    public void ellipse(double x, double y, double w, double h) {
        drawEllipse(x, y, w, h);
    }

    public void ellipse(PVector p, double w, double h) {
        ellipse(p.x, p.y, w, h);
    }

    public void ellipse(PVector p, PVector s) {
        ellipse(p.x, p.y, s.x, s.y);
    }

    // Circle
    public void circle(double x, double y, double r) {
        ellipse(x, y, r, r);
    }

    public void circle(PVector p, double r) {
        ellipse(p.x, p.y, r, r);
    }

    // Line
    public void drawLine(double x1, double y1, double x2, double y2) {
        AffineTransform old = g2d.getTransform();

        g2d.translate(translation.x, translation.y);
        g2d.scale(scale, scale);
        g2d.rotate(rotation);

        // Universal scale
        g2d.scale(universalScale, universalScale);

        g2d.setColor(strokeColor.toColor());
        g2d.setStroke(new BasicStroke(strokeWeight));
        g2d.draw(new Line2D.Double(x1, y1, x2, y2));

        g2d.setTransform(old);

    }

    public void line(double x1, double y1, double x2, double y2) {
        drawLine(x1, y1, x2, y2);
    }

    public void line(PVector p1, PVector p2) {
        line(p1.x, p1.y, p2.x, p2.y);
    }

    // PVector
    public void point(double x, double y) {
        int previousEllipseMode = ellipseMode;
        ellipseMode = CENTER;
        circle(x, y, strokeWeight);
        ellipseMode = previousEllipseMode;
    }

    public void point(PVector p) {
        point(p.x, p.y);
    }

    // Quad
    public void drawQuad(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        AffineTransform old = g2d.getTransform();

        g2d.translate(translation.x, translation.y);
        g2d.scale(scale, scale);
        g2d.rotate(rotation);

        // Universal scale
        g2d.scale(universalScale, universalScale);

        // Fill
        g2d.setColor(fillColor.toColor());
        g2d.fillPolygon(new int[] { (int) x1, (int) x2, (int) x3, (int) x4 },
                new int[] { (int) y1, (int) y2, (int) y3, (int) y4 }, 4);
        // Stroke
        g2d.setColor(strokeColor.toColor());
        g2d.setStroke(new BasicStroke(strokeWeight));
        g2d.drawPolygon(new int[] { (int) x1, (int) x2, (int) x3, (int) x4 },
                new int[] { (int) y1, (int) y2, (int) y3, (int) y4 }, 4);

        g2d.setTransform(old);

    }

    public void quad(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        drawQuad(x1, y1, x2, y2, x3, y3, x4, y4);
    }

    public void quad(PVector p1, PVector p2, PVector p3, PVector p4) {
        quad(p1.x, p1.y, p2.x, p2.y, p3.x, p3.y, p4.x, p4.y);
    }

    // Rect
    public void drawRect(double x, double y, double w, double h, double r) {
        if (rectMode == CENTER) {
            x -= w / 2;
            y -= h / 2;
        }

        if (r == 0) {
            drawGenericShape(new Rectangle2D.Double(x, y, w, h));
        } else {
            drawGenericShape(new RoundRectangle2D.Double(x, y, w, h, r, r));
        }
    }

    public void rect(double x, double y, double w, double h, double r) {
        drawRect(x, y, w, h, r);
    }

    public void rect(double x, double y, double w, double h) {
        rect(x, y, w, h, 0);
    }

    public void rect(PVector p, double w, double h, double r) {
        rect(p.x, p.y, w, h, r);
    }

    public void rect(PVector p, double w, double h) {
        rect(p.x, p.y, w, h);
    }

    public void rect(PVector p, PVector s, double r) {
        rect(p.x, p.y, s.x, s.y, r);
    }

    public void rect(PVector p, PVector s) {
        rect(p.x, p.y, s.x, s.y);
    }

    // Square
    public void square(double x, double y, double s) {
        rect(x, y, s, s);
    }

    public void square(PVector p, double s) {
        square(p.x, p.y, s);
    }

    // Triangle
    public void drawTriangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        AffineTransform old = g2d.getTransform();

        g2d.translate(translation.x, translation.y);
        g2d.scale(scale, scale);
        g2d.rotate(rotation);

        // Universal scale
        g2d.scale(universalScale, universalScale);

        // Fill
        g2d.setColor(fillColor.toColor());
        g2d.fillPolygon(new int[] { (int) x1, (int) x2, (int) x3 },
                new int[] { (int) y1, (int) y2, (int) y3 }, 3);
        // Stroke
        g2d.setColor(strokeColor.toColor());
        g2d.setStroke(new BasicStroke(strokeWeight));
        g2d.drawPolygon(new int[] { (int) x1, (int) x2, (int) x3 },
                new int[] { (int) y1, (int) y2, (int) y3 }, 3);

        g2d.setTransform(old);
    }

    public void triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        drawTriangle(x1, y1, x2, y2, x3, y3);
    }

    public void triangle(PVector p1, PVector p2, PVector p3) {
        triangle(p1.x, p1.y, p2.x, p2.y, p3.x, p3.y);
    }

    // Text
    public void drawText(String text, double x, double y) {
        AffineTransform old = g2d.getTransform();

        g2d.setFont(new Font(textFont, Font.PLAIN, (int) textSize));
        int w = g2d.getFontMetrics().stringWidth(text);
        int h = g2d.getFontMetrics().getHeight();

        if (textAlign == CENTER) {
            x -= w / 2;
        } else if (textAlign == RIGHT) {
            x -= w;
        }
        y += h / 4;

        g2d.translate(translation.x, translation.y);
        g2d.scale(scale, scale);
        g2d.rotate(rotation);

        // Universal scale
        g2d.scale(universalScale, universalScale);

        g2d.setColor(fillColor.toColor());
        g2d.drawString(text, (int) x, (int) y);

        g2d.setTransform(old);

    }

    public void text(String text, double x, double y) {
        drawText(text, x, y);
    }

    public void text(Object text, double x, double y) {
        text(str(text), x, y);
    }

    public void text(String text, PVector p) {
        text(text, p.x, p.y);
    }

    public void text(Object text, PVector p) {
        text(str(text), p.x, p.y);
    }

    // Shape
    public void drawShape() {
        AffineTransform old = g2d.getTransform();

        g2d.translate(translation.x, translation.y);
        g2d.scale(scale, scale);
        g2d.rotate(rotation);

        // Universal scale
        g2d.scale(universalScale, universalScale);

        if (shapeMode == RIGID) {
            int[] xPVectors = new int[points.size()];
            int[] yPVectors = new int[points.size()];

            g2d.setColor(strokeColor.toColor());
            g2d.setStroke(new BasicStroke(strokeWeight));
            for (int i = 0; i < points.size() - 1; i++) {
                PVector p1 = points.get(i);
                PVector p2 = points.get(i + 1);
                g2d.drawLine((int) p1.x, (int) p1.y, (int) p2.x, (int) p2.y);

                xPVectors[i] = (int) p1.x;
                yPVectors[i] = (int) p1.y;
            }
            xPVectors[points.size() - 1] = (int) points.get(points.size()
                    - 1).x;
            yPVectors[points.size() - 1] = (int) points.get(points.size()
                    - 1).y;
            // Draw the filled in shape
            g2d.setColor(fillColor.toColor());
            g2d.fillPolygon(xPVectors, yPVectors, points.size());
        } else if (shapeMode == SMOOTH) {
            // Catmull-Rom splines smoothing algorithm

            g2d.setColor(strokeColor.toColor());
            g2d.setStroke(new BasicStroke(strokeWeight));

            ArrayList<Integer> xPVectors = new ArrayList<Integer>();
            ArrayList<Integer> yPVectors = new ArrayList<Integer>();

            for (int i = 0; i < points.size() - 3; i++) {
                PVector p1 = points.get(i);
                PVector p2 = points.get(i + 1);
                PVector p3 = points.get(i + 2);
                PVector p4 = points.get(i + 3);

                double x1 = p1.x;
                double y1 = p1.y;
                double x2 = p2.x;
                double y2 = p2.y;
                double x3 = p3.x;
                double y3 = p3.y;
                double x4 = p4.x;
                double y4 = p4.y;

                double t = 0;
                while (t <= 1) {
                    double x = 0.5 * ((2 * x2) + (-x1 + x3) * t + (2 * x1 - 5 * x2 + 4 * x3 - x4)
                            * t * t
                            + (-x1 + 3 * x2 - 3 * x3 + x4) * t * t * t);
                    double y = 0.5 * ((2 * y2) + (-y1 + y3) * t + (2 * y1 - 5 * y2 + 4 * y3 - y4)
                            * t * t
                            + (-y1 + 3 * y2 - 3 * y3 + y4) * t * t * t);
                    int xInt = (int) x;
                    int yInt = (int) y;
                    g2d.drawLine(xInt, yInt, xInt, yInt);
                    xPVectors.add(xInt);
                    yPVectors.add(yInt);
                    t += 0.01;
                }
            }

            int[] xPVectorsArray = Helper.toIntArray(xPVectors);
            int[] yPVectorsArray = Helper.toIntArray(yPVectors);

            // Draw the filled in shape
            g2d.setColor(fillColor.toColor());
            g2d.fillPolygon(xPVectorsArray, yPVectorsArray, xPVectorsArray.length);

            // Draw lines
            g2d.setColor(strokeColor.toColor());
            g2d.setStroke(new BasicStroke(strokeWeight));
            g2d.drawPolyline(xPVectorsArray, yPVectorsArray, xPVectorsArray.length);

            g2d.setTransform(old);
        }
    }

    public void beginShape() {
        points = new ArrayList<PVector>();
    }

    public void beginShape(int mode) {
        shapeMode = mode;
        beginShape();
    }

    public void endShape() {
        drawShape();
        shapeMode = RIGID;
    }

    public void endShape(int endBehavior) {
        if (endBehavior == CLOSE) {
            points.add(points.get(0));
        }
        endShape();
    }

    public void vertex(double x, double y) {
        points.add(new PVector(x, y));
    }

    public void vertex(PVector p) {
        vertex(p.x, p.y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D output = (Graphics2D) g.create();

        // Draw to a double buffer
        Image doubleBuffer = createImage(displayWidth, displayHeight);
        Graphics2D g2dDoubleBuffer = (Graphics2D) doubleBuffer.getGraphics();

        // g2dDoubleBuffer.scale(universalScale, universalScale);

        g2dDoubleBuffer.drawImage(img, 0, 0, null);

        // Draw the double buffer to the screen
        output.drawImage(doubleBuffer, 0, 0, null);
        output.dispose();
    }

    public void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void exit() {
        frame.dispose();
        System.exit(0);
    }

    // Noise
    public void noiseSeed(int seed) {
        noiseXOffset = seed;
        noiseYOffset = seed;
        noiseZOffset = seed;
        noiseWOffset = seed;
    }

    // ------------------------------------------------------------
    // ------------------------------------------------------------
    // ------------------------------------------------------------
    // PROCESSING FUNCTIONS

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

    // TODO match all/match

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

    // TODO splitTokens()

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

    public int millis() {
        return (int) (System.currentTimeMillis() - startTime);
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

    public float noise(double x, double y, double z, double w) {
        return Helper.perlinNoise(x + noiseXOffset, y + noiseYOffset, z + noiseZOffset, w + noiseWOffset);
    }

    public float noise(double x, double y, double z) {
        return Helper.perlinNoise(x + noiseXOffset, y + noiseYOffset, z + noiseZOffset);
    }

    public float noise(double x, double y) {
        return Helper.perlinNoise(x + noiseXOffset, y + noiseYOffset);
    }

    public float noise(double x) {
        return noise(x + noiseXOffset, 0);
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

    /**
     * Saves the frame to a .png file.
     * 
     * @param filename
     */
    public void save(String filename) {
        try {
            ImageIO.write(img, "png", new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveStrings(String[] lines, String filename) {
        try {
            Files.write(Paths.get(filename), Arrays.asList(lines));
        } catch (IOException e) {
        }
    }

    // Shaders
    /**
     * Runs a shader on the entire image. The Dispatch method of the shader should
     * be written for one pixel and it will be run for every pixel in the image in
     * parallel.
     * 
     * @param shader
     */
    public void shaderTexture(Shader shader) {
        int cores = Runtime.getRuntime().availableProcessors();
        // Create columns of chunks
        int threadWidth = width / cores;
        int threadWidthRemainder = width % cores;

        Thread[] threads = new Thread[cores];
        for (int i = 0; i < cores; i++) {
            final int xStart = i * threadWidth;
            int w = threadWidth;
            if (i == cores - 1) {
                w += threadWidthRemainder;
            }
            final int xEnd = xStart + w;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int x = xStart; x < xEnd; x++) {
                        for (int y = 0; y < height; y++) {
                            shader.Dispatch(new PVector(x, y));
                        }
                    }
                }
            });
            threads[i].start();
        }

        for (int i = 0; i < cores; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Runs a shader on an array. The Dispatch method of the shader should be
     * written for one element in the array.
     * 
     * @param shader
     * @param arrayLength
     */
    public void shaderArray(Shader shader, int arrayLength) {
        int cores = Runtime.getRuntime().availableProcessors();
        // Sections of the array
        int threadWidth = arrayLength / cores;
        int threadWidthRemainder = arrayLength % cores;

        Thread[] threads = new Thread[cores];
        for (int i = 0; i < cores; i++) {
            final int xStart = i * threadWidth;
            int w = threadWidth;
            if (i == cores - 1) {
                w += threadWidthRemainder;
            }
            final int xEnd = xStart + w;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int x = xStart; x < xEnd; x++) {
                        shader.Dispatch(new PVector(x, 0));
                    }
                }
            });
            threads[i].start();
        }

        for (int i = 0; i < cores; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}