package org.VoxelTest.renderengine.shaders.main;

import org.VoxelTest.entities.Camera;
import org.VoxelTest.renderengine.shaders.ShaderProgram;
import org.VoxelTest.toolbox.Maths;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class StaticShader extends ShaderProgram {
	
	private static final String vertexFile = "main/vertexShader.txt";
	private static final String fragmentFile = "main/fragmentShader.txt";
	
	int location_transformationMatrix;
	int location_projectionMatrix;
	int location_viewMatrix;
	int location_skyColor;
	
	public StaticShader() {
		super(vertexFile, fragmentFile);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute("position", 0);
		super.bindAttribute("textureCoords", 1);
	}

	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
		location_skyColor = super.getUniformLocation("skyColor");
	}
	
	public void loadSkyColor(float r, float g, float b) {
		super.load3DVector(location_skyColor, new Vector3f(r,g,b));
	}
	
	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(location_transformationMatrix, matrix);
	}
	
	public void loadProjectionMatrix(Matrix4f matrix) {
		super.loadMatrix(location_projectionMatrix, matrix);
	}
	
	public void loadViewMatrix(Camera camera) {
		super.loadMatrix(location_viewMatrix, Maths.createViewMatrix(camera));
	}
}
