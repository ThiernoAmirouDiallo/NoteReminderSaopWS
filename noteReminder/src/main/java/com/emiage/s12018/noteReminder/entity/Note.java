package com.emiage.s12018.noteReminder.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Note {

	@Id
	@GeneratedValue
	private Long idSondage;
	
	private String texte;
	private String couleur;
	private String echeance;
	private long ordre;
	public Long getIdSondage() {
		return idSondage;
	}
	public void setIdSondage(Long idSondage) {
		this.idSondage = idSondage;
	}
	public String getCouleur() {
		return couleur;
	}
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	public String getEcheance() {
		return echeance;
	}
	public void setEcheance(String echeance) {
		this.echeance = echeance;
	}
	public String getTexte() {
		return texte;
	}
	public void setTexte(String texte) {
		this.texte = texte;
	}
	public long getOrdre() {
		return ordre;
	}
	public void setOrdre(long ordre) {
		this.ordre = ordre;
	}
	
	
}
