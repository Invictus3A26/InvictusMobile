/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author islem
 */
public class Aeroport {
    private int idAeroport;
    private String nomAeroport;
    private String villeAeroport;
    private String pays;

    public Aeroport() {
    }

    public Aeroport(int idAeroport, String nomAeroport, String villeAeroport, String pays) {
        this.idAeroport = idAeroport;
        this.nomAeroport = nomAeroport;
        this.villeAeroport = villeAeroport;
        this.pays = pays;
    }

    public Aeroport(String nomAeroport, String villeAeroport, String pays) {
        this.nomAeroport = nomAeroport;
        this.villeAeroport = villeAeroport;
        this.pays = pays;
    }

    public int getIdAeroport() {
        return idAeroport;
    }

    public void setIdAeroport(int idAeroport) {
        this.idAeroport = idAeroport;
    }

    public String getNomAeroport() {
        return nomAeroport;
    }

    public void setNomAeroport(String nomAeroport) {
        this.nomAeroport = nomAeroport;
    }

    public String getVilleAeroport() {
        return villeAeroport;
    }

    public void setVilleAeroport(String villeAeroport) {
        this.villeAeroport = villeAeroport;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }
     
    
}
