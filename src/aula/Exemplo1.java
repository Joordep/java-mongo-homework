/*
Exemplo 1 acesso ao MongoDB
Prof. José Maurício Carré Maciel (2015)

requer mongo-java-driver 2.13.1

Para configurar o servidor MongoDB para permitor acesso externo:
editar o arquivo /etc/mongosmongod.conf e setar a linha 

bind_ip = 0.0.0.0 (default é 127.0.0.1)

*/
package aula;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import java.util.List;
import java.util.Set;

public class Exemplo1 {
    public static void main(String[] args) {
        try {
            MongoClient mongo = new MongoClient( "192.168.56.101" , 27017 ); 
            
            //obtem lista dos bancos de dados 
            List<String> dbs = mongo.getDatabaseNames();
            System.out.println("Bancos de dados disponiveis: " + dbs + "\n");
            
            //seleciona DB
            DB db = mongo.getDB( "javaDB" ); 
                        
            // obtem nomes das collections no DB selecionado
            Set<String> collections = db.getCollectionNames();
            System.out.println("Colecoes do database: " + collections + "\n");
           
            //seleciona uma colecao
            DBCollection coll = db.getCollection("novaColecao");            

            // inserindo documentos em uma nova collection
            BasicDBObject doc = new BasicDBObject("name", "MongoDB")
                                    .append("type", "database")
                                    .append("count", 1)
                                    .append("info", new BasicDBObject("x", 203).append("y", 102));
            coll.insert(doc);
            
            /* 
            {   "name" : "MongoDB" ,
                "type" : "database" ,
                "count" : 1 ,
                "info" : { 
                            "x" : 203 , 
                            "y" : 102
                         }
            }
            */
            doc = new BasicDBObject("name", "Cassandra")
                      .append("type", "database")
                      .append("count", 1)
                      .append("info", new BasicDBObject("w", 878).append("z", 999));
            coll.insert(doc);                                
                      
            //obtem o primeiro objeto armazenado
            System.out.println("Resultado findOne(): " + coll.findOne().toString());
            System.out.println("");
            
            // obter o resultado de uma consulta
            BasicDBObject query = new BasicDBObject("name", "Cassandra");
            DBCursor cursor = coll.find(query);
            System.out.println("Resultado consulta: name = 'Cassandra'");
            try {
                    while(cursor.hasNext()) {
                        System.out.println(cursor.next());
                    }             
            } finally {
                    cursor.close();
            }             
            
            //consulta por valor em um array
            System.out.println("Resultado consulta objeto com elemento array x  > 200");
            query = new BasicDBObject("info.x", new BasicDBObject("$gt", 200));

            cursor = coll.find(query);
            try {
                while (cursor.hasNext()) {
                    System.out.println(cursor.next());
                }
            } finally {
                cursor.close();
            }
            
            /*  modificar dados de um objeto 
                argumento (1) criterio de selecao de dados, 
                          (2) modificacoes a aplicar 
            */
            coll.update(new BasicDBObject("name","MongoDB"), 
                        new BasicDBObject("$set", new BasicDBObject("type","noSQL Database")));
            
            //excluir dados de um objeto
            System.out.println("Remove todos os objetos da collection");
            query = new BasicDBObject();
            //equivale a db.colecao.remove({});
            coll.remove(query);
            
            // conta objetos na colecao
            System.out.println("Objetos na colecao: " + coll.count() + "\n");
            
        } catch (Exception ex) {
             ex.printStackTrace();
        }
    }
}