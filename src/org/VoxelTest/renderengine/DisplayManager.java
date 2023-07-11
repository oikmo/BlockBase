package org.VoxelTest.renderengine;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import org.VoxelTest.main.VoxelTest;
import org.VoxelTest.renderengine.world.audio.AudioMaster;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.*;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.*;
import org.lwjgl.opengl.DisplayMode;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class DisplayManager {
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	private static final int FPS_CAP = 60;
	
	private static long lastFrameTime;
	private static float delta;
	static long startTime = System.nanoTime();
    static int frames = 0;
	
	public static void createDisplay() throws MalformedURLException, URISyntaxException {
		ContextAttribs attribs = new ContextAttribs(3,2).withForwardCompatible(true).withProfileCore(true);
		
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create(new PixelFormat(), attribs);
			AudioMaster.init();
			Display.setTitle("[DEBUG] VoxelENGINE - OIKMO");
			GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
			VoxelTest.isOpenGL = GL11.glGetString(GL11.GL_VERSION) != null;
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
		AL.destroy();
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
