package hu.vidragabor.springbatchdojo.userstore.writer;

import hu.vidragabor.springbatchdojo.model.User;
import hu.vidragabor.springbatchdojo.userstore.repositroy.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserStoreWriter implements ItemWriter<User> {
	
	private final UserRepository userRepository;
	
	@Override
	public void write(final List<? extends User> userList) {
		userList.forEach(userRepository::save);
	}
}
