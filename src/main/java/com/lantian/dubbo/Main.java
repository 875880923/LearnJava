/**
 * 
 */
package com.lantian.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bj58.suyun.driver.task.api.ExclusiveOrderTaskService;

/**
 * @author lantian
 *
 */
public class Main {

	
	public static void main(String[] args) {
		testOffline();
	}
	
	//测试环境
	private static void testOffline(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"classpath*:spring-dubbo-test.xml"});
		ExclusiveOrderTaskService service = (ExclusiveOrderTaskService)context.getBean("exclusiveOrderTaskService");
		service.beginDriverTask(123);
	}

}
