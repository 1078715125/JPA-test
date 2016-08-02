package com.atguigu.test;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.atguigu.springdata.entity.News;
import com.atguigu.springdata.repository.NewsRepository;

public class ApplicationIOCTest {

	private ApplicationContext ioc;
	private NewsRepository newsRepository;

	private DataSource dataSource;
	{
		ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
		dataSource = ioc.getBean(DataSource.class);
		newsRepository = ioc.getBean(NewsRepository.class);
	}

	/**
	 * 注意: Order 是 Sort 的静态内部类.
	 */
	@Test
	public void testJpaRepository() {
		Sort sort = new Sort(new Order(Direction.ASC, "title"), new Order(
				Direction.DESC, "id"));
		List<News> list = newsRepository.findAll(sort);
		for (News news : list) {
			System.out.println(news);
		}
	}

	@Test
	public void testCrudRepository() {
		long count = newsRepository.count();
		System.out.println(count);
	}

	@Test
	public void testSpringData() {
		News news = newsRepository.getById(1);
		System.out.println(news);
	}

	@Test
	public void testJpa() {
		EntityManagerFactory entityManagerFactory = ioc
				.getBean(EntityManagerFactory.class);
		EntityManager entityManager = entityManagerFactory
				.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		News news = new News();
		news.setTitle("新浪新闻");
		news.setPublishDate(new Date());

		entityManager.persist(news);
		transaction.commit();
		entityManager.close();
		entityManagerFactory.close();
	}

	@Test
	public void testDataSource() throws SQLException {
		System.out.println(dataSource.getConnection().getClass());
	}

}
