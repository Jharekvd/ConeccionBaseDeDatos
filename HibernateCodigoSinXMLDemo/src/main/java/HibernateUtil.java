import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	static {
		try {
			Configuration configuration = new Configuration();
			configuration.addAnnotatedClass(Empleado.class);
			configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
			configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
			configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/empleados_db");
			configuration.setProperty("hibernate.connection.username", "root");
			configuration.setProperty("hibernate.connection.password", "root");
			// Nos permite moficar la tabla tiene otras claves aparte de update
			configuration.setProperty("hibernate.hbm2ddl.auto", "update");
			configuration.setProperty("hibernate.show_sql", "true");

			sessionFactory = configuration.buildSessionFactory();
		} catch (Exception e) {
			System.out.println("SescionFactory fallo a la hora de la creacion: " + e);
			throw new ExceptionInInitializerError(e);
		}
	}
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public static Session getSession() {
		return getSessionFactory().openSession();
	}
	public static void closeSession() {
		if (sessionFactory!=null) {
			sessionFactory.close();
		}
	}
}
