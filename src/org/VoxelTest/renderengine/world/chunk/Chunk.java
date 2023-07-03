package org.VoxelTest.renderengine.world.chunk;

import java.util.List;

import org.VoxelTest.renderengine.world.cube.Block;
import org.VoxelTest.toolbox.GlueList;
import org.lwjgl.util.vector.Vector3f;

public class Chunk {
	
	private static final int CHUNK_SIZE = 16;

    private Block[][][] blocks;
	public Vector3f origin;
	
	public Chunk(List<Block> blocks, Vector3f origin) {
		this.blocks = blocks;
		this.origin = origin;
	}	
	
	private void generateChunk() {
        // Generate blocks for the chunk, you can use procedural generation or load from file.
        for (int x = 0; x < CHUNK_SIZE; x++) {
            for (int y = 0; y < CHUNK_SIZE; y++) {
                for (int z = 0; z < CHUNK_SIZE; z++) {
                    // Example: Fill the bottom layer with grass, rest with stone
                    if (y == 0) {
                        blocks[x][y][z] = new Block(x, y, z, Block.Type.GRASS);
                    } else {
                        blocks[x][y][z] = new Block(x, y, z, Block.Type.STONE);
                    }
                }
            }
        }
    }
	
	public List<Block> getBlocks() {
		return blocks;
	}
}