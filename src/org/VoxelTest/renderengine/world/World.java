package org.VoxelTest.renderengine.world;

import java.util.ArrayList;
import java.util.List;

import org.VoxelTest.entities.Camera;
import org.VoxelTest.entities.Entity;
import org.VoxelTest.main.VoxelTest;
import org.VoxelTest.renderengine.Frustum;
import org.VoxelTest.renderengine.Loader;
import org.VoxelTest.renderengine.models.RawModel;
import org.VoxelTest.renderengine.models.TexturedModel;
import org.VoxelTest.renderengine.renderers.MasterRenderer;
import org.VoxelTest.renderengine.textures.ModelTexture;
import org.VoxelTest.renderengine.world.chunk.Chunk;
import org.VoxelTest.renderengine.world.chunk.ChunkMesh;
import org.VoxelTest.renderengine.world.cube.Block;
import org.VoxelTest.toolbox.Maths;
import org.VoxelTest.toolbox.PerlinNoiseGenerator;
import org.VoxelTest.toolbox.datastructures.FastArrayList;
import org.VoxelTest.toolbox.datastructures.ViewFrustum;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class World {
	
	public static int WORLD_HEIGHT = 128;
	
	Loader loader = new Loader();
	ModelTexture texture = new ModelTexture(loader.loadSquareTexture("defaultPack"));
	
	public static int thatY = 0;
	public static boolean thatYlock = true;
	
	
	public World(String seed) {
		PerlinNoiseGenerator generator = new PerlinNoiseGenerator(seed);
		
		
		
		//chunk render radius
		new Thread(new Runnable() { 
			public void run() {
				while(!VoxelTest.closeDisplay) {
					for (int x = (int) (VoxelTest.camPos.x - VoxelTest.WORLD_SIZE) / 16; x < (VoxelTest.camPos.x + VoxelTest.WORLD_SIZE) / 16; x++) {
						for (int z = (int) (VoxelTest.camPos.z - VoxelTest.WORLD_SIZE) / 16; z < (VoxelTest.camPos.z + VoxelTest.WORLD_SIZE) / 16; z++) {
							int chunkX = x * 16;
							int chunkZ = z * 16;
							
							if(!VoxelTest.usedPos.contains(new Vector3f(chunkX, 0, chunkZ))) {
								
								FastArrayList<Block> blocks = new FastArrayList<>();
								
								for (int i = 0; i < 16; i++) {
									for (int j = 0; j < 16; j++) {
										int y = (int) generator.generateHeight(i + chunkX, j + chunkZ);
										
										Block block = new Block(i, y, j, Block.Type.GRASS);
										//System.out.println(grass.x + " " + block.x);
										
										blocks.add(block);
										
										//loop until y = 0
										for(int yy = 1; yy < 1; yy++) {
											blocks.add(new Block(i, y - yy, j, Block.Type.DIRT));
										}
										
									}
								}

								Chunk chunk = new Chunk(blocks, new Vector3f(chunkX, 0, chunkZ));
								//Chunk chunk1 = new Chunk(blocks, new Vector3f(chunkX, 0, chunkZ));
								ChunkMesh mesh = new ChunkMesh(chunk);
								VoxelTest.chunks.add(mesh);
								VoxelTest.usedPos.add(new Vector3f(chunkX, 0, chunkZ));
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
			
			renderer.addEntity(VoxelTest.entities.get(i));
		}
	}
	
}
