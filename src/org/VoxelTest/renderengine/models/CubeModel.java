package org.VoxelTest.renderengine.models;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class CubeModel {
	
	//front face
	public static Vector3f[] PX_POS = {
			new Vector3f(0.5f, -0.5f, -0.5f),
			new Vector3f(0.5f, 0.5f, -0.5f),
			new Vector3f(0.5f, 0.5f, 0.5f),
			new Vector3f(0.5f, 0.5f, 0.5f),
			new Vector3f(0.5f, -0.5f, 0.5f),
			new Vector3f(0.5f, -0.5f, -0.5f),

			
	};
	//back face
	public static Vector3f[] NX_POS = {
			new Vector3f(-0.5f, 0.5f, -0.5f),
			new Vector3f(-0.5f, -0.5f, -0.5f),
			new Vector3f(-0.5f, -0.5f, 0.5f),
			new Vector3f(-0.5f, -0.5f, 0.5f),
			new Vector3f(-0.5f, 0.5f, 0.5f),
			new Vector3f(-0.5f, 0.5f, -0.5f)
	};
	//top face
	public static Vector3f[] PY_POS = {
		    new Vector3f(-0.5f, 0.5f, 0.5f),
		    new Vector3f(0.5f, 0.5f, 0.5f),
		    new Vector3f(0.5f, 0.5f, -0.5f),
		    new Vector3f(0.5f, 0.5f, -0.5f),
		    new Vector3f(-0.5f, 0.5f, -0.5f),
		    new Vector3f(-0.5f, 0.5f, 0.5f)
		};

	//bottom face
	public static Vector3f[] NY_POS = {
			new Vector3f(0.5f, -0.5f, -0.5f),
			new Vector3f(0.5f, -0.5f, 0.5f),
			new Vector3f(-0.5f, -0.5f, 0.5f),
			new Vector3f(-0.5f, -0.5f, 0.5f),
			new Vector3f(-0.5f, -0.5f, -0.5f),
			new Vector3f(0.5f, -0.5f, -0.5f),
			
	};
	//right face
	public static Vector3f[] PZ_POS = {
			new Vector3f(-0.5f, 0.5f, 0.5f),
			new Vector3f(-0.5f, -0.5f, 0.5f),
			new Vector3f(0.5f, -0.5f, 0.5f),
			new Vector3f(0.5f, -0.5f, 0.5f),
			new Vector3f(0.5f, 0.5f, 0.5f),
			new Vector3f(-0.5f, 0.5f, 0.5f)
	};
	//left face
	public static Vector3f[] NZ_POS = {
			new Vector3f(-0.5f, 0.5f, -0.5f),
		    new Vector3f(0.5f, 0.5f, -0.5f),
		    new Vector3f(0.5f, -0.5f, -0.5f),
		    new Vector3f(0.5f, -0.5f, -0.5f),
		    new Vector3f(-0.5f, -0.5f, -0.5f),
		    new Vector3f(-0.5f, 0.5f, -0.5f)
	};


	public static Vector2f[] UV = {
			
			new Vector2f(0.f, 0.f),
			new Vector2f(0.f, 1.f),
			new Vector2f(1.f, 1.f),
			new Vector2f(1.f, 1.f),
			new Vector2f(1.f, 0.f),
			new Vector2f(0.f, 0.f)
			
	};
	
	public static Vector2f[] UV_PX = {
			
			// GRASS
			new Vector2f(1f / 16.f, 0.f),
			new Vector2f(1f / 16.f, 1.f / 16.f),
			new Vector2f(2f / 16.f, 1.f / 16.f),
			new Vector2f(2f / 16.f, 1.f / 16.f),
			new Vector2f(2f / 16.f, 0.f / 16.f),
			new Vector2f(1f / 16.f, 0.f),
			
			// DIRT
			new Vector2f(2f / 16.f, 0.f),
			new Vector2f(2f / 16.f, 1.f / 16.f),
			new Vector2f(3f / 16.f, 1.f / 16.f),
			new Vector2f(3f / 16.f, 1.f / 16.f),
			new Vector2f(3f / 16.f, 0.f / 16.f),
			new Vector2f(2f / 16.f, 0.f),
			
			// STONE
			new Vector2f(3.f / 16.f, 0.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 0.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f),
			
			// TREEBARK
			new Vector2f(4.f / 16.f, 0.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(5.f / 16.f, 1.f / 16.f),
			new Vector2f(5.f / 16.f, 1.f / 16.f),
			new Vector2f(5.f / 16.f, 0.f / 16.f),
			new Vector2f(4.f / 16.f, 0.f),
			
			// TREELEAF
			new Vector2f(6.f / 16.f, 0.f),
			new Vector2f(6.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 0.f / 16.f),
			new Vector2f(6.f / 16.f, 0.f),
			
			// COBBLESTONE
			new Vector2f(7.f / 16.f, 0.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(8.f / 16.f, 1.f / 16.f),
			new Vector2f(8.f / 16.f, 1.f / 16.f),
			new Vector2f(8.f / 16.f, 0.f / 16.f),
			new Vector2f(7.f / 16.f, 0.f),
			
			// BEDROCK
			new Vector2f(8.f / 16.f, 0.f),
			new Vector2f(8.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 0.f / 16.f),
			new Vector2f(8.f / 16.f, 0.f)
			
	};	
	public static Vector2f[] UV_NX = {
			
			// GRASS
			new Vector2f(1.f / 16.f, 0.f),
			new Vector2f(1.f / 16.f, 1.f / 16.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(2.f / 16.f, 0.f / 16.f),
			new Vector2f(1.f / 16.f, 0.f),
			
			// DIRT
			new Vector2f(2.f / 16.f, 0.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f / 16.f),
			new Vector2f(2.f / 16.f, 0.f),
			
			// STONE
			new Vector2f(3.f / 16.f, 0.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 0.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f),
			
			// TREEBARK
			new Vector2f(4.f / 16.f, 0.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(5.f / 16.f, 1.f / 16.f),
			new Vector2f(5.f / 16.f, 1.f / 16.f),
			new Vector2f(5.f / 16.f, 0.f / 16.f),
			new Vector2f(4.f / 16.f, 0.f),
			
			// TREELEAF
			new Vector2f(6.f / 16.f, 0.f),
			new Vector2f(6.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 0.f / 16.f),
			new Vector2f(6.f / 16.f, 0.f),
			
			// COBBLESTONE
			new Vector2f(7.f / 16.f, 0.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(8.f / 16.f, 1.f / 16.f),
			new Vector2f(8.f / 16.f, 1.f / 16.f),
			new Vector2f(8.f / 16.f, 0.f / 16.f),
			new Vector2f(7.f / 16.f, 0.f),
			
			// BEDROCK
			new Vector2f(8.f / 16.f, 0.f),
			new Vector2f(8.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 0.f / 16.f),
			new Vector2f(8.f / 16.f, 0.f)
	};
	public static Vector2f[] UV_PY = {
			
			// GRASS
			new Vector2f(0.f, 0.f),
			new Vector2f(0.f, 1.f / 16.f),
			new Vector2f(1.f / 16.f, 1.f / 16.f),
			new Vector2f(1.f / 16.f, 1.f / 16.f),
			new Vector2f(1.f / 16.f, 0.f),
			new Vector2f(0.f, 0.f),
			
			// DIRT
			new Vector2f(2.f / 16.f, 0.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f / 16.f),
			new Vector2f(2.f / 16.f, 0.f),
			
			// STONE
			new Vector2f(3.f / 16.f, 0.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 0.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f),
			
			// TREEBARK
			new Vector2f(5.f / 16.f, 0.f),
			new Vector2f(5.f / 16.f, 1.f / 16.f),
			new Vector2f(6.f / 16.f, 1.f / 16.f),
			new Vector2f(6.f / 16.f, 1.f / 16.f),
			new Vector2f(6.f / 16.f, 0.f / 16.f),
			new Vector2f(5.f / 16.f, 0.f),
			
			// TREELEAF
			new Vector2f(6.f / 16.f, 0.f),
			new Vector2f(6.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 0.f / 16.f),
			new Vector2f(6.f / 16.f, 0.f),
			
			// COBBLESTONE
			new Vector2f(7.f / 16.f, 0.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(8.f / 16.f, 1.f / 16.f),
			new Vector2f(8.f / 16.f, 1.f / 16.f),
			new Vector2f(8.f / 16.f, 0.f / 16.f),
			new Vector2f(7.f / 16.f, 0.f),
			
			// BEDROCK
			new Vector2f(8.f / 16.f, 0.f),
			new Vector2f(8.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 0.f / 16.f),
			new Vector2f(8.f / 16.f, 0.f)
	};
	public static Vector2f[] UV_NY = {
			
			// GRASS
			new Vector2f(2.f / 16.f, 0.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f / 16.f),
			new Vector2f(2.f / 16.f, 0.f),
			
			// DIRT
			new Vector2f(2.f / 16.f, 0.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f / 16.f),
			new Vector2f(2.f / 16.f, 0.f),
			
			// STONE
			new Vector2f(3.f / 16.f, 0.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 0.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f),
			
			// TREEBARK
			new Vector2f(5.f / 16.f, 0.f),
			new Vector2f(5.f / 16.f, 1.f / 16.f),
			new Vector2f(6.f / 16.f, 1.f / 16.f),
			new Vector2f(6.f / 16.f, 1.f / 16.f),
			new Vector2f(6.f / 16.f, 0.f / 16.f),
			new Vector2f(5.f / 16.f, 0.f),
			
			// TREELEAF
			new Vector2f(6.f / 16.f, 0.f),
			new Vector2f(6.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 0.f / 16.f),
			new Vector2f(6.f / 16.f, 0.f),
			
			// COBBLESTONE
			new Vector2f(7.f / 16.f, 0.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(8.f / 16.f, 1.f / 16.f),
			new Vector2f(8.f / 16.f, 1.f / 16.f),
			new Vector2f(8.f / 16.f, 0.f / 16.f),
			new Vector2f(7.f / 16.f, 0.f),
			
			// BEDROCK
			new Vector2f(8.f / 16.f, 0.f),
			new Vector2f(8.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 0.f / 16.f),
			new Vector2f(8.f / 16.f, 0.f)
	};	
	public static Vector2f[] UV_PZ = {
			
			// GRASS
			new Vector2f(1.f / 16.f, 0.f),
			new Vector2f(1.f / 16.f, 1.f / 16.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(2.f / 16.f, 0.f / 16.f),
			new Vector2f(1.f / 16.f, 0.f),
			
			// DIRT
			new Vector2f(2.f / 16.f, 0.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f / 16.f),
			new Vector2f(2.f / 16.f, 0.f),
			
			// STONE
			new Vector2f(3.f / 16.f, 0.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 0.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f),
			
			// TREEBARK
			new Vector2f(4.f / 16.f, 0.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(5.f / 16.f, 1.f / 16.f),
			new Vector2f(5.f / 16.f, 1.f / 16.f),
			new Vector2f(5.f / 16.f, 0.f / 16.f),
			new Vector2f(4.f / 16.f, 0.f),
			
			// TREELEAF
			new Vector2f(6.f / 16.f, 0.f),
			new Vector2f(6.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 0.f / 16.f),
			new Vector2f(6.f / 16.f, 0.f),
			
			// COBBLESTONE
			new Vector2f(7.f / 16.f, 0.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(8.f / 16.f, 1.f / 16.f),
			new Vector2f(8.f / 16.f, 1.f / 16.f),
			new Vector2f(8.f / 16.f, 0.f / 16.f),
			new Vector2f(7.f / 16.f, 0.f),
			
			// BEDROCK
			new Vector2f(8.f / 16.f, 0.f),
			new Vector2f(8.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 0.f / 16.f),
			new Vector2f(8.f / 16.f, 0.f)
	};
	public static Vector2f[] UV_NZ = {
		
			// GRASS
			new Vector2f(1.f / 16.f, 0.f),
			new Vector2f(1.f / 16.f, 1.f / 16.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(2.f / 16.f, 0.f / 16.f),
			new Vector2f(1.f / 16.f, 0.f),
			
			// DIRT
			new Vector2f(2.f / 16.f, 0.f),
			new Vector2f(2.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f / 16.f),
			new Vector2f(2.f / 16.f, 0.f),
			
			// STONE
			new Vector2f(3.f / 16.f, 0.f),
			new Vector2f(3.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(4.f / 16.f, 0.f / 16.f),
			new Vector2f(3.f / 16.f, 0.f),
			
			// TREEBARK
			new Vector2f(4.f / 16.f, 0.f),
			new Vector2f(4.f / 16.f, 1.f / 16.f),
			new Vector2f(5.f / 16.f, 1.f / 16.f),
			new Vector2f(5.f / 16.f, 1.f / 16.f),
			new Vector2f(5.f / 16.f, 0.f / 16.f),
			new Vector2f(4.f / 16.f, 0.f),
			
			// TREELEAF
			new Vector2f(6.f / 16.f, 0.f),
			new Vector2f(6.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(7.f / 16.f, 0.f / 16.f),
			new Vector2f(6.f / 16.f, 0.f),
			
			// COBBLESTONE
			new Vector2f(7.f / 16.f, 0.f),
			new Vector2f(7.f / 16.f, 1.f / 16.f),
			new Vector2f(8.f / 16.f, 1.f / 16.f),
			new Vector2f(8.f / 16.f, 1.f / 16.f),
			new Vector2f(8.f / 16.f, 0.f / 16.f),
			new Vector2f(7.f / 16.f, 0.f),
			
			// BEDROCK
			new Vector2f(8.f / 16.f, 0.f),
			new Vector2f(8.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 1.f / 16.f),
			new Vector2f(9.f / 16.f, 0.f / 16.f),
			new Vector2f(8.f / 16.f, 0.f)
	};
	
	public static Vector3f[] NORMALS = {
		    new Vector3f(1.f, 0.f, 0.f),   // Normal for Vertex 1
		    new Vector3f(1.f, 0.f, 0.f),   // Normal for Vertex 2
		    new Vector3f(1.f, 0.f, 0.f),   // Normal for Vertex 3
		    new Vector3f(1.f, 0.f, 0.f),   // Normal for Vertex 4
		    new Vector3f(1.f, 0.f, 0.f),   // Normal for Vertex 5
		    new Vector3f(1.f, 0.f, 0.f)    // Normal for Vertex 6
	};

}