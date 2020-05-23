package ec.com.sigc.servicio;

import java.util.List;

import ec.com.sigc.entidad.Seccion;

public interface SectionService {
	public abstract List<Seccion> findAllSections();
	public abstract Seccion findSection(Integer id);
	public abstract void saveSection(Seccion section);
	public abstract void deleteSection(Integer id);
}
