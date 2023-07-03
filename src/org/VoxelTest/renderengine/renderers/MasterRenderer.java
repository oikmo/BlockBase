package org.VoxelTest.renderengine.renderers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.VoxelTest.entities.Camera;
import org.VoxelTest.entities.Entity;
import org.VoxelTest.main.VoxelTest;	
import org.VoxelTest.renderengine.models.TexturedModel;
import org.VoxelTest.renderengine.shaders.main.StaticShader;
import org.VoxelTest.toolbox.Maths;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

public class MasterRenderer {
	
	Matrix4f projectionMatrix;
	
	private static final float FOV = 90f;
	private static final float NEAR_PLANE = 0.01f;
	private static final float FAR_PLANE = 1000f;
	
	Map<TexturedModel, HashSet<Entity>> entities = new HashMap<TexturedModel, HashSet<Entity>>();
	//EntityRenderer renderer = new EntityRenderer();
	static StaticShader shader = new StaticShader();
	
	public MasterRenderer() {
		createProjectionMatrix();
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}
	
	public void prepare() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClearColor(0.4f, 0.7f, 1.0f, 1);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}
	
	Matrix4f viewMatrix;
	
	public void render(Camera camera) {
		prepare();
		
	    shader.start();
	    shader.loadViewMatrix(camera);
	    shader.loadProjectionMatrix(projectionMatrix);
	    shader.loadSkyColor(0.4f, 0.7f, 1.0f);
	    
	    EntityRenderer.render(entities);
		
	    
	    shader.stop();
		
		entities.clear();
	}
	public void addEntity(Entity entity) {
		TexturedModel model = entity.getModel();
		
		HashSet<Entity> batch = entities.get(model);
		
		if(batch != null) {
			batch.add(entity);
		} else {
			HashSet<Entity> newBatch = new HashSet<>();
			newBatch.add(entity);
			entities.put(model, newBatch);
		}
	}
	
	public void removeEntity(Entity entity) {
		TexturedModel model = entity.getModel();
		
		entities.remove(model, VoxelTest.entities);
	}
	
	public Matrix4f getProjectionMatrix() {
		return projectionMatrix;
	}
	
	public void createProjectionMatrix() {
		projectionMatrix = new Matrix4f();
		float aspect = (float) Display.getWidth() / (float) Display.getHeight();
		float yScale = (float) (1f / Math.tan(Math.toRadians(FOV / 2f)));
		float xScale = yScale / aspect;
		float zp = FAR_PLANE + NEAR_PLANE;
		float zm = FAR_PLANE - NEAR_PLANE;
		
		projectionMatrix.m00 = xScale;
		projectionMatrix.m11 = yScale;
		projectionMatrix.m22 = -zp/zm;
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -(2 * FAR_PLANE * NEAR_PLANE) / zm;
		projectionMatrix.m33 = 0;
		
	}
	
	public void cleanUp() {
		shader.cleanUp();
	}
}
