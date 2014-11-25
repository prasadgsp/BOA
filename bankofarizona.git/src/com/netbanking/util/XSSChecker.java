package com.netbanking.util;
import java.util.regex.Pattern;

public class XSSChecker {
	
	
public static String stripXSS(String input) {
		
		//REFERENCE LINK USED ------ http://www.javacodegeeks.com/2012/07/anti-cross-site-scripting-xss-filter.html
		
		        if (input != null) {
		        	
		            input = input.replaceAll("", "");
		            
		            Pattern pattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
		    		
		            input = pattern.matcher(input).replaceAll("");
		
		            pattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
		
		            input = pattern.matcher(input).replaceAll("");
		            
		            pattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
		    		
		            input = pattern.matcher(input).replaceAll("");
		
		            pattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		
		            input = pattern.matcher(input).replaceAll("");
		
		            pattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
	
		            input = pattern.matcher(input).replaceAll("");
	
		            pattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		
		            input = pattern.matcher(input).replaceAll("");
		
		            pattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		
		            input = pattern.matcher(input).replaceAll("");
		
		            pattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		
		            input = pattern.matcher(input).replaceAll("");
		
		            pattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		
		            input = pattern.matcher(input).replaceAll("");
	
		
		            pattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
		
		            input = pattern.matcher(input).replaceAll("");
		
		        }
		
		        return input;
		
		    }
		

}
