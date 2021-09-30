package hu.vidragabor.springbatchdojo.api.status.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Status {
	private int id;
	
	@JsonAlias("status_name")
	private String name;
}
