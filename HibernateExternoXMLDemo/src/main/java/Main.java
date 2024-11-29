import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSession();
		try{
			//Inciamos la transaacion
			session.beginTransaction();
			
			//String hql ="FROM Empleado e WHERE e.nombre =:nombre";
			String hql = "From Empleado";
			Query<Empleado> query = session.createQuery(hql,Empleado.class);
			//query.setParameter("nombre","Antonio");
			List<Empleado>resultados = query.list();
			
			for (Empleado empleado : resultados) {
				System.out.println(empleado);
			}
			//Confirma la transaccion
			session.getTransaction().commit();
		}catch (Exception e) {
			// TODO: handle exception
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
