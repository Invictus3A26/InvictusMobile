/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp;

import services.ServiceCompagnie;
import com.codename1.components.InfiniteProgress;
//import com.codename1.
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.ui.CN;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import entity.CompagnieModel;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @author User
 */
public class EditCompagnie extends Inbox1Form {

    String fileNameInServer;
    String ipath;
    String filePath = "";

    protected String saveFileToDevice(String hi, String ext) throws IOException {
        URI uri;
        try {
            uri = new URI(hi);
            String path = uri.getPath();
            int index = hi.lastIndexOf("/");
            hi = hi.substring(index + 1);
            return hi;
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
        return "error";
    }

    public EditCompagnie(Resources res, CompagnieModel game) {
        getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Edit Compagnie", "Title")
                )
        );
        setLayout(BoxLayout.y());

        setLayout(BoxLayout.y());

         TextField textfieldcode_iata = new TextField(game.getCode_IATA(), "Code_IATA");
    TextField textfielNomCom = new TextField(game.getNomCom(), "NomCom");
      TextField textfieldLink = new TextField(game.getLink(), "Link");
    TextField textfielPays = new TextField(game.getPays(), "Pays");
    TextField textfielSiege = new TextField(game.getSiege(), "Siege");
      TextField textfieldAeBase = new TextField(game.getAeBase(), "AeBase");
      TextField textfielDescription = new TextField(game.getDescription(), "Description");
        Button btnValider = new Button("Edit");
        btnValider.setUIID("InboxNumber3");

        Button img1 = new Button("select an image");
        CheckBox multiSelect = new CheckBox("multi");
        img1.addActionListener((ActionEvent e) -> {
            if (FileChooser.isAvailable()) {
                FileChooser.setOpenFilesInPlace(true);
                FileChooser.showOpenDialog(multiSelect.isSelected(), ".jpg, .jpeg, .png", (ActionEvent e2) -> {
                    if (e2 == null || e2.getSource() == null) {
                        add("no file selected");
                        revalidate();
                        return;
                    }
                    if (multiSelect.isSelected()) {
                        String[] paths = (String[]) e2.getSource();
                        for (String path : paths) {
                            System.out.println(path);
                            CN.execute(path);
                        }
                        return;
                    }
                    String file = (String) e2.getSource();
                    System.out.println("here : " + file);
                    if (file == null) {
                        add("no file selected");
                        revalidate();
                    } else {
                        String extension = null;
                        if (file.lastIndexOf(".") > 0) {
                            try {
                                StringBuilder hi = new StringBuilder(file);
                                if (file.startsWith("file://")) {
                                    hi.delete(0, 7);
                                }
                                int lastIndexPeriod = hi.toString().lastIndexOf(".");
                                Log.p(hi.toString());
                                String ext = hi.toString().substring(lastIndexPeriod);
                                String hmore = hi.toString().substring(0, lastIndexPeriod - 1);
                                String namePic = saveFileToDevice(file, ext);
                                System.err.println(namePic);
                                btnValider.addActionListener((ev) -> {

                                    try {
                                        if (textfieldcode_iata.getText().isEmpty() || textfielNomCom.getText().isEmpty() || textfieldLink.getText().isEmpty()) {
                                            Dialog.show("Please fill all the fields", "", "Cancel", "OK");
                                        } else {
                                            InfiniteProgress ip = new InfiniteProgress();
                                            final Dialog iDialog = ip.showInfiniteBlocking();

                                            game.setCode_IATA(textfieldcode_iata.getText());
                                            game.setNomCom(textfielNomCom.getText());
                                            game.setLink(textfieldLink.getText());
                                             game.setPays(textfielPays.getText());
                                             game.setSiege(textfielSiege.getText());
                                               game.setAeBase(textfieldAeBase.getText());
                                            game.setDescription(textfielDescription.getText());

                                                
                                            game.setImage(namePic);

                                            ServiceCompagnie.getInstance().edit(game);

                                            iDialog.dispose();
                                            new ShowCompagnies(res).show();
                                            refreshTheme();
                                        }
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                });
                                revalidate();
                            } catch (IOException ex) {
                            }
                        }
                    }
                });
            }
        });

        addAll(textfieldcode_iata, textfielNomCom, textfieldLink,textfielPays,textfielSiege,textfieldAeBase,textfielDescription,  btnValider);

    }
}
