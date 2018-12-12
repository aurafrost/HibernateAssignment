import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class EmployeeQuery{
	public static void main(String[]args){
		StandardServiceRegistry s=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta=new MetadataSources(s).getMetadataBuilder().build();
		
		SessionFactory factory=meta.getSessionFactoryBuilder().build();
		Session session=factory.openSession();
		Transaction t=session.beginTransaction();
		//all details
		System.out.println("All details");
		List list=session.createQuery("from Employee").list();
		for(Iterator i=list.iterator();i.hasNext();) {
			Employee e=(Employee)i.next();
			System.out.println(e.getId()+","+e.getName()+","+e.getAddress()+","+e.getPhoneno()+","+e.getCity()+","+e.getSalary());
		}
		
		//salary between 30000 and 50000
		System.out.println("salary between 30000 and 50000");
		List list2=session.createQuery("from Employee where(salary>30000 and salary<50000)").list();
		for(Iterator i=list2.iterator();i.hasNext();) {
			Employee e=(Employee)i.next();
			System.out.println(e.getId()+","+e.getName()+","+e.getAddress()+","+e.getPhoneno()+","+e.getCity()+","+e.getSalary());
		}
		
		//total salary
		System.out.println("total salary");
		Query q=session.createQuery("select sum(salary) from Employee");
		List<Integer>p=q.getResultList();
		System.out.println(p.get(0));
		
		//based on descending city
		System.out.println("Descending city");
		List list3=session.createQuery("from Employee order by city desc").list();
		for(Iterator i=list3.iterator();i.hasNext();) {
			Employee e=(Employee)i.next();
			System.out.println(e.getId()+","+e.getName()+","+e.getAddress()+","+e.getPhoneno()+","+e.getCity()+","+e.getSalary());
		}
		
	}
}