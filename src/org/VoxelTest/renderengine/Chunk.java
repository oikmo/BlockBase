package org.VoxelTest.renderengine;

import java.util.List;

import org.VoxelTest.entities.Entity;
import org.lwjgl.util.vector.Vector3f;

public class Chunk {
	
	private List<Entity> blocks;
	private Vector3f origin;
	
	public Chunk(List<Entity> blocks, Vector3f origin) {
		this.setBlocks(blocks);
		this.origin = origin;
	}
	
	public List<Entity> getBlocks() {
		return blocks;
	}
	
	public void setBlocks(List<Entity> blocks) {
		this.blocks = blocks;
	}
	
	public Vector3f getOrigin() {
		return origin;
	}
	
}