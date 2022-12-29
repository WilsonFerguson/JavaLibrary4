package library.core;

import java.io.File;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window {

    public static int frameRate = 60;
    public static int frameRateDelay = 1000 / frameRate;

    public static void init(Applet applet) {
        run(applet);
    }

    public static void init() {
        // Scan every file in the current directory for a class that extends Applet
        File dir = new File(".");
        File[] files = dir.listFiles();

        String className = "";

        // Go through each file
        for (File file : files) {

            // Make sure it's a .java file
            if (!file.getName().endsWith(".java"))
                continue;

            // Check to see if it extends Applet
            try {
                Scanner scanner = new Scanner(file);

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();

                    if (!line.contains("class"))
                        continue;

                    if (!line.contains("extends Applet"))
                        continue;

                    className = line.split(" ")[1];
                    run(className);
                }

                scanner.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void run(String className) {
        try {

            Class<?> c = Class.forName(className);
            Applet applet = (Applet) c.getDeclaredConstructor().newInstance();
            run(applet);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void run(Applet applet) {
        PComponent.applet = applet;
        applet.setup();

        new javax.swing.Timer(0, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                long start = System.currentTimeMillis();

                applet.backendUpdate();

                // Set the frame rate
                long end = System.currentTimeMillis();
                long time = end - start;
                if (time < frameRateDelay) {
                    try {
                        Thread.sleep(frameRateDelay - time);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }).start();
    }

}