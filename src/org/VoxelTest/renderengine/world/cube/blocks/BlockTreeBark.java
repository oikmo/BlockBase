package org.VoxelTest.renderengine.world.cube.blocks;

public class BlockTreeBark extends Block {
	
	public BlockTreeBark(Type type) {
		super(type);
	}

	public boolean isSolid() {
		return true;
	}
	
	public float getStrength() {
		return 0.5f;
	}
	
	public boolean blocksLight() {
		return false;
	}
}
