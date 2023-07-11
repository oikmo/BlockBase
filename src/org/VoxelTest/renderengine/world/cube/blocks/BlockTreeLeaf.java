package org.VoxelTest.renderengine.world.cube.blocks;

public class BlockTreeLeaf extends Block {
	
	public BlockTreeLeaf(Type type) {
		super(type);
	}

	public boolean isSolid() {
		return true;
	}
	
	public float getStrength() {
		return 0.1f;
	}
	
	public boolean blocksLight() {
		return false;
	}
	
}
