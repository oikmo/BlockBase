package org.VoxelTest.renderengine.world.chunk;

import java.util.List;

import org.VoxelTest.renderengine.world.World;
import org.VoxelTest.renderengine.world.cube.Block;
import org.VoxelTest.toolbox.GlueList;
import org.VoxelTest.toolbox.PerlinNoiseGenerator;
import org.lwjgl.util.vector.Vector3f;

public class Chunk {

	public static final int CHUNK_SIZE = 16;

	public Block[][][] blocks;
	public Vector3f origin;
	PerlinNoiseGenerator noiseGen;
	public Chunk(Vector3f origin, String seed) {
		this.origin = origin;
		blocks = new Block[CHUNK_SIZE][World.WORLD_HEIGHT][CHUNK_SIZE];
		noiseGen = new PerlinNoiseGenerator(seed);
		generateChunk();
	}

	public Chunk(Vector3f origin) {
		this.origin = origin;
		blocks = new Block[CHUNK_SIZE][World.WORLD_HEIGHT][CHUNK_SIZE];
		noiseGen = new PerlinNoiseGenerator();
		generateChunk();
	}
	
	private void generateChunk() {
	    // Generate the blocks using the noise generator
	    for (int x = 0; x < CHUNK_SIZE; x++) {
	        for (int z = 0; z < CHUNK_SIZE; z++) {
	            int actualX = (int) (origin.x + x);
	            int actualZ = (int) (origin.z + z);

	            int height = (int) noiseGen.generateHeight(actualX, actualZ);

	            for (int y = 0; y < World.WORLD_HEIGHT; y++) {
	                if (y < height) {
	                    // Create a solid block at this position
	                    blocks[x][y][z] = new Block(new Vector3f(x, y, z), calculateBlockType(y));
	                } else {
	                    // Air block above the height limit
	                    blocks[x][y][z] = null;
	                }
	            }
	        }
	    }

	    // Set the top layer blocks to grass
	    Block[] topLayer = getTopLayer();
	    for (int i = 0; i < topLayer.length; i++) {
	        Block block = topLayer[i];
	        if (block != null) {
	            block.setType(Block.Type.GRASS); // Set the block type to grass
	        }
	    }
	}
	
	private Block[] getTopLayer() {
	    Block[] topLayer = new Block[CHUNK_SIZE * CHUNK_SIZE];

	    for (int x = 0; x < CHUNK_SIZE; x++) {
	        for (int z = 0; z < CHUNK_SIZE; z++) {
	            for (int y = World.WORLD_HEIGHT - 1; y >= 0; y--) {
	                if (blocks[x][y][z] != null) {
	                    topLayer[x * CHUNK_SIZE + z] = blocks[x][y][z];
	                    break;
	                }
	            }
	        }
	    }

	    return topLayer;
	}
	
	public float distance(Vector3f other) {
	        float dx = other.x - this.origin.x;
	        float dy = other.y - this.origin.y;
	        float dz = other.z - this.origin.z;
	        return (float) Math.sqrt(dx * dx + dy * dy + dz * dz);
	}
	
	private Block.Type calculateBlockType(int height) {
	    if (height >= 60) {
	        return Block.Type.DIRT;
	    } else if (height >= 40) {
	        return Block.Type.DIRT;
	    } else {
	        return Block.Type.STONE;
	    }
	}

	public Block getBlock(Vector3f globalOrigin) {
		int localX = (int) (globalOrigin.x - origin.x);
		int localY = (int) (globalOrigin.y - origin.y);
		int localZ = (int) (globalOrigin.z - origin.z);

		if (isWithinChunk(localX, localY, localZ)) {
			return blocks[localX][localY][localZ];
		} else {
			// Block is outside the chunk, handle accordingly (return null, generate dynamically, etc.)
			return null;
		}
	}

	public void setBlock(int globalX, int globalY, int globalZ, Block block) {
		int localX = globalX - (int) origin.x;
		int localY = globalY - (int) origin.y;
		int localZ = globalZ - (int) origin.z;

		if (isWithinChunk(localX, localY, localZ)) {
			blocks[localX][localY][localZ] = block;
		} else {
			// Block is outside the chunk, handle accordingly (generate neighboring chunks, etc.)
		}
	}

	private boolean isWithinChunk(int localX, int localY, int localZ) {
		return localX >= 0 && localX < CHUNK_SIZE &&
				localY >= 0 && localY < CHUNK_SIZE &&
				localZ >= 0 && localZ < CHUNK_SIZE;
	}
}