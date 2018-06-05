package com.emiage.s12018.noteReminder.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Note {

	@Id
	@GeneratedValue
	private Long idNote;
	
	private String texte;
	private String couleur;
	private String echeance;
	private long ordre;
	
	//bi-directional many-to-one association to users
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ID_USER", nullable=true)
	//@JsonIgnore
	private Users user;
	
	public Long getIdNote() {
		return idNote;
	}
	public void setIdNote(Long idNote) {
		this.idNote = idNote;
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
	
	
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Note [idNote=" + idNote + ", texte=" + texte + ", couleur=" + couleur + ", echeance=" + echeance
				+ ", ordre=" + ordre + ", user=" + user + "]";
	}
	
	
	
	
}
