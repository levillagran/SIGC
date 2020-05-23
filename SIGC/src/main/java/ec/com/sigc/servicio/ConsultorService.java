package ec.com.sigc.servicio;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ec.com.sigc.entidad.CheckList;
import ec.com.sigc.entidad.Informe;


public interface ConsultorService {
	public abstract List<Informe> findAllAssignedAudits(String auditor);
	public abstract List<Informe> findAllAuditsSendNC(String auditor);
	public abstract List<Informe> findAllAuditsToCheck(String auditor);
	public abstract List<Informe> findAllAuditsHistory(String auditor);
	public abstract void createCkeckList(int informeId);
	public abstract CheckList reply(int informeId);
	public abstract CheckList replyUploadFile(int informeId);
	public abstract CheckList replyPost(int informeId, String codigo, String accion);
	public abstract CheckList replyPostUploadFile(int informeId, String codigo, String accion);
	public abstract void saveReply(MultipartFile f, String evidencia, boolean respuesta, String codigo);
	public abstract String sendNonConformities(Integer informeId);
	public abstract String sendToCheck(Integer informeId);
	public abstract Integer firstQualification(Informe inf);
	public abstract void finalQualification(Integer id);
}
