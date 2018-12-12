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

public class DealerMain{

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
		
		StandardServiceRegistry s=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta=new MetadataSources(s).getMetadataBuilder().build();
		
		SessionFactory factory=meta.getSessionFactoryBuilder().build();
		Session session=factory.openSession();
		Transaction t=session.beginTransaction();
		
		//insertion
		Dealer d=new Dealer();
		d.setId(id);
		d.setName(name);
		d.setAddress(address);
		d.setPhoneno(phoneno);
		d.setCity(city);
		
		session.save(d);
		t.commit();
		
		//deletion
		/*
		Dealer d1=session.get(Dealer.class, 2);
		session.delete(d1);
		t.commit();
		*/
		
		//updation
		/*
		Dealer d2=session.get(Dealer.class, 1);
		d2.setName("Paris");
		session.update(d2);
		t.commit();
		*/
		
		//fetch
		List list=session.createQuery("from Dealer").list();
		for(Iterator i=list.iterator();i.hasNext();) {
			Dealer d3=(Dealer)i.next();
			System.out.println(d3.getId()+","+d3.getName()+","+d3.getAddress()+","+d3.getPhoneno()+","+d3.getCity());
		}
	}
	
}