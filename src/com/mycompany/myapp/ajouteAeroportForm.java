/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import Service.ServiceAeroport;
import com.codename1.components.InfiniteProgress;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entities.Aeroport;





/**
 *
 * @author islem
 */
public class ajouteAeroportForm extends Inbox1Form {
  Form current;

    public ajouteAeroportForm(Resources res) {
        Toolbar tbar = new Toolbar(true);
        current = this;
        setToolbar(tbar);
        getTitleArea().setUIID("Container");
        setTitle("Ajouter reservation");
        getContentPane().setScrollVisible(false);
        
 
        

        
        
        
        
        
        

       TextField nom_aeroport = new TextField("","entrer nom aeroport !");
        nom_aeroport.setUIID("TextFieldBlack");
        addStringValue("Nom",nom_aeroport);
        
         TextField ville_aeroport = new TextField("","entrer ville aeroport !");
        ville_aeroport.setUIID("TextFieldBlack");
        addStringValue("Ville",ville_aeroport);
        
         TextField pays = new TextField("","entrer pays aeroport !");
        pays.setUIID("TextFieldBlack");
        addStringValue("Pays",pays);

        Button btnAdd = new Button("Ajouter");
        addStringValue("",btnAdd);
        
        //Onclick event
        btnAdd.addActionListener(( e) -> {
     
        try{
            if(nom_aeroport.getText()=="" || ville_aeroport.getText()=="" || pays.getText()=="" ){
                Dialog.show("Veuillez verifier les données", "", "Annuler", "OK");
            }
            else{
                InfiniteProgress ip = new InfiniteProgress();
                final Dialog iDialog =ip.showInfiniteBlocking();
                Aeroport a = new Aeroport(String.valueOf(nom_aeroport.getText().toString()).toString(),String.valueOf(ville_aeroport.getText().toString()).toString(),String.valueOf(pays.getText().toString()).toString());
                System.out.println("data aeroport =="+a); 
                ServiceAeroport.getInstance().ajouteAeroport(a);
                iDialog.dispose();
                  LocalNotification ln = new LocalNotification();
            ln.setId("LnMessage");
            ln.setAlertTitle("Welcome");
            ln.setAlertBody("Thanks for arriving!");
            Display.getInstance().scheduleLocalNotification(ln, System.currentTimeMillis() + 10, LocalNotification.REPEAT_NONE);
                            
                new ListAeroportForm(res).show();
                refreshTheme();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
                });
            }
        
        
        
    
    
    private void addStringValue(String s, Component v){
     add(BorderLayout.west(new Label(s,"PaddedLabel"))
     .add(BorderLayout.CENTER,v));
    }
    
}
