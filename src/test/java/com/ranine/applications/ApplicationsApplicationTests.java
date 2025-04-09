package com.ranine.applications;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.ranine.applications.entities.Application;
import com.ranine.applications.entities.Editeur;
import com.ranine.applications.repos.AppRepository;
import com.ranine.applications.services.AppService;

@SpringBootTest
class ApplicationsApplicationTests {

	@Autowired
	private AppRepository appRepository;
	
	@Autowired
	private AppService appService;

	@Test
	public void testCreateApp() {
		Application app = new Application("instagram", 9000.600, new Date());
		appRepository.save(app);
	}

	@Test
	public void testFindApp() {
		Application a = appRepository.findById(2L).get();
		System.out.println(a);
	}

	@Test
	public void testUpdateApp() {
		Application a = appRepository.findById(2L).get();
		a.setNbtl(10000.0);
		appRepository.save(a);
	}

	@Test
	public void testDeleteProduit() {
		appRepository.deleteById(2L);
		;
	}

	@Test
	public void testListerTousApplications() {
		List<Application> apps = appRepository.findAll();
		for (Application a : apps) {
			System.out.println(a);
		}
	}

	@Test
	public void testFindByNomProduitContains() {
		Page<Application> apps = appService.getAllAppsParPage(0, 2);
		System.out.println(apps.getSize());
		System.out.println(apps.getTotalElements());
		System.out.println(apps.getTotalPages());
		apps.getContent().forEach(a -> {System.out.println(a.toString());
		});
		/*
		 * ou bien 
		 * for (Produit p : prods) 
		 * { 
		 * System.out.println(p); }
		 */
	}
	
	@Test
	public void findByNomApp()
	{
	List<Application> apps = appRepository.findByNomApp("TikTok");
	for (Application a:apps)
	
	System.out.println(a);
	
	}
	
	@Test
	public void findByNomAppContains()
	{
	List<Application> apps = appRepository.findByNomAppContains("T");
	for (Application a:apps)
	
	System.out.println(a);
	
	}
	
	@Test
	public void findByNomNbtl()
	{
	List<Application> apps = appRepository.findByNomNbtl("TikTok", 1000.0);
	for (Application a : apps)
	{
	System.out.println(a);
	}
	}

	
	@Test
	public void testfindByCategorie()
	{
	Editeur edit= new Editeur();
	edit.setIdEdit(1L);
	List<Application> apps = appRepository.findByEditeur(edit);
	for (Application a : apps)
	{
	System.out.println(a);
	}
	}
	
	@Test
	public void findByEditeurIdEdit()
	{
	List<Application> apps = appRepository.findByEditeurIdEdit(1L);
	for (Application a : apps)
	{
	System.out.println(a);
	}
	 }


	@Test
	public void testfindByOrderByNomAppAsc()
	{
	List<Application> apps =appRepository.findByOrderByNomAppAsc();
	for (Application a : apps)
	{
	System.out.println(a);
	}
	}
	
	@Test
	public void testTrierApplicationsNomsNbtl()
	{
	List<Application> apps = appRepository.trierApplicationsNomsNbtl();
	for (Application a : apps)
	{
	System.out.println(a);
	}
	}

	
}
