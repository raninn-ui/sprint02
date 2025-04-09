package com.ranine.applications.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Editeur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEdit;
	private String nomEdit;
	private String adresseEdit;
	
	@JsonIgnore
	@OneToMany (mappedBy="editeur")
	private List<Application> applications;
	
	public Long getIdEdit() {
		return idEdit;
	}
	public void setIdEdit(Long idEdit) {
		this.idEdit = idEdit;
	}
	public String getNomEdit() {
		return nomEdit;
	}
	public void setNomEdit(String nomEdit) {
		this.nomEdit = nomEdit;
	}
	public String getAdresseEdit() {
		return adresseEdit;
	}
	public void setAdresseEdit(String adresseEdit) {
		this.adresseEdit = adresseEdit;
	}
	public List<Application> getApplications() {
		return applications;
	}
	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}
	
	
}
