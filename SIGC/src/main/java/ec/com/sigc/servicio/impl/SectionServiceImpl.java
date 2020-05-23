package ec.com.sigc.servicio.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ec.com.sigc.entidad.Seccion;
import ec.com.sigc.repositorio.SectionRepository;
import ec.com.sigc.servicio.SectionService;

@Service("sectionService")
public class SectionServiceImpl implements SectionService {

	@Autowired
	@Qualifier("sectionRepository")
	private SectionRepository sectionRepository;

	@Override
	public List<Seccion> findAllSections() {
		return sectionRepository.findAll();
	}

	@Override
	public Seccion findSection(Integer id) {
		return sectionRepository.findById(id).get();
	}

	@Override
	public void saveSection(Seccion section) {
		sectionRepository.save(section);
		
	}

	@Override
	public void deleteSection(Integer id) {
		sectionRepository.deleteById(id);
		
	}

	
	

}
