package hu.vidragabor.springbatchdojo.userstore.processor;

import hu.vidragabor.springbatchdojo.userstore.model.User;
import lombok.NonNull;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class UserStoreProcessor implements ItemProcessor<User, User> {
	
	private static final String SUFFIX = "_test";
	
	public User process(@NonNull final User user) {
		final User newUser = new User();
		newUser.setFirstName(generateFirstName(user));
		newUser.setLastName(user.getLastName());
		newUser.setAge(user.getAge());
		return newUser;
	}
	
	private String generateFirstName(final User user) {
		return user.getFirstName().concat(SUFFIX);
	}
}
