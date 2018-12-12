import java.util.*;

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

public class EmployeeMain{

	public static void main(String[]args){
		Scanner k=new Scanner(System.in);
		System.out.println("Enter id: ");
		int id=k.nextInt();k.nextLine();
		System.out.println("Enter name: ");
		String name=k.nextLine();
		System.out.println("Enter address: ");
		String address=k.nextLine();
		System.out.println("Enter phone number: ");
		String phoneno=k.nextLine();
		System.out.println("Enter city: ");
		String city=k.nextLine();
		System.out.println("Enter salary: ");
		double salary=k.nextDouble();k.nextLine();
		
		StandardServiceRegistry s=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta=new MetadataSources(s).getMetadataBuilder().build();
		
		SessionFactory factory=meta.getSessionFactoryBuilder().build();
		Session session=factory.openSession();
		Transaction t=session.beginTransaction();
		
		//insertion
		Employee e=new Employee();
		
		e.setId(id);
		e.setName(name);
		e.setAddress(address);
		e.setPhoneno(phoneno);
		e.setCity(city);
		e.setSalary(salary);
		
		session.save(e);
		t.commit();
		
		//deletion
		/*
		Employee e1=session.get(Employee.class, 2);
		session.delete(e1);
		t.commit();
		*/
		
		//updation
		/*
		Employee e2=session.get(Employee.class, 1);
		e2.setName("Paris");
		session.update(e2);
		t.commit();
		*/
		
		//fetch
		List list=session.createQuery("from Employee").list();
		for(Iterator i=list.iterator();i.hasNext();) {
			Employee e3=(Employee)i.next();
			System.out.println(e3.getId()+","+e3.getName()+","+e3.getAddress()+","+e3.getPhoneno()+","+e3.getCity()+","+e3.getSalary());
		}
	}
	
}