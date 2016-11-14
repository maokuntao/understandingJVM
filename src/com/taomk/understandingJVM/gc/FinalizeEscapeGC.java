package com.taomk.understandingJVM.gc;

/**
 * <pre>
 * 一个对象自我拯救的演示
 * 此代码演示了两点：
 * 1，对象可以在被GC时自我拯救
 * 2，这种自救的机会只有一次，因为一个对象的finalize()方法只能被系统自动调用一次
 * </pre>
 * 
 * @author taomk 2016年11月14日 下午4:33:28
 *
 */
public class FinalizeEscapeGC {

	public static FinalizeEscapeGC SAVE_HOOK = null;
	
	public void isAlive(){
		System.out.println("yes , i am still alive. :)");
	}
	
	@Override
	protected void finalize() throws Throwable{
		super.finalize();
		System.out.println("finalize() method exexuted!");
		FinalizeEscapeGC.SAVE_HOOK = this;
	}
	
	public static void main(String[] args) throws Throwable {
		SAVE_HOOK = new FinalizeEscapeGC();
		
//		对象第一次拯救自己成功
		SAVE_HOOK = null;
		System.gc();
//		因为finalize()方法的优先级较低，所有等待500毫秒
		Thread.sleep(500L);
		
		if(SAVE_HOOK != null){
			SAVE_HOOK.isAlive();
		}else{
			System.out.println("no , i am dead. :(");
		}
		
//		对象第二次执行和以上部分相同的代码，但是却自救失败
		SAVE_HOOK = null;
		System.gc();
//		因为finalize()方法的优先级较低，所有等待500毫秒
		Thread.sleep(500L);
		
		if(SAVE_HOOK != null){
			SAVE_HOOK.isAlive();
		}else{
			System.out.println("no , i am dead. :(");
		}
		
	}

}
