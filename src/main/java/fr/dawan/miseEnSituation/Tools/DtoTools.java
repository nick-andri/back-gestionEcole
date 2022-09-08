package fr.dawan.miseEnSituation.Tools;

import fr.dawan.miseEnSituation.dto.DG2LocationDto;
import org.modelmapper.ModelMapper;


import fr.dawan.miseEnSituation.dto.VilleDto;


public class DtoTools {

	private static ModelMapper myMapper = new ModelMapper();

	public static <TSource,TDestination> TDestination convert(TSource obj, Class<TDestination> clazz) {

		//ajouter ici les règles personnalisées

		//LocationDto =========> VilleDto
		myMapper.typeMap(DG2LocationDto.class, VilleDto.class)
				.addMappings(mapper->{
					mapper.map(src->src.getName(), VilleDto::setNom);
					mapper.map(src->src.getSlug(), VilleDto::setSlug);
				});

		return myMapper.map(obj, clazz);
	}
}
