/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.goblin.analyzer;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;

import javax.persistence.Column;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;

/**
 *
 * @author aeloy
 */
public class AnnotationAnalyzer {

	/**
	 * Retrieves all java files from declared directory
	 * @param dir declared directory for file search
	 * @return
	 */
	public Collection<File> getAllClassesFromDir(File dir) {

		// retrieves all .java files but AnnotationAnalyzer.java
		IOFileFilter notCurrentClass = FileFilterUtils
				.notFileFilter(FileFilterUtils
						.nameFileFilter("AnnotationAnalyzer.java"));
		IOFileFilter javaFiles = FileFilterUtils.suffixFileFilter(".java");

		// list all files using file filters
		return FileUtils.listFiles(dir,
				FileFilterUtils.and(notCurrentClass, javaFiles),
				FileFilterUtils.trueFileFilter());

	}

	@SuppressWarnings("unchecked")
	public Collection<Grep.GrepDetail> grepClassesWith(
			Class<? extends Annotation> annotation) throws IOException {

		Grep<Grep.GrepDetail> columnGrep = Grep.compile(new GrepWithDetails(
				Pattern.compile(annotation.getName())));
		AnnotationAnalyzer analyzer = new AnnotationAnalyzer();

		File root = new File(System.getProperty("user.dir"));
		Collection<Grep.GrepDetail> entitiesWithColumns = new ArrayList<>();

		for (File file : analyzer.getAllClassesFromDir(root)) {
			Collection<Grep.GrepDetail> found = columnGrep.grep(file);
			entitiesWithColumns = CollectionUtils.union(entitiesWithColumns,
					found);
		}

		return entitiesWithColumns;
	}

	/**
	 * Load className without methods and fields that does not contains <code>@Column</code> annotation
	 * @param className canonical class name.
	 * @return a modified loaded class 
	 */
	public Class<?> loadSimpleClass(String className) {
		ClassPool pool = ClassPool.getDefault();

		try {
			CtClass cc = pool.get(className);
			CtMethod[] methods = cc.getDeclaredMethods();

			for (CtMethod method : methods) {
				cc.removeMethod(method);
			}

			CtField[] fields = cc.getDeclaredFields();
			
			for (CtField field : fields) {
				if (!field.hasAnnotation(Column.class)) {
					cc.removeField(field);
				}
			}
			
			return cc.toClass();
		} catch (NotFoundException | CannotCompileException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Usage example for AnnotationAnalyzer
	 * @param args
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws IOException,
			ClassNotFoundException {
		AnnotationAnalyzer analyzer = new AnnotationAnalyzer();
		Collection<Grep.GrepDetail> found = analyzer
				.grepClassesWith(Column.class);

		for (Grep.GrepDetail detail : found) {
			Collection<String> packageDetail = Grep.compile(
					new GrepClassPackage(Pattern.compile("package"))).grep(
					detail.getFile());

			String pkg = packageDetail.iterator().next();
			String className = pkg + "." + detail.getClassName();
			Class<?> clazz = analyzer.loadSimpleClass(className);
			System.out.println(clazz.getDeclaredMethods().length);
		}

	}

}
