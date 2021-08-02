package hu.vidragabor.springbatchdojo.userstore.listener;

import hu.vidragabor.springbatchdojo.userstore.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserStoreListener implements ItemProcessListener<User, User> {
	
	@Override
	public void beforeProcess(final User user) {
	}
	
	@Override
	public void afterProcess(final User user, final User newUser) {
		log.info("Old user: " + user + " change to new user: " + user);
	}
	
	@Override
	public void onProcessError(final User user, Exception e) {
	}
}
