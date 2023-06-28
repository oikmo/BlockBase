package org.VoxelTest.renderengine;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import org.VoxelTest.main.VoxelTest;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.*;
import org.lwjgl.opengl.*;
import org.lwjgl.opengl.DisplayMode;
import java.io.File;

public class DisplayManager {
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	private static final int FPS_CAP = 120;
	
	private static long lastFrameTime;
	private static float delta;
	static long startTime = System.nanoTime();
    static int frames = 0;
	
	public static void createDisplay() {
		ContextAttribs attribs = new ContextAttribs(3,2).withForwardCompatible(true).withProfileCore(true);
		
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create(new PixelFormat(), attribs);
			Display.setTitle("[DEBUG] VoxelTest");
			GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		Mouse.setGrabbed(false);
	}
	
	public static void updateDisplay() throws LWJGLException {
		Display.sync(FPS_CAP);
		Display.update();
		
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) { closeDisplay();}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_E)) {
			Mouse.setGrabbed(!Mouse.isGrabbed());
		}
		
		long currentFrameTime = getCurrentTime();
		delta = (currentFrameTime - lastFrameTime)/1000f;
		lastFrameTime = currentFrameTime;
		
	}
	
	public static void saveScreenshot() throws Exception {
	    System.out.println("Saving screenshot!");
	    Rectangle screenRect = new Rectangle(Display.getX(), Display.getY(), Display.getWidth(), Display.getHeight());
	    BufferedImage capture = new Robot().createScreenCapture(screenRect);
	    ImageIO.write(capture, "png", new File("screenshot.png"));
	}
	
	public static void closeDisplay() {
		VoxelTest.loader.cleanUp();
		VoxelTest.renderer.cleanUp();
		Display.destroy();
		System.exit(0);
	}
	
	public static float getFrameTimeSeconds() {
		return delta;
	}
	
	
	private static long getCurrentTime() {
		return Sys.getTime()*1000/Sys.getTimerResolution();
	}
}	
