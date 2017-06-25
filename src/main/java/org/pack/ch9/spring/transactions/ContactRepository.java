package org.pack.ch9.spring.transactions;

import org.springframework.data.jpa.repository.Query;

public interface ContactRepository extends CrudRepository<Contact, Long> {
	@Query("select count(c) from contact c")
	Long countAllContacts();
//	List<Contact> findByFirstName(String firstName);
//	List<Contact> findByFirstNameAndLastName(String firstName, String lastName);
}
