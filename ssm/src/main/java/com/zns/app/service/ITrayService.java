package com.zns.app.service;

import java.util.List;

import com.zns.app.bean.Tray;

public interface ITrayService {

	public List<Tray> getTrayListByUserNo(String userNo);
}
