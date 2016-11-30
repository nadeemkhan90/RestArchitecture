package org.example.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.example.model.Greeting;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
@Configuration
public class GreetingServiceBean implements GreetingService{
	
	private static int nextInt;
	private static Map<Integer, Greeting> greetingMap;
	
	private static Greeting save(Greeting greeting) {
		if (greetingMap == null) {
			greetingMap = new HashMap<Integer, Greeting>();
			nextInt = 1;
		}

		if (greeting.getId() != 0) { //update case
			Greeting oldGreeting = greetingMap.get(greeting.getId());
			if (oldGreeting == null) {
				return null;
			}

			greetingMap.remove(greeting.getId());
			greetingMap.put(greeting.getId(), greeting);
			return greeting;
		}
		//create case
		greeting.setId(nextInt);
		nextInt++;
		greetingMap.put(greeting.getId(), greeting);
		return greeting;
	}

	static{Greeting g1=new Greeting();g1.setText("Hey");
		save(g1);

		Greeting g2 = new Greeting();
		g2.setText("Hi");
		save(g2);

	}
	@Override
	public Collection<Greeting> findAll() {
		Collection<Greeting> greetings = greetingMap.values();
		return greetings;
	}

	@Override
	public Greeting findOne(int id) {
		Greeting greeting = greetingMap.get(id);
		return greeting;
	}

	@Override
	public Greeting create(Greeting greeting) {
		Greeting savedGreeting = save(greeting);
		return greeting;
	}

	@Override
	public Greeting update(Greeting greeting) {
		Greeting updateGreeting = save(greeting);
		return updateGreeting;

	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

}
