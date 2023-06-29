package org.VoxelTest.renderengine.chunk;

import java.util.List;

import org.VoxelTest.renderengine.cube.Block;
import org.lwjgl.util.vector.Vector3f;

public class Chunk {
	
	public List<Block> blocks;
	public Vector3f origin;
	
	public Chunk(List<Block> blocks, Vector3f origin) {
		this.blocks = blocks;
		this.origin = origin;
	}	
}