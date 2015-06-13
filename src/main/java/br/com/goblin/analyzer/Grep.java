package br.com.goblin.analyzer;

/*
 * Copyright (c) 2001, 2014, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Collection;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;

/* Search a list of files for lines that match a given regular-expression
 * pattern.  Demonstrates NIO mapped byte buffers, charsets, and regular
 * expressions.
 */
public class Grep<T> {

    // Charset and decoder for ISO-8859-15
    private final Charset charset = Charset.forName("ISO-8859-15");
    private final CharsetDecoder decoder = charset.newDecoder();

    private GrepTemplate<T> grepTemplate;
    
    private Grep(GrepTemplate<T> template) {
        
        try {
        	grepTemplate = template;
        } catch (PatternSyntaxException x) {
            System.err.println(x.getMessage());
            System.exit(1);
        }
        
    }
    
    // Compile the pattern from the command line
    //
    public static <T> Grep<T> compile(GrepTemplate<T> template) {
        return new Grep<T>(template);
        
    }

    // Search for occurrences of the input pattern in the given file
    //
    public Collection<T> grep(File f) throws IOException {

        // Open the file and then get a channel from the stream
        try (FileInputStream fis = new FileInputStream(f);
             FileChannel fc = fis.getChannel()) {
            
            // Get the file's size and then map it into memory
            int sz = (int) fc.size();
            MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, sz);

            // Decode the file into a char buffer
            CharBuffer cb = decoder.decode(bb);

            // Perform the search
            return grepTemplate.grep(f, cb);
            
        }

    }

    public static class GrepDetail {
        
        @Getter
        private File file;
        
        @Getter
        private int lineNumber;
        
        @Getter
        private String content;
        
        public GrepDetail(File file, int lineNumber, String content) {
            this.file = file;
            this.lineNumber = lineNumber;
            this.content = content;
        }
        
        public String getClassName() {
        	
        	if (file != null) {
        		String fileName = file.getName();
        		return StringUtils.strip(fileName, ".java");
        	}
        	
        	return null;
        }
    }
    
}
