package org.VoxelTest.renderengine.world.audio;

import org.lwjgl.openal.AL10;
import org.lwjgl.util.vector.Vector3f;

public class Source {
	
	private int sourceID;
	
	float volume = 1, pitch = 1, x = 0, y = 0, z = 0;
	
	public Source() {
		sourceID = AL10.alGenSources();
		AL10.alSourcef(sourceID, AL10.AL_GAIN, volume);
		AL10.alSourcef(sourceID, AL10.AL_PITCH, pitch);
		AL10.alSource3f(sourceID, AL10.AL_POSITION, x, y, z);
	}
	
	public void play(int buffer) {
		stop();
		AL10.alSourcei(sourceID, AL10.AL_BUFFER, buffer);
		continuePlaying();
	}
	
	public void delete() {
		stop();
		AL10.alDeleteSources(sourceID);
	}
	
	public void pause() {
		AL10.alSourcePause(sourceID);
	}
	
	public void continuePlaying() {
		AL10.alSourcePlay(sourceID);
	}
	
	public void stop() {
		AL10.alSourceStop(sourceID);
	}
	
	public void setVelocity(float x, float y, float z) {
		AL10.alSource3f(sourceID, AL10.AL_VELOCITY, x, y, z);
	}
	
	public void setLooping(boolean loop) {
		AL10.alSourcei(sourceID, AL10.AL_LOOPING, loop ? AL10.AL_TRUE : AL10.AL_FALSE);
	}
	
	public float getVolume() {
		return volume;
	}

	public void setVolume(float volume) {
		this.volume = volume;
		AL10.alSourcef(sourceID, AL10.AL_GAIN, this.volume);
	}

	public float getPitch() {
		return pitch;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
		AL10.alSourcef(sourceID, AL10.AL_PITCH, this.pitch);
	}

	public void setPosition(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		AL10.alSource3f(sourceID, AL10.AL_POSITION, this.x, this.y, this.z);
	}
	
	public Vector3f getPosition() {
		return new Vector3f(x,y,z);
	}
	
}
