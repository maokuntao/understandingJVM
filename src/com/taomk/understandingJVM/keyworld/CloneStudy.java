package com.taomk.understandingJVM.keyworld;

/**
 * <code>clone()</code> 关键字研究
 * 
 * @author taomk 2017年2月26日 下午8:50:44
 *
 */
public class CloneStudy  implements Cloneable{

	private String name;
	private String[] address;
	
	CloneStudy(String name ){
		this.name = name;
		this.address = new String[2];
	}
	
	
	public static void main(String[] args) {
		
		CloneStudy a = new CloneStudy("Kobe");
		a.address[0] = "LA";
		a.address[1] = "LA";

		CloneStudy b = (CloneStudy) a.clone();
		b.name = "James";
		b.address[0] = "CL";
		b.address[1] = "Miami";
		
//		b = a;
//		b.name = "James";
		
		System.out.println(a.name + " : " + a.address[0] + " & " + a.address[1]);
		System.out.println(b.name + " : " + b.address[0] + " & " + b.address[1]);
	}

	@Override
	public Object clone(){
		CloneStudy cs = null;
		try {
			cs = (CloneStudy) super.clone();	//浅度clone
			cs.address = address.clone();//深度clone，存在更为复杂的成员变量时，如Vector等存储对象地址的容器时，就必须clone彻底。
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return cs;
	}
}
