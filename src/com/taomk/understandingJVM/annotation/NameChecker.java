package com.taomk.understandingJVM.annotation;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementScanner8;

/**
 * 命名检查器实现
 * 
 * @author taomk 2017-1-29
 *
 */
public class NameChecker {

	private final Messager messager;
	
	NameCheckScanner nameCheckScanner = new NameCheckScanner();
	
	public NameChecker(ProcessingEnvironment processingEnv) {
		this.messager = processingEnv.getMessager();
	}

	/**
	 * 委托给nameCheckScanner完成对命名的检查
	 * 
	 * @param typeElement
	 */
	public void checkName(Element typeElement) {
		nameCheckScanner.scan(typeElement);
	}

	private class NameCheckScanner extends ElementScanner8<Void, Void>{
		
		@Override
		public Void visitType(TypeElement e, Void p) {
			scan(e.getTypeParameters(), p);
			checkCamelCase(e, true);
			super.visitType(e, p);
			return null;
		}

		/**
		 * 检查传入的Element是否符合驼峰命名法则，如果不符合，则输出警告信息
		 * @param e
		 * @param b
		 */
		private void checkCamelCase(TypeElement e, boolean initialCaps) {
			String name = e.getSimpleName().toString();
			@SuppressWarnings("unused")
			boolean previousUpper = false;
			boolean conventional = true;
			int firstCodePoint = name.codePointAt(0);
			
			if(Character.isUpperCase(firstCodePoint)){
				previousUpper = true;
				if(!initialCaps){
					messager.printMessage(javax.tools.Diagnostic.Kind.WARNING, "名称“"+name+"”应当以小写字母开头", e);
					return;
				}
			}else if(Character.isLowerCase(firstCodePoint)){
				if(initialCaps){
					messager.printMessage(javax.tools.Diagnostic.Kind.WARNING, "名称“"+name+"”应当以大写字母开头", e);
					return;
				}
			}else{
				conventional = false;
			}
			
			if(conventional){
				int cp = firstCodePoint;
				for (int i = Character.charCount(cp); i < name.length(); i+=Character.charCount(cp)) {
					cp = name.codePointAt(i);
					if(Character.isUpperCase(cp)){
						conventional = false;
						break;
					}
					previousUpper = false;
				}
			}
			
			if(!conventional){
				messager.printMessage(javax.tools.Diagnostic.Kind.WARNING, "名称“"+name+"”应当符合驼峰式命名规则（Camel Case Names）", e);
				return;
			}
		}
	}
	
}
