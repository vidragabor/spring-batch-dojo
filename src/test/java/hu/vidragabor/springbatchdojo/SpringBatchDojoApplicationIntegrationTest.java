package hu.vidragabor.springbatchdojo;

import hu.vidragabor.springbatchdojo.configuration.PrimaryDataSourceConfiguration;
import hu.vidragabor.springbatchdojo.configuration.RemoteDataSourceConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.AssertFile;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.util.FileSystemUtils;

import java.io.IOException;
import java.nio.file.Path;

@Slf4j
@SpringBatchTest
@ActiveProfiles("test")
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SpringBatchDojoApplication.class, PrimaryDataSourceConfiguration.class, RemoteDataSourceConfiguration.class, SpringBatchDojoJobConfiguration.class}, initializers = ConfigDataApplicationContextInitializer.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class SpringBatchDojoApplicationIntegrationTest {
	
	// @SpringBatchTest creates and injects those two beans in the test context.
	// IntelliJ IDEA is not able to intorspect that, hence the error (or warning, depending on the level you've set).
	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;
	@Autowired
	private JobRepositoryTestUtils jobRepositoryTestUtils;
	
	@Value("${remote.dump.file.path}")
	private String dumpFilePath;
	
	@Value("${remote.dump.file.name}")
	private String dumpFile;
	
	@Value("${expected.file}")
	private String expectedFile;
	
	@After
	public void cleanUp() throws IOException {
		jobRepositoryTestUtils.removeJobExecutions();
		if (FileSystemUtils.deleteRecursively(Path.of(dumpFilePath))) {
			log.info("Delete \"{}\" folder!", dumpFilePath);
		}
	}
	
	@Test
	public void testRemoteDumpStep() throws Exception {
		final JobExecution jobExecution = jobLauncherTestUtils.launchStep("remoteDumpStep");
		
		Assert.assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
		Assert.assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		
		AssertFile.assertFileEquals(
				new FileSystemResource(expectedFile),
				new FileSystemResource(dumpFilePath + "/" + dumpFile)
		);
	}
	
}
