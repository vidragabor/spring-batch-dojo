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
		user.setFirstName(user.getFirstName() + "_test");
		log.debug("Create user: {}", user);
		return user;
	}
}
