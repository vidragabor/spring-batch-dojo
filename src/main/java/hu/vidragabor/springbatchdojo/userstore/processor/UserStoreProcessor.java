package hu.vidragabor.springbatchdojo.userstore.processor;

import hu.vidragabor.springbatchdojo.model.User;
import lombok.NonNull;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class UserStoreProcessor implements ItemProcessor<User, User> {
	
	private static final String SUFFIX = "_test";
	
	@Override
	public User process(@NonNull final User user) {
		return createNewUser(user);
	}
	
	private User createNewUser(final User user) {
		return User.builder()
				.firstName(generateName(user.getFirstName()))
				.lastName(user.getLastName())
				.age(user.getAge())
				.build();
	}
	
	private String generateName(final String name) {
		return name.concat(SUFFIX);
	}
}