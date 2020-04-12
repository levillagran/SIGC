package ec.com.sigc.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "estado_auditoria")
public class EstadoConsultoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ESTADO_CONSULTORIA_ID")
    private Integer estadoConsultoriaId;
    @Size(max = 20)
    @Column(name = "ESTADO_CONSULTORIA")
    private String estadoConsultoria;
    @OneToMany(mappedBy = "SOLICITUD_CONSULTORIA_ID")
    private List<SolicitudConsultoria> solicitudConsultoriaList;
    
    
	public Integer getEstadoConsultoriaId() {
		return estadoConsultoriaId;
	}


	public void setEstadoConsultoriaId(Integer estadoConsultoriaId) {
		this.estadoConsultoriaId = estadoConsultoriaId;
	}


	public String getEstadoConsultoria() {
		return estadoConsultoria;
	}


	public void setEstadoConsultoria(String estadoConsultoria) {
		this.estadoConsultoria = estadoConsultoria;
	}


	public List<SolicitudConsultoria> getSolicitudConsultoriaList() {
		return solicitudConsultoriaList;
	}


	public void setSolicitudConsultoriaList(List<SolicitudConsultoria> solicitudConsultoriaList) {
		this.solicitudConsultoriaList = solicitudConsultoriaList;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public EstadoConsultoria(@NotNull Integer estadoConsultoriaId, @Size(max = 20) String estadoConsultoria,
			List<SolicitudConsultoria> solicitudConsultoriaList) {
		super();
		this.estadoConsultoriaId = estadoConsultoriaId;
		this.estadoConsultoria = estadoConsultoria;
		this.solicitudConsultoriaList = solicitudConsultoriaList;
	}


	public EstadoConsultoria() {
    }

    
}
