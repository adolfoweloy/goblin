package br.com.goblin;

import java.util.ArrayList;
import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.openqa.selenium.WebDriver;

public class LiabilitiesConfigurationCase extends JUnitStory {

	private WebDriver driver;
	
	@Override
	public Configuration configuration() {
        return new MostUsefulConfiguration()
        	.useStoryLoader(new LoadFromClasspath(this.getClass()))  
        	.useStoryReporterBuilder(new StoryReporterBuilder()
        		.withDefaultFormats()
        		.withFormats(Format.CONSOLE, Format.TXT)); 
	}
	
	@Override
	public InjectableStepsFactory stepsFactory() {
		List<Object> steps = new ArrayList<>();
		steps.add(new LiabilitiesCreationStep(driver));
		
		return new InstanceStepsFactory(configuration(), steps);
	}
}
