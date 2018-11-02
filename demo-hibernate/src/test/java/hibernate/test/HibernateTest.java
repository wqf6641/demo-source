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

	// ��������ʵ��
	static {
		try {
			Configuration configuration = new Configuration().configure(); // ʵ���������ļ�
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
					.buildServiceRegistry(); // ʵ��������Ǽ�
			sessionFactory = configuration.buildSessionFactory(serviceRegistry); // ��ȡSession����
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TbScenery scenery = new TbScenery();
		scenery.setJdname("��������");

		// ���ھ�������id���� �� ��Ʊ�����id�������Ǵ������л�ȡ���ģ�����û��������Щֵ��
		// Ҳû�а취Ϊ��Ʊ���󸳾������sceneryId��ֵ
		TbSceneryTickets ticket1 = new TbSceneryTickets();
		ticket1.setTypeName("����Ʊ");

		TbSceneryTickets ticket2 = new TbSceneryTickets();
		ticket2.setTypeName("��ͯƱ");
		
		TbSceneryTickets ticket3 = new TbSceneryTickets();
		ticket3.setTypeName("�������Ʊ");
		
		TbSceneryTickets ticket4 = new TbSceneryTickets();
		ticket4.setTypeName("�м���Ʊ");

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
