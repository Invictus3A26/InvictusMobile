/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;



import Service.ServiceAeroport;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entities.Aeroport;

/**
 *
 * @author islem
 */
public class UpdateAirportForm extends Inbox1Form {
      Form current;

    public UpdateAirportForm(Resources res, Aeroport aeroport) {

        Toolbar tbar = new Toolbar(true);
        current = this;
        setToolbar(tbar);
        getTitleArea().setUIID("Container");
        setTitle("");
        getContentPane().setScrollVisible(false);
        
        
        
        TextField nom = new TextField(aeroport.getVilleAeroport(), "nom aéroport", 16, TextField.ANY);
        nom.setUIID("HewsTopLine");
        nom.setSingleLineTextArea(true);

        TextField ville = new TextField(aeroport.getVilleAeroport(), "ville aeroport", 16, TextField.ANY);
        ville.setUIID("HewsTopLine");
        ville.setSingleLineTextArea(true);
        
        TextField pays = new TextField(aeroport.getPays(), "pays aeroport", 16, TextField.ANY);
        pays.setUIID("HewsTopLine");
        pays.setSingleLineTextArea(true);

       

        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");

        //Event onclick brnModifer
        btnModifier.addActionListener((e) -> {

            aeroport.setNomAeroport(nom.getText());
            aeroport.setVilleAeroport(ville.getText());
            aeroport.setPays(pays.getText());
          
  
        if (ServiceAeroport.getInstance().updateAeroport(aeroport, aeroport.getIdAeroport())) {
            System.out.println("hhh");
            new ListAeroportForm(res).show();
        }
      });
        Button btnannuler = new Button("Annuler");
        btnannuler.addActionListener((e) -> {

            new ListAeroportForm(res).show();

        });
   

        Container content = BoxLayout.encloseY(
            
                new FloatingHint(nom),
                new FloatingHint(ville),
                new FloatingHint(pays),
               
          
                btnModifier,
                btnannuler);
        add(content);
        show();

    }

   private void addStringValue(String s, Component c) {
        add(BorderLayout.west(new Label(s, "PaddedLabel"))
                .add(BorderLayout.CENTER, c));
        
   }
}
