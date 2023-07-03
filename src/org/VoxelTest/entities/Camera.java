package org.VoxelTest.entities;

import org.VoxelTest.toolbox.datastructures.ViewFrustum;
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
	
	ViewFrustum viewFrustum;
	
	public Camera(Vector3f position, Vector3f rotation, float scale) {
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		viewFrustum = new ViewFrustum();
	}
	
	public ViewFrustum getFrustum() {
		return viewFrustum;
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
	
	public void move() {
		viewFrustum.updateFrustum();
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
		float dy = (float) (moveAt * Math.sin(Math.toRadians(rotation.x)));
		float dz = (float) (moveAt * Math.cos(Math.toRadians(rotation.y)));
		
		position.x += dx;
		position.y += dy;
		position.z += dz;
		//increasePosition(dx, dy, dz);
	}
}
