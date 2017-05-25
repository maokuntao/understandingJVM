package com.taomk.understandingJVM.method_interface;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * <code>Serializable</code>
 * 
 * @author taomk 2017年2月25日 下午9:33:08
 *
 */
public class SerializeStudy extends SerializeStudyParent implements Serializable{

	SerializeStudy(String parentCommonStr) {
		super(parentCommonStr);
	}

	private static final long serialVersionUID = 1L;
	
	/**
	 * 静态变量
	 */
	public static int staticVal = 5;
	
	/**
	 * 普通属性
	 */
	private String commonStr = "abc";
	
	/**
	 * 被<code>transient</code>关键字修饰的属性
	 */
	private transient String transientStr = "123";

	public static void main(String[] args) {
		
		try {
			
			SerializeStudy a = new SerializeStudy("world");
			// 序列化对象到本地文件，此时静态属性staticVal的值是5
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("example.object"));
			out.writeObject(a);
			out.flush();
			out.close();
			
			// 将静态属性的值改为10
			SerializeStudy.staticVal = 10;
			a.commonStr = "xyz";
			
			// 从文件中读取序列化后的对象
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("example.object"));
			SerializeStudy b = (SerializeStudy) in.readObject();
			in.close();
			
			System.out.println(SerializeStudy.staticVal);//序列化保存的是对象的状态，静态变量属于类的状态，因此 序列化并不保存静态变量。
			System.out.println(b.commonStr);
			System.out.println(b.transientStr);
			System.out.println(b.parentCommonStr);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}

}


class SerializeStudyParent{
	public String parentCommonStr = "hello";
	SerializeStudyParent(String parentCommonStr){
		this.parentCommonStr = parentCommonStr;
	}
}
