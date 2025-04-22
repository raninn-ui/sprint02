package com.ranine.applications.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.ranine.applications.entities.Application;
import com.ranine.applications.entities.Editeur;
import com.ranine.applications.services.AppService;

import jakarta.validation.Valid;

@Controller
public class AppController {

	@Autowired
	AppService appService;
	
	@GetMapping(value = "/")
	public String welcome() {
	 return "index";
	}

	@GetMapping("/accessDenied")
	public String error()
	{
	return "accessDenied";
	}
	
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
	public String showCreate(ModelMap modelMap) {
		List<Editeur> edits = appService.getAllEditeurs();
		modelMap.addAttribute("application", new Application());
		modelMap.addAttribute("mode", "new");
		modelMap.addAttribute("editeurs", edits); 
		return "formApp";
	}

	@RequestMapping("/saveApplication")
	public String saveApp(@Valid  Application application, BindingResult bindingResult,
			@RequestParam (name="page",defaultValue = "0") int page,
			@RequestParam (name="size",defaultValue = "2") int size)
   {
		int currentPage;
		boolean isNew = false;
		if (bindingResult.hasErrors()) return "formApp";
		
		if (application.getIdApp()==null) //ajout
			isNew=true;
		
		appService.saveApp(application);
		if (isNew) //ajout
		{
		Page<Application> apps = appService.getAllAppsParPage(page, size);
		currentPage = apps.getTotalPages()-1;
		}
		else //modif
		currentPage=page;
		return ("redirect:/ListeApplications?page="+currentPage+"&size="+size);
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
	public String editerApplication(@RequestParam("id") Long id, ModelMap modelMap,
			@RequestParam (name="page",defaultValue = "0") int page,
			@RequestParam (name="size", defaultValue = "2") int size) {
		Application a = appService.getApp(id);
		List<Editeur> edits = appService.getAllEditeurs();
		modelMap.addAttribute("application", a);
		modelMap.addAttribute("mode", "edit");
		modelMap.addAttribute("editeurs", edits);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("size", size);
		return "formApp";
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
