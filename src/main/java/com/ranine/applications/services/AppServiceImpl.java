package com.ranine.applications.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ranine.applications.entities.Application;
import com.ranine.applications.entities.Editeur;
import com.ranine.applications.repos.AppRepository;

@Service
public class AppServiceImpl implements AppService{

	@Autowired
	AppRepository appRepository;
	
	@Override
	public Application saveApp(Application a) {
		return appRepository.save(a);
	}

	@Override
	public Application updateApp(Application a) {
		return appRepository.save(a);
	}

	@Override
	public void deleteApp(Application a) {
		appRepository.delete(a);
		
	}

	@Override
	public void deleteAppById(Long id) {
		appRepository.deleteById(id);
		
	}

	@Override
	public Application getApp(Long id) {
		return appRepository.findById(id).get();
	}

	@Override
	public List<Application> getAllApps() {
		return appRepository.findAll();
	}

	@Override
	public Page<Application> getAllAppsParPage(int page, int size) {
		return appRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public List<Application> findByNomApp(String nom) {
		return appRepository.findByNomApp(nom);
	}

	@Override
	public List<Application> findByNomAppContains(String nom) {
		return appRepository.findByNomAppContains(nom);
	}

	@Override
	public List<Application> findByNomNbtl(String nom, Double nbtl) {
		return appRepository.findByNomNbtl(nom,nbtl);
	}

	@Override
	public List<Application> findByEditeur(Editeur editeur) {
		return appRepository.findByEditeur(editeur);
	}

	@Override
	public List<Application> findByEditeurIdEdit(Long id) {
		return appRepository.findByEditeurIdEdit(id);
	}

	@Override
	public List<Application> findByOrderByNomAppAsc() {
		return appRepository.findByOrderByNomAppAsc();
	}

	@Override
	public List<Application> trierApplicationsNomsNbtl() {
		return appRepository.trierApplicationsNomsNbtl();
	}

}
