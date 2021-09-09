package hu.vidragabor.springbatchdojo.ftp.decider;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FtpUploadDecider {
	
	@Value("${ftp.upload.threshold}")
	private int deciderThreshold;
	
	public boolean shouldUpload() {
		final int randomInt = RandomUtils.nextInt(1, 10);
		final boolean shouldUpload = randomInt >= deciderThreshold;
		log.info("randomInt: {}, shouldUpload: {}", randomInt, shouldUpload);
		return shouldUpload;
	}
}
