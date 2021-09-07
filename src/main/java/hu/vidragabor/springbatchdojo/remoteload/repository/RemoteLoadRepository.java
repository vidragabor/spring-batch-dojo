package hu.vidragabor.springbatchdojo.remoteload.repository;

import lombok.RequiredArgsConstructor;
import org.postgresql.copy.CopyManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.FileReader;

@Repository
@RequiredArgsConstructor
public class RemoteLoadRepository {
	
	@Value("${remote.dump.file.path}/${remote.dump.file.name}")
	private String fileName;
	
	private final CopyManager copyManager;
	
	public long loadDumpIntoTable() throws Exception {
		return copyManager.copyIn("COPY source (id, first_name, last_name, age) FROM STDIN", new FileReader(fileName));
	}
}
