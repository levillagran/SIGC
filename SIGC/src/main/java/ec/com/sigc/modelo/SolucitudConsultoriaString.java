package ec.com.sigc.modelo;

import java.io.Serializable;

public class SolucitudConsultoriaString implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String auxClienteTipoId;
    public String auxTipoConsultoriaId;
    public String auxfechaInicio;
    public String auxhoraInicio;
    public String auxfechaFinal;
    public String auxhoraFin;
  
	
	public String getAuxClienteTipoId() {
		return auxClienteTipoId;
	}


	public void setAuxClienteTipoId(String auxClienteTipoId) {
		this.auxClienteTipoId = auxClienteTipoId;
	}


	public String getAuxTipoConsultoriaId() {
		return auxTipoConsultoriaId;
	}


	public void setAuxTipoConsultoriaId(String auxTipoConsultoriaId) {
		this.auxTipoConsultoriaId = auxTipoConsultoriaId;
	}


	public String getAuxfechaInicio() {
		return auxfechaInicio;
	}


	public void setAuxfechaInicio(String auxfechaInicio) {
		this.auxfechaInicio = auxfechaInicio;
	}


	public String getAuxhoraInicio() {
		return auxhoraInicio;
	}


	public void setAuxhoraInicio(String auxhoraInicio) {
		this.auxhoraInicio = auxhoraInicio;
	}


	public String getAuxfechaFinal() {
		return auxfechaFinal;
	}


	public void setAuxfechaFinal(String auxfechaFinal) {
		this.auxfechaFinal = auxfechaFinal;
	}


	public String getAuxhoraFin() {
		return auxhoraFin;
	}


	public void setAuxhoraFin(String auxhoraFin) {
		this.auxhoraFin = auxhoraFin;
	}


	public SolucitudConsultoriaString(String auxClienteTipoId, String auxTipoConsultoriaId, String auxfechaInicio,
			String auxhoraInicio, String auxfechaFinal, String auxhoraFin) {
		super();
		this.auxClienteTipoId = auxClienteTipoId;
		this.auxTipoConsultoriaId = auxTipoConsultoriaId;
		this.auxfechaInicio = auxfechaInicio;
		this.auxhoraInicio = auxhoraInicio;
		this.auxfechaFinal = auxfechaFinal;
		this.auxhoraFin = auxhoraFin;
	}


	public SolucitudConsultoriaString() {

	}
  
}