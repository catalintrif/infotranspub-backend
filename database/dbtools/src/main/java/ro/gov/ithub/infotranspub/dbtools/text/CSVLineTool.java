package ro.gov.ithub.infotranspub.dbtools.text;

import java.util.List;
import java.util.ArrayList;

public class CSVLineTool{
	static private String REGEX_COMMA_OR_QUOTES = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
	
	public static String[]  quoteAwareSplit(String line){
		return line.split(REGEX_COMMA_OR_QUOTES, -1);
	}

	public static String simpleQuoteRepeate(String line) {
		return line.replaceAll("'","''");
	}

	static public List<String> trimElements(String[] array) {
		List<String> trimmedArray = new ArrayList<String>();
		for (String cell:array){
			trimmedArray.add(cell.trim());
		}
		return trimmedArray;
	}

	static public List<String>  growArrayList(List<String> array, int growthFactor){
		List<String> grownArray = new ArrayList<String>(array);
		while ( growthFactor-- > 0) {
			grownArray.add("");
		}
		return grownArray;
	}

	static public String[] toStringArray(List<String> array) {
		String[] result = new String[array.size()];
		return array.toArray(result);
	} 	
}
