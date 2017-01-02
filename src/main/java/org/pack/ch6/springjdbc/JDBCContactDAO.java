package org.pack.ch6.springjdbc;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class JDBCContactDAO implements ContactDAO, InitializingBean {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		MySQLErrorCodesTranslator errorTranslator = new MySQLErrorCodesTranslator();
		errorTranslator.setDataSource(dataSource);
		jdbcTemplate.setExceptionTranslator(errorTranslator);
		this.jdbcTemplate = jdbcTemplate;
	}

	public void afterPropertiesSet() throws Exception {
		if (dataSource == null) {
			throw new BeanCreationException("Must set dataSource on ContactDao");
		}
	}

	public List<Contact> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Contact> findByFirstName(String firstName) {
		// TODO Auto-generated method stub
		return null;
	}

	public String findLastNameById(Long id) {
		return null;
	}

	public String findFirstNameById(Long id) {
		return jdbcTemplate.queryForObject("select first_name from contact where id = ?", new Object[] { id },
				String.class);
	}

	public void insert(Contact contact) {
		// TODO Auto-generated method stub

	}

	public void update(Contact contact) {
		// TODO Auto-generated method stub

	}

	public void delete(Long contactId) {
		// TODO Auto-generated method stub

	}
	
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
//		ctx.load("classpath:META-INF/spring/app-context-spring-jdbc.xml");
		ctx.load("classpath:META-INF/spring/app-context-embedded-jdbc.xml");
		ctx.refresh();
		ContactDAO contactDao = ctx.getBean("contactDAO", ContactDAO.class);
		System.out.println("First name for contact id 1 is: " + contactDao.findFirstNameById(1l));
	}

	@Override
	public List<Contact> findAllWithDetail() {
		// TODO Auto-generated method stub
		return null;
	}

}
