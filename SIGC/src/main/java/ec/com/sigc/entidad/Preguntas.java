package ec.com.sigc.entidad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "preguntas")
public class Preguntas implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @NotNull
    @Column(name = "PREGUNTAS_ID")
    private Integer preguntasId;
    
    @Column(name = "PREGUNTAS")
    private String preguntas;
    
    @OneToMany(mappedBy = "preguntasId")
    private List<CheckList> checkListList;
    
    @JoinColumn(name = "SECCION_ID", referencedColumnName = "SECCION_ID")
    @ManyToOne
    private Seccion seccionId;
    
    @JoinColumn(name = "TIPO_CONSULTORIA_ID", referencedColumnName = "TIPO_CONSULTORIA_ID")
    @ManyToOne
    private TipoConsultoria tipoConsultoriaId;

   	public Integer getPreguntasId() {
		return preguntasId;
	}

	public void setPreguntasId(Integer preguntasId) {
		this.preguntasId = preguntasId;
	}

	public String getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(String preguntas) {
		this.preguntas = preguntas;
	}

	public List<CheckList> getCheckListList() {
		return checkListList;
	}

	public void setCheckListList(List<CheckList> checkListList) {
		this.checkListList = checkListList;
	}

	public Seccion getSeccionId() {
		return seccionId;
	}

	public void setSeccionId(Seccion seccionId) {
		this.seccionId = seccionId;
	}

	public TipoConsultoria getTipoConsultoriaId() {
		return tipoConsultoriaId;
	}

	public void setTipoConsultoriaId(TipoConsultoria tipoConsultoriaId) {
		this.tipoConsultoriaId = tipoConsultoriaId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Preguntas(@NotNull Integer preguntasId, String preguntas, List<CheckList> checkListList,
			Seccion seccionId, TipoConsultoria tipoConsultoriaId) {
		super();
		this.preguntasId = preguntasId;
		this.preguntas = preguntas;
		this.checkListList = checkListList;
		this.seccionId = seccionId;
		this.tipoConsultoriaId = tipoConsultoriaId;
	}

	public Preguntas(@NotNull Integer preguntasId, @Size(max = 200) String preguntas, Seccion seccionId,
			TipoConsultoria tipoConsultoriaId) {
		super();
		this.preguntasId = preguntasId;
		this.preguntas = preguntas;
		this.seccionId = seccionId;
		this.tipoConsultoriaId = tipoConsultoriaId;
	}

	public Preguntas() {
    }

    
    
}
