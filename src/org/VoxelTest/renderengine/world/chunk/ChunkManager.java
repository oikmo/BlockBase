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
		int localX = (int) (globalOrigin.x - chunk.origin.x);
		int localY = (int) globalOrigin.y;
		int localZ = (int) (globalOrigin.z - chunk.origin.z);
		System.out.println("(" + localX + " " + localY + " " + localZ + ") (" + (int)globalOrigin.x + " " + (int)globalOrigin.y + " " + (int)globalOrigin.z + ")");
	}
	
	public static void setBlock(Vector3f globalOrigin, Block block, ChunkMesh chunkMesh) {
		int localX = (int) Math.floorMod((int) (globalOrigin.x + chunkMesh.chunk.origin.x), Chunk.CHUNK_SIZE);
		int localY = (int) globalOrigin.y;
		int localZ = (int) Math.floorMod((int) (globalOrigin.z + chunkMesh.chunk.origin.z), Chunk.CHUNK_SIZE);
		
		if (isWithinChunk(localX, localY, localZ)) {
			if(chunkMesh.chunk.blocks[localX][localY][localZ] == null) {
				chunkMesh.chunk.blocks[localX][localY][localZ] = block;
				System.out.println("am i created? (" + localX + " " + localY + " " + localZ + ") (" + (int)chunkMesh.chunk.origin.x + " " + (int) chunkMesh.chunk.origin.y +  " "+(int) chunkMesh.chunk.origin.z +  ")");
				chunkMesh.dirty = true;
			} else {
				System.out.println("am i not null? (" + localX + " " + localY + " " + localZ + ") (" + (int)globalOrigin.x + " " + (int) globalOrigin.y +  " "+(int) globalOrigin.z +  ") " + chunkMesh.chunk.blocks[localX][localY][localZ].type.name());
			}
		} else {
			System.out.println("am i out? (" + localX + " " + localY + " " + localZ + ")");
		}
	}
	
	// Call this method to update the mesh only when necessary
	public static void updateMesh(ChunkMesh chunkMesh) {
	    if (chunkMesh.dirty) {
	        chunkMesh.rebuildMesh();
	        chunkMesh.dirty = false; // Reset the dirty flag
	    }
	}


	private static boolean isWithinChunk(int localX, int localY, int localZ) {
		return localX >= 0 && localX < 16 &&
				localY >= 0 && localY < World.WORLD_HEIGHT &&
				localZ >= 0 && localZ < 16;
	}
	
	public static Vector3f getChunkBlockPos(Vector3f playerPos, ChunkMesh chunkMesh) {
        int x = (int) (playerPos.x - chunkMesh.chunk.origin.x);
        int y = (int) playerPos.y;
        int z = (int) (playerPos.z - chunkMesh.chunk.origin.z);
        return new Vector3f(x, y, z);
    }
}
