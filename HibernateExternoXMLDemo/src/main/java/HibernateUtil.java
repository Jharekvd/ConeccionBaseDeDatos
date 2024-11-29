import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	@SuppressWarnings("unused")
	private static SessionFactory sessionFactory;
	static {
		try {
			//Cargamos la configuracion de hibernate desde el archivo
			Configuration configuration = new Configuration().configure();
			sessionFactory = configuration.buildSessionFactory();
		} catch (Exception e) {
			// TODO: handle exception
			//Manejamos el error en caso de fallo durante la creacion de la sessionFactory
			System.out.println("SescionFactory fallo a la hora de la creacion: "+e);
			throw new ExceptionInInitializerError(e);
		}
	}
	//Es un singleton que asegura que solo haya una instacion de sessionFactory
	//en la aplicacion
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	//Metodo para obtener una nueva version
	public static Session getSession() {
		return getSessionFactory().openSession();
	}
	//Metodo para cerrar la sesion
	public static void closeSession() {
		if (sessionFactory!=null) {
			sessionFactory.close();
		}
	}

}
