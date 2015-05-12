package aula;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

/**
 *
 */
@SuppressWarnings("unused")
public class Crud {
    
	private boolean running = true;
	
	private RegexValidator regexValidator = new RegexValidator();
	
    public Crud() {
    	/*
    	 * TODO initializate JDBC
    	 */
    }
    
    public void run() {
        // reads input from console
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        while (running) {
        	try {
        		System.out.println(Messages.WAIT_INPUT);
                String userInput = reader.readLine();
                
                HashMap<String, String> queryMap = parseInput(userInput);
                
                for (Map.Entry<String, String> entry : queryMap.entrySet()) {
                	System.out.println(entry.getKey() + " : " + entry.getValue());
                }
                
                
        	} catch (Exception e) {
        		handlesExcp(e);
        	}
        } 
    }
    
    private HashMap<String, String> parseInput(String userInput) throws Exception {
    	HashMap<String, String> queryMap = new HashMap<>();
    	
    	String action = getAction(userInput);
    	
    	Matcher m = null;
		switch (action.toLowerCase().trim()) {
    		case Constants.INSERT:
    			m = regexValidator.validateInsert(userInput);
    			if (m.find()) {
    				queryMap.put(Constants.ACAO, Constants.INSERT);
    				queryMap.put(Constants.NOME_VINHO, m.group(3));
    				queryMap.put(Constants.ANO, m.group(5));
    				queryMap.put(Constants.ORIGEM, m.group(7));
    				queryMap.put(Constants.VALOR, m.group(9));
    				queryMap.put(Constants.QTD, m.group(11));
    			} else {
    				throw new Exception(Constants.INVALID_INPUT);
    			}
    			break;
    		case Constants.DELETE:
    			m = regexValidator.validateDelete(userInput);
    			if (m.find()) {
    				
    			} else {
    				throw new Exception(Constants.INVALID_INPUT);
    			}
    			break;
    		case Constants.UPDATE:
    			m = regexValidator.validateUpdate(userInput);
    			if (m.find()) {
    				
    			} else {
    				throw new Exception(Constants.INVALID_INPUT);
    			}
    			break;
    		case Constants.QUERY:
    			m = regexValidator.validateQuery(userInput);
    			if (m.find()) {
    				
    			} else {
    				throw new Exception(Constants.INVALID_INPUT);
    			}
    			break;
    		case Constants.HELP:
    			System.out.println(Messages.HELP);
    			break;
    		case "Q":
    		case "QUIT":
    			this.running = false;
    			break;
    		default:
    			throw new Exception(Constants.INVALID_INPUT);
    	}
    	return queryMap;
    }
    
    /**
     * insert {vinho: Intenso maria pode, ano: 1978, origem: Londres, valor: 5600.64, qtd: 4}
     */
    private String getAction(String userInput) {
    	return userInput.split("\\{")[0];
    	
    }
    
    private void handlesExcp(Exception e) {
    	if (e.getMessage().equals(Constants.INVALID_INPUT)) {
    		System.out.println(Messages.INVALID_INPUT_WARN);
    	}
    }
    
    public void greeting() {
        System.out.println(Messages.WELCOME);
    }
    
    public static void main(String[] args) {
        Crud myCrud = new Crud();
        myCrud.greeting();
        myCrud.run();
    }
}
