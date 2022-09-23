package com.training.ifaces;

import java.util.Collection;


public interface CrudRepository<T> {
	public boolean save(T obj);
	public Collection<T> findAll() throws Exception;
}
