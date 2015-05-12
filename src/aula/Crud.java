package aula;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;

/**
 *
 * @author 123244
 */
public class Crud {
    
	private boolean running = true;
	
	/**
	 * Vari�vel usada para validar as entradas do usu�rio
	 */
	private RegexValidator regexValidator = new RegexValidator();
	
    public Crud() {

    }
    
    /* regex:
     * 	String line = "insert {vinho: Intenso maria pode, ano: 1978, origem: Londres, valor: 5600.64, qtd: 4}";
	    String pattern = "(^[a-zA-Z]+) \\{(\\w+): (.+), (\\w+): (.+), (\\w+): (.+), (\\w+): (.+)}";
	    Pattern r = Pattern.compile(pattern);
	    
	    Matcher m = r.matcher(line);
	    
	    if (m.find( )) {
	         System.out.println("Found value: " + m.group(0) );
	         System.out.println("Found value: " + m.group(1) );
	         System.out.println("Found value: " + m.group(2) );
	         System.out.println("Found value: " + m.group(3) );
	         System.out.println("Found value: " + m.group(4) );
	         System.out.println("Found value: " + m.group(5) );
	         System.out.println("Found value: " + m.group(6) );
	         System.out.println("Found value: " + m.group(7) );
	         System.out.println("Found value: " + m.group(8) );
	         System.out.println("Found value: " + m.group(9) );
	      } else {
	         System.out.println("NO MATCH");
	    }
     */
    
    public void run() {
        // reads output from console
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        while (running) {
        	try {
        		System.out.println(Messages.WAIT_INPUT);
                String userInput = reader.readLine();
                
                ArrayList<String> data = parseInput(userInput);
                for (String e: data) {
                	System.out.println(e);
                }
                
        	} catch (Exception e) {
        		handlesExcp(e);
        	}
        } 
    }
    
    private void handlesExcp(Exception e) {
    	 if (e.getMessage().equals(Constants.INVALID_INPUT)) {
			 System.out.println(Messages.INVALID_INPUT_WARN);
         }
    }
    
    private ArrayList<String> parseInput(String userInput) throws Exception {
    	ArrayList<String> mArray = new ArrayList<String>();
    	
    	String action = getAction(userInput);
    	
		switch (action.toUpperCase()) {
    		case Constants.INSERT:
    			break;
    		case Constants.DELETE:
    			break;
    		case Constants.UPDATE:
    			break;
    		case Constants.QUERY:
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
    	return mArray;
    }
    
    /**
     * insert {vinho: Intenso maria pode, ano: 1978, origem: Londres, valor: 5600.64, qtd: 4}
     */
    private String getAction(String userInput) {
    	return userInput.split("\\{")[0];
    	
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
