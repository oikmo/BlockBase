package org.VoxelTest.renderengine.world.audio;

import org.lwjgl.util.WaveData;

public class FileData {
	int buffer;
	WaveData data;
	
	public FileData(int buffer, WaveData data) {
		this.buffer = buffer;
		this.data = data;
	}
	
	public int getBuffer() {
		return buffer;
	}
	
	public WaveData getData() {
		return data;
	}
}
