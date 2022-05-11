/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import entity.CompagnieModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utilis.Statics;

public class ServiceCompagnie {

    public ArrayList<CompagnieModel> compagnies;

    public static ServiceCompagnie instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceCompagnie() {
        req = new ConnectionRequest();
    }

    public static ServiceCompagnie getInstance() {
        if (instance == null) {
            instance = new ServiceCompagnie();
        }
        return instance;
    }
 public boolean deleteCompagnie(int id) {
        String url = Statics.BASE_URL + "/compagnie/delete/comp/" + id;
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.setFailSilently(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;

    }
    public boolean remove(CompagnieModel c) {

        String url = Statics.BASE_URL +"/delete/comp/"+c.getId();
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
     public void addComp (TextField Code_IATA,TextField NomCom,TextField Number,TextField Link,TextField Siege,TextField Pays,TextField AeBase,TextField PassagerNum,TextField Description) {

        String url = Statics.BASE_URL + "/compagnie/addc?Code_IATA="+Code_IATA.getText().toString()+"&NomCom="+NomCom.getText().toString()+"&Link="+Link.getText().toString()+"&Number="+Number.getText().toString()+"&Siege="+Siege.getText().toString()+"&Pays="+Pays.getText().toString()+"&AeBase="+AeBase.getText().toString()+"&PassagerNum="+PassagerNum.getText().toString()+"&Description="+Description.getText().toString();
  
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
      
    }

   
  public boolean edit(CompagnieModel c) {

        String url = Statics.BASE_URL + "/compagnie/edit/comp/"+"&Code_IATA="+c.getCode_IATA()+"&NomCom="+c.getNomCom()+"&Number="+c.getNumber()+"&Link="+c.getLink()+"&Siege="+c.getSiege()+"&Pays="+c.getPays()+"&AeBase="+c.getAeBase()+"&PassagerNum="+c.getPassagerNum()+"&Description="+c.getDescription()+"&id="+c.getId();
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
  public ArrayList<CompagnieModel> getAllCompagnies() {
        ArrayList<CompagnieModel> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/compagnie/get/com";
        System.out.println("===>" + url);
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              JSONParser jsonp ;
               jsonp = new JSONParser();
              
               try{
                   Map<String,Object>mapAeroport = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                  List<Map<String,Object>>  ListOfMaps = (List<Map<String,Object>>) mapAeroport.get("root");     
                  for(Map<String,Object> obj : ListOfMaps){
                      CompagnieModel ar = new CompagnieModel();
                      float id   = Float.parseFloat(obj.get("id").toString());
                      String nom = obj.get("NomCom").toString();
                      String code_iata = obj.get("Code_IATA").toString();
                      String pays = obj.get("Pays").toString();
                      String siege = obj.get("Siege").toString();
                       String aebase = obj.get("AeBase").toString();
                       String description = obj.get("Description").toString();
                     
                      
                      ar.setId((int)id);
                      ar.setNomCom(nom);
                      ar.setCode_IATA(code_iata);
                      ar.setPays(pays);
                      ar.setSiege(siege);
                      ar.setAeBase(aebase);
                      ar.setDescription(description);
                      result.add(ar);
                     
                      
                      
                  }
               
               }catch(Exception ex){
                   ex.printStackTrace();
               }
            }
        });
              NetworkManager.getInstance().addToQueueAndWait(req);
                 System.out.println(result);
              return result;
               
        }  
    
    
////       public ArrayList<CompagnieModel> parseCompagnie(String jsonText) {
////        try {
////            compagnies = new ArrayList<>();
////            JSONParser j = new JSONParser();
////            Map<String, Object> catListJson
////                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
////
////            List<Map<String, Object>> list = (List<Map<String, Object>>) catListJson.get("root");
////          
////            for (Map<String, Object> obj : list) {
////                CompagnieModel t = new CompagnieModel();
////                int id = Integer.parseInt(obj.get("id").toString());
////                t.setId((int) id);
////                t.setCode_IATA(obj.get("Code_IATA").toString());
////                t.setNomCom(obj.get("NomCom").toString());
////                t.setLink(obj.get("Link").toString());
////                int Number = Integer.parseInt(obj.get("Number").toString());
////                t.setNumber((int) Number);
////                t.setPays(obj.get("Pays").toString());
////                t.setSiege(obj.get("Siege").toString());
////                t.setAeBase(obj.get("AeBase").toString());
////                int PassagerNum = Integer.parseInt(obj.get("PassagerNum").toString());
////                t.setPassagerNum((int) PassagerNum);
////                t.setDescription(obj.get("Description").toString());
////               
////              
////
////                compagnies.add(t);
////            }
////
////        } catch (IOException ex) {
////
////        }
////        return compagnies;
////    }
////  
//////
////    public ArrayList<CompagnieModel> getAllCompagnies() {
////        req = new ConnectionRequest();
////        String url = Statics.BASE_URL + "/compagnie/get/com";
////
////        //System.out.println("===>"+url);
////        req.setUrl(url);
////        req.setPost(false);
////        req.addResponseListener(new ActionListener<NetworkEvent>() {
////            @Override
////            public void actionPerformed(NetworkEvent evt) {
////                compagnies = parseCompagnie(new String(req.getResponseData()));
////                req.removeResponseListener(this);
////
////            }
////        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return compagnies;
//    }

}
