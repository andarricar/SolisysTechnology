//Télécharger un JDBC driver pour PostgreSQL
// Ici, version 42.2.12

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;



//executeQuery() : requête de lecture
//executeUpdate() : requête insert, update ou delete
//CTRL + SHIFT + O pour générer les imports

public class ConnexionDriver {
    public static void main(String[] args) {  

      //Chargement du driver JDBC     
      try {
        Class.forName("org.postgresql.Driver");
        System.out.println("Driver OK");
      } catch (ClassNotFoundException e){
        System.out.println("Driver introuvable");
      }
      
      //Conexion à PostgreSQL
      String url = "jdbc:postgresql://localhost:5432/Solisys";
      String user = "postgres";
      String passwd = "isima";
      
      Connection connexion = null;
      Statement statement = null;
      ResultSet resultat = null;
      int requete = 0;

      try{
        System.out.println("Connexion a la bdd...");
        connexion = DriverManager.getConnection(url, user, passwd);
        System.out.println("Connexion reussie !");    
        
        //Création de l'objet gérant les requêtes
        statement = connexion.createStatement();   
        
        //Exécution d'une requête de lecture
        resultat = statement.executeQuery( "SELECT id, createdat FROM entity;" );

    
        //Récupération des données du résultat de la requête de lecture
      while ( resultat.next() ) {
        long id = resultat.getLong( "id" );
        Date createdAt = resultat.getDate("createdat");
        
       // Traiter ici les valeurs récupérées
       System.out.println("Requete de lecture :");
       System.out.println("id = " + id + " createdAt = " + createdAt);
      }

      //Exécution d'une requête 
      // UPDATE/DELETE: requete = nb de lignes ajoutées/supprimées
      // INSERT: requete = 0 si échec, 1 sinon
      
      // Test insertion dans la table Entity
      requete = statement.executeUpdate( "INSERT INTO Entity (id, createdat) VALUES (7, '2020-08-07')");
      if (requete == 1)
        System.out.println("INSERT fait");
      else 
        System.out.println("echec de l'INSERT");
      

      // Test insertion dans la table Device
      requete = statement.executeUpdate( "INSERT INTO Device (id, createdat, uuid, lastemit, countemit, disabletag, addressip) VALUES (7, '2020-08-07', 1, '2020-06-08', 5, true, '93.0.0.8')");
      if (requete == 1)
        System.out.println("INSERT fait");
      else 
        System.out.println("echec de l'INSERT");
      

      // Test suppression dans la table Entity
      requete = statement.executeUpdate("DELETE FROM entity WHERE id = 1");
      if (requete == 1)
        System.out.println("DELETE fait");
      else 
        System.out.println("echec du DELETE");


      // Test insertion de la table Company
      requete = statement.executeUpdate( "INSERT INTO company (id, createdat, name) VALUES (7, '2020-08-07', 'Solysis')");
      if (requete == 1)
        System.out.println("INSERT fait");
      else 
        System.out.println("echec de l'INSERT");


      // Test mise à jour de la table Company
            requete = statement.executeUpdate( "UPDATE company SET name = 'Technology' WHERE name = 'Solysis'");
      if (requete == 1)
        System.out.println("UPDATE fait");
      else 
        System.out.println("echec de l'UPDATE");
      
      // Attention !! Je n'ai pas géré les différentes erreurs
      }catch (Exception e) { 

          //Erreur 
          e.printStackTrace();
          System.out.println("Erreur");
        } finally {
          //Fermeture de resultat
          if (resultat !=null){
            try {
              resultat.close();
            } catch (SQLException ignore) {}
          }

          //Fermetue du statement
          if (statement !=null){
            try {
              statement.close();
            } catch (SQLException ignore) {}
          }

          //Déconnexion
          if (connexion !=null){
            try {
              connexion.close();
            } catch (SQLException ignore) {}
          }
        }
  }
}  