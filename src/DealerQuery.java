import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.*;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class DealerQuery{
	public static void main(String[]args){
		StandardServiceRegistry s=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta=new MetadataSources(s).getMetadataBuilder().build();
		
		SessionFactory factory=meta.getSessionFactoryBuilder().build();
		Session session=factory.openSession();
		Transaction t=session.beginTransaction();
		//all details
		System.out.println("All details");
		Criteria cr = session.createCriteria(Dealer.class);
		List<Dealer>results = cr.list();
		for(Dealer da:results){
			System.out.println(da.getId()+","+da.getName()+","+da.getAddress()+","+da.getPhoneno()+","+da.getCity());
		}
	
		//based on city
		System.out.println("Based on city");
		Criteria ccity=session.createCriteria(Dealer.class);
		ccity.add(Restrictions.eq("city","atlanta"));
		List<Dealer>cityresults = ccity.list();
		for(Dealer dc:cityresults){
			System.out.println(dc.getId()+","+dc.getName()+","+dc.getAddress()+","+dc.getPhoneno()+","+dc.getCity());
		}
		
		//names start with s
		System.out.println("Names that start with s");
		Criteria cname=session.createCriteria(Dealer.class)
				.add(Restrictions.disjunction()
				.add(Restrictions.like("name", "s%"))
				.add(Restrictions.like("name", "S%"))
				);
		List<Dealer>nameresults=cname.list();
		for(Dealer dn:nameresults){
			System.out.println(dn.getId()+","+dn.getName()+","+dn.getAddress()+","+dn.getPhoneno()+","+dn.getCity());
		}
		
		//descending address
		System.out.println("Details by descending address");
		Criteria c1=session.createCriteria(Dealer.class).add(Restrictions.like("name", "%"));
		c1.addOrder(Order.desc("address"));
		List<Dealer>obj1=c1.list();
		obj1=c1.list();
		for(Dealer d2:obj1){
			System.out.println(d2.getId()+","+d2.getName()+","+d2.getAddress()+","+d2.getPhoneno()+","+d2.getCity());
		}
		
		//only dealer name
		System.out.println("Dealer name only");
		Criteria c=session.createCriteria(Dealer.class);
		c.setProjection(Projections.property("name"));
		List<String>str=c.list();
		for(String st:str){
			System.out.println(st);
		}
	}
}