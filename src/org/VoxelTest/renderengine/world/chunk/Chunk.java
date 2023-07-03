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
	
	public List<Block> getBlocks() {
		return blocks;
	}
}