package org.VoxelTest.renderengine.world.cube;

public class Block {
	public int x, y, z;

	public static enum Type {
		GRASS, DIRT, STONE;
	};
	
	public Type type;
	
	public Block(int x, int y, int z, Type type) {
		this.x = x;
		this.y = y;
		this.z = z;
		
		this.type = type;
	}

	public int getType() {
		return type.ordinal();
	}
}