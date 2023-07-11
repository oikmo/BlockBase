package org.VoxelTest.renderengine.world.audio;

import java.util.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.util.WaveData;

public class AudioMaster {
	
	private static List<Integer> buffers = new ArrayList<Integer>();
	
	public static void init() {
		try {
			AL.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
	} 
	
	public static void setListenerData(float x, float y, float z) {
		AL10.alListener3f(AL10.AL_POSITION, x, y, z);
		AL10.alListener3f(AL10.AL_VELOCITY, 0, 0, 0);
	}
	
	public static FileData loadSound(String file) {
		int buffer = AL10.alGenBuffers();
		buffers.add(buffer);
		System.out.println(AudioMaster.class.getResourceAsStream("sounds/" + file + ".wav"));
		WaveData waveFile = null;
		if(AudioMaster.class.getResourceAsStream("sounds/" + file + ".wav") != null) {
			waveFile  = WaveData.create(AudioMaster.class.getResourceAsStream("sounds/" + file + ".wav"));
		} else {
			waveFile = WaveData.create(AudioMaster.class.getResourceAsStream(file + ".wav"));
		}
		
		AL10.alBufferData(buffer, waveFile.format, waveFile.data, waveFile.samplerate);
		waveFile.dispose();
		return new FileData(buffer, waveFile);
	}
	
	public static void cleanUp() {
		buffers.forEach(AL10::alDeleteBuffers);
		AL.destroy();
	}
	
	public static void playBackgroundMusic(String fileName)  {
		//System.out.println(Sound.class.getResourceAsStream(fileName));
		
		new Thread(new Runnable() {
			public void run() {
				FileData data = loadSound(fileName);
				
				Source source = new Source();
				source.play(data.buffer);
				
				System.out.println("Duration: " + getDuration(data.data)  + " seconds");
				sleep((long)getDuration(data.data));
				
				source.delete();
			}
		}).start();
		
	}
	
	static float getDuration(WaveData data) {
		int sampleRate = data.samplerate;
        int numSamples = data.data.remaining() / (data.format == AL10.AL_FORMAT_STEREO8 || data.format == AL10.AL_FORMAT_STEREO16 ? 2 : 1);
        
        return ((float) numSamples / (float) sampleRate) / 2;
	}
	
	static void sleep( long seconds )
	{
		try
		{
			Thread.sleep( 1000 * seconds );
		}
		catch( InterruptedException e )
		{}
	}

	
}
