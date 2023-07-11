package org.VoxelTest.renderengine.world.chunk;

import org.VoxelTest.main.VoxelTest;
import org.VoxelTest.renderengine.world.World;
import org.VoxelTest.renderengine.world.cube.blocks.Block;
import org.VoxelTest.toolbox.PerlinNoiseGenerator;
//import org.VoxelTest.toolbox.datastructures.Fast3DArray;
import org.lwjgl.util.vector.Vector3f;

public class Chunk {

	public static final int CHUNK_SIZE = 16;
	
	boolean isYes = false;
	
	public boolean isDirty;
	
	public void setDirty() {
		isDirty = true;
	}
	
	public void setDirty(boolean dirty) {
		isDirty = dirty;
	}
	
	public boolean isDirty() {
	    return isDirty;
	}
	
	public Block[][][] blocks;
	//public Fast3DArray<Block> bloccks;
	
	public Vector3f origin;
	PerlinNoiseGenerator noiseGen;
	public Chunk(Vector3f origin, String seed) {
		this.origin = origin;
		blocks = new Block[CHUNK_SIZE][World.WORLD_HEIGHT][CHUNK_SIZE];
		//bloccks = new Fast3DArray<Block>(CHUNK_SIZE, World.WORLD_HEIGHT, CHUNK_SIZE);
		noiseGen = new PerlinNoiseGenerator(seed);
		generateChunk();
	}

	public Chunk(Vector3f origin) {
		this.origin = origin;
		blocks = new Block[CHUNK_SIZE][World.WORLD_HEIGHT][CHUNK_SIZE];
		//bloccks = new Fast3DArray<Block>(CHUNK_SIZE, World.WORLD_HEIGHT, CHUNK_SIZE);
		noiseGen = new PerlinNoiseGenerator();
		generateChunk();
	}
	
	public Chunk(Vector3f origin, boolean isYes) {
		this.origin = origin;
		this.isYes = isYes;
		blocks = new Block[CHUNK_SIZE][World.WORLD_HEIGHT][CHUNK_SIZE];
		//bloccks = new Fast3DArray<Block>(CHUNK_SIZE, World.WORLD_HEIGHT, CHUNK_SIZE);
		noiseGen = new PerlinNoiseGenerator();
		generateChunk();
	}
	
	public Chunk(Vector3f origin, Block[][][] blocks) {
		this.origin = origin;
		this.blocks = blocks;
		this.blocks = new Block[CHUNK_SIZE][World.WORLD_HEIGHT][CHUNK_SIZE];
		//bloccks = new Fast3DArray<Block>(CHUNK_SIZE, World.WORLD_HEIGHT, CHUNK_SIZE);
	}
	
	private void generateChunk() {
		
		//System.out.println(origin.x + " " + origin.z);
		 
		if(VoxelTest.theWorld.usedPos.contains(origin)) {
			return;
		}
		
	    // Generate the blocks using the noise generator
	    for (int x = 0; x < CHUNK_SIZE; x++) {
	        for (int z = 0; z < CHUNK_SIZE; z++) {
	            int actualX = (int) (origin.x + x);
	            int actualZ = (int) (origin.z + z);

	            int height = (int) noiseGen.generateHeight(actualX, actualZ);
	            for (int y = 0; y < World.WORLD_HEIGHT; y++) {
	                if (y < height) {
	                    // Create a solid block at this position
	                	if(isYes) {
	                		//System.out.println(x + " " + y + " " + z);
	                	}	                    
	                	blocks[x][y][z] = calculateBlockType(y);
	                } else {
	                    // Air block above the height limit
	                    blocks[x][y][z] = null;
	                }
	            }
	        }
	    }
	    
	    getTopLayer();
	}
	
	private void getTopLayer() {

	    for (int x = 0; x < CHUNK_SIZE; x++) {
	        for (int z = 0; z < CHUNK_SIZE; z++) {
	            for (int y = World.WORLD_HEIGHT - 1; y >= 0; y--) {
	                if (blocks[x][y][z] != null) {
	                	blocks[x][y + 1][z] = Block.grass;
	                    break;
	                }
	            }
	        }
	    }
	}
	
	private Block calculateBlockType(int height) {
	    if (height >= 60) {
	        return Block.dirt;
	    } else if (height >= 40) {
	        return Block.dirt;
	    } else {
	        return Block.stone;
	    }
	}
	
	
}