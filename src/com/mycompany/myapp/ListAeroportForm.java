/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import Service.ServiceAeroport;
import com.codename1.components.FloatingHint;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import entities.Aeroport;
import java.util.ArrayList;

/**
 *
 * @author islem
 */
public class ListAeroportForm extends Inbox1Form {
    
    Form current;
    
    public ListAeroportForm(Resources res) {
        Toolbar tbar = new Toolbar(true);
    current = this;
    setToolbar(tbar);
     getTitleArea().setUIID("Container");
     setTitle(" ");
     getContentPane().setScrollVisible(false);
     
        Button btnAdd = new Button("Ajouter Aeroport");
          btnAdd.addActionListener((a) -> {

            new ajouteAeroportForm(res).show();

        });
       
        
        
        Button btnMap = new Button("Consulter map");
          btnMap.addActionListener((e) -> {

      //     new MapForm();

        });
     
     
      Container content = BoxLayout.encloseY(
            
              
               
          
                btnMap,
                btnAdd);
       add(content);
        show();
     

        ArrayList<Aeroport> r= ServiceAeroport.getInstance().affichageAeroport(); 
      for (Aeroport aeroport : r) {
          String urlImage="airport.jpg";
          Image placeHolder = Image.createImage(120,90);
          EncodedImage enc = EncodedImage.createFromImage(placeHolder, false);
          URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage,URLImage.RESIZE_SCALE);
          
            addButton(urlim,aeroport.getNomAeroport(),aeroport.getPays(),aeroport.getVilleAeroport(), aeroport, res);
            
            ScaleImageLabel image = new ScaleImageLabel(urlim);
            Container containerImg = new Container();
            image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        }

   
}
   private void addButton(Image img,String nom,String pays,String ville, Aeroport aeroport, Resources res){
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       TextArea ta = new TextArea(nom);
        ta.setUIID("NewaTopLine");
        ta.setEditable(false);
        
        Label nomTxt = new Label("Nom Aéroport :"+nom,"newsTopLine2");
        Label paysTxt = new Label("Pays Aéroport :"+pays,"newsTopLine2");
        Label villeTxt = new Label("Ville Aéroport :"+ville,"newsTopLine2");
        Label empty = new Label(" ","newsTopLine2");
         Label action = new Label(" Action :","newsTopLine2");
        
        
        

        Label DeletU = new Label(" ");
      DeletU.setUIID("delete");
        Style supprimerStyle = new Style(DeletU.getUnselectedStyle());
        supprimerStyle.setFgColor(0xf21f1f);
        FontImage supprimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerStyle);
        DeletU.setIcon(supprimerImage);
        DeletU.setTextPosition(RIGHT);
        
         DeletU.addPointerPressedListener(l -> {
            Dialog dig = new Dialog("Suppression");
            if (dig.show("Suppression", "Voulez-vous vraiment supprimer ce aeroport?", "Annuler", "Confirmer")) {
            System.out.println("-----------------------------");

                dig.dispose();

            } else {
                dig.dispose();
                            System.out.println("-----------------------------");

                if (ServiceAeroport.getInstance().deleteAeroport(aeroport.getIdAeroport())) {
                    System.out.println("-----------------------------");
                    
                    System.out.println(aeroport.getIdAeroport());
                    new ListAeroportForm(res).show();
                    refreshTheme();
                } else {
                }
            }
        });
         
         Label UpdateR = new Label("");
        UpdateR.setUIID("NewsTopline");
        Style modifierStyle = new Style(UpdateR.getUnselectedStyle());
        modifierStyle.setFgColor(0x63e6be);
        FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
        UpdateR.setIcon(mFontImage);
        UpdateR.setTextPosition(LEFT);
        UpdateR.addPointerPressedListener(l -> {
            new UpdateAirportForm(res, aeroport).show();
        });
        
        
        
          Button btnannuler = new Button("Ajouter Aeroport");
        btnannuler.addActionListener((e) -> {

            new ajouteAeroportForm(res).show();

        });
         
         
         
         
         
         
       cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(BoxLayout.encloseX(empty),BoxLayout.encloseX(nomTxt),BoxLayout.encloseX(paysTxt),BoxLayout.encloseX(villeTxt),BoxLayout.encloseX(action,DeletU,UpdateR)));
        add(cnt);
     /* 
        //click
        DeletU.addPointerPressedListener(l -> {
            Dialog dig = new Dialog("Suppression");
            if (dig.show("Suppression", "Voulez-vous vraiment supprimer cette reservation?", "Annuler", "Confirmer")) {
            System.out.println("-----------------------------");

                dig.dispose();

            } else {
                dig.dispose();
                            System.out.println("-----------------------------");

                if (ServiceAeroport.getInstance().deleteAeroport(aeroport.getIdAeroport())) {
                    System.out.println("-----------------------------");
                    
                    System.out.println(aeroport.getIdAeroport());
                    new ListAeroportForm(res).show();
                    refreshTheme();
                } else {
                }
            }
        });*/}

   private void addStringValue(String s, Component v){
     add(BorderLayout.west(new Label(s,"PaddedLabel"))
     .add(BorderLayout.CENTER,v));
 
    }

    private void addStringsValue(String s, Component v) {
   add(BorderLayout.west(new Label(s,"PaddedLabel"))
     .add(BorderLayout.CENTER,v));
    }
}

     
    
  
 
