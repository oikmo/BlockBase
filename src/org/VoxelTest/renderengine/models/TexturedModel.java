package org.VoxelTest.renderengine.models;

import org.VoxelTest.renderengine.textures.ModelTexture;

public class TexturedModel {
	
	RawModel model;
	ModelTexture texture;
	
	public TexturedModel(RawModel model, ModelTexture texture) {
		this.model = model;
		this.texture = texture;
	}

	public RawModel getModel() {
		return model;
	}

	public ModelTexture getTexture() {
		return texture;
	}	
}
