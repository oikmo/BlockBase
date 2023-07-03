package org.VoxelTest.main;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import org.VoxelTest.entities.Camera;
import org.VoxelTest.entities.Entity;
import org.VoxelTest.renderengine.DisplayManager;
import org.VoxelTest.renderengine.Loader;
import org.VoxelTest.renderengine.renderers.MasterRenderer;
import org.VoxelTest.renderengine.world.World;
import org.VoxelTest.renderengine.world.chunk.ChunkMesh;
import org.VoxelTest.toolbox.GlueList;
import org.VoxelTest.toolbox.StringTranslate;
import org.VoxelTest.toolbox.datastructures.FastArrayList;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

public class VoxelTest {
	
	public static Loader loader = new Loader();
	public static MasterRenderer renderer = null;
	
	public static Vector3f camPos = new Vector3f(0,0,0);
	public static FastArrayList<ChunkMesh> chunks = new FastArrayList<ChunkMesh>();
	public static FastArrayList<Vector3f> usedPos = new FastArrayList<>();
	public static FastArrayList<Entity> entities = new FastArrayList<>();
	public static boolean paused = false;
	
	public static int CHUNK_SIZE = 3;
	public static int WORLD_SIZE = CHUNK_SIZE * 16;
	
	public static boolean closeDisplay = false;
	
	public static World theWorld = null;
	
	public static Vector3f lastHeighestPoint =  new Vector3f(0,78,0);
	
	public static void main(String[] args) throws LWJGLException, IOException, InterruptedException {
		
		//thing.add(new Vector3f(0,0,0));
		DisplayManager.createDisplay();
		
		StringTranslate translator = StringTranslate.getInstance();
		
		System.out.println(translator.translateKey("main.fuckyou"));
		
		renderer = new MasterRenderer();
		
		theWorld = new World("AY YO KYS DARLING!!");
		
		Camera camera = new Camera(lastHeighestPoint, new Vector3f(0,0,0), 1);
		
		//MAINGAMELOOP
		while(!Display.isCloseRequested()) {
			
			camera.move();
			camPos = camera.getPosition();
			
			theWorld.update(renderer, camera);
			
			renderer.render(camera);
			
			//picker.update();
			
			DisplayManager.updateDisplay();
		}
		
		closeDisplay = true;
		DisplayManager.closeDisplay();
	}
}