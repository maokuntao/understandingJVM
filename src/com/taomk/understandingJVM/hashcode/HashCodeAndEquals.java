package com.taomk.understandingJVM.hashcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author taomk 2017年7月6日 下午11:10:31
 *
 */
public class HashCodeAndEquals {

	public static void main(String[] args) {

		Student s1 = new Student("1001", "小红", 12, "Beijing");
		Student s2 = new Student("1002", "小明", 12, "Harbin");
		Student s3 = new Student("1003", "小强", 13, "Tianjin");
		Student s4 = new Student("1001", "小花", 13, "HongKong");

		Set<Student> students = new HashSet<Student>();
		students.add(s1);
		students.add(s2);
		students.add(s3);
		students.add(s4);

		for (Student student : students) {
			System.out.println(student);
		}

		// Student类没有重写hashCode()方法，导致原本相等的两个对象（s1、s3）都放入了集合中

		
		System.out.println("______________________");
		
		Map<Student, String> studentMap = new HashMap<Student, String>();
		studentMap.put(s1, "s1");
		studentMap.put(s2, "s2");
		studentMap.put(s3, "s3");
		studentMap.put(s4, "s4");
		
		Iterator<Student> iter = studentMap.keySet().iterator();
		while(iter.hasNext()){
			Student student = iter.next();
			System.out.println(student + " -> " + studentMap.get(student));
		}
	}

}

class Student {

	// 属性定义
	private String number;
	private String name;
	private int age;
	private String address;

	// 构造函数
	public Student(String number, String name, int age, String address) {
		super();
		this.number = number;
		this.name = name;
		this.age = age;
		this.address = address;
	}

	// 省略getter、setter

	@Override
	public String toString() {
		return "Student [number=" + number + ", name=" + name + ", age=" + age + ", address=" + address + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Student)) {
			return false;
		}

		Student otherStudent = (Student) obj;
		return this.number.equals(otherStudent.number);
	}
}