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
	
	public boolean intersectsRay(Vector3f rayOrigin, Vector3f rayDirection) {
		float blockMinX = position.x;
	    float blockMaxX = position.x + 1.0f;
	    float blockMinY = position.y;
	    float blockMaxY = position.y + 1.0f;
	    float blockMinZ = position.z;
	    float blockMaxZ = position.z + 1.0f;
		
	    // Calculate the ray intersection with the block's AABB
	    float tMin = (blockMinX - rayOrigin.x) / rayDirection.x;
	    float tMax = (blockMaxX - rayOrigin.x) / rayDirection.x;

	    float tYMin = (blockMinY - rayOrigin.y) / rayDirection.y;
	    float tYMax = (blockMaxY - rayOrigin.y) / rayDirection.y;

	    float tZMin = (blockMinZ - rayOrigin.z) / rayDirection.z;
	    float tZMax = (blockMinZ - rayOrigin.z) / rayDirection.z;

	    // Calculate the intersection distances
	    float tEnter = Math.max(Math.max(Math.min(tMin, tMax), Math.min(tYMin, tYMax)), Math.min(tZMin, tZMax));
	    float tExit = Math.min(Math.min(Math.max(tMin, tMax), Math.max(tYMin, tYMax)), Math.max(tZMin, tZMax));

	    // Check if there is a valid intersection
	    return tEnter <= tExit && tExit >= 0;
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