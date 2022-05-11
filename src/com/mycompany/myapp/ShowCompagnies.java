/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import services.ServiceCompagnie;
import com.codename1.components.ImageViewer;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import entity.CompagnieModel;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class ShowCompagnies extends Inbox1Form {

    Container c = new Container(BoxLayout.x());
    Container cxx = new Container(BoxLayout.xCenter());

    public ShowCompagnies(Resources res) {
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Compagnie List", "Title")
                )
        );
        setLayout(BoxLayout.y());

        ArrayList<CompagnieModel> List = ServiceCompagnie.getInstance().getAllCompagnies();
        System.out.println("test2");

        for (CompagnieModel cat : List) {

            addButton(cat, res);
        }

    }

    private void addButton(CompagnieModel cat, Resources res) {

           
        Label nomTxt = new Label("Code Aéroport :"+cat.getCode_IATA(),"newsTopLine2");
        Label paysTxt = new Label("Nom compagnie :"+cat.getNomCom(),"newsTopLine2");
        Label codeTxt = new Label("Pays compagnie :"+cat.getPays(),"newsTopLine2");
        Label numberTxt = new Label("Numeros telephone :"+cat.getNumber(),"newsTopLine2");
        Label siegeTxt = new Label("Siege compagnie :"+cat.getSiege(),"newsTopLine2");
        Label aebaseTxt = new Label("aeroport de base :"+cat.getAeBase(),"newsTopLine2");
        Label passagernumTxt = new Label("Numeros de passagers :"+cat.getPassagerNum(),"newsTopLine2");
        Label descriptionTxt = new Label("descpriton :"+cat.getDescription(),"newsTopLine2");
        Image logo;
        String file = "uploads" + cat.getImage();
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(this.getWidth() / 2, this.getHeight() / 5, 0xffff0000), true);
        Image im = URLImage.createToStorage(placeholder, file, file);
        ImageViewer imagea = new ImageViewer(im.scaled(120, 120));
        Label lSupprimer = new Label(" ");
        lSupprimer.setUIID("NewsTopLine");
        Style supprimerStyle = new Style(lSupprimer.getUnselectedStyle());
        supprimerStyle.setFgColor(0xf21f1f);
        FontImage supprimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerStyle);
        lSupprimer.setIcon(supprimerImage);
        lSupprimer.setTextPosition(RIGHT);
        //click
        lSupprimer.addPointerPressedListener(l -> {
            Dialog dig = new Dialog("Deleting");
            if (dig.show("Deleting", "Are you sure you want to delete this ?", "Cancel", "Yes")) {
                dig.dispose();
            } else {
                dig.dispose();
                if (ServiceCompagnie.getInstance().deleteCompagnie(cat.getId())) {
                //    new ShowCompagnies(res).show;
                    refreshTheme();
                }
            }
        });
//        //Update button
        Label lModifier = new Label(" ");
        lModifier.setUIID("NewsTopLine");
        Style modifierStyle = new Style(lModifier.getUnselectedStyle());
        modifierStyle.setFgColor(0xf7ad02);
        FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
        lModifier.setIcon(mFontImage);
        lModifier.setTextPosition(LEFT);
        lModifier.addPointerPressedListener(l -> {
            new EditCompagnie(res, cat).show();
        });

        Container post = BoxLayout.encloseY(
                GridLayout.encloseIn(2, nomTxt, paysTxt), BorderLayout.centerAbsolute(GridLayout.encloseIn(1, codeTxt)), GridLayout.encloseIn(2, lModifier,  lSupprimer));
        Container pub = BoxLayout.encloseY(
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY(
                                imagea
                        )
                ),//.add(BorderLayout.WEST, pubImage),
                BoxLayout.encloseY(post)
        );

        pub.getStyle().setFgColor(0xffffff);
        pub.getStyle().setBgColor(0xadd8e6);
        pub.getStyle().setBgTransparency(255);
        pub.getStyle().setPadding(7, 7, 7, 7);
        pub.getStyle().setMargin(20, 20, 30, 30);
        pub.getStyle().setBorder(Border.createRoundBorder(50, 50));

        add(pub);
    }

}
