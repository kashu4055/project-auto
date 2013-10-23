package com.company.project;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.CONSOLE;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import org.jbehave.core.Embeddable;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.Keywords;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.model.ExamplesTableFactory;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.parsers.StoryParser;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.ParameterConverters.DateConverter;
import org.jbehave.core.steps.ParameterConverters.ExamplesTableConverter;
import org.jbehave.core.steps.StepMonitor;
import org.jbehave.web.selenium.WebDriverHtmlOutput;

import com.company.project.pages.Pages;
import com.company.project.selenium.WebDriverManager;
import com.company.project.selenium.WebDriverScreenshotOnFailure;
import com.company.project.steps.BingSteps;
import com.company.project.steps.GoogleSteps;
import com.company.project.steps.MySteps;

/**
 * <p>
 * {@link Embeddable} class to run multiple textual stories via JUnit.
 * </p>
 * <p>
 * Stories are specified in classpath and correspondingly the {@link LoadFromClasspath} story loader is configured.
 * </p> 
 */
public class MyStories extends JUnitStories {
	
	// CrossReference gera os dados utilizados pelo relatório StoryNavigator
	private static final CrossReference xref = new CrossReference().withJsonOnly().withOutputAfterEachStory(true);
	
	private WebDriverManager webDriverManager = new WebDriverManager();
	private Pages pages = new Pages(webDriverManager);
    
    public MyStories() {
        configuredEmbedder().embedderControls().doGenerateViewAfterStories(true).doIgnoreFailureInStories(true)
                .doIgnoreFailureInView(true).useThreads(2).useStoryTimeoutInSecs(60);
    }
    
    @Override
    public Configuration configuration() {
        Class<? extends Embeddable> embeddableClass = this.getClass();
        
        // Define locale para o idioma brasileiro
        Locale locale = new Locale("pt","BR");
        // Carrega palavras chaves para o idioma informado (suporte nativo ao keywords_pt.properties)
        Keywords keywords = new LocalizedKeywords(locale);
        
        StepMonitor stepMonitor = xref.getStepMonitor();
        
        // Start from default ParameterConverters instance
        ParameterConverters parameterConverters = new ParameterConverters(stepMonitor, locale, ParameterConverters.DEFAULT_LIST_SEPARATOR, ParameterConverters.DEFAULT_THREAD_SAFETY);
        // factory to allow parameter conversion and loading from external resources (used by StoryParser too)
        ExamplesTableFactory examplesTableFactory = new ExamplesTableFactory(keywords, new LoadFromClasspath(embeddableClass), parameterConverters);
        // add custom converters
        parameterConverters.addConverters(new DateConverter(new SimpleDateFormat("dd/MM/yyyy")),
                new ExamplesTableConverter(examplesTableFactory));
        
        // Habilita na leitura(parse) das histórias a interpretação das palavras chaves no locale informado 
        final StoryParser storyParser = new RegexStoryParser(keywords, examplesTableFactory);
        
        return new MostUsefulConfiguration()
            .useStoryLoader(new LoadFromClasspath(embeddableClass))
            .useStoryParser(storyParser)
            // Utiliza as palavras chaves no locale informado
            .useKeywords(keywords)
            .useStoryReporterBuilder(new StoryReporterBuilder()
                .withCodeLocation(CodeLocations.codeLocationFromClass(embeddableClass))
                .withDefaultFormats()
                //.withFormats(CONSOLE, HTML) // Para screenshots de erro utilizar WebDriverHtmlOutput.WEB_DRIVER_HTML
                .withFormats(CONSOLE, WebDriverHtmlOutput.WEB_DRIVER_HTML)
                // CrossReference permite o uso do relatório Story Navigator
                .withCrossReference(xref)
                // Permite ver relatório com palavras chaves de acordo com o locale informado
                .withKeywords(keywords)
                )
             // Aplica parameterConverters/stepMonitor relacionados ao CrossReference
            .useParameterConverters(parameterConverters)
            .useStepMonitor(stepMonitor);
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
    	Configuration configuration = configuration();
    	
        return new InstanceStepsFactory(configuration, 
        		webDriverManager, // Inicializa e fecha o browser
        		new MySteps(), new BingSteps(pages), new GoogleSteps(pages),
        		new WebDriverScreenshotOnFailure(webDriverManager, configuration.storyReporterBuilder())
        		);
        // Dica: Para gerar screenshots de erro você deve reimplementar a classe WebDriverScreenshotOnFailure passando webDriverManager e storyReporterBuilder como parâmetros.
        // Obs.: Para visualizar os screenshots de erro você deve adicionar o formato WebDriverHtmlOutput.WEB_DRIVER_HTML ao StoryReporterBuilder
        // Isto permite que no momento em que for tirado um screenshot de erro, seja recuperada a mesma instância do webdriver onde o erro ocorreu.
    }

    @Override
    protected List<String> storyPaths() {
        return new StoryFinder().findPaths(codeLocationFromClass(this.getClass()), "**/*.story", "**/excluded*.story");
    }

}