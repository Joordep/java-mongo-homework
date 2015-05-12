package aula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexValidator {

	private final Pattern INSERT_PATTERN = RegexFactory.getInsertPattern();
	
//	private final Pattern DELETE_PATTERN = RegexFactory.getDeletePattern();
	
//	private final Pattern UPDATE_PATTERN = RegexFactory.getDeletePattern();
	
//	private final Pattern QUERY_PATTERN = RegexFactory.getDeletePattern();
	
	public Matcher validateInsert(String input) {
		return INSERT_PATTERN.matcher(input);
	}
}
