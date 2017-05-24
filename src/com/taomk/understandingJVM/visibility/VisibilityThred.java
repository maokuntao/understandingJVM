package com.taomk.understandingJVM.visibility;

/**
 *  <pre>
 *  测试线程可见性问题
 *  <a>http://ifeve.com/concurrency-visibility/</a>
 *  </pre>
 *  
 * @author taomk 2017年5月24日 下午9:58:33
 *
 */
public class VisibilityThred extends Thread{

	private boolean stop = false;
	
	@Override
	public void run() {
		int count = 0;
		System.out.println("开始计数：" + count);
		while(!stop){
			count++;
		}
		System.out.println("结束计数：" + count);
	}
	
	/**
	 * 停止计数
	 */
	public void stopCount(){
		this.stop = true;
	}
	
	/**
	 * @return true-已停止计数；false-未停止计数
	 */
	public boolean isStop(){
		return stop;
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		VisibilityThred t = new VisibilityThred();
		t.start();
		
		// 确保测试线程已启动
		Thread.sleep(1000);
		
		t.stopCount();
		
		// 确保测试线程执行完毕
		Thread.sleep(1000);
		
		System.out.println("计数线程中的stop值为：" + t.isStop());
		
	}

}
