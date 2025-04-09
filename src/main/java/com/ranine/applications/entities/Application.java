package com.ranine.applications.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Application {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idApp;
	
	private String nomApp;
	private Double nbtl;
	private Date releasedate;
	
	@ManyToOne
	private Editeur editeur;
	
	public Application() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Application(String nomApp, Double nbtl, Date releasedate) {
		super();
		this.nomApp = nomApp;
		this.nbtl = nbtl;
		this.releasedate = releasedate;
	}


	public Long getIdApp() {
		return idApp;
	}
	public void setIdApp(Long idApp) {
		this.idApp = idApp;
	}
	public String getNomApp() {
		return nomApp;
	}
	public void setNomApp(String nomApp) {
		this.nomApp = nomApp;
	}
	public Double getNbtl() {
		return nbtl;
	}
	public void setNbtl(Double nbtl) {
		this.nbtl = nbtl;
	}
	public Date getReleasedate() {
		return releasedate;
	}
	public void setReleasedate(Date releasedate) {
		this.releasedate = releasedate;
	}

	@Override
	public String toString() {
		return "Application [idApp=" + idApp + ", nomApp=" + nomApp + ", nbtl=" + nbtl + ", releasedate=" + releasedate
				+ "]";
	}

	public Editeur getEditeur() {
		return editeur;
	}

	public void setEditeur(Editeur editeur) {
		this.editeur = editeur;
	}
	
}
