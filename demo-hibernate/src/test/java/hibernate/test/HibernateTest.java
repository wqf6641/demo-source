package hibernate.test;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import demo.pojo.TbScenery;
import demo.pojo.TbSceneryTickets;

public class HibernateTest {

	private static SessionFactory sessionFactory;

	// 创建工厂实例
	static {
		try {
			Configuration configuration = new Configuration().configure(); // 实例化配置文件
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
					.buildServiceRegistry(); // 实例化服务登记
			sessionFactory = configuration.buildSessionFactory(serviceRegistry); // 获取Session工厂
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TbScenery scenery = new TbScenery();
		scenery.setJdname("东方明珠");

		// 由于景点对象的id主键 和 门票对象的id主键都是从序列中获取到的，这里没法设置这些值。
		// 也没有办法为门票对象赋景点外键sceneryId的值
		TbSceneryTickets ticket1 = new TbSceneryTickets();
		ticket1.setTypeName("成人票");

		TbSceneryTickets ticket2 = new TbSceneryTickets();
		ticket2.setTypeName("儿童票");
		
		TbSceneryTickets ticket3 = new TbSceneryTickets();
		ticket3.setTypeName("退伍军人票");
		
		TbSceneryTickets ticket4 = new TbSceneryTickets();
		ticket4.setTypeName("残疾人票");

		List<TbSceneryTickets> tbSceneryTickets = new ArrayList<TbSceneryTickets>();
		tbSceneryTickets.add(ticket1);
		tbSceneryTickets.add(ticket2);
		tbSceneryTickets.add(ticket3);
		tbSceneryTickets.add(ticket4);
		scenery.setTbSceneryTickets(tbSceneryTickets);

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(scenery);
		tx.commit();
		session.close();

	}
}
