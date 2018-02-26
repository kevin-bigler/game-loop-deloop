package com.ktb;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MAJOR;
import static org.lwjgl.glfw.GLFW.GLFW_CONTEXT_VERSION_MINOR;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_CORE_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_FORWARD_COMPAT;
import static org.lwjgl.glfw.GLFW.GLFW_OPENGL_PROFILE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_SAMPLES;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetTime;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * <p>{@link Window} implementation using LWJGL's GLFW integration</p>
 *
 * <p>See {@link GLFW}</p>
 */
public class LwjglGlfwWindow implements Window {
    private long windowId = NULL;

    @Override
    public void open() {
        initWindow();
        createWindow();
        prepareForRendering();
    }

    @Override
    public void close() throws Exception {
        destroy();
    }

    @Override
    public boolean isOpen() {
        return windowId != NULL && !glfwWindowShouldClose(windowId);
    }

    @Override
    public void update() {
        if (isOpen()) {
            glfwPollEvents();
            glfwSwapBuffers(windowId);
        }
    }

    private void initWindow() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()) {
            System.err.println("Error initializing GLFW");
            System.exit(1);
        }
    }

    private void createWindow() {
        windowHints();
        // TODO add createWindow() params to constructor
        windowId = glfwCreateWindow(640, 480, "Game Loop DeLoop", NULL, NULL);

        if (windowId == NULL) {
            System.err.println("Error creating a window");
            System.exit(1);
        }
    }

    /**
     * Must be called before {@link GLFW#glfwCreateWindow(int, int, CharSequence, long, long)}
     */
    private void windowHints() {
        glfwWindowHint(GLFW_SAMPLES, 4);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
    }

    private void prepareForRendering() {
        glfwMakeContextCurrent(windowId);
        GL.createCapabilities();
        glfwSwapInterval(1);
    }

    private void destroy() {
        glfwDestroyWindow(windowId);
        glfwTerminate();
    }
}
