package org.pack.ch8.springdata.jpa.home;

import java.util.List;

public interface ContactRepository extends CrudRepository<Contact, Long> {
	List<Contact> findByFirstName(String firstName);
	List<Contact> findByFirstNameAndLastName(String firstName, String lastName);
}
