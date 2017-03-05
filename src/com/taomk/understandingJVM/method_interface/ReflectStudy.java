package com.taomk.understandingJVM.method_interface;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * reflect 研究
 * @author taomk 2017年3月5日 下午6:15:29
 *
 */
public class ReflectStudy {

	public static void main(String[] args) {
		try {
			Class<?> carClass = Class.forName("com.taomk.understandingJVM.method_interface.PrivateCar");
//			ClassLoader loader = Thread.currentThread().getContextClassLoader();
//			Class<?> carClass = loader.loadClass("com.taomk.understandingJVM.method_interface.PrivateCar");
			
			Constructor<?> constructor = carClass.getDeclaredConstructor(new Class[]{String.class , String.class});
			PrivateCar aRealCar = (PrivateCar) constructor.newInstance("white" , "lily");
			
//			PrivateCar aRealCar = (PrivateCar) carClass.newInstance();
			
//			Field[] fields = carClass.getDeclaredFields();
//			for (Field field : fields) {
//				if(!field.isAccessible()){
//					field.setAccessible(true);
//				}
//				if(field.getName().equals("color")){
//					field.set(aRealCar, "black");
//				}else if(field.getName().equals("owner")){
//					field.set(aRealCar, "taomk");
//				}
//			}
			
//			Field color = carClass.getDeclaredField("color");
//			color.setAccessible(true);
//			color.set(aRealCar, "black");
			
			Method drive = carClass.getDeclaredMethod("drive", (Class[])null);
			drive.setAccessible(true);
			drive.invoke(aRealCar, (Object[])null);
			
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException  | SecurityException | NoSuchMethodException | IllegalArgumentException | InvocationTargetException  e) {
			e.printStackTrace();
		}
	}

}


class PrivateCar{
	
	/**
	 * 属性：车的主人，public 方法，无任何限制
	 */
	public String owner;
	/**
	 * 属性：车的颜色，private 方法，只允许在本类内部使用
	 */
	private String color;
	
	/**
	 * 构造函数
	 * 
	 * @param ownerName
	 * @param color
	 */
	PrivateCar(String ownerName , String color){
		this.owner = ownerName;
		this.color = color;
	}
	
	/**
	 * 方法：开车，protected 方法，允许子类和同包的类使用
	 */
	protected void drive(){
		System.out.println(owner + " driving a " + color + " car ...");
	}
}
