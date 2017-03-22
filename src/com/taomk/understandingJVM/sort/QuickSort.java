package com.taomk.understandingJVM.sort;

/**
 * 快速排序
 * 
 * @author taomk 2017年3月16日 上午11:03:18
 *
 */
public class QuickSort {

	public static void main(String[] args) {
		
		int[] array = {3,5,7,9,13,2,4,6,7,1,7,16,18};
		
		array = sort(array, 0, array.length-1);
		
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " , ");
		}
	}

	public static int partition(int[] array, int lo, int hi) {
		
		// 固定的切分方式
		int key = array[lo];
		while (lo < hi) {
			while (array[hi] >= key && hi > lo) {// 从后半部分向前扫描
				hi--;
			}
			array[lo] = array[hi];
			while (array[lo] <= key && hi > lo) {// 从前半部分向后扫描
				lo++;
			}
			array[hi] = array[lo];
		}
		array[hi] = key;
		return hi;
	}

	public static int[] sort(int[] array, int lo, int hi) {
		
		if (lo >= hi) {
			return null;
		}
		
		int index = partition(array, lo, hi);
		
		sort(array, lo, index - 1);
		
		sort(array, index + 1, hi);
		
		return array;
	}

}
