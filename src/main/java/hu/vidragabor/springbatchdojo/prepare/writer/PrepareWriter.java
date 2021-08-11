package hu.vidragabor.springbatchdojo.prepare.writer;

import hu.vidragabor.springbatchdojo.prepare.model.Prepare;
import hu.vidragabor.springbatchdojo.prepare.repository.PrepareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PrepareWriter implements ItemWriter<Prepare> {
	
	private final PrepareRepository prepareRepository;
	
	@Override
	public void write(final List<? extends Prepare> prepareList) {
		prepareList.forEach(prepareRepository::save);
	}
	
}
