package com.taomk.understandingJVM.annotation;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

/**
 * 注解处理器
 * 
 * @author taomk 2017-1-29
 *
 */
// 表示对所有的annotations都感兴趣
@SupportedAnnotationTypes("*")
// 表示只支持JDK 1.8的Java代码
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class NameCheckProcessor extends AbstractProcessor {

	private NameChecker nameChecker;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.annotation.processing.AbstractProcessor#init(javax.annotation.
	 * processing.ProcessingEnvironment)
	 */
	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
		// 初始化名称检查插件
		nameChecker = new NameChecker(processingEnv);
	}

	/* (non-Javadoc)
	 * @see javax.annotation.processing.AbstractProcessor#process(java.util.Set, javax.annotation.processing.RoundEnvironment)
	 */
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		if(!roundEnv.processingOver()){
			for (Element typeElement : roundEnv.getRootElements()) {
				// 对输入的语法树的各个节点进行名称检查
				nameChecker.checkName(typeElement);
			}
		}
		return false;
	}

}
