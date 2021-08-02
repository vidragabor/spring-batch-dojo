package hu.vidragabor.springbatchdojo.userstore.processor;

import hu.vidragabor.springbatchdojo.userstore.model.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class UserStoreProcessor implements ItemProcessor<User, User> {
	
	private static final String SUFFIX = "_test";
	
	@Override
	public User process(final User user) {
		user.setFirstName(generateFirstName(user));
		return user;
	}
	
	private String generateFirstName(final User user) {
		return user.getFirstName().concat(SUFFIX);
	}
}
