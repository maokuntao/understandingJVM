package com.taomk.understandingJVM.concurrent.lock;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.System.out; 

/**
 * http://blog.csdn.net/kjfcpua/article/details/8541433
 * 
 * @author taomk 2017年6月7日 下午1:34:38
 *
 */
public class TestLocks implements Runnable {

	public enum LockType {
		JVM, JUC
	}

	public static LockType lockType;

	public static final long ITERATIONS = 5L * 1000L * 1000L;
	public static long counter = 0L;

	public static final Object jvmLock = new Object();
//	public static final Lock jucLock = new ReentrantLock(false);
	public static final Lock jucLock = new ReentrantLock(true);
	private static int numThreads;

	private final long iterationLimit;
	private final CyclicBarrier barrier;
	private long localCounter = 0L;

	public long getLocalCounter() {
		return localCounter;
	}

	public TestLocks(final CyclicBarrier barrier, final long iterationLimit) {
		this.barrier = barrier;
		this.iterationLimit = iterationLimit;
	}

	public static void main(final String[] args) throws Exception {
//		lockType = LockType.valueOf("JVM");
		lockType = LockType.valueOf("JUC");
		numThreads = Integer.parseInt("8");

		final long start = System.nanoTime();
		runTest(numThreads, ITERATIONS);
		final long duration = System.nanoTime() - start;

		out.printf("%d threads, duration %,d (ns)\n", numThreads, duration);
		out.printf("%,d ns/op\n", duration / ITERATIONS);
		out.printf("%,d ops/s\n", (ITERATIONS * 1000000000L) / duration);
		out.println("counter = " + counter);
	}

	private static void runTest(final int numThreads, final long iterationLimit) throws Exception {
		CyclicBarrier barrier = new CyclicBarrier(numThreads);
		Thread[] threads = new Thread[numThreads];
		TestLocks[] testLocks = new TestLocks[numThreads];
		for (int i = 0; i < threads.length; i++) {
			testLocks[i] = new TestLocks(barrier, iterationLimit);
			threads[i] = new Thread(testLocks[i]);
		}

		for (Thread t : threads) {
			t.start();
		}

		for (Thread t : threads) {
			t.join();
		}
		for (int i = 0; i < threads.length; i++) {
			out.printf("%d thread, local counter = %,d\n", i, testLocks[i].getLocalCounter());
		}
	}

	@Override
	public void run() {
		try {
			barrier.await();
		} catch (Exception e) {
			// don't care
		}

		switch (lockType) {
		case JVM:
			jvmLockInc();
			break;
		case JUC:
			jucLockInc();
			break;
		}
	}

	private void jvmLockInc() {

		while (true) {
			long count = 0;
			synchronized (jvmLock) {
				++counter;
				count = counter;
			}
			localCounter++;
			if (count >= iterationLimit) {
				break;
			}
		}
	}

	private void jucLockInc() {
		while (true) {
			long count = 0L;
			jucLock.lock();
			try {
				++counter;
				count = counter;
			} finally {
				jucLock.unlock();
			}
			localCounter++;
			if (count >= iterationLimit) {
				break;
			}
		}
	}
}
