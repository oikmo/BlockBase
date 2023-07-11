package org.VoxelTest.renderengine.world.cube.blocks;

public class BlockDirt extends Block {

	public BlockDirt(Type type) {
		super(type);
	}
	
	public boolean isSolid() {
		return true;
	}
	
	public float getStrength() {
		return 0.2f;
	}
	
	public boolean blocksLight() {
		return false;
	}

}
