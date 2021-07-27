package hu.vidragabor.springbatchdojo.component;

import hu.vidragabor.springbatchdojo.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserItemProcessor implements ItemProcessor<User, User> {
	@Override
	public User process(final User user) {
		
		final User transformedUser = new User();
		transformedUser.setAge(user.getAge());
		transformedUser.setFirstName(user.getFirstName());
		transformedUser.setLastName(user.getLastName());
		
		log.info("Converting (" + user + ") into (" + transformedUser + ")");
		
		return transformedUser;
	}
}
