package aula;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
	
	/**
	 * DB variables 	
	 */
	private MongoClient mongo = null;
	private DB db = null;
	private DBCollection coll = null;
	
    public Crud() {
    	try {
    		mongo = new MongoClient("192.168.1.104", 27017 );
    		List<String> dbs = mongo.getDatabaseNames();
            System.out.println("Bancos de dados disponiveis: " + dbs + "\n");
    		db = mongo.getDB("vinhoDB");
    		coll = db.getCollection("vinho");
    	} catch (Exception e) {
    		e.printStackTrace();
    		System.exit(1);
    	}
    }
    
    private void insert(HashMap<String, String> obj) throws Exception {
    	BasicDBObject doc = getDBObject(obj);
    	coll.insert(doc);
    }
    
    private void delete(HashMap<String, String> obj) throws Exception {
    	BasicDBObject doc = getDBObject(obj);
    	coll.remove(doc);
    }
    
    /**
     * updates a database find
     * 
     * currently only finding by name and updating quantities xD
     * @param obj
     * @throws Exception
     */
    private void update(HashMap<String, String> obj) throws Exception {
    	BasicDBObject doc = new BasicDBObject(Constants.NAME, obj.get(Constants.NAME));
    	coll.update(doc, new BasicDBObject("$set", new BasicDBObject(Constants.QTT, obj.get(Constants.QTT))));
    }
    
    private void query(HashMap<String, String> obj) throws Exception {

    }
    
    /**
     * Generates a new DB Object from the hashmap of data
     * @param obj
     * @return
     */
    private BasicDBObject getDBObject(HashMap<String, String> obj) {
    	BasicDBObject doc = new BasicDBObject();
    	for (Map.Entry<String, String> entry : obj.entrySet()) {
    		if (!entry.getKey().equals(Constants.ACTION)) {
    			doc.append(entry.getKey(), entry.getValue());
    		}
        }
    	return doc;
    }
    
    /**
     * Main loop
     */
    public void run() {
        // reads input from console
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        while (running) {
        	try {
        		System.out.println(Messages.WAIT_INPUT);
                String userInput = reader.readLine();
                
                // ideally this should be an object, I'll get to it if I get time xD 	
                HashMap<String, String> queryMap = parseInput(userInput);
                
                switch (queryMap.get(Constants.ACTION)) {
                case Constants.INSERT:
                	insert(queryMap);
                	System.out.printf("Vinho %s adicionado com sucesso!\n\n", queryMap.get(Constants.NAME));
                	break;
                case Constants.DELETE:
                	delete(queryMap);
                	System.out.printf("Vinho %s removido com sucesso!\n\n", queryMap.get(Constants.NAME));
                	break;
                case Constants.UPDATE:
                	update(queryMap);
                	System.out.printf("Vinho %s updateado com sucesso!\n\n", queryMap.get(Constants.NAME));
                	break;
                case Constants.QUERY:
                	query(queryMap);
                	break;
                case Constants.HELP:
                	System.out.println(Messages.HELP);
                	break;
                case Constants.EXIT:
                	this.running = false;
                	break;
                default:
                	break;
                }
//                for (Map.Entry<String, String> entry : queryMap.entrySet()) {
//                	System.out.println(entry.getKey() + " : " + entry.getValue());
//                }
                
                
        	} catch (Exception e) {
        		handlesExcp(e);
        	}
        } 
    }
    
    /**
     * Parses user input and place it into a hashmap containing the respective
     * keys and values.
     * 
     * Unfortunately, this is still not as generic as I'd like, since you need to 
     * pass the arguments in the right order
     * @param userInput
     * @return hashmap containing the object keys and values 
     * @throws Exception
     */
    private HashMap<String, String> parseInput(String userInput) throws Exception {
    	HashMap<String, String> queryMap = new HashMap<>();
    	
    	String action = getAction(userInput);
    	
    	Matcher m = null;
		switch (action.toLowerCase().trim()) {
		case Constants.INSERT:
			m = regexValidator.validateInsert(userInput);
			if (m.find()) {
				queryMap.put(Constants.ACTION, Constants.INSERT);
				queryMap.put(Constants.NAME, m.group(3));
				queryMap.put(Constants.YEAR, m.group(5));
				queryMap.put(Constants.PLACE_OF_ORIGIN, m.group(7));
				queryMap.put(Constants.COST, m.group(9));
				queryMap.put(Constants.QTT, m.group(11));
			} else {
				throw new Exception(Constants.INVALID_INPUT);
			}
			break;
		case Constants.DELETE:
			m = regexValidator.validateDelete(userInput);
			if (m.find()) {
				queryMap.put(Constants.ACTION, Constants.DELETE);
				
				// currently only supporting deletes by name 
				// TODO add delete by price (>500) || (<300) - update regexValidator.validateDelete
				// TODO REFACTOR - pass to specialized method
				switch(m.group(2)) {
				case Constants.NAME:
					queryMap.put(Constants.NAME, m.group(3));
					break;
				case Constants.COST:
					// TODO
					break;
				}
			} else {
				throw new Exception(Constants.INVALID_INPUT);
			}
			break;
		case Constants.UPDATE:
			m = regexValidator.validateUpdate(userInput);
			if (m.find()) {
				queryMap.put(Constants.ACTION, Constants.UPDATE);
				queryMap.put(Constants.NAME, m.group(3));
				
				// same as delete -- currently only supporting deletes by qtt 
				// TODO add change cost - update regexValidator.validateUpdate
				// TODO REFACTOR - pass to specialized method
				switch(m.group(4)) {
				case Constants.QTT:
					queryMap.put(Constants.QTT, m.group(5));
					break;
				case Constants.COST:
					// TODO
					break;
				}
			} else {
				throw new Exception(Constants.INVALID_INPUT);
			}
			break;
		case Constants.QUERY:
			m = regexValidator.validateQuery(userInput);
	    	//"--- query {ano: 1787} (busca todos os vinhos do ano de 1787)"
			if (m.find()) {
				
			} else {
				throw new Exception(Constants.INVALID_INPUT);
			}
			break;
		case Constants.HELP:
			queryMap.put(Constants.ACTION, Constants.HELP);
			break;
		case "q":
		case "quit":
			queryMap.put(Constants.ACTION, Constants.EXIT);
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
