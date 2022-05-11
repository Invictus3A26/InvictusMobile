/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.L10NManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import entity.CompagnieModel;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import services.ServiceCompagnie;
public class ALLCompagnies extends SideMenuBaseForm {
        services.ServiceCompagnie servicecompagnie = ServiceCompagnie.instance.getInstance();
        Form current;

    
     
    
    public ALLCompagnies(Resources res) {
   super ("Liste des compagnie", BoxLayout.y()); //hesioste nen Newafeed w1 formulaire vertical
        Toolbar tbar = new Toolbar(true);
    current = this;
    setToolbar(tbar);
     getTitleArea().setUIID("Container");;
     getContentPane().setScrollVisible(false);

        ArrayList<CompagnieModel> r= servicecompagnie.getInstance().getAllCompagnies(); 
      for (CompagnieModel compagnieModel : r) {
          String urlImage="airport.jpg";
          Image placeHolder = Image.createImage(120,90);
          EncodedImage enc = EncodedImage.createFromImage(placeHolder, false);
          URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage,URLImage.RESIZE_SCALE);
          
            addButton(urlim,compagnieModel.getCode_IATA(),compagnieModel.getNomCom(),compagnieModel.getPays(), compagnieModel.getNumber(),compagnieModel.getSiege(),compagnieModel.getAeBase(),compagnieModel.getPassagerNum(),compagnieModel.getDescription(),compagnieModel, res);
            addButton2(urlImage, compagnieModel, res);
            ScaleImageLabel image = new ScaleImageLabel(urlim);
            Container containerImg = new Container();
            image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        }

   
}
   private void addButton(Image img, String code_iata, String nomcom, String pays, int number, String siege, String aebase,int passagernum, String description,CompagnieModel centre, Resources res){
       CompagnieModel compagnieModel = null;
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       TextArea ta = new TextArea(code_iata);
        ta.setUIID("NewaTopLine");
        ta.setEditable(false);
        
        Label nomTxt = new Label("Nom Aéroport :"+nomcom,"newsTopLine2");
        Label paysTxt = new Label("Pays compagnie :"+pays,"newsTopLine2");
        Label codeTxt = new Label("Code compagnie :"+code_iata,"newsTopLine2");
        Label numberTxt = new Label("Numeros telephone :"+number,"newsTopLine2");
        Label siegeTxt = new Label("Siege compagnie :"+siege,"newsTopLine2");
        Label aebaseTxt = new Label("aeroport de base :"+aebase,"newsTopLine2");
        Label passagernumTxt = new Label("Numeros de passagers :"+passagernum,"newsTopLine2");
        Label descriptionTxt = new Label("descpriton :"+description,"newsTopLine2");

        Label empty = new Label(" ","newsTopLine2");
        
        
        cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(BoxLayout.encloseX(empty),BoxLayout.encloseX(codeTxt),BoxLayout.encloseX(nomTxt),BoxLayout.encloseX(paysTxt),BoxLayout.encloseX(siegeTxt),BoxLayout.encloseX(aebaseTxt),BoxLayout.encloseX(descriptionTxt)));
        add(cnt);
        
//        Label Name = new Label(nomcom);
//
//        Name.setUIID("NewaTopLine");
//        Label Deletc = new Label(" ");
//        Deletc.setUIID("delete");
//        Style supprimerStyle = new Style(Deletc.getUnselectedStyle());
//        supprimerStyle.setFgColor(0xf21f1f);
//        FontImage supprimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerStyle);
//        Deletc.setIcon(supprimerImage);
//        Deletc.setTextPosition(RIGHT);
//        //click
//        Deletc.addPointerPressedListener(l -> {
//            Dialog dig = new Dialog("Suppression");
//            if (dig.show("Suppression", "Voulez-vous vraiment supprimer ce centre?", "Annuler", "Confirmer")) {
//                System.out.println("-----------------------------");
//
//                dig.dispose();
//
//            } else {
//                dig.dispose();
//                System.out.println("-----------------------------");
//
//                if (servicecompagnie.getInstance().deleteCompagnie(centre.getId())) {
//                    System.out.println("-----------------------------");
//
//                    System.out.println(centre.getId());
//                    new ALLCompagnies(res).show();
//                    refreshTheme();
//                }
//            }
//        });
//       Container cont = new Container();
//
//        cont.add(BorderLayout.CENTER, BoxLayout.encloseY(
//                BoxLayout.encloseX(Name),
//                BoxLayout.encloseX(Deletc)
//        ));
//        add(cont);

   }
 private void addButton2( String nomcom, CompagnieModel centre, Resources res) {
   
        
        
    }
        
        

    @Override
    protected void showOtherForm(Resources res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
