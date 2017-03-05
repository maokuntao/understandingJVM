package com.taomk.understandingJVM.method_interface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Proxy Study
 * 
 * @author taomk 2017年3月5日 下午9:33:33
 *
 */
public class ProxyStudy {

	public static void main(String[] args) {

		ForumService serviceImpl = new ForumServiceImpl();
		PerformanceHandler handler = new PerformanceHandler(serviceImpl);

		ForumService proxy = (ForumService) Proxy.newProxyInstance(serviceImpl.getClass().getClassLoader(),
				serviceImpl.getClass().getInterfaces(), handler);

		proxy.removeForum("ae78bcg");
		proxy.removeTopic("Ng990xs");
	}

}

/**
 * 业务类接口定义
 * 
 * @author taomk 2017年3月5日 下午9:35:29
 *
 */
interface ForumService {
	void removeTopic(String topicId);

	void removeForum(String forumId);
}

/**
 * 业务类接口实现
 * 
 * @author taomk 2017年3月5日 下午9:36:15
 *
 */
class ForumServiceImpl implements ForumService {

	@Override
	public void removeTopic(String topicId) {

		System.out.println("业务方法，模拟删除Topic记录：" + topicId);

		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void removeForum(String forumId) {

		System.out.println("业务方法，模拟删除Forum记录：" + forumId);

		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}

/**
 * 通过InvocationHandler将业务逻辑和性能监控逻辑交织
 * 
 * @author taomk 2017年3月5日 下午9:48:20
 *
 */
class PerformanceHandler implements InvocationHandler {

	private Object target;

	public PerformanceHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		MethodPerformance performance = new MethodPerformance(target.getClass().getName() + "." + method.getName());
		performance.start();

		Object result = method.invoke(target, args);

		performance.printPerformance();

		return result;
	}

}

/**
 * 性能计算工具类
 * 
 * @author taomk 2017年3月5日 下午9:44:51
 *
 */
class MethodPerformance {

	private long startTime;
	private long endTime;
	private String serviceName;

	MethodPerformance(String serviceName) {
		this.serviceName = serviceName;
	}

	public void start() {
		this.startTime = System.currentTimeMillis();
	}

	public void printPerformance() {
		this.endTime = System.currentTimeMillis();
		long elapse = endTime - startTime;
		System.out.println("[" + serviceName + "]，花费时间（ms）：" + elapse);
	}
}