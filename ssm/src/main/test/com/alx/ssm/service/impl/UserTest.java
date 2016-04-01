//package com.alx.ssm.service.impl;
//
//import static org.junit.Assert.fail;
//
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.alibaba.fastjson.JSON;
//import com.alx.ssm.bean.User;
//import com.alx.ssm.service.IUserService;
//import com.github.pagehelper.PageInfo;
//
//@RunWith(SpringJUnit4ClassRunner.class)     //琛ㄧず缁ф壙浜哠pringJUnit4ClassRunner绫� 
//@ContextConfiguration(locations = {"classpath:applicationContext.xml"}) 
//public class UserTest {
//	
//	private static Logger logger = LoggerFactory.getLogger(UserTest.class);
//
////  private ApplicationContext ac = null;  
//    @Resource  
//    private IUserService userService;  
//  
////  @Before  
////  public void before() {  
////      ac = new ClassPathXmlApplicationContext("applicationContext.xml");  
////      userService = (IUserService) ac.getBean("userService");  
////  }  
//	
//	@Test
//	public void testGetUserById() {
//		User user = userService.getUserById(1);  
//        // System.out.println(user.getUserName());  
//        // logger.info("鍊硷細"+user.getUserName());  
//        logger.info(JSON.toJSONString(user));  
//	}
//
//	@Test
//	public void testGetUserPageList() {
//		User user = new User();
//		user.setUserName("a");
//		PageInfo<User> userList = userService.queryPageList(new User(), 2, 2); 
//        logger.info(JSON.toJSONString(userList.getList()));  
//	}
//	
//	@Test
//	public void testInsertUser() {
//		User user = new User();
//		user.setAge(26);
//		user.setPassword("1111");
//		user.setUserName("alex");
//		userService.insert(user);
//	}
//	
//	@Test
//	public void testUpdateUser() {
//		User user = new User();
//		user.setId(4);
//		user.setAge(26);
//		user.setPassword("1111");
//		user.setUserName("jeef");
//		userService.update(user);
//	}
//}
