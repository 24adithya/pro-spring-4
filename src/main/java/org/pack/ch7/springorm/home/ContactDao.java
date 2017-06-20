package org.pack.ch7.springorm.home;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

public interface ContactDao {
	List<Contact> findAll();

	List<Contact> findAllWithDetail();

	Contact findById(Long id);

	Contact save(Contact contact);

	void delete(Contact contact);

	List<Contact> findAll(GenericXmlApplicationContext ctx);
}
