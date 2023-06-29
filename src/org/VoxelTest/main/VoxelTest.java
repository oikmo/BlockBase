package org.VoxelTest.main;

import java.util.*;

import org.VoxelTest.entities.*;
import org.VoxelTest.renderengine.*;
import org.VoxelTest.renderengine.chunk.*;
import org.VoxelTest.renderengine.cube.Block;
import org.VoxelTest.renderengine.models.*;
import org.VoxelTest.renderengine.renderers.MasterRenderer;
import org.VoxelTest.renderengine.textures.ModelTexture;
import org.VoxelTest.toolbox.PerlinNoiseGenerator;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

public class VoxelTest {
	
	public static Loader loader = new Loader();
	public static MasterRenderer renderer = null;
	
	static Vector3f camPos = new Vector3f(0,0,0);
	static List<ChunkMesh> chunks = Collections.synchronizedList(new ArrayList<ChunkMesh>());
	static List<Vector3f> usedPos = new ArrayList<>();
	
	static List<Entity> entities = new ArrayList<>();
	
	public static int CHUNK_SIZE = 5;
	
	static final int WORLD_SIZE = CHUNK_SIZE * 16;
	
	static boolean closeDisplay = false;
	
	public static void main(String[] args) throws LWJGLException {
		DisplayManager.createDisplay();
		
		renderer = new MasterRenderer();
		
		Camera camera = new Camera(new Vector3f(0,0,0), new Vector3f(0,0,0), 1);
		
		ModelTexture texture = new ModelTexture(loader.loadSquareTexture("grassTex"));
		
		PerlinNoiseGenerator generator = new PerlinNoiseGenerator();
		
		//chunk render top half
		new Thread(new Runnable() { 
			public void run() {
				while(!closeDisplay) {
					for (int x = (int) (camPos.x - WORLD_SIZE) / 32; x < (camPos.x + WORLD_SIZE) / 32; x++) {
						for (int z = (int) (camPos.z - WORLD_SIZE) / 32; z < (camPos.z + WORLD_SIZE) / 32; z++) {
							int chunkX = x * 32;
							int chunkZ = z * 32;
							
							if(!usedPos.contains(new Vector3f(chunkX, 0, chunkZ))) {
								List<Block> blocks = new ArrayList<>();
								
								for (int i = 0; i < 32; i++) {
									for (int j = 0; j < 32; j++) {
										
										blocks.add(new Block(i, (int) generator.generateHeight(i + chunkX, j + chunkZ), j, Block.Type.GRASS));
										
									}
								}
								
								Chunk chunk = new Chunk(blocks, new Vector3f(chunkX, 0, chunkZ));
								ChunkMesh mesh = new ChunkMesh(chunk);
								
								chunks.add(mesh);
								usedPos.add(new Vector3f(chunkX, 0, chunkZ));
							}
						}
					}
				}
			}
		}).start();
		
		//RawModel model = loader.loadToVAO(AtlasCubeModel.vertices, AtlasCubeModel.indices, AtlasCubeModel.uv);
		
		
		/*List<Block> blocks = new ArrayList<Block>();
		
		for(int x = 0; x < 10; x++) {
			for(int y = 0; y < 10; y++) {
				for(int z = 0; z < 10; z++) {
					blocks.add(new Block(x, y, z, Block.Type.DIRT));
				}
			}
		}
		
		Chunk chunk = new Chunk(blocks, new Vector3f(0,0,0));
		ChunkMesh mesh = new ChunkMesh(chunk);*/
		
		//MAINGAMELOOP
		int index = 0;
		while(!Display.isCloseRequested()) {
			
			camera.move();
			camPos = camera.getPosition();
			
			if(index < chunks.size()) {
				RawModel model123 = loader.loadToVAO(chunks.get(index).positions, chunks.get(index).uvs);
				TexturedModel texModel123 = new TexturedModel(model123, texture);
				Entity entity = new Entity(texModel123, chunks.get(index).chunk.origin);
				entities.add(entity);
				
				chunks.get(index).positions = null;
				chunks.get(index).uvs = null;
				chunks.get(index).normals = null;
				
				index++;
			}
			
			for(int i = 0; i < entities.size(); i++) {
				Vector3f origin = entities.get(i).getPosition();
				
				int distX = (int) (camPos.x - origin.x);
				int distZ = (int) (camPos.z - origin.z);
				
				if(distX < 0) {
					distX = -distX;
				}
				
				if(distZ < 0) {
					distZ = -distZ;
				}
				
				
				if((distX <= WORLD_SIZE) && (distZ <= WORLD_SIZE)) {
					renderer.addEntity(entities.get(i));
				}
			}
			
			renderer.render(camera);
			
			DisplayManager.updateDisplay();
		}
		
		closeDisplay = true;
		DisplayManager.closeDisplay();
	}
}
 