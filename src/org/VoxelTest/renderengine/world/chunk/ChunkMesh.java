package org.VoxelTest.renderengine.world.chunk;

import java.util.*;

import org.VoxelTest.renderengine.world.World;
import org.VoxelTest.renderengine.world.cube.*;
import org.VoxelTest.renderengine.models.CubeModel;
import org.lwjgl.util.vector.Vector3f;

public class ChunkMesh {
	public List<Vertex> vertices;
	private List<Float> positionsList;
	private List<Float> uvsList;
	private List<Float> normalsList;

	public float[] positions, uvs, normals;
	
	public Chunk chunk;
	
	public ChunkMesh(Chunk chunk) {
		this.chunk = chunk;
		
		vertices = new ArrayList<Vertex>();
		positionsList = new ArrayList<Float>();
		uvsList = new ArrayList<Float>();
		normalsList = new ArrayList<Float>();
		
		buildMesh();
		populateLists();
	}
	
	public void update(Chunk chunk) {
		this.chunk = chunk;
		
		buildMesh();
		populateLists();
	}
	
	private void buildMesh() {
		//loop thru each block in chunk and determine which faces are visible :3
		for (int x = 0; x < chunk.blocks.length; x++) {
		    for (int y = 0; y < World.WORLD_HEIGHT; y++) {
		        for (int z = 0; z < chunk.blocks[x][y].length; z++) {
		            Block blockI = chunk.blocks[x][y][z];
		            boolean px = false, nx = false, py = false, ny = false, pz = false, nz = false;
		            
		            if(blockI == null) { continue; }
		            
		            // Loop through the neighbouring blocks
		            for (int dx = -1; dx <= 1; dx++) {
		                for (int dy = -1; dy <= 1; dy++) {
		                    for (int dz = -1; dz <= 1; dz++) {
		                        // Skip the centre block (blockI)
		                        if (dx == 0 && dy == 0 && dz == 0) {
		                            continue;
		                        }
		                        
		                        // Calculate the neighbouring block's coordinates
		                        int neighborX = x + dx;
		                        int neighborY = y + dy;
		                        int neighborZ = z + dz;
		                        
		                        // Check if the neighbour is within the chunk bounds
		                        if (neighborX >= 0 && neighborX < chunk.blocks.length &&
		                            neighborY >= 0 && neighborY < World.WORLD_HEIGHT &&
		                            neighborZ >= 0 && neighborZ < chunk.blocks[neighborX][neighborY].length) {
		                            Block blockJ = chunk.blocks[neighborX][neighborY][neighborZ];
		                            
		                            if(blockJ == null) { continue; }
		                            //PX
		            				if(((blockI.position.x + 1) == (blockJ.position.x)) && ((blockI.position.y) == (blockJ.position.y)) && ((blockI.position.z) == (blockJ.position.z))) {
		            					px = true;
		            				}
		            				//NX
		            				if(((blockI.position.x - 1) == (blockJ.position.x)) && ((blockI.position.y) == (blockJ.position.y)) && ((blockI.position.z) == (blockJ.position.z))) {
		            					nx = true;
		            				}
		            				//PY
		            				if(((blockI.position.x) == (blockJ.position.x)) && ((blockI.position.y + 1) == (blockJ.position.y)) && ((blockI.position.z) == (blockJ.position.z))) {
		            					py = true;
		            				}
		            				//NY
		            				if(((blockI.position.x) == (blockJ.position.x)) && ((blockI.position.y - 1) == (blockJ.position.y)) && ((blockI.position.z) == (blockJ.position.z))) {
		            					ny = true;
		            				}
		            				//PZ
		            				if(((blockI.position.x) == (blockJ.position.x)) && ((blockI.position.y) == (blockJ.position.y)) && ((blockI.position.z + 1) == (blockJ.position.z))) {
		            					pz = true;
		            				}
		            				//NZ
		            				if(((blockI.position.x) == (blockJ.position.x)) && ((blockI.position.y) == (blockJ.position.y)) && ((blockI.position.z - 1) == (blockJ.position.z))) {
		            					nz = true;
		            				}
		                        }
		                    }
		                }
		            }
		          //Add visible face to the chunkMesh
					
					if(!px) {
						for(int k = 0; k < 6; k++) {
							vertices.add(new Vertex(new Vector3f(CubeModel.PX_POS[k].x + blockI.position.x, CubeModel.PX_POS[k].y + blockI.position.y, CubeModel.PX_POS[k].z + blockI.position.z), CubeModel.UV_PX[(blockI.getType() * 6) + k], CubeModel.NORMALS[k]));
						}
					}
					
					if(!nx) {
						for(int k = 0; k < 6; k++) {
							vertices.add(new Vertex(new Vector3f(CubeModel.NX_POS[k].x + blockI.position.x, CubeModel.NX_POS[k].y + blockI.position.y, CubeModel.NX_POS[k].z + blockI.position.z), CubeModel.UV_NX[(blockI.getType() * 6) + k], CubeModel.NORMALS[k]));
						}
					}
					
					if(!py) {
						for(int k = 0; k < 6; k++) {
							vertices.add(new Vertex(new Vector3f(CubeModel.PY_POS[k].x + blockI.position.x, CubeModel.PY_POS[k].y + blockI.position.y, CubeModel.PY_POS[k].z + blockI.position.z), CubeModel.UV_PY[(blockI.getType() * 6) + k], CubeModel.NORMALS[k]));
						}
					}
					
					if(!ny) {
						for(int k = 0; k < 6; k++) {
							vertices.add(new Vertex(new Vector3f(CubeModel.NY_POS[k].x + blockI.position.x, CubeModel.NY_POS[k].y + blockI.position.y, CubeModel.NY_POS[k].z + blockI.position.z), CubeModel.UV_NY[(blockI.getType() * 6) + k], CubeModel.NORMALS[k]));
						}
					}
					
					if(!pz) {
						for(int k = 0; k < 6; k++) {
							vertices.add(new Vertex(new Vector3f(CubeModel.PZ_POS[k].x + blockI.position.x, CubeModel.PZ_POS[k].y + blockI.position.y, CubeModel.PZ_POS[k].z + blockI.position.z), CubeModel.UV_PZ[(blockI.getType() * 6) + k], CubeModel.NORMALS[k]));
						}
					}
					
					if(!nz) {
						for(int k = 0; k < 6; k++) {
							vertices.add(new Vertex(new Vector3f(CubeModel.NZ_POS[k].x + blockI.position.x, CubeModel.NZ_POS[k].y + blockI.position.y, CubeModel.NZ_POS[k].z + blockI.position.z),CubeModel.UV_NZ[(blockI.getType() * 6) + k], CubeModel.NORMALS[k]));
						}
					}
		        }	
		        
			}
			
			
			
		}
		
	}
	
	private void populateLists() {
		for(int i = 0; i < vertices.size(); i++) {
			positionsList.add(vertices.get(i).positions.x);
			positionsList.add(vertices.get(i).positions.y);
			positionsList.add(vertices.get(i).positions.z);
			
			uvsList.add(vertices.get(i).uvs.x);
			uvsList.add(vertices.get(i).uvs.y);
			
			normalsList.add(vertices.get(i).normals.x);
			normalsList.add(vertices.get(i).normals.y);
			normalsList.add(vertices.get(i).normals.z);
		}
		
		positions = new float[positionsList.size()];
		uvs = new float[uvsList.size()];
		normals = new float[normalsList.size()];
		
		for(int i = 0; i < positionsList.size(); i++) {
			positions[i] = positionsList.get(i);
		}
		
		for(int i = 0; i < uvsList.size(); i++) {
			uvs[i] = uvsList.get(i);
		}
		
		for(int i = 0; i < normalsList.size(); i++) {
			normals[i] = normalsList.get(i);
		}
		
		positionsList.clear();
		uvsList.clear();
		normalsList.clear();
		
	}
} 