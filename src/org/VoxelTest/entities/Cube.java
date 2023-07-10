package org.VoxelTest.entities;

import org.VoxelTest.renderengine.models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;

public class Cube extends Entity {
	float range = 3;
	Camera cam;
	
	public Cube(TexturedModel model, Vector3f position) {
		super(model, position);
	}
	
	private float calculateHorizontalDistance() {
		return (float) (range * Math.cos(Math.toRadians(cam.rotation.y)));
	}
	
	private float calculateVerticalDistance() {
		return (float) (range * Math.sin(Math.toRadians(cam.rotation.y)));
	}
	
	
	private void calculateRayPosition(float horzDistance, float vertDistance) {
		float theta = cam.getRotY() + range;
		float offsetX = (float) (horzDistance * Math.sin(Math.toRadians(theta)));
		float offsetZ = (float) (horzDistance * Math.cos(Math.toRadians(theta)));
		position.x = cam.getPosition().x - offsetX;
		position.z = cam.getPosition().z - offsetZ;
		position.y = cam.getPosition().y + vertDistance;
		//System.out.println(position);
	}
	
	public void update() {
		calculateRayPosition(calculateHorizontalDistance(), calculateVerticalDistance());
	}
	
	public void setCamera(Camera camera) {
		this.cam = camera;
	}
	
}
