package org.VoxelTest.renderengine.world.cube.blocks;

public class BlockStone extends Block {
	
	public BlockStone(Type type) {
		super(type);
	}

	public boolean isSolid() {
		return true;
	}
	
	public float getStrength() {
		return 0.6f;
	}
	
	public boolean blocksLight() {
		return false;
	}
	
}
