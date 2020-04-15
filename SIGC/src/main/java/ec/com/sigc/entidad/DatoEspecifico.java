package ec.com.sigc.entidad;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "dato_especifico")
public class DatoEspecifico implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@NotNull
	@Column(name = "DATO_ESPECIFICO_ID")
	private Integer datoEspecificoId;

	@Column(name = "RESPUESTA")
	private boolean respuesta;

	@Size(max = 900)
	@Column(name = "EVIDENCIA")
	private String evidencia;

	@OneToMany(mappedBy = "datoEspecificoId")
	private List<CheckList> checkListList;

	@JoinColumn(name = "ARCHIVO_ID", referencedColumnName = "ARCHIVO_ID")
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Archivo archivoId;

		public DatoEspecifico() {
	}

		public Integer getDatoEspecificoId() {
			return datoEspecificoId;
		}

		public void setDatoEspecificoId(Integer datoEspecificoId) {
			this.datoEspecificoId = datoEspecificoId;
		}

		public boolean isRespuesta() {
			return respuesta;
		}

		public void setRespuesta(boolean respuesta) {
			this.respuesta = respuesta;
		}

		public String getEvidencia() {
			return evidencia;
		}

		public void setEvidencia(String evidencia) {
			this.evidencia = evidencia;
		}

		public List<CheckList> getCheckListList() {
			return checkListList;
		}

		public void setCheckListList(List<CheckList> checkListList) {
			this.checkListList = checkListList;
		}

		public Archivo getArchivoId() {
			return archivoId;
		}

		public void setArchivoId(Archivo archivoId) {
			this.archivoId = archivoId;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		public DatoEspecifico(@NotNull Integer datoEspecificoId, boolean respuesta, @Size(max = 900) String evidencia,
				List<CheckList> checkListList, Archivo archivoId) {
			super();
			this.datoEspecificoId = datoEspecificoId;
			this.respuesta = respuesta;
			this.evidencia = evidencia;
			this.checkListList = checkListList;
			this.archivoId = archivoId;
		}

}
