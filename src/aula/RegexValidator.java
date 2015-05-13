package aula;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class validates common inputs of this program through predefined regexes 
 * made in the RegexFactory class
 *
 */
public class RegexValidator {

	private final Pattern INSERT_PATTERN = RegexFactory.getInsertPattern();
	
	private final Pattern DELETE_PATTERN = RegexFactory.getDeletePattern();
	
	private final Pattern UPDATE_PATTERN = RegexFactory.getUpdatePattern();
	
	private final Pattern QUERY_PATTERN = RegexFactory.getQueryPattern();
	
	public Matcher validateInsert(String input) {
		return INSERT_PATTERN.matcher(input);
	}
	
	public Matcher validateDelete(String input) {
		return DELETE_PATTERN.matcher(input);
	}
	
	public Matcher validateUpdate(String input) {
		return UPDATE_PATTERN.matcher(input);
	}
	
	public Matcher validateQuery(String input) {
		return QUERY_PATTERN.matcher(input);
	}
}
