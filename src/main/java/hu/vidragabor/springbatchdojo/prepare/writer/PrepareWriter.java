package hu.vidragabor.springbatchdojo.prepare.writer;

import hu.vidragabor.springbatchdojo.model.User;
import hu.vidragabor.springbatchdojo.prepare.repository.PrepareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PrepareWriter implements ItemWriter<User> {
	
	private final PrepareRepository prepareRepository;
	
	@Override
	public void write(final List<? extends User> prepareList) {
		prepareList.forEach(prepareRepository::save);
	}
}
