/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.capture.Capture;
import com.codename1.components.FloatingActionButton;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import entity.CompagnieModel;


import java.io.IOException;
import services.ServiceCompagnie;

public class AddComp extends Inbox1Form {

    Container containercode_iata = new Container(new FlowLayout(CENTER, CENTER));
    Container containerNomCom = new Container(new FlowLayout(CENTER, CENTER));
       Container containerLink = new Container(new FlowLayout(CENTER, CENTER));
    Container containerPays = new Container(new FlowLayout(CENTER, CENTER));
       Container containerNumber = new Container(new FlowLayout(CENTER, CENTER));
    Container containerSiege = new Container(new FlowLayout(CENTER, CENTER));
       Container containerAeBase = new Container(new FlowLayout(CENTER, CENTER));
    Container containerPassagerNum = new Container(new FlowLayout(CENTER, CENTER));
           Container containerDescription = new Container(new FlowLayout(CENTER, CENTER));
    Container containerphoto = new Container(new FlowLayout(CENTER, CENTER));
 Container containercode_iata2 = new Container(new FlowLayout(CENTER, CENTER));
    Container containerNomCom2 = new Container(new FlowLayout(CENTER, CENTER));
       Container containerLink2 = new Container(new FlowLayout(CENTER, CENTER));
    Container containerPays2 = new Container(new FlowLayout(CENTER, CENTER));
       Container containerNumber2 = new Container(new FlowLayout(CENTER, CENTER));
    Container containerSiege2 = new Container(new FlowLayout(CENTER, CENTER));
       Container containerAeBase2 = new Container(new FlowLayout(CENTER, CENTER));
    Container containerPassagerNum2 = new Container(new FlowLayout(CENTER, CENTER));
        Container containerDescription2 = new Container(new FlowLayout(CENTER, CENTER));
    Container containerphoto2 = new Container(new FlowLayout(CENTER, CENTER));
    Container containerphoto3 = new Container(new FlowLayout(CENTER, CENTER));

    Label labelcode_iata = new Label("Code Compagnie");
    Label labelNomCom = new Label("Nom Compagnie");
      Label labelLink = new Label("Lien Compagnie");
    Label labelPays = new Label("Pays Compagnie");
      Label labelNumber = new Label("Number tel Compagnie");
    Label labelSiege = new Label("Siege Compagnie");
      Label labelAeBase = new Label("Aeroport de base du Compagnie");
    Label labelPassagerNum = new Label("Nombre de passager Compagnie");
      Label labelDescription = new Label("Description Compagnie");
    Label labelphoto = new Label("Image Categorie");

    TextField textfieldcode_iata = new TextField("", "Code_IATA");
    TextField textfielNomCom = new TextField("", "NomCom");
      TextField textfieldLink = new TextField("", "Link");
    TextField textfielPays = new TextField("", "Pays");
      TextField textfieldNumber = new TextField("", "Number");
    TextField textfielSiege = new TextField("", "Siege");
      TextField textfieldAeBase = new TextField("", "AeBase");
    TextField textfielPassagerNum = new TextField("", "PassagerNum");
      TextField textfielDescription = new TextField("", "Description");
    Label lblimage = new Label();
    com.codename1.ui.Button btnAjouter = new com.codename1.ui.Button("Ajouter");

    FloatingActionButton btnCapture = FloatingActionButton.createFAB(FontImage.MATERIAL_UPLOAD_FILE);

    String path = "";

    public AddComp(com.codename1.ui.util.Resources resourceObjectInstance) {

        BoxLayout.y();
        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
       

        initGuiBuilderComponents(resourceObjectInstance);

    }

    private void initGuiBuilderComponents(Resources resourceObjectInstance) {
        setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setInlineStylesTheme(resourceObjectInstance);
        setInlineStylesTheme(resourceObjectInstance);
        setTitle("Nouveau Compagnie");
        setName("Forums");
        Border border = Border.createCompoundBorder(null, Border.createLineBorder(1, 0x858585), null, null);

        textfieldcode_iata.setUIID("TextField2");
        textfieldcode_iata.getStyle().setFgColor(0x000000);
        textfieldcode_iata.getStyle().setBorder(border);
        textfieldcode_iata.getSelectedStyle().setBorder(border);

        textfielNomCom.setUIID("TextField2");
        textfielNomCom.getStyle().setFgColor(0x000000);
        textfielNomCom.getStyle().setBorder(border);
        textfielNomCom.getSelectedStyle().setBorder(border);
        
        textfieldLink.setUIID("TextField2");
        textfieldLink.getStyle().setFgColor(0x000000);
        textfieldLink.getStyle().setBorder(border);
        textfieldLink.getSelectedStyle().setBorder(border);
        
        textfielPays.setUIID("TextField2");
        textfielPays.getStyle().setFgColor(0x000000);
        textfielPays.getStyle().setBorder(border);
        textfielPays.getSelectedStyle().setBorder(border);
        
        textfieldNumber.setUIID("TextField2");
        textfieldNumber.getStyle().setFgColor(0x000000);
        textfieldNumber.getStyle().setBorder(border);
        textfieldNumber.getSelectedStyle().setBorder(border);
        
        textfielSiege.setUIID("TextField2");
        textfielSiege.getStyle().setFgColor(0x000000);
        textfielSiege.getStyle().setBorder(border);
        textfielSiege.getSelectedStyle().setBorder(border);

        textfieldAeBase.setUIID("TextField2");
        textfieldAeBase.getStyle().setFgColor(0x000000);
        textfieldAeBase.getStyle().setBorder(border);
        textfieldAeBase.getSelectedStyle().setBorder(border);
        
        textfielPassagerNum.setUIID("TextField2");
        textfielPassagerNum.getStyle().setFgColor(0x000000);
        textfielPassagerNum.getStyle().setBorder(border);
        textfielPassagerNum.getSelectedStyle().setBorder(border);
        
        textfielDescription.setUIID("TextField2");
        textfielDescription.getStyle().setFgColor(0x000000);
        textfielDescription.getStyle().setBorder(border);
        textfielDescription.getSelectedStyle().setBorder(border);
        //container global
        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        add(container);

        //ajout des composants aux containers

        containercode_iata.add(labelcode_iata);
        containercode_iata2.add(textfieldcode_iata);

        containerNomCom.add(labelNomCom);
        containerNomCom2.add(textfielNomCom);
        
         containerLink.add(labelLink);
        containerLink2.add(textfieldLink);
        
         containerPays.add(labelPays);
        containerPays2.add(textfielPays);
        
         containerNumber.add(labelNumber);
        containerNumber2.add(textfieldNumber);
        
         containerSiege.add(labelSiege);
        containerSiege2.add(textfielSiege);
        
         containerAeBase.add(labelAeBase);
        containerAeBase2.add(textfieldAeBase);
        
         containerPassagerNum.add(labelPassagerNum);
        containerPassagerNum2.add(textfielPassagerNum);
        
         containerDescription.add(labelDescription);
        containerDescription2.add(textfielDescription);
        
     

        containerphoto.add(labelphoto);
        containerphoto2.add(lblimage);
        containerphoto3.add(btnCapture);

        container.add(containercode_iata);
        container.add(containercode_iata2);
        container.add(containerNomCom);
        container.add(containerNomCom2);
        container.add(containerLink);
        container.add(containerLink2);
          container.add(containerPays);
        container.add(containerPays2);
        container.add(containerNumber);
           container.add(containerNumber2);
        container.add(containerSiege);
           container.add(containerSiege2);
        container.add(containerAeBase);
           container.add(containerAeBase2);
        container.add(containerPassagerNum);
           container.add(containerPassagerNum2);;
           container.add(containerDescription);
        container.add(containerDescription2);
        container.add(containerphoto);
        container.add(containerphoto2);
        container.add(containerphoto3);
        
        container.add(btnAjouter);

        //importer image
        btnCapture.getStyle().setBgColor(0xB22222);
        btnCapture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                path = Capture.capturePhoto(containerphoto.getWidth(), btnCapture.getHeight() * 5);
                if (path != null) {
                    try {
                        Image img = Image.createImage(path);
                        lblimage.setIcon(img);
                        containerphoto.revalidate();

                    } catch (IOException ex) {

                    }
                }
            }
        });

        //ajouter
          
        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (!textfieldcode_iata.getText().isEmpty() && !textfielNomCom.getText().isEmpty() &&!textfieldLink.getText().isEmpty() && !textfielPays.getText().isEmpty() && !textfielPays.getText().isEmpty() &&!textfielSiege.getText().isEmpty() && !textfieldAeBase.getText().isEmpty()) {
                    services.ServiceCompagnie serviceCompagnie = ServiceCompagnie.getInstance();

                   serviceCompagnie.addComp(textfieldcode_iata, textfielNomCom,textfieldNumber,textfieldLink,textfielPays,textfielSiege,textfieldAeBase,textfielPassagerNum,textfielDescription);
                  
                    Dialog.show("Success", new Label("Compagnie ajouté"), new Command("OK"));
                  
                } else {
                    Dialog.show("Erreur", new Label("Les champs ne doivent pas etre vide"), new Command("OK"));
                }
            }
        });

   }

    

}
