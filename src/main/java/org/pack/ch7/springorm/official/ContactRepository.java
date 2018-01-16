package org.pack.ch7.springorm.official;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ContactRepository extends CrudRepository<Contact, Long> {
	List<Contact> findByFirstName(String firstName);
	List<Contact> findByFirstNameAndLastName(String firstName, String lastName);
}
