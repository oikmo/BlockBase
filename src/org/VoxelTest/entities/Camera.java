package org.VoxelTest.entities;

import org.VoxelTest.main.VoxelTest;
import org.VoxelTest.renderengine.DisplayManager;
import org.VoxelTest.renderengine.world.chunk.Chunk;
import org.VoxelTest.renderengine.world.chunk.ChunkManager;
import org.VoxelTest.renderengine.world.chunk.ChunkMesh;
import org.VoxelTest.renderengine.world.cube.blocks.Block;
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
	
	float collisionAreaSize = 0.5f; // Adjust this value to fit your needs

	int chunkX = 0;
	int chunkZ = 0;
	
	ChunkMesh currentChunk = null;
	
	public Camera(Vector3f position, Vector3f rotation, float scale) {
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		startThread1();
		
	}

	boolean isChunkNull = true;
	
	int minX;
	int minY;
	int minZ;
	int maxX;
	int maxY;
	int maxZ;
	private void startThread1() {

		Thread chunkGetter = new Thread(new Runnable() {
			public void run() {
				while(!VoxelTest.closeDisplay) {
					if(position.x >= 0) {
						chunkX = (int) position.x / Chunk.CHUNK_SIZE;
					} else {
						if(position.x > -16) {
							chunkX = (int)-1;
						} else {
							chunkX = (int) (position.x / Chunk.CHUNK_SIZE)-1;
						}
					}
					
					if(position.z >= 0) {
						chunkZ = (int) position.z / Chunk.CHUNK_SIZE;
					} else {
						if(position.z > -16) {
							chunkZ = (int)-1;
						} else {
							chunkZ = (int) (position.z / Chunk.CHUNK_SIZE)-1;
						}
					}
					
					//System.out.println("("+(int) position.x+ " " + (int) position.z + ") (" + chunkX + " " + chunkZ + ")");
					for(int i = 0; i < VoxelTest.theWorld.chunks.size(); i++) {
						ChunkMesh mesh = VoxelTest.theWorld.chunks.get(i);
						if(mesh != null) {
							//
							if(mesh.chunk.origin.x/16 == chunkX && mesh.chunk.origin.z/16 == chunkZ) {
								currentChunk = mesh;
							}
							
						} else {
							System.out.println("Null!");
						}
					}
				}
			}
		});
		
		chunkGetter.setName("Chunk Grabber");
		chunkGetter.start();
		
		/*new Thread(new Runnable() {
			public void run() {
				while(!VoxelTest.closeDisplay) {
					//System.out.println("tru!!");
					// Calculate the minimum and maximum indices for x, y, and z coordinates
					if( minX != Math.max((int) (position.x - collisionAreaSize), 0)) {
						minX = Math.max((int) (position.x - collisionAreaSize), 0);
					}
					if( maxX != Math.max((int) (position.x + collisionAreaSize), 16 - 1)) {
						maxX = Math.max((int) (position.x + collisionAreaSize), 16 - 1);
					}
					if(maxX != Math.min((int) (position.x + collisionAreaSize), 16 - 1)) {
						maxX = Math.min((int) (position.x + collisionAreaSize), 16 - 1);
					}
					if(minY != Math.max((int) (position.y - collisionAreaSize), 0)) {
						minY = Math.max((int) (position.y - collisionAreaSize), 0);
					}
					if(maxY != Math.min((int) (position.y + collisionAreaSize), World.WORLD_HEIGHT - 1)) {
						maxY = Math.min((int) (position.y + collisionAreaSize), World.WORLD_HEIGHT - 1);
					}
					if(minZ != Math.max((int) (position.z - collisionAreaSize), 0)) {
						minZ = Math.max((int) (position.z - collisionAreaSize), 0);
					}
					if(maxZ != Math.min((int) (position.z + collisionAreaSize), 16 - 1)) {
						maxZ = Math.min((int) (position.z + collisionAreaSize), 16 - 1);
					}
					// Retrieve the chunk from your chunk data structure using the calculated coordinates
					if(isChunkNull) {
						@SuppressWarnings("unused")
						boolean bool = currentChunk != null;
						//System.out.println(currentChunk != null);
					}
					
					if(currentChunk != null) {
						if(isChunkNull) {
							isChunkNull = false;
						}
						//System.out.println("(" + (int) currentChunk.origin.x/16 + " " + (int)currentChunk.origin.z/16 + ") (" + chunkX+ " "+ chunkZ + ")");
						
						boolean collisionDetected = false;
						Block block = currentChunk.getBlock(position);
						//System.out.println("block : " + block + " blockPos : " + x + " " + y + " " + z + " player : " + (int)position.x + " " + (int) position.y + " " + (int)position.z);
						if (block != null && block.collidesWith((int)position.x, (int)position.y, (int)position.z)) {
							//System.out.println("block : " + x + " " + y + " " + z + " player : " + (int)position.x + " " + (int) position.y + " " + (int)position.z);
							// Collision detected with the block at position (x, y, z)
							// Stop player from falling further
							//position.y = block.position.y + 1; // Set player position just above the block
							//System.out.println("Block");
							collisionDetected = true;
							break;
						}
						if(!collisionDetected) {
						}
					} else {
						if(!isChunkNull) {
							isChunkNull = true;
						}
						//System.out.println("CHUNK IS NULL!"); 
						
					}
				}
			}
		});*/
		
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
	
	public Vector3f getRotation() {
		return rotation;
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
		
		
		
		
		//Vector3f vec = new Vector3f(position.x, position.y, position.z);
		
		if(currentChunk != null) {
			
			//System.out.println("(" + (int)currentChunk.chunk.origin.x + " " + (int)currentChunk.chunk.origin.z + ") ("  + (int) position.x  + " " + (int) position.z+")");
			if(Mouse.isButtonDown(1)) {
				//System.out.println("");
				ChunkManager.setBlock(new Vector3f(position.x, position.y, position.z), Block.bedrock, currentChunk.chunk);
			} else if(Mouse.isButtonDown(0)) {
				ChunkManager.setBlock(new Vector3f(position.x, position.y, position.z), null, currentChunk.chunk);
			}
		} else {
			//System.out.println("ball scak!!!");
		}
	}
	
	public void move() {
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			position.y += 10 * DisplayManager.getFrameTimeSeconds();
		} 
		if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
			position.y -= 10 * DisplayManager.getFrameTimeSeconds();
		} 
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
		//float dy = (float) (moveAt * Math.sin(Math.toRadians(rotation.x)));
		float dz = (float) (moveAt * Math.cos(Math.toRadians(rotation.y)));
		
		position.x += (dx*100) * DisplayManager.getFrameTimeSeconds();
		//position.y += dy;
		position.z += (dz*100) * DisplayManager.getFrameTimeSeconds();
		//increasePosition(dx, dy, dz);
	}
}
