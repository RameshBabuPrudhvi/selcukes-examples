package io.github.selcukes.cucumber.listeners;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;
import io.github.selcukes.core.logging.Logger;
import io.github.selcukes.core.logging.LoggerFactory;

import java.util.Optional;


public class CucumberListener implements ConcurrentEventListener {
    private final Logger logger = LoggerFactory.getLogger(CucumberListener.class);
    private final TestSourcesModel testSources = new TestSourcesModel();

    /**
     * Registers an event handler for a specific event.
     * <p>
     * The available events types are:
     * <ul>
     * <li>{@link TestRunStarted} - the first event sent.
     * <li>{@link TestSourceRead} - sent for each feature file read, contains the feature file source.
     * <li>{@link TestCaseStarted} - sent before starting the execution of a Test Case(/Pickle/Scenario), contains the Test Case
     * <li>{@link TestStepStarted} - sent before starting the execution of a Test Step, contains the Test Step
     * <li>{@link TestStepFinished} - sent after the execution of a Test Step, contains the Test Step and its Result.
     * <li>{@link TestCaseFinished} - sent after the execution of a Test Case(/Pickle/Scenario), contains the Test Case and its Result.
     * <li>{@link TestRunFinished} - the last event sent.
     * <li>{@link EmbedEvent} - calling scenario.embed in a hook triggers this event.
     * <li>{@link WriteEvent} - calling scenario.write in a hook triggers this event.
     * </ul>
     */
    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestRunStarted.class, this::beforeTest);
        publisher.registerHandlerFor(TestSourceRead.class, this::getTestSourceReadHandler);
        publisher.registerHandlerFor(TestCaseStarted.class, this::beforeScenario);
        publisher.registerHandlerFor(TestStepStarted.class, this::beforeStep);
        publisher.registerHandlerFor(TestStepFinished.class, this::afterStep);
        publisher.registerHandlerFor(TestCaseFinished.class, this::afterScenario);
        publisher.registerHandlerFor(TestRunFinished.class, this::afterTest);
        publisher.registerHandlerFor(EmbedEvent.class, getEmbedEventHandler());
        publisher.registerHandlerFor(WriteEvent.class, getWriteEventHandler());
    }

    private void getTestSourceReadHandler(TestSourceRead event) {
        testSources.addTestSourceReadEvent(event.getUri(), event);
        logger.info(() -> String.format("TestSource Test: \n Source [%s] URI [%s]",
            event.getSource(),
            event.getUri()
        ));
    }

    private void beforeTest(TestRunStarted event) {

        logger.info(() -> String.format("Before Test: \nEvent[%s]",
            event.toString()

        ));

    }


    private void beforeScenario(TestCaseStarted event) {
        logger.info(() -> String.format("Before Scenario: \nScenario Name[%s] \nKeyword [%s] \nSteps [%s]",
            event.getTestCase().getName(),
            event.getTestCase().getKeyword(),
            event.getTestCase().getTestSteps().toString()
        ));

    }

    private void beforeStep(TestStepStarted event) {
        logger.info(() -> String.format("Before Step: [%s]", event.getTestStep().toString()));

    }

    private void afterStep(TestStepFinished event) {
        logger.info(() -> String.format("After Step: [%s]",
            event.getTestStep().toString()
        ));

        if (event.getResult().getStatus().is(Status.FAILED) && event.getTestStep() instanceof PickleStepTestStep) {
            PickleStepTestStep testStep = (PickleStepTestStep) event.getTestStep();
            StringBuilder stepsReport = new StringBuilder();
            stepsReport.append("Cucumber Step Failed : ")
                .append(testStep.getStep().getText()).append("  [")
                .append(testStep.getStep().getLine()).append("] ");
            Optional<StepArgument> stepsArgs = Optional.ofNullable(testStep.getStep().getArgument());
            if (stepsArgs.isPresent()) stepsReport.append("Step Argument: [").append(stepsArgs).append("] ");
            logger.info(() -> String.format("Step Report: [%s]",
                stepsReport
            ));
        }

    }


    private void afterScenario(TestCaseFinished event) {
        logger.info(() -> String.format("After Scenario: \nStatus [%s] \nDuration [%s] \nError [%s]",
            event.getResult().getStatus(),
            event.getResult().getDuration(),
            event.getResult().getError().getMessage()
        ));
    }

    private void afterTest(TestRunFinished event) {
        logger.info(() -> String.format("After Test: \nEvent [%s]",
            event.toString()
        ));

    }

    private EventHandler<EmbedEvent> getEmbedEventHandler() {
        return event ->
            logger.info(() -> String.format("Embed Event: [%s]", event.getName()));

    }

    private EventHandler<WriteEvent> getWriteEventHandler() {
        return event ->
            logger.info(() -> String.format("Write Event: [%s]", event.getText()));

    }

}
