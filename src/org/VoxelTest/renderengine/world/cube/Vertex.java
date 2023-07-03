package org.VoxelTest.renderengine.world.cube;

import org.lwjgl.util.vector.*;

public class Vertex {
	public Vector3f positions, normals;
	public Vector2f uvs;
	
	public Vertex(Vector3f positions, Vector2f uvs, Vector3f normals) {
		this.positions = positions;
		this.uvs = uvs;
		this.normals = normals;
	}
}
