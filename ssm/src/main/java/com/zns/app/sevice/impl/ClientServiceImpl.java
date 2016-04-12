package com.zns.app.sevice.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zns.app.bean.Client;
import com.zns.app.dao.IClientDao;
import com.zns.app.service.IClientService;

@Service("clientService")
public class ClientServiceImpl implements IClientService{
	
	@Resource
	private IClientDao clientDao;

	@Override
	public List<Client> getClientList() {
		// TODO Auto-generated method stub
		
		return clientDao.getClientList();
	}

	@Override
	public boolean deleteClientById(Integer clientId) {
		// TODO Auto-generated method stub
		int result = clientDao.deleteByPrimaryKey(clientId);
		if (result == 1) return true;
		return false;
	}

	@Override
	public boolean updateClient(Client client) {
		// TODO Auto-generated method stub
		int result = clientDao.updateByPrimaryKeySelective(client);
		if (result == 1) return true;
		return false;
	}

	@Override
	public Client selectClientById(Integer clientId) {
		// TODO Auto-generated method stub
		return clientDao.selectByPrimaryKey(clientId);
	}

	@Override
	public String insertClient(Client client) {
		// TODO Auto-generated method stub
		int result = clientDao.insertSelective(client);
		if (result == 1) return "success";
		return "Unknown Error";
	}
}
