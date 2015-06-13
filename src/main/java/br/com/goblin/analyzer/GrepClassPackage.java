package br.com.goblin.analyzer;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class GrepClassPackage extends GrepTemplate<String> {
	
	private final Pattern pattern;
	
	public GrepClassPackage(Pattern pattern) {
		this.pattern = pattern;
	}
	
	@Override
	protected Pattern getPattern() {
		return pattern;
	}

	@Override
	protected Collection<String> getCollectionForResults() {
		return new HashSet<>();
	}

	@Override
	protected void addResult(Collection<String> collectionForResults, File f,
			int lines, CharSequence cs) {
		String content = cs.toString();
		String pkg = content.split("\\s")[1];
		pkg = StringUtils.stripEnd(pkg, ";");
		collectionForResults.add(pkg);
	}

}
