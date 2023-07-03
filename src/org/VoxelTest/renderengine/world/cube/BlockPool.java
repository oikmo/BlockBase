package org.VoxelTest.renderengine.world.cube;

import java.util.ArrayList;
import java.util.List;

public class BlockPool {
    private static final int BLOCK_POOL_SIZE = 100;
    private static final List<Block> blockPool = new ArrayList<>(BLOCK_POOL_SIZE);
    
    public static void initializePool() {
        for (int i = 0; i < BLOCK_POOL_SIZE; i++) {
            blockPool.add(new Block());
        }
    }
    
    public static Block acquireBlock(int x, int y, int z, Block.Type type) {
        Block block = getBlockFromPool();
        block.setPosition(x, y, z);
        block.setType(type);
        return block;
    }
    
    public static void releaseBlock(Block block) {
        block.reset();
        blockPool.add(block);
    }
    
    private static Block getBlockFromPool() {
        if (blockPool.isEmpty()) {
            return new Block();
        } else {
            return blockPool.remove(blockPool.size() - 1);
        }
    }
    
    public static class Block {
        public int x, y, z;
        private Type type;
        
        public void setPosition(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
        
        public void setType(Type type) {
            this.type = type;
        }
        
        public int getType() {
        	return type.ordinal();
        }
        
        public void reset() {
            // Reset any other properties of the block if needed
        }
        
        // Other methods and properties of the Block class
        
        public enum Type {
            GRASS,
            DIRT,
            // Other block types
        }
    }
}