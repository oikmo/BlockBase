package org.VoxelTest.renderengine.world.chunk;

import java.util.Comparator;

import org.lwjgl.util.vector.Vector3f;

// Define a custom comparator for sorting chunks based on distance from camera
public class ChunkComparator implements Comparator<ChunkMesh> {
    private Vector3f cameraPos;

    public ChunkComparator(Vector3f cameraPos) {
        this.cameraPos = cameraPos;
    }

    @Override
    public int compare(ChunkMesh chunk1, ChunkMesh chunk2) {
        float distance1 = chunk1.chunk.distance(cameraPos);
        float distance2 = chunk2.chunk.distance(cameraPos);
        return Float.compare(distance1, distance2);
    }
}