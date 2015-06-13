package br.com.goblin.analyzer;

import java.io.File;
import java.nio.CharBuffer;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class GrepTemplate<T> {

    // Pattern used to parse lines
    protected final Pattern linePattern = Pattern.compile(".*\r?\n");
    
	protected abstract Pattern getPattern();
	
	protected abstract Collection<T> getCollectionForResults();
	
	protected abstract void addResult(Collection<T> collectionForResults, File f, int lines, CharSequence cs);
	
	public Collection<T> grep(File f, CharBuffer cb) {
        
        Matcher lm = linePattern.matcher(cb); 
        Matcher pm = null;      
        int lines = 0;
        
        Collection<T> collectionForResults = getCollectionForResults();
        
        while (lm.find()) {
            lines++;
            CharSequence cs = lm.group();   // The current line
            
            if (pm == null) {
                pm = getPattern().matcher(cs);
            } else {
                pm.reset(cs);
            }
            
            if (pm.find()) {
            	addResult(collectionForResults, f, lines, cs);
            }
            
            if (lm.end() == cb.limit()) {
                break;
            }
        }

        return collectionForResults;
    }
	
}
