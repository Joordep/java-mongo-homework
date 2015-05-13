package aula;

import java.util.regex.Pattern;

/**
 * Creates the regexes used through predefined business rules of input
 *
 */
public class RegexFactory {
	
	/**
	 * INSERT_PATTERN
	 * insert example: insert {vinho: Chateau Ste, ano: 1997, origem: France, valor: 500.00, qtd: 3}
	 * 
	 * This is pretty huge indeed. No time to make it pretty though XD
	 */
	private static final String INSERT_PATTERN = "(^[a-zA-Z]+)\\s*\\{(\\w+):\\s*([a-zA-Z\\s]+),\\s*(\\w+):\\s*([0-9\\s]+),\\s*(\\w+):\\s*([a-zA-Z\\s]+),\\s*(\\w+):\\s*([0-9.(0-9)\\s]+),\\s*(\\w+):\\s*([0-9]+)\\s*}";
	
	/**
	 * DELETE_PATTERN
	 * delete example: delete {vinho: Chateau Lafite Rothschild}"
	 */
	private static final String DELETE_PATTERN = "(^[a-zA-Z]+)\\s*\\{(\\w+):\\s*([a-zA-Z\\s]+)\\s*}";
	
	/**
	 * UPDATE_PATTERN 
	 * update example: update {vinho: Château Lafite Rothschild, add OU rem : 3} (aumenta/diminui a quantidade em 3)"
	 */
	private static final String UPDATE_PATTERN = "(^[a-zA-Z]+)\\s*\\{(\\w+):\\s*([a-zA-Z\\s]+)\\s*, (\\w+):\\s*([\\w]+)\\s*}";
	
	/**
	 *  QUERY_PATTERN
	 *  query example: query {ano: 1787} (busca todos os vinhos do ano de 1787)"
	 */
	private static final String QUERY_PATTERN = "(^[a-zA-Z]+)\\s*\\{(\\w+):\\s*([a-zA-Z0-9]+)\\s*}";
	
	static Pattern getInsertPattern() {
		return Pattern.compile(INSERT_PATTERN);
	}
	
	static Pattern getDeletePattern() {
		return Pattern.compile(DELETE_PATTERN);
	}
	
	static Pattern getUpdatePattern() {
		return Pattern.compile(UPDATE_PATTERN);
	}
	
	static Pattern getQueryPattern() {
		return Pattern.compile(QUERY_PATTERN);
	}
}
