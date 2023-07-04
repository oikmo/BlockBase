package org.VoxelTest.entities;

import java.util.Iterator;

import org.VoxelTest.main.VoxelTest;
import org.VoxelTest.renderengine.DisplayManager;
import org.VoxelTest.renderengine.world.World;
import org.VoxelTest.renderengine.world.chunk.Chunk;
import org.VoxelTest.renderengine.world.chunk.ChunkMesh;
import org.VoxelTest.renderengine.world.cube.Block;
import org.VoxelTest.toolbox.datastructures.ViewFrustum;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

	float speed = 0.1f;
	float turnSpeed = 0.1f;
	float moveAt;
	
	final float BASE_SPEED = 0.1f;
	final float SPRINT_SPEED = 0.2f;
	
	Vector3f position;
	Vector3f rotation;
	float scale;
	
	int collisionAreaSize = 2; // Adjust this value to fit your needs

	
	
	ViewFrustum viewFrustum;
	
	public Camera(Vector3f position, Vector3f rotation, float scale) {
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		viewFrustum = new ViewFrustum();
	}
	
	
	
	public ViewFrustum getFrustum() {
		return viewFrustum;
	}
	
	public void increasePosition(float dx, float dy, float dz) {
		this.position.x += dx;
		this.position.y += dy;
		this.position.z += dz;
	}
	
	public void increaseRotation(float dx, float dy, float dz) {
		this.rotation.x += dx;
		this.rotation.y += dy;
		this.rotation.z += dz;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getRotX() {
		return rotation.x;
	}

	public void setRotX(float rotX) {
		this.rotation.x = rotX;
	}

	public float getRotY() {
		return rotation.y;
	}

	public void setRotY(float rotY) {
		this.rotation.y = rotY;
	}

	public float getRotZ() {
		return rotation.z;
	}

	public void setRotZ(float rotZ) {
		this.rotation.z = rotZ;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
	public void update() {
		move();
		// Calculate the minimum and maximum indices for x, y, and z coordinates
		int minX = Math.max((int) (position.x - collisionAreaSize), 0);
		int maxX = Math.min((int) (position.x + collisionAreaSize), 16 - 1);
		int minY = Math.max((int) (position.y - collisionAreaSize), 0);
		int maxY = Math.min((int) (position.y + collisionAreaSize), World.WORLD_HEIGHT - 1);
		int minZ = Math.max((int) (position.z - collisionAreaSize), 0);
		int maxZ = Math.min((int) (position.z + collisionAreaSize), 16 - 1);
		
		int chunkX = (int) position.x / Chunk.CHUNK_SIZE;
		int chunkZ = (int) position.z / Chunk.CHUNK_SIZE;
		// Retrieve the chunk from your chunk data structure using the calculated coordinates
		
		
		
		
		if(getChunk(chunkX, chunkZ) != null) {
			System.out.println((int) getChunk(chunkX, chunkZ).origin.x + " " + chunkX + " " + (int)getChunk(chunkX, chunkZ).origin.z + " " + chunkZ);
			
			boolean collisionDetected = false;
			for (int x = minX; x <= maxX; x++) {
				for (int y = minY; y <= maxY; y++) {
					for (int z = minZ; z <= maxZ; z++) {
						
						Block block = getChunk(chunkX, chunkZ).blocks[x][y][z];
						if (block != null && block.collidesWith((int)position.x, (int)position.y, (int)position.z)) {
							System.out.println("block : " + x + " " + y + " " + z + " player : " + (int)position.x + " " + (int) position.y + " " + (int)position.z);
							// Collision detected with the block at position (x, y, z)
							// Stop player from falling further
							position.y = block.position.y + 1; // Set player position just above the block
							collisionDetected = true;
							break;
						} else {
							//System.out.println("chunk : " + chunkX + " " + chunkZ + " player : " + (int)position.x + " " + (int) position.y + " " + (int)position.z);
						}
						if (!collisionDetected) {
							if(position.y <= 60)
							position.y -= 1 * DisplayManager.getFrameTimeSeconds(); 
						}
					}

				}
			}

		} else {
			
			
		}
	}
		
	public Chunk getChunk(int chunkX,  int chunkZ) {
	    // Iterate through your chunk data structure
	    for (ChunkMesh chunk : VoxelTest.chunks) {
	        if (chunk.chunk.origin.x == chunkX && chunk.chunk.origin.z == chunkZ) {
	            return chunk.chunk; // Return the matching chunk
	        }
	    }
	    return null; // Chunk not found
	}

		//index =
	
	public void move() {
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			position.y = 75;
		} 
		
		viewFrustum.updateFrustum();
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			speed = SPRINT_SPEED;
		} else {
			speed = BASE_SPEED;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			//System.out.println(getPosition().y);
			moveAt = -speed;
		} else if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			//System.out.println(getPosition().y);
			moveAt = speed;
		} else {
			moveAt = 0;
		}
		
		if(Mouse.isGrabbed()) {
			rotation.x += -Mouse.getDY() * turnSpeed;
			rotation.y += Mouse.getDX() * turnSpeed;
		}
		
		float dx = (float) -(moveAt * Math.sin(Math.toRadians(rotation.y)));
		float dy = (float) (moveAt * Math.sin(Math.toRadians(rotation.x)));
		float dz = (float) (moveAt * Math.cos(Math.toRadians(rotation.y)));
		
		position.x += dx;
		position.z += dz;
		//increasePosition(dx, dy, dz);
	}
}
