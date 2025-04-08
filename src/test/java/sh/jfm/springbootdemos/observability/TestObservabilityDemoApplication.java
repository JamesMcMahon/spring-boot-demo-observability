package sh.jfm.springbootdemos.observability;

import org.springframework.boot.SpringApplication;

public class TestObservabilityDemoApplication {

	public static void main(String[] args) {
		SpringApplication.from(ObservabilityDemoApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
