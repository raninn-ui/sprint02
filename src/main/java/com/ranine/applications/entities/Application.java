package com.ranine.applications.entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
public class Application {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idApp;
	
	@NotNull
	@Size (min = 4,max = 15)
	private String nomApp;
	
	@Min(value = 10)
	@Max(value = 1000000)
	private Double nbtl;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@PastOrPresent
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
