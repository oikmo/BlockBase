package org.VoxelTest.main;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.channels.Pipe.SourceChannel;
import java.time.Instant;
import org.VoxelTest.entities.Camera;
import org.VoxelTest.entities.Cube;
import org.VoxelTest.renderengine.DisplayManager;
import org.VoxelTest.renderengine.Loader;
import org.VoxelTest.renderengine.models.AtlasCubeModel;
import org.VoxelTest.renderengine.models.CubeModel;
import org.VoxelTest.renderengine.models.RawModel;
import org.VoxelTest.renderengine.models.TexturedModel;
import org.VoxelTest.renderengine.renderers.MasterRenderer;
import org.VoxelTest.renderengine.textures.ModelTexture;
import org.VoxelTest.renderengine.world.World;
import org.VoxelTest.renderengine.world.audio.AudioMaster;
import org.VoxelTest.toolbox.StringTranslate;
import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

public class VoxelTest {
	
	
	public static Loader loader = Loader.getLoader();
	public static MasterRenderer renderer = null;
	
	public static Vector3f camPos = new Vector3f(0,0,0);
	
	public static boolean paused = false;
	
	public static int CHUNK_SIZE = 2;
	public static int WORLD_SIZE = CHUNK_SIZE * 16;
	
	public static boolean closeDisplay = false;
	
	public static World theWorld = null;
	
	public static boolean isOpenGL = false;
	
	public static void main(String[] args) throws LWJGLException, IOException, InterruptedException, URISyntaxException {

		DisplayManager.createDisplay();
		
		//StringTranslate translator = StringTranslate.getInstance();
		
		//System.out.println(translator.translateKey("main.fuckyou"));
		
		renderer = new MasterRenderer();
		
		theWorld = new World("AY YO KYS DARLING!!");
		

		
		Camera camera = new Camera(new Vector3f(0,78,0), new Vector3f(0,0,0), 1);

		AudioMaster.playBackgroundMusic("dontRemindMe");
		//Sound.play("./Portrait-of-a-Blank-Slate.mp3");
		
		//MAINGAMELOOP
		while(!Display.isCloseRequested()) {
			
			camera.update();
			camPos = camera.getPosition();

			theWorld.update(renderer, camera);
			renderer.render(camera);
			
			DisplayManager.updateDisplay();
		}
		
		closeDisplay = true;
		DisplayManager.closeDisplay();
	}
}