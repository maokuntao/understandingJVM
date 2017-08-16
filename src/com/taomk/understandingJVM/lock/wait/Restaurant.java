package com.taomk.understandingJVM.lock.wait;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 生产者-消费者问题<br>
 * 
 * https://zhuanlan.zhihu.com/p/25474331
 * 
 * @author taomk 2017年8月16日 下午8:50:34
 *
 */
public class Restaurant {

	Meal meal;
	ExecutorService exec = Executors.newCachedThreadPool();
	WaitPerson waitPerson = new WaitPerson(this);
	Chef chef = new Chef(this);

	public Restaurant() {
		exec.execute(chef);
		exec.execute(waitPerson);
	}

	public static void main(String[] args) {
		new Restaurant();
	}
}

/**
 * 物料
 * 
 * @author taomk 2017年8月16日 下午9:01:41
 *
 */
class Meal {

	/**
	 * 编号
	 */
	private final int orderNum;

	public Meal(int orderNum) {
		this.orderNum = orderNum;
	}

	public String toString() {
		return "Meal " + orderNum;
	}
}

/**
 * 消费者
 * 
 * @author taomk 2017年8月16日 下午9:03:55
 *
 */
class WaitPerson implements Runnable {
	
	private Restaurant restaurant;

	public WaitPerson(Restaurant r) {
		this.restaurant = r;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (restaurant.meal == null)
						wait();// ..for the chef to produce a meal
				}
				System.out.println("WaitPerson got " + restaurant.meal);
				synchronized (restaurant.chef) {
					restaurant.meal = null;
					restaurant.chef.notifyAll();// ready for another
				}
			}
			TimeUnit.MICROSECONDS.sleep(100);
		} catch (InterruptedException e) {
			System.out.println("WaitPerson interrupted");
		}
	}
}

/**
 * 生产者
 * 
 * @author taomk 2017年8月16日 下午9:05:05
 *
 */
class Chef implements Runnable {
	
	private Restaurant restaurant;
	private int count = 0;

	public Chef(Restaurant r) {
		this.restaurant = r;
	}

	@Override
	public void run() {
		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (restaurant.meal != null)
						wait();// ...for the meal to be taken
				}
				if (++count == 10) {// produce food , until count > 9
					System.out.println("Out of food,closing");
					restaurant.exec.shutdownNow();
				}
				System.out.println("Order up!");
				synchronized (restaurant.waitPerson) {
					restaurant.meal = new Meal(count);
					restaurant.waitPerson.notifyAll();
				}

			}
		} catch (InterruptedException e) {
		}
	}
}