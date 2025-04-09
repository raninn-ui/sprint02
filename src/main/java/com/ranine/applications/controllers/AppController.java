package com.ranine.applications.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.ranine.applications.entities.Application;
import com.ranine.applications.services.AppService;

@Controller
public class AppController {

	@Autowired
	AppService appService;

	@RequestMapping("/ListeApplications")
	public String listeApplications(ModelMap modelMap, 
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "3") int size) {
		Page<Application> apps = appService.getAllAppsParPage(page, size);
		modelMap.addAttribute("applications", apps);
		modelMap.addAttribute("pages", new int[apps.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("size", size);

		
		return "listeApplications";
	}

	@RequestMapping("/showCreate")
	public String showCreate() {
		return "createApplication";
	}

	@RequestMapping("/saveApplication")
	public String saveApp(@ModelAttribute("application") Application application, @RequestParam("date") String date,
			ModelMap modelMap) throws ParseException {
		// conversion de la date
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date Rdate = dateformat.parse(String.valueOf(date));
		application.setReleasedate(Rdate);

		Application saveApp = appService.saveApp(application);
		String msg = "application enregistré avec Id " + saveApp.getIdApp();
		modelMap.addAttribute("msg", msg);
		return "createApplication";
	}

	@RequestMapping("/supprimerApplication")
	public String supprimerApplication(@RequestParam("id") Long id, ModelMap modelMap,
			@RequestParam (name="page",defaultValue = "0") int page,
			@RequestParam (name="size", defaultValue = "2") int size) {
		appService.deleteAppById(id);
		
		Page<Application> apps = appService.getAllAppsParPage(page,size);
		modelMap.addAttribute("applications", apps);
		modelMap.addAttribute("pages", new int[apps.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("size", size);
		return "listeApplications";
	}

	@RequestMapping("/modifierApplication")
	public String editerApplication(@RequestParam("id") Long id, ModelMap modelMap) {
		Application a = appService.getApp(id);
		modelMap.addAttribute("application", a);
		return "editerApplication";
	}

	@RequestMapping("/updateApplication")
	public String updateApplication(@ModelAttribute("application") Application application,
			@RequestParam("date") String date, ModelMap modelMap) throws ParseException {
		// conversion de la date
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date Rdate = dateformat.parse(String.valueOf(date));
		application.setReleasedate(Rdate);

		appService.updateApp(application);
		List<Application> apps = appService.getAllApps();
		modelMap.addAttribute("applications", apps);
		return "listeApplications";
	}

}
