package com.zns.app.service;

import java.util.List;

import com.zns.app.bean.Client;

public interface IClientService {
	
	public List<Client> getClientList();
	
	public boolean deleteClientById(Integer clientId);
	
	public boolean updateClient(Client client);
	
	public Client selectClientById(Integer clientId);
	
	public String insertClient(Client client);
}
