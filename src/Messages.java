package Aula;

public final class Messages {
    public static final String WELCOME = "MongoDB para vinhos.";
    
    public static final String WAIT_INPUT = "Digite a operacao desejada:"
       + "\n*help*, *insert*, *delete*, *update*, *query*, *exit*";
    
    public static final String INVALID_INPUT_WARN = "Sua entrada n�o � v�lida. Digite 'help' para exemplos.";
    		
    public static final String HELP;

    private static final String HELP_INSERT = "insert {"
        + "vinho: Château Lafite Rothschild, "
        + "ano: 1787,"
        + "origem: Londres"
        + "valor: 500.64"
        + "qtd: 4}";

    private static final String HELP_DELETE = 
        "delete {vinho: Château Lafite Rothschild} (deleta todos vinhos com esse nome)"
        + "\ndelete {ano: 1787} (deleta todos vinhos desse ano)"
        + "\ndelete {origem: Londres} (deleta todos vinhos dessa localidade)"
        + "\ndelete {valor: gt _OU_ lt 500} (deleta todos vinhos com preço acima/abaixo do valor)";

    private static final String HELP_UPDATE = 
        "update {vinho: Château Lafite Rothschild, add OU rem qtd +3} (aumenta/diminui a quantidade em 3)"
        + "\nupdate {vinho: Château Lafite Rothschild, val 3000.99} (aumenta/diminui a quantidade em 3)";

    private static final String HELP_QUERY =
        "query {ano: 1787} (busca todos os vinhos do ano de 1787)"
        + "\nquery {origem: Londres} (busca todos os vinhos de Londres)"
        + "\nquery {valor: gt _OU_ lt 500.00} (busca todos os vinhos com valor maior/menor que 500)";
    
    static {
        HELP = new String().format(
            "Exemplo de inserção: %s"
            + "\nExemplos de remoção: %s"
            + "\nExemplos de update: %s"
            + "\nExemplos de query: %s\n"
            + "ou pressione q ou quit para sair", 
                                        HELP_INSERT, 
                                        HELP_DELETE,
                                        HELP_UPDATE,
                                        HELP_QUERY);
   }
}
