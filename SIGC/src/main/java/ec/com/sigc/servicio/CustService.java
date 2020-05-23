package ec.com.sigc.servicio;

import ec.com.sigc.entidad.Cliente;
import ec.com.sigc.entidad.User;

public interface CustService {
	public abstract void custSave(Cliente cliente);
	public abstract void custUpdate(String usuario, String longitud, String latitud);
	//public abstract ClienteTipo findCustTipe(int tipoCLienteId);
	public abstract Cliente findCustById(int clienteId);
	public abstract Cliente findCustByUser(User user);
}
