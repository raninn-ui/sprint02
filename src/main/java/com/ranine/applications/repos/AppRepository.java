package com.ranine.applications.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ranine.applications.entities.Application;
import com.ranine.applications.entities.Editeur;

@RepositoryRestResource(path = "rest")
public interface AppRepository extends JpaRepository<Application, Long> {

	List <Application> findByNomApp(String nom);
	List <Application> findByNomAppContains(String nom);
	
	/*@Query("select a from Application a where a.nomApp like %?1 and a.nbtl > ?2")
	List<Application> findByNomNbtl (String nom, Double nbtl);*/
	
	@Query("select p from Application p where p.nomApp like %:nom and p.nbtl > :nbtl")
	List<Application> findByNomNbtl (@Param("nom") String nom,@Param("nbtl") Double nbtl);
	
	@Query("select a from Application a where a.editeur = ?1")
	List<Application> findByEditeur (Editeur editeur);
	
	List<Application> findByEditeurIdEdit (Long id);
	
	List<Application> findByOrderByNomAppAsc();
	
	@Query("select a from Application a order by a.nomApp ASC, a.nbtl DESC")
	List<Application> trierApplicationsNomsNbtl ();

}
