package org.VoxelTest.renderengine.world.cube;

import org.lwjgl.util.vector.*;

public class Vertex {
    public float posX, posY, posZ;
    public float uvX, uvY;
    public float normalX, normalY, normalZ;

    public Vertex(Vector3f positions, Vector2f uvs, Vector3f normals) {
        this.posX = positions.x;
        this.posY = positions.y;
        this.posZ = positions.z;
        this.uvX = uvs.x;
        this.uvY = uvs.y;
        this.normalX = normals.x;
        this.normalY = normals.y;
        this.normalZ = normals.z;
    }
}