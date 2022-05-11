/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionEvent;

import com.codename1.ui.events.ActionListener;
import entities.Aeroport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utilis.Statics;

/**
 *
 * @author islem
 */
public class ServiceAeroport {
    public static ServiceAeroport instance = null;
    private ConnectionRequest req;
     public boolean result;
         public ArrayList<Aeroport> aeroports;
    public static ServiceAeroport getInstance(){
        if(instance == null)
            instance = new ServiceAeroport();
        return instance ;}
        
        public ServiceAeroport() {
            req = new ConnectionRequest();
        }
        
        public void ajouteAeroport(Aeroport aeroport){
            
            String url = Statics.BASE_URL+"/AddAeroport?nom_aeroport="+aeroport.getNomAeroport()+"&ville_aeroport="+aeroport.getVilleAeroport()+"&pays="+aeroport.getPays();
            req.setUrl(url);
            req.addResponseListener((a) -> {
            String str = new String(req.getResponseData());
                System.out.println("data == "+str);
        });
            NetworkManager.getInstance().addToQueueAndWait(req);
        }
        
        
     

    public ArrayList<Aeroport> affichageAeroport() {
        ArrayList<Aeroport> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/json/airport";
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
                      Aeroport ar = new Aeroport();
                      float id   = Float.parseFloat(obj.get("idAeroport").toString());
                      String nom = obj.get("nomAeroport").toString();
                      String ville = obj.get("villeAeroport").toString();
                      String pays = obj.get("pays").toString();
                      
                      ar.setIdAeroport((int)id);
                      ar.setNomAeroport(nom);
                      ar.setVilleAeroport(ville);
                      ar.setPays(pays);
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
        
       
    
      
      
      
          public boolean deleteAeroport(int id) {
        String url = Statics.BASE_URL + "/removeAeroport/" + id;
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.setFailSilently(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                result = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;

    }
    
          
          public boolean updateAeroport(Aeroport r, int idreservation) {
        String url =Statics.BASE_URL + "/edit/aeroport/"+r.getIdAeroport()+"?nom_aeroport="+r.getNomAeroport()+"&ville_aeroport="+r.getVilleAeroport()+"&pays="+r.getPays();
        req.setUrl(url);
        req.addArgument("idaeroport", String.valueOf(r.getIdAeroport()));
        req.addArgument("nom_aeroport", r.getNomAeroport());
        req.addArgument("ville_aeroport",r.getVilleAeroport());
        req.addArgument("pays", r.getPays());
       
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                result = req.getResponseCode() == 200;
                req.removeResponseListener(this);

            }
        });
        try {
            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (Exception e) {

        }
        return result;
    }
}
