package com.zns.app.sevice.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zns.app.bean.Material;
import com.zns.app.dao.IMaterialDao;
import com.zns.app.service.IMaterialService;

@Service("materialService")
public class MaterialServiceImpl implements IMaterialService{

	@Resource
	private IMaterialDao materDao;

	@Override
	public List<Material> getMaterialList() {
		// TODO Auto-generated method stub
		return materDao.getMaterialList();
	}

	@Override
	public boolean deleteMaterialById(Integer materialId) {
		// TODO Auto-generated method stub
		int result = materDao.deleteByPrimaryKey(materialId);
		if (result == 1) return true;
		return false;
	}

	@Override
	public boolean updateMaterial(Material material) {
		// TODO Auto-generated method stub
		if (material.getMaterialId() == null) return false;
		int result = materDao.updateByPrimaryKeySelective(material);
		if (result == 1) return true;
		return false;
	}

	@Override
	public Material selectMaterialById(Integer materialId) {
		// TODO Auto-generated method stub
		return materDao.selectByPrimaryKey(materialId);
	}

	@Override
	public String insertMaterial(Material material) {
		// TODO Auto-generated method stub
		if(material.getMaterialId() != null) return "Error: MaterialId must be empty";
		int result = materDao.insertSelective(material);
		if (result == 1) return "success";
		return "Error: UnKnown Error";
	}
}
