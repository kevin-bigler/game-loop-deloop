package com.ktb.deloop.example;

import org.lwjgl.glfw.GLFWErrorCallback;

import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * From following https://goharsha.com/lwjgl-tutorial-series/hello-window/ starting Feb 4, 2018
 */
public class HelloWorldDeux {
    /**
     * main method to get things going
     */
    public void run() {

    }

    public void initWindow() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            System.err.println("Error initializing GLFW");
            System.exit(1);
        }
    }

    public void destroy() {
        glfwTerminate();
    }

    public void createWindow() {
        long windowID = glfwCreateWindow(640, 480, "My GLFW Window", NULL, NULL);

        if (windowID == NULL) {
            System.err.println("Error creating a window");
            System.exit(1);
        }
    }
}
