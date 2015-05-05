package br.com.goblin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDateFormat {
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");
//		String data = "2015-04-29T17:14:05";
//		Date dt = sdf.parse(data);
//		System.out.println(dt);
		
		System.out.println(sdf.format(new Date()));
	}
}
