package org.VoxelTest.renderengine.world.cube;

import org.lwjgl.util.vector.Vector3f;

public class Block {
	public Vector3f position;

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
	
	public Block(Vector3f position, Type type) {
		this.position = position;
		
		this.type = type;
	}
	
	public void setType(Block.Type type) {
		this.type = type;
	}

	public int getType() {
		return type.ordinal();
	}
	
	public boolean collidesWith(float pointX, float pointY, float pointZ) {
	    // Compare the position of the point with the boundaries of the block
	    float blockMinX = position.x;
	    float blockMaxX = position.x + 1.0f;
	    float blockMinY = position.y;
	    float blockMaxY = position.y + 1.0f;
	    float blockMinZ = position.z;
	    float blockMaxZ = position.z + 1.0f;

	    // Check if the point is within the block's boundaries
	    return pointX >= blockMinX && pointX <= blockMaxX &&
	           pointY >= blockMinY && pointY <= blockMaxY &&
	           pointZ >= blockMinZ && pointZ <= blockMaxZ;
	}
}