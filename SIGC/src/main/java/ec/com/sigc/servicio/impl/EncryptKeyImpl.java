package ec.com.sigc.servicio.impl;

import org.springframework.stereotype.Service;

import ec.com.sigc.crypto.bcrypt.BCryptPasswordEncoder;
import ec.com.sigc.servicio.EncryptKey;

@Service("encryptKey")
public class EncryptKeyImpl implements EncryptKey {

	@Override
	public String encryptKey(String clave) {
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
		return pe.encode(clave);
	}
	
	

}
