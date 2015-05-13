package aula;

public final class Messages {
    public static final String WELCOME = "MongoDB para vinhos.";
    
    public static final String WAIT_INPUT = "Digite a operacao desejada:"
       + "\n*help*, *insert*, *delete*, *update*, *query*, *quit*";
    
    public static final String INVALID_INPUT_WARN = "Sua entrada nao eh valida. Digite 'help' para exemplos.";
    		
    public static final String HELP;

    private static final String HELP_INSERT = 
    	"--- insert {"
        + "nome: Chateau Lafite Rothschild, "
        + "ano: 1787,"
        + "origem: Londres"
        + "valor: 500.64"
        + "qtd: 4}";

    private static final String HELP_DELETE = 
        "--- delete {nome: Chateau Lafite Rothschild} (deleta todos vinhos com esse nome)";
        /* TODO?
        + "\n--- delete {ano: 1787} (deleta todos vinhos desse ano)"
        + "\n--- delete {origem: Londres} (deleta todos vinhos dessa localidade)"
        + "\n--- delete {valor: gt _OU_ lt 500} (deleta todos vinhos com preço acima/abaixo do valor)";
        */

    private static final String HELP_UPDATE = 
        + "\n--- update {vinho: Chateau Lafite Rothschild, val: 3000.99} (seta novo valor)";

    private static final String HELP_QUERY =
        "--- query {nome: Super Vinho}";
    
    static {
        HELP = new String().format(
            "Exemplo de inserção:\n%s"
            + "\nExemplos de remoção:\n%s"
            + "\nExemplos de update:\n%s"
            + "\nExemplos de query:\n%s\n"
            + "ou pressione q ou quit para sair", 
                                        HELP_INSERT, 
                                        HELP_DELETE,
                                        HELP_UPDATE,
                                        HELP_QUERY);
   }
}
