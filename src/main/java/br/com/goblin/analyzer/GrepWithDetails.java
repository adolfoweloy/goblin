package br.com.goblin.analyzer;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;

import br.com.goblin.analyzer.Grep.GrepDetail;

public class GrepWithDetails extends GrepTemplate<GrepDetail> {

	private Pattern pattern;
	 
	public GrepWithDetails(Pattern pattern) {
		this.pattern = pattern;
	}
	
	@Override
	protected Pattern getPattern() {
		return pattern;
	}

	@Override
	protected Collection<GrepDetail> getCollectionForResults() {
		return new ArrayList<>();
	}

	@Override
	protected void addResult(Collection<GrepDetail> collectionForResults,
			File f, int lines, CharSequence cs) {
		collectionForResults.add(new GrepDetail(f, lines, cs.toString()));
	}

}
