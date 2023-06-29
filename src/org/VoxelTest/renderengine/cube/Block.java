package org.VoxelTest.renderengine.cube;

import org.VoxelTest.renderengine.Loader;

public class Block {
	public int x, y, z;
	
	public static Loader loader = new Loader();
	
	public static enum Type {
		DIRT, GRASS;
	};
	
	public Type type;
	
	public Block(int x, int y, int z, Type type) {
		this.x = x;
		this.y = y;
		this.z = z;
		
		this.type = type;
	}
}
