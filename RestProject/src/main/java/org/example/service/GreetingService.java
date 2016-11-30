package org.example.service;

import java.util.Collection;
import org.example.model.Greeting;

public interface GreetingService {
	
	Collection<Greeting> findAll();
	
	Greeting findOne(int id);
	
	Greeting create(Greeting greeting);
	
	Greeting update(Greeting greeting);
	
	void delete(long id);
	
}
