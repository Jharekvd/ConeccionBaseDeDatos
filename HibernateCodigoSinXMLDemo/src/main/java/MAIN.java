import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class MAIN {
	public static void main(String[] args) {
		//Abrimos una sesion
		Session session = HibernateUtil.getSession();
		try {
			session.beginTransaction();
			String hql = "From Empleado";
			Query<Empleado>query = session.createQuery(hql,Empleado.class);
			List<Empleado>resultado = query.list();
			for (Empleado empleado : resultado) {
				System.out.println(empleado);
			}
			//Confirmamos la transsaccion
			session.getTransaction().commit();
		}catch (Exception e) {
			//Si hay un error revertimos
			if (session.getTransaction()!=null) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		}finally {
			session.close();
			HibernateUtil.closeSession();
		}
	}
}
