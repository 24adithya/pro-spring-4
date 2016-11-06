package org.pack.ch6.springjdbc;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class JDBCAutoContactDAO extends JdbcDaoSupport implements ContactDAO, InitializingBean {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public List<Contact> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Contact> findByFirstName(String firstName) {
		// TODO Auto-generated method stub
		return null;
	}

	public String findLastNameById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public String findFirstNameById(Long id) {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public List<Contact> findAllWithDetail() {
		// TODO Auto-generated method stub
		return null;
	}

}
