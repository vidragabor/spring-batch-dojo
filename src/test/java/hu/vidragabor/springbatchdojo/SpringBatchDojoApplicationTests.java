package hu.vidragabor.springbatchdojo;

import hu.vidragabor.springbatchdojo.model.User;
import hu.vidragabor.springbatchdojo.userstore.processor.UserStoreProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SpringBatchDojoApplicationTests {
	
	@Test
	void testProcessor() {
		final UserStoreProcessor userStoreProcessor = new UserStoreProcessor();
		final User user = initUser();
		final User processedUser = userStoreProcessor.process(user);
		
		assertNotNull(processedUser);
		assertEquals("first_test", processedUser.getFirstName());
		assertEquals("last", processedUser.getLastName());
		assertEquals(1, processedUser.getAge());
	}
	
	private User initUser() {
		return User.builder()
				.firstName("first")
				.lastName("last")
				.age(1)
				.build();
	}
	
}
