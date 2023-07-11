package org.VoxelTest.renderengine.world.chunk;

import org.VoxelTest.renderengine.Loader;
import org.VoxelTest.renderengine.textures.ModelTexture;
import org.VoxelTest.renderengine.world.World;
import org.VoxelTest.renderengine.world.cube.blocks.Block;
import org.lwjgl.util.vector.Vector3f;

public class ChunkManager {
	static Loader loader = Loader.getLoader();
	static ModelTexture texture = new ModelTexture(loader.loadSquareTexture("defaultPack"));
	public static Block getBlock(Vector3f globalOrigin, ChunkMesh chunkMesh) {
		Chunk chunk = chunkMesh.chunk;
		int localX = (int) (globalOrigin.x - chunk.origin.x);
		int localY = (int) globalOrigin.y;
		int localZ = (int) (globalOrigin.z - chunk.origin.z);
		
		Block block = null;
		
		if (isWithinChunk(localX, localY, localZ)) {
			block = chunk.blocks[localX][localY][localZ];
			//chunkMesh.chunk = chunk;
		}
		return block;
	}
	
	public static void literallyJustForTesting(Vector3f globalOrigin, Chunk chunk) {
		int localX = (int) (globalOrigin.x + chunk.origin.x);
		int localY = (int) globalOrigin.y;
		int localZ = (int) (globalOrigin.z + chunk.origin.z);
		System.out.println("(" + localX + " " + localY + " " + localZ + ") (" + (int)globalOrigin.x + " " + (int)globalOrigin.y + " " + (int)globalOrigin.z + ")");
	}
	
	public static void setBlock(Vector3f globalOrigin, Block block, Chunk chunk) {
		int localX = (int) (globalOrigin.x - chunk.origin.x);
		int localY = (int) globalOrigin.y;
		int localZ = (int) (globalOrigin.z - chunk.origin.z);
		
		if (isWithinChunk(localX, localY, localZ)) {
			
			if (block != null) {
	            if (chunk.blocks[localX][localY][localZ] == null) {
	                chunk.blocks[localX][localY][localZ] = block;
	            } else {
	                return;
	            }
	        } else {
	            chunk.blocks[localX][localY][localZ] = null;
	        }
			System.out.println(localX + " " + localY + " " + localZ);
			chunk.setDirty();

		} else {
			//System.out.println("am i out? (" + localX + " " + localY + " " + localZ + ")");
		}
	}

	public static boolean isWithinChunk(int localX, int localY, int localZ) {
		return localX >= 0 && localX < Chunk.CHUNK_SIZE &&
				localY >= 0 && localY < World.WORLD_HEIGHT &&
				localZ >= 0 && localZ < Chunk.CHUNK_SIZE;
	}
}