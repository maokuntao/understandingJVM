package com.taomk.understandingJVM.forfun;

/**
 * @author taomk 2017年6月19日 下午9:04:45
 *
 */
public class Test {

	public static void main(String[] args) {
		
		String a = "123";
		change(a);
		System.out.println(a);
		
		int c = 123;
		change(c);
		System.out.println(c);
		
		Student s = new Student();
		s.setName("Tom");
		s.setAge(18);
		change(s);
		System.out.println(s);
	}

	public static String change(String target){
		target = "welcome";
		return target;
	}
	
	public static int change(int target){
		target = 233;
		return target;
	}
	
	public static Student change(Student target){
		target.setName("Bob");
		target.setAge(20);
		return target;
	}
}

class Student{
	
	String name;
	int age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + "]";
	}
	
}
