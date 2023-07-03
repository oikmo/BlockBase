package org.VoxelTest.phys;

import org.lwjgl.util.vector.Vector3f;

public class AABB {
	private float epsilon = 0.0F;
	public float minX;
	public float minY;
	public float minZ;
	public float maxX;
	public float maxY;
	public float maxZ;

	public AABB(float x0, float y0, float z0, float x1, float y1, float z1) {
		this.minX = x0;
		this.minY = y0;
		this.minZ = z0;
		this.maxX = x1;
		this.maxY = y1;
		this.maxZ = z1;
	}
	
	public Vector3f getMin() {
		return new Vector3f(minX, minY, minZ);
	}
	
	public Vector3f getMax() {
		return new Vector3f(maxX, maxY, maxZ);
	}

	public AABB expand(float dx, float dy, float dz) {
		float _minX = this.minX;
		float _minY = this.minY;
		float _minZ = this.minZ;
		float _maxX = this.maxX;
		float _maxY = this.maxY;
		float _maxZ = this.maxZ;
		if(dx < 0.0F) {
			_minX += dx;
		}

		if(dx > 0.0F) {
			_minY += dx;
		}

		if(dy < 0.0F) {
			_minZ += dy;
		}

		if(dy > 0.0F) {
			_maxX += dy;
		}

		if(dz < 0.0F) {
			_maxY += dz;
		}

		if(dz > 0.0F) {
			_maxZ += dz;
		}

		return new AABB(_minX, _minY, _minZ, _maxX, _maxY, _maxZ);
	}

	public AABB grow(float dx, float dy, float dz) {
		float _minX = this.minX - dx;
		float _minY = this.minY - dy;
		float _minZ = this.minZ - dz;
		float _maxX = this.maxX + dx;
		float _maxY = this.maxY + dy;
		float _maxZ = this.maxZ + dz;
		return new AABB(_minX, _minY, _minZ, _maxX, _maxY, _maxZ);
	}

	public float clipXCollide(AABB aabb, float dx) {
		if(aabb.maxY > this.minY && aabb.minY < this.maxY) {
			if(aabb.maxZ > this.minZ && aabb.minZ < this.maxZ) {
				float max;
				if(dx > 0.0F && aabb.maxX <= this.minX) {
					max = this.minX - aabb.maxX - this.epsilon;
					if(max < dx) {
						dx = max;
					}
				}

				if(dx < 0.0F && aabb.minX >= this.maxX) {
					max = this.maxX - aabb.minX + this.epsilon;
					if(max > dx) {
						dx = max;
					}
				}
				
				return dx;
			} else {
				return dx;
			}
		} else {
			return dx;
		}
	}

	public float clipYCollide(AABB aabb, float dy) {
		if(aabb.maxX > this.minX && aabb.minX < this.maxX) {
			if(aabb.maxZ > this.minZ && aabb.minZ < this.maxZ) {
				float max;
				if(dy > 0.0F && aabb.maxY <= this.minY) {
					max = this.minY - aabb.maxY - this.epsilon;
					if(max < dy) {
						dy = max;
					}
				}

				if(dy < 0.0F && aabb.minY >= this.maxY) {
					max = this.maxY - aabb.minY + this.epsilon;
					if(max > dy) {
						dy = max;
					}
				}

				return dy;
			} else {
				return dy;
			}
		} else {
			return dy;
		}
	}

	public float clipZCollide(AABB aabb, float dz) {
		if(aabb.maxX > this.minX && aabb.minX < this.maxX) {
			if(aabb.maxY > this.minY && aabb.minY < this.maxY) {
				float max;
				if(dz > 0.0F && aabb.maxZ <= this.minZ) {
					max = this.minZ - aabb.maxZ - this.epsilon;
					if(max < dz) {
						dz = max;
					}
				}

				if(dz < 0.0F && aabb.minZ >= this.maxZ) {
					max = this.maxZ - aabb.minZ + this.epsilon;
					if(max > dz) {
						dz = max;
					}
				}

				return dz;
			} else {
				return dz;
			}
		} else {
			return dz;
		}
	}

	public boolean intersects(AABB c) {
		return c.maxX > this.minX && c.minX < this.maxX ? (c.maxY > this.minY && c.minY < this.maxY ? c.maxZ > this.minZ && c.minZ < this.maxZ : false) : false;
	}

	public void move(float dx, float dy, float dz) {
		this.minX += dx;
		this.minY += dy;
		this.minZ += dz;
		this.maxX += dx;
		this.maxY += dy;
		this.maxZ += dz;
	}
}
