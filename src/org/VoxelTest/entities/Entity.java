package org.VoxelTest.entities;

import org.VoxelTest.phys.AABB;
import org.VoxelTest.renderengine.models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;

public class Entity {
	TexturedModel model;
	Vector3f position;
	Vector3f rotation;
	float scale;
	AABB boundingBox;
	
	public Entity(TexturedModel model, Vector3f position, Vector3f rotation, float scale) {
		super();
		this.model = model;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}
	
	public Entity(TexturedModel model, Vector3f position) {
		super();
		this.model = model;
		this.position = position;
		this.rotation = new Vector3f(0,0,0);
		this.scale = 1;
		this.boundingBox = calculateBoundingBox();
	}
	
	// Calculate the bounding box based on the entity's position and scale
    private AABB calculateBoundingBox() {
        float minX = position.x - scale;
        float minY = position.y;
        float minZ = position.z - scale;
        float maxX = position.x + scale;
        float maxY = position.y + 2 * scale;
        float maxZ = position.z + scale;
        return new AABB(minX, minY, minZ, maxX, maxY, maxZ);
    }
    
    public AABB getBoundingBox() {
    	return boundingBox;
    }

    // Update the bounding box based on the entity's position and scale
    private void updateBoundingBox() {
    	float minX = position.x - scale;
        float minY = position.y;
        float minZ = position.z - scale;
        float maxX = position.x + scale;
        float maxY = position.y + 2 * scale;
        float maxZ = position.z + scale;
        boundingBox.minX = minX;
        boundingBox.minY = minY;
        boundingBox.minZ = minZ;
        boundingBox.maxX = maxX;
        boundingBox.maxY = maxY;
        boundingBox.maxZ = maxZ;
    }
	
	public void increasePosition(float dx, float dy, float dz) {
		this.position.x += dx;
		this.position.y += dy;
		this.position.z += dz;
		updateBoundingBox();
	}
	
	public void increaseRotation(float dx, float dy, float dz) {
		this.rotation.x += dx;
		this.rotation.y += dy;
		this.rotation.z += dz;
		updateBoundingBox();
	}

	public TexturedModel getModel() {
		return model;
	}

	public void setModel(TexturedModel model) {
		this.model = model;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
		updateBoundingBox();
	}

	public float getRotX() {
		return rotation.x;
	}

	public void setRotX(float rotX) {
		this.rotation.x = rotX;
		updateBoundingBox();
	}

	public float getRotY() {
		return rotation.y;
	}

	public void setRotY(float rotY) {
		this.rotation.y = rotY;
		updateBoundingBox();
	}

	public float getRotZ() {
		return rotation.z;
	}

	public void setRotZ(float rotZ) {
		this.rotation.z = rotZ;
		updateBoundingBox();
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
		updateBoundingBox();
	}
}
