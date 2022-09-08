package fr.dawan.miseEnSituation.services;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericService <Tdto> {
	
	List<Tdto> getAll();

	Tdto saveOrUpdate(Tdto tDto);

	void delete(long id);

}