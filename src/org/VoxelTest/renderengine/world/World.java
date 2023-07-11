package org.VoxelTest.renderengine.world;

import java.util.*;

import org.VoxelTest.entities.*;
import org.VoxelTest.main.*;
import org.VoxelTest.renderengine.Loader;
import org.VoxelTest.renderengine.models.*;
import org.VoxelTest.renderengine.renderers.MasterRenderer;
import org.VoxelTest.renderengine.textures.ModelTexture;
import org.VoxelTest.renderengine.world.chunk.*;
import org.VoxelTest.toolbox.datastructures.FastArrayList;
import org.lwjgl.util.vector.*;

public class World {
	
	public static int WORLD_HEIGHT = 128;
	
	Loader loader = Loader.getLoader();
	ModelTexture texture = new ModelTexture(loader.loadSquareTexture("defaultPack"));
	
	public List<ChunkMesh> chunks = Collections.synchronizedList(new ArrayList<ChunkMesh>()) ;
	public FastArrayList<Vector3f> usedPos = new FastArrayList<>();
	public FastArrayList<Entity> entities = new FastArrayList<>();
	//public Map<Vector2f, ChunkMesh> coordChunkMesh = new HashMap<Vector2f, ChunkMesh>();
	
	boolean chunkLoaded = false;
	
	public World(String seed) {
		//single chunks
		
		//loop1();
		
		new Thread(new Runnable() { 
			public void run() {
				while (!VoxelTest.closeDisplay) {
					if(chunkLoaded) { return; }
					Vector3f newPos = new Vector3f(-16,0,-16);
					// Create a new chunk and add it to the world
					Chunk chunk = new Chunk(newPos, true);
					//System.gc();
					ChunkMesh mesh = new ChunkMesh(chunk);
					chunks.add(mesh);
					
					Vector3f newPos2 = new Vector3f(16,0,16);
					// Create a new chunk and add it to the world
					Chunk chunk2 = new Chunk(newPos2);
					//System.gc();
					ChunkMesh mesh2 = new ChunkMesh(chunk2);
					chunks.add(mesh2);
					chunkLoaded = true;
					
				}
			}
		});
		
		
		//chunk render radius
		new Thread(new Runnable() { 
			public void run() {//System.out.println("thread " + this.getClass());
				while (!VoxelTest.closeDisplay) {
					//check if chunks doesnt exisst and creates chunks
		            int playerChunkX = (int) (VoxelTest.camPos.x / Chunk.CHUNK_SIZE);
		            int playerChunkZ = (int) (VoxelTest.camPos.z / Chunk.CHUNK_SIZE);
		            int chunkRadius = VoxelTest.CHUNK_SIZE;
		            
		            for (int x = playerChunkX - chunkRadius; x <= playerChunkX + chunkRadius; x++) {
		                for (int z = playerChunkZ - chunkRadius; z <= playerChunkZ + chunkRadius; z++) {
		                    // Calculate the chunk's origin based on its position
		                    float chunkOriginX = x * Chunk.CHUNK_SIZE ;
		                    float chunkOriginZ = z * Chunk.CHUNK_SIZE ;
		                    Vector3f chunkOrigin = new Vector3f(chunkOriginX, 0, chunkOriginZ);

		                    // Check if the chunk already exists
		                    if (!usedPos.contains(chunkOrigin)) {
		                        // Create a new chunk and add it to the world
		                        Chunk chunk = new Chunk(chunkOrigin);
		                        ChunkMesh mesh = new ChunkMesh(chunk);
		                        chunks.add(mesh);
		                        usedPos.add(chunkOrigin);
		                    }
		                }
		            }
				}
			}
		}).start();
	}
	
	public void updateMeshIfNeeded() {
		synchronized(chunks) {
			for (ChunkMesh chunkMesh : chunks) {
		        Chunk chunk = chunkMesh.chunk;
		        if (chunk.isDirty()) {
		            chunkMesh.rebuildMesh();
		            chunk.setDirty(false);
		        }
		    }
		}
	    
	}
	
	boolean chunksModified = true;
	public void update(MasterRenderer renderer, Camera camera) {	
		
		updateMeshIfNeeded();

		
		
		for (int index = 0; index < chunks.size(); index++) {
		    if (chunks.get(index).positions == null || chunks.get(index).uvs == null || chunks.get(index).normals == null) {
		        continue; // Skip the chunk if positions, UVs, or normals are null
		    }
		    
		    //System.out.println(chunks.get(index).positions + " " + chunks.get(index).uvs);
		    
		    RawModel model123 = loader.loadToVAO(chunks.get(index).positions, chunks.get(index).uvs);
		    TexturedModel texModel123 = new TexturedModel(model123, texture);
		    Entity entity = new Entity(texModel123, chunks.get(index).chunk.origin);
		    
		    if (entities.contains(entity)) {
		        return;
		    }
		    
		    entities.add(entity);
		    
		    chunks.get(index).positions = null;
		    chunks.get(index).uvs = null;
		    chunks.get(index).normals = null;
		    
		    // Check if the chunk mesh was modified
		   
		}

		
		// render chunks if player in radius
		for(int i = 0; i < entities.size(); i++) {
			
			
			Vector3f origin = entities.get(i).getPosition();
			
			int distX = (int) Math.abs(VoxelTest.camPos.x - origin.x);
			int distZ = (int) Math.abs(VoxelTest.camPos.z - origin.z);			
			
			
			if((distX <= VoxelTest.WORLD_SIZE) && (distZ <= VoxelTest.WORLD_SIZE)) {
				renderer.addEntity(entities.get(i));
			}
		}
	}
	
}
