package org.pack.ch9.spring.transactions.hibernate.home;

import java.io.Serializable;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface CrudRepository<T, ID extends Serializable> extends Repository<T, ID> {
	long count();

	void delete(ID id);

	void delete(Iterable<? extends T> entities);

	void delete(T entity);

	void deleteAll();

	boolean exists(ID id);

	Iterable<T> findAll();

	T findOne(ID id);

	Iterable<T> save(Iterable<? extends T> entities);

	T save(T entity);
}
