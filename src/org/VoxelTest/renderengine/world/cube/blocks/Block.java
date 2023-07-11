package org.VoxelTest.renderengine.world.cube.blocks;

import org.VoxelTest.phys.AABB;

public abstract class Block {
	
	public final Block[] blocks = new Block[256];
	public static final Block grass = new BlockGrass(Type.GRASS);
	public static final Block dirt = new BlockDirt(Type.DIRT);
	public static final Block stone = new BlockStone(Type.STONE);
	public static final Block treebark = new BlockTreeBark(Type.TREEBARK);
	public static final Block treeleaf = new BlockTreeLeaf(Type.TREELEAF);
	public static final Block cobble = new BlockCobble(Type.COBBLE);
	public static final Block bedrock = new BlockBedrock(Type.BEDROCK);
	
	public static enum Type {
		GRASS, 
		DIRT, 
		STONE,
		TREEBARK,
		TREELEAF,
		COBBLE,
		BEDROCK
	};
	
	public Type type;
	
	public Block(Type type) {
		blocks[type.ordinal()] = this;
		this.type = type;
	}
	
	public void setType(Block.Type type) {
		this.type = type;
	}

	public int getType() {
		return type.ordinal();
	}
	
	public abstract float getStrength();
	
	public abstract boolean blocksLight();

	public abstract boolean isSolid();
	
	public AABB getAABB(int x, int y, int z) {
		return new AABB((float)x, (float)y, (float)z, (float)(x + 1), (float)(y + 1), (float)(z + 1));
	}
	
}