package com.company.project;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.HTML;
import static org.jbehave.core.reporters.Format.TXT;
import static org.jbehave.core.reporters.Format.XML;

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

import com.company.project.steps.MySteps;

/**
 * <p>
 * {@link Embeddable} class to run multiple textual stories via JUnit.
 * </p>
 * <p>
 * Stories are specified in classpath and correspondingly the {@link LoadFromClasspath} story loader is configured.
 * </p> 
 * 
 * <p>##############################</p>
 * <p>
 * Classe gerada/adaptada a partir do <a href="http://jbehave.org/reference/stable/archetypes.html">jbehave-simple-archetype</a>.<br/>
 * Fonte utilizado no evento <a href="http://www.thedevelopersconference.com.br/#portoalegre">TDC2013</a> realizado em Porto Alegre
 * para a palestra <a href="http://prezi.com/wter-r9fgvqz/?utm_campaign=share&utm_medium=copy&rc=ex0share">"Execução de testes em paralelo com JBehave e Selenium Grid"</a></b>.
 * </p>
 * 
 * @author Marcelo Tocchetto
 */
public class MyStories extends JUnitStories {
	
	// CrossReference gera os dados utilizados pelo relatório StoryNavigator
	private static final CrossReference xref = new CrossReference().withJsonOnly().withOutputAfterEachStory(true);
    
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
                .withFormats(CONSOLE, TXT, HTML, XML)
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
        return new InstanceStepsFactory(configuration(), new MySteps());
    }

    @Override
    protected List<String> storyPaths() {
        return new StoryFinder().findPaths(codeLocationFromClass(this.getClass()), "**/*.story", "**/excluded*.story");
                
    }
        
}