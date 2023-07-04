package org.VoxelTest.renderengine.world;

import org.VoxelTest.entities.*;
import org.VoxelTest.main.*;
import org.VoxelTest.renderengine.Loader;
import org.VoxelTest.renderengine.models.*;
import org.VoxelTest.renderengine.renderers.MasterRenderer;
import org.VoxelTest.renderengine.textures.ModelTexture;
import org.VoxelTest.renderengine.world.chunk.*;
import org.VoxelTest.toolbox.PerlinNoiseGenerator;
import org.lwjgl.util.vector.Vector3f;

public class World {
	
	public static int WORLD_HEIGHT = 128;
	
	Loader loader = Loader.getLoader();
	ModelTexture texture = new ModelTexture(loader.loadSquareTexture("defaultPack"));
	
	public static int thatY = 0;
	public static boolean thatYlock = true;
	
	
	public World(String seed) {
		
		
		
		PerlinNoiseGenerator generator = new PerlinNoiseGenerator(seed);
		//chunk render radius
		new Thread(new Runnable() { 
			public void run() {
				while (!VoxelTest.closeDisplay) {
		            for (int x = (int) (VoxelTest.camPos.x - VoxelTest.WORLD_SIZE) / 16; x < (VoxelTest.camPos.x + VoxelTest.WORLD_SIZE) / 16; x++) {
		                for (int z = (int) (VoxelTest.camPos.z - VoxelTest.WORLD_SIZE) / 16; z < (VoxelTest.camPos.z + VoxelTest.WORLD_SIZE) / 16; z++) {
		                    int chunkX = x * 16;
		                    int chunkZ = z * 16;

		                    Vector3f chunkPos = new Vector3f(chunkX, 0, chunkZ);
		                    if (!VoxelTest.usedPos.contains(chunkPos)) {
		                        Chunk chunk = new Chunk(chunkPos);
		                        ChunkMesh mesh = new ChunkMesh(chunk);
		                        VoxelTest.chunks.add(mesh);
		                        VoxelTest.usedPos.add(chunkPos);
		                    }
		                }
		            }
				}
			}
		}).start();
		
	}
	
	/*
	 * 
	 */
			
			
	boolean removeChunks = false;
	int index = 0;
	public void update(MasterRenderer renderer, Camera camera) {	
		if(index < VoxelTest.chunks.size()) {
			
			
			RawModel model123 = loader.loadToVAO(VoxelTest.chunks.get(index).positions, VoxelTest.chunks.get(index).uvs);
			TexturedModel texModel123 = new TexturedModel(model123, texture);
			Entity entity = new Entity(texModel123, VoxelTest.chunks.get(index).chunk.origin);
			
			VoxelTest.entities.add(entity);
			
			VoxelTest.chunks.get(index).positions = null;
			VoxelTest.chunks.get(index).uvs = null;
			VoxelTest.chunks.get(index).normals = null;
		
			index++;
		}
		
		for(int i = 0; i < VoxelTest.entities.size(); i++) {
			Vector3f origin = VoxelTest.entities.get(i).getPosition();
			
			int distX = (int) (VoxelTest.camPos.x - origin.x);
			int distZ = (int) (VoxelTest.camPos.z - origin.z);
			
			if(distX < 0) {
				distX = -distX;
			}
			
			if(distZ < 0) {
				distZ = -distZ;
			}
			
			
			
			if((distX <= VoxelTest.WORLD_SIZE) && (distZ <= VoxelTest.WORLD_SIZE)) {
				renderer.addEntity(VoxelTest.entities.get(i));
			}
			
			
		}
	}
	
}
