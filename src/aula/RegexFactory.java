package aula;

import java.util.regex.Pattern;

/**
 * Creates the regexes used through predefined business rules of input
 *
 */
public class RegexFactory {
	
	/**
	 * INSERT_PATTERN
	 * insert working example: insert {vinho: Chateau Ste, ano: 1997, origem: France, valor: 500.00, qtd: 3}
	 * 
	 * This is pretty huge indeed. No time to make it pretty though XD
	 */
	private static final String INSERT_PATTERN = "(^[a-zA-Z]+)\\s*\\{(\\w+):\\s*([a-zA-Z\\s]+),\\s*(\\w+):\\s*([0-9\\s]+),\\s*(\\w+):\\s*([a-zA-Z\\s]+),\\s*(\\w+):\\s*([0-9.(0-9)\\s]+),\\s*(\\w+):\\s*([0-9]+)\\s*}";
	
	/**
	 * DELETE_PATTERN
	 * TODO
	 */
	private static final String DELETE_PATTERN = "";
	
	/**
	 * UPDATE_PATTERN 
	 * TODO
	 */
	private static final String UPDATE_PATTERN = "";
	
	/**
	 *  QUERY_PATTERN
	 *  TODO
	 */
	private static final String QUERY_PATTERN = "";
	
	static Pattern getInsertPattern() {
		return Pattern.compile(INSERT_PATTERN);
	}
	
	static Pattern getDeletePattern() {
		return Pattern.compile(DELETE_PATTERN);
	}
	
	static Pattern getUpdatePattern() {
		return Pattern.compile(UPDATE_PATTERN);
	}
	
	static Pattern getQueryattern() {
		return Pattern.compile(QUERY_PATTERN);
	}
}
