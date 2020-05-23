package ec.com.sigc.preLoad;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ec.com.sigc.entidad.BackOffice;
import ec.com.sigc.entidad.Cliente;
import ec.com.sigc.entidad.Consultor;
import ec.com.sigc.entidad.Preguntas;
import ec.com.sigc.entidad.RoleSys;
import ec.com.sigc.entidad.Seccion;
import ec.com.sigc.entidad.TipoConsultoria;
import ec.com.sigc.entidad.User;
import ec.com.sigc.repositorio.BackRepository;
import ec.com.sigc.repositorio.ConsultorRepository;
import ec.com.sigc.repositorio.CustRepository;
import ec.com.sigc.repositorio.QuestJpaRepository;
import ec.com.sigc.repositorio.RoleSysRepository;
import ec.com.sigc.repositorio.SectionRepository;
import ec.com.sigc.repositorio.UserRepository;
import ec.com.sigc.servicio.CustService;
import ec.com.sigc.repositorio.TipoConsRepository;

@Configuration
@Slf4j
class LoadDatabase {

	@Bean
	CommandLineRunner initDatabase(UserRepository userRepository, RoleSysRepository roleSysRepository, 
			BackRepository backRepository, ConsultorRepository consRepo, CustRepository clienteRepositorio, TipoConsRepository tipoConsRepository,
			SectionRepository sectionRepository, QuestJpaRepository questRepository) {
		return args -> {
			//roles
			roleSysRepository.save(new RoleSys(1, "ADMINISTRADOR"));
			roleSysRepository.save(new RoleSys(2, "BACK-OFFICE"));
			roleSysRepository.save(new RoleSys(3, "CONSULTOR"));
			roleSysRepository.save(new RoleSys(4, "CLIENTE"));
			//administradores
			userRepository.save(new User(1, "admin", "admin", "admin@sigc", Long.valueOf("9999999999"), "admin", "admin", "UCE", roleSysRepository.findById(1).get(), true));
			//back-office
			userRepository.save(new User(2, "back", "back", "back@sigc", Long.valueOf("9999999999"), "back", "back", "UCE", roleSysRepository.findById(2).get(), true));
			backRepository.save(new BackOffice(1, "1721", userRepository.findById(2).get()));
			
			//consultor
			userRepository.save(new User(3, "cons", "cons", "cons@sigc", Long.valueOf("9999999999"), "cons", "cons", "UCE", roleSysRepository.findById(3).get(), true));
			consRepo.save(new Consultor(1, null, userRepository.findById(3).get()));
			
			//cliente
			userRepository.save(new User(4, "cli", "cli", "cli@sigc", Long.valueOf("9999999999"), "cli", "cli", "UCE", roleSysRepository.findById(4).get(), true));
			clienteRepositorio.save(new Cliente(1, "0609", "Lenervil.corp", userRepository.findById(4).get()));
			
			//tipo cuestionarios
			
			TipoConsultoria tC = tipoConsRepository.save(new TipoConsultoria(1, "ISO 9001:2015"));
			
			//secciones
			Seccion s1 = sectionRepository.save(new Seccion(	1	,"	Contexto de la organizaci�n	"));
			Seccion s2 =  sectionRepository.save(new Seccion(	2	,"	Liderazgo	"));
			Seccion s3 = sectionRepository.save(new Seccion(	3	,"	Planificaci�n	"));
			Seccion s4 = sectionRepository.save(new Seccion(	4	,"	Apoyo	"));
			Seccion s5 = sectionRepository.save(new Seccion(	5	,"	Operaci�n	"));
			Seccion s6 = sectionRepository.save(new Seccion(	6	,"	Evaluaci�n de desempe�o	"));
			Seccion s7 = sectionRepository.save(new Seccion(	7	,"	Mejora	"));
			
			//preguntas
			questRepository.save(new Preguntas(	1	, "	Mantiene un an�lisis de contexto, donde se han identificado las cuestiones internas y externas que afectan al desarrollo de los productos / servicios de la organizaci�n	", s1, tC));
			questRepository.save(new Preguntas(	2	, "	Ha identificado las necesidades y expectativas de las partes interesadas de la organizaci�n	", s1, tC));
			questRepository.save(new Preguntas(	3	, "	Ha determinado medios de seguimiento para asegurar cumplir con las necesidades / expectativas de las partes interesadas	", s1, tC));
			questRepository.save(new Preguntas(	4	, "	Ha definido el alcance del sistema integrado de gesti�n, y definido la no aplicabilidad de clausulas cuando sea apropiado de acuerdo al giro de negocio	", s1, tC));
			questRepository.save(new Preguntas(	5	, "	Se ha identificado entradas, salidas, controles, indicadores, recursos, aspectos ambientales, RIESGOS en los distintos procesos definidos y aplicables al SGC	", s1, tC));
			questRepository.save(new Preguntas(	6	, "	La alta direcci�n mantiene un criterio claro sobre la comprensi�n del SGC, sus implicaciones, asignaci�n de recursos para la mejora y eficacia del sistema	", s2, tC));
			questRepository.save(new Preguntas(	7	, "	La alta direcci�n rinde cuentas sobre el rendimiento y eficacia del SGC	", s2, tC));
			questRepository.save(new Preguntas(	8	, "	La alta direcci�n comunica al personal sus objetivos, cumplimiento actual y estado de la organizaci�n respecto de sus resultados y SGC	", s2, tC));
			questRepository.save(new Preguntas(	9	, "	Se ha establecido una pol�tica de calidad en la organizaci�n	", s2, tC));
			questRepository.save(new Preguntas(	10	, "	Es apropiada al contexto de la organizaci�n; incluye compromiso con el cumplimiento de requisitos aplicables y mejora continua	", s2, tC));
			questRepository.save(new Preguntas(	11	, "	La pol�tica se comunica, se mantiene vigente y es entendida por las partes interesadas	", s2, tC));
			questRepository.save(new Preguntas(	12	, "	Se han asignado  responsabilidades y autoridades para los distintos procesos definidos por la organizaci�n	", s2, tC));
			questRepository.save(new Preguntas(	13	, "	Se tiene definido acciones para abordar los riesgos y oportunidades por cada proceso definido dentro del alcance de la organizaci�n, as� como del an�lisis del contexto	", s3, tC));
			questRepository.save(new Preguntas(	14	, "	Se encuentra establecido un m�todo para identificiar, evaluar, establecer acciones y analizar eficacia para los riesgos y oportunidades	", s3, tC));
			questRepository.save(new Preguntas(	15	, "	Se mantienen objetivos de calidad coherentes con la pol�tica, son medibles. Adicional, se ha establecido un m�todo de seguimiento para evaluar cumplimiento y establecer mejoras.	", s3, tC));
			questRepository.save(new Preguntas(	16	, "	Se han comunicado los objetivos al personal pertinente	", s3, tC));
			questRepository.save(new Preguntas(	17	, "	Se ha definido en los objetivos: Que se va a hacer,  que recursos se requieren,  responsable, finalizaci�n y como se evaluaran resultados	", s3, tC));
			questRepository.save(new Preguntas(	18	, "	Cuando se determinan cambios en el SGC, estos se llevan a cabo de manera planificada?	", s3, tC));
			questRepository.save(new Preguntas(	19	, "	Se han definido recursos para la implementaci�n y  mejora del SGC	", s4, tC));
			questRepository.save(new Preguntas(	20	, "	Estan definidas las personas necesarias para cumplir cada proceso definido en la organizaci�n	", s4, tC));
			questRepository.save(new Preguntas(	21	, "	Se ha definido y mantenido los equipos, infraestructura, recursos de transporte, y tecnolog�a necesaria para la organizaci�n	", s4, tC));
			questRepository.save(new Preguntas(	22	, "	Se realiza un an�lisis del clima laboral, se ha establecido acciones para mejorar el ambiente laboral, considerando el trabajo seguro y previniendo riesgos ergon�micos, psicosociales, etc	", s4, tC));
			questRepository.save(new Preguntas(	23	, "	Se identifican los equipos necesarios de medici�n para asegurar la calidad de nuestros productos / servicios otorgados	", s4, tC));
			questRepository.save(new Preguntas(	24	, "	Se ha determinado un m�todo para calibrar, o verificar estos equipos a Intervalos planificados	", s4, tC));
			questRepository.save(new Preguntas(	25	, "	Existe trazabilidad de medicion que testifique la valid�z de los patrones de medici�n	", s4, tC));
			questRepository.save(new Preguntas(	26	, "	Si el equipo es calibrado, se tiene la informaci�n documentada necesaria para demostrar que el proveedor es competente para realizar dicha actividad	", s4, tC));
			questRepository.save(new Preguntas(	27	, "	Se ha determinado el conocimiento necesario de la organizaci�n, proveniente de las experiencias pasadas, resultados de �xito, fracaso, tomando en cuenta las condiciones cambiantes, para asegurar que ese conocimiento no escape de la misma	", s4, tC));
			questRepository.save(new Preguntas(	28	, "	Se ha determinado la competencia necesaria del personal para su proceso a desempe�ar	", s4, tC));
			questRepository.save(new Preguntas(	29	, "	El personal es conciente de la pol�tica  de calidad,  objetivos de calidad, como aporta a la eficacia del SGC	", s4, tC));
			questRepository.save(new Preguntas(	30	, "	Mantiene un plan de comunicaci�n que contemple la comunicaci�n tanto interna como externa	", s4, tC));
			questRepository.save(new Preguntas(	31	, "	Ha definido un procedimiento / mecanismo para asegurar la identificaci�n, disposici�n, seguridad, ubicaci�n, resguardo, etc de la informaci�n documentada	", s4, tC));
			questRepository.save(new Preguntas(	32	, "	Se ha desarrollado mecanismos de planificaci�n de actividades, tomando en cuenta la informaci�n documentada necesaria para respaldar su identificaci�n, comunicaci�n con el cliente y control de cambios	", s5, tC));
			questRepository.save(new Preguntas(	33	, "	Se han definido mecanismos para la gesti�n de los Requisitos para los productos / servicios	", s5, tC));
			questRepository.save(new Preguntas(34,"Se ha planificado el dise�o y desarrollo de sus productos/servicios tomando en cuenta las etapas, controles, actividades de verificaci�n y validaci�n; responsabilidades, autoridades, requisitos y recursos", s5, tC));
			questRepository.save(new Preguntas(35, "	Para el dise�o y desarrollo de los productos / servicios, la organizaci�n ha establecido mecanismos para determinar las entradas para el dise�o y desarrollo tomando en cuenta: Los requisitos funcionales, ", s5, tC));
			questRepository.save(new Preguntas(36, "Se han definido controles para el dise�o y desarrollo; incluyendo mecanismos para controlar los cambios del dise�o y desarrollo conservando informaci�n documentada	", s5, tC));
			questRepository.save(new Preguntas(	37	, "	Se establecen lineamientos para asegurar las salidas del dise�o y desarrollo de acuerdo con: Cumplimiento  de  los requisitos de las entradas, adecuacion a los procesos posteriores (Validados), cumplen ", s5, tC));
			questRepository.save(new Preguntas(	38	, "	Se ha defindo mecanismos para:	-Identificar a los proveedores de servicio / productos suministrados externamente - Seleccionar, aprobar, evaluar y medir el desempe�o de dichos proveedores"	, s5, tC));
			questRepository.save(new Preguntas(	39	, "	Se han definido los mecanismos y condiciones controladas para cumplir con los requerimientos controlados para la gesti�n de los productos - servicios que ofrece	", s5, tC));
			questRepository.save(new Preguntas(	40	, "	Se han establecido mecanismos e informaci�n documentada para la verificaci�n / validaci�n de sus productos, considerando los requisitos legales, normativos, reglamentarios y contractuales aplicables	", s5, tC));
			questRepository.save(new Preguntas(	41	, "	Las salidas no conformes se identifican, se controlan y se establecen acciones 	", s5, tC));
			questRepository.save(new Preguntas(	42	, "	Se ha identificado que medir en la oganizaci�n para medir el desempe�o del SGC y sus procesos	", s6, tC));
			questRepository.save(new Preguntas(	43	, "	Se ha definido un metodo de medici�n, frecuencia para medici�n. Adicional se considera acciones preventivas / correctivas de mejora continua cuando los objetivos no se alcanzan	", s6, tC));
			questRepository.save(new Preguntas(	44	, "	Est� definido un mecanismo de evaluaci�n de la satisfacci�n de las necesidades y expectativas de los clientes, se an�liza esta informaci�n para la mejora continua	", s6, tC));
			questRepository.save(new Preguntas(	45	, "	Se ha definido realizar auditor�as internas del SGC a intervalos planificados, as� como toda la informaci�n documentada para demostrar la realizaci�n de las auditor�as planificadas, as� como sus acciones de mejora 	", s6, tC));
			questRepository.save(new Preguntas(	46	, "	Se ha definido un mecanismo de selecci�n de auditores internos, que contemple ISO 9001:2015	", s6, tC));
			questRepository.save(new Preguntas(	47	, "	La direcci�n revisa a intervalos planificados los siguientes puntos: a)el estado de las acciones de las revisiones por la direcci�n previas; b)los cambios en las cuestiones externas e internas que sean pertinentes ", s6, tC));
			questRepository.save(new Preguntas(	48	, "	Se obtienen, de la revisi�n por la direcci�n, acciones de mejora y asignaci�n de recursos?	", s6, tC));
			questRepository.save(new Preguntas(	49	, "	La organizaci�n  mejora sus procesos, productos, servicios y eficacia del SGC	", s7, tC));
			questRepository.save(new Preguntas(	50	, "	Establece acciones de mejora continua frente a los resultados no alcanzados	", s7, tC));
			questRepository.save(new Preguntas(	51	, "	Se analiza eficacia de las acciones tomadas dentro del SGC	", s7, tC));

			
		};
	}
}
