package com.zns.app.service;

import java.util.List;

import com.zns.app.bean.Material;

public interface IMaterialService {

	public List<Material> getMaterialList();
	
	public boolean deleteMaterialById(Integer materialId);
	
	public boolean updateMaterial(Material material);
	
	public Material selectMaterialById(Integer materialId);
	
	public String insertMaterial(Material material);
}
