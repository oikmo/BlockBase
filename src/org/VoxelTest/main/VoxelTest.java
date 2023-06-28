package org.VoxelTest.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.VoxelTest.entities.Camera;
import org.VoxelTest.entities.Entity;
import org.VoxelTest.renderengine.*;
import org.VoxelTest.renderengine.models.*;
import org.VoxelTest.renderengine.renderers.MasterRenderer;
import org.VoxelTest.renderengine.textures.ModelTexture;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

public class VoxelTest {
	
	public static Loader loader = new Loader();
	public static MasterRenderer renderer = null;
	
	static Vector3f camPos = new Vector3f(0,0,0);
	static List<Chunk> chunks = Collections.synchronizedList(new ArrayList<Chunk>());
	static List<Vector3f> usedPos = new ArrayList<>();
	
	public static int CHUNK_SIZE = 5;
	
	static final int WORLD_SIZE = CHUNK_SIZE * 16;
	
	static boolean closeDisplay = false;
	
	public static void main(String[] args) throws LWJGLException {
		DisplayManager.createDisplay();
		
		MasterRenderer render1 = new MasterRenderer();
		renderer = render1;
		
		
		RawModel model = loader.loadToVAO(AtlasCubeModel.vertices, AtlasCubeModel.indices, AtlasCubeModel.uv);
		ModelTexture texture = new ModelTexture(loader.loadSquareTexture("grassTex"));
		TexturedModel texModel = new TexturedModel(model, texture);
		Camera camera = new Camera(new Vector3f(0,0,0), new Vector3f(0,0,0), 1);
		
		//chunk render top half
		new Thread(new Runnable() { 
			public void run() {
				while(!closeDisplay) {
					for (int x = (int) (camPos.x - WORLD_SIZE) / 16; x < (camPos.x + WORLD_SIZE) / 16; x++) {
						for (int z = (int) (camPos.z - WORLD_SIZE) / 16; z < (camPos.z + WORLD_SIZE) / 16; z++) {
							int chunkX = x * 16;
							int chunkZ = z * 16;
							
							if(!usedPos.contains(new Vector3f(chunkX, 0, chunkZ))) {
								List<Entity> blocks = new ArrayList<>();
								
								for (int i = 0; i < 16; i++) {
									for (int j = 0; j < 16; j++) {
										
										blocks.add(new Entity(texModel, new Vector3f(i + chunkX , 0, j + chunkZ), new Vector3f(0,0,0), 1));
										
									}
								}
								chunks.add(new Chunk(blocks, new Vector3f(chunkX, 0, chunkX)));
								usedPos.add(new Vector3f(chunkX, 0, chunkZ));
							}
						}
					}
				}
			}
		}).start();

		
		while(!Display.isCloseRequested()) {
			
			update(camera);
			camPos = camera.getPosition();
			
			for(int i = 0; i < chunks.size(); i++) {
				Vector3f origin = chunks.get(i).getOrigin();
				
				int distX = (int) (camPos.x - origin.x);
				int distZ = (int) (camPos.z - origin.z);
				
				distX = Math.abs(distX);
				
				distZ = Math.abs(distZ);
				
				if((distX <= WORLD_SIZE) && (distZ <= WORLD_SIZE)) {
					for(int j = 0; j < chunks.get(i).getBlocks().size(); j++) {
						renderer.addEntity(chunks.get(i).getBlocks().get(j));
					}
				}
			}
			
			renderer.render(camera);
			
			DisplayManager.updateDisplay();
		}
		
		closeDisplay = true;
		DisplayManager.closeDisplay();
	}
	
	public static void update(Camera camera) {
		camera.move();
		
		
	}
}
 