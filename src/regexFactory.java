package Aula;

import java.util.regex.Pattern;

public class RegexFactory {
	
	/**
	 * INSERT_PATTERN
	 * insert working example: insert {vinho: Chateau Ste, ano: 1997, origem: France, valor: 500.00, qtd: 3}
	 */
	private static final String INSERT_PATTERN = "(^[a-zA-Z]+)\\s*\\{(\\w+):\\s*([a-zA-Z\\s]+),\\s*(\\w+):\\s*([0-9\\s]+),\\s*(\\w+):\\s*([a-zA-Z\\s]+),\\s*(\\w+):\\s*([0-9.(0-9)\\s]+),\\s*(\\w+):\\s*([0-9]+)\\s*}";
	
	/**
	 * DELETE_PATTERN
	 * 
	 */
	
	/**
	 * UPDATE_PATTERN 
	 * 
	 */
	
	/**
	 *  QUERY_PATTERN
	 *  
	 */
	
	static Pattern getInsertPattern() {
		return Pattern.compile(INSERT_PATTERN);
	}
	
	static Pattern getDeletePattern() {
		return Pattern.compile(INSERT_PATTERN);
	}
	
	static Pattern getUpdatePattern() {
		return Pattern.compile(INSERT_PATTERN);
	}
	
	static Pattern getQueryattern() {
		return Pattern.compile(INSERT_PATTERN);
	}
}
