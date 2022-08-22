package duke;

import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for `Parser` class.
 */
public class ParserTest {
    /** Dummy `TaskList` object to help in testing. */
    private static final TaskList dummyTaskList = new TaskList();

    /** `Parser` object to be tested. */
    private static final Parser testParser = new Parser(dummyTaskList);

    /**
     * Tests `handleUserInputs` method of `Parser` class.
     */
    @Test
    public void handleUserInputs_wrongInputs_exceptionThrown() {
        String testInput1 = "event /at EVENT VENUE";
        String expectedOutput1 = "Wrong format! To add a new " +
                "event, please enter the following:\n" +
                "   event [TASK DESCRIPTION] /at [VENUE]\n";
        try {
            BiFunction<TaskList, String, String> testOutput
                    = testParser.handleUserInputs(testInput1);
        } catch (DukeException e) {
            assertEquals(expectedOutput1, e.getMessage());
        }

        String testInput2 = "eventEVENT DESCRIPTION /atEVENT VENUE";
        String expectedOutput2 = String.format(
                "Sorry, I did not quite get that! (%s)\n", testInput2);
        try {
            BiFunction<TaskList, String, String> testOutput
                    = testParser.handleUserInputs(testInput2);
        } catch (DukeException e) {
            assertEquals(expectedOutput2, e.getMessage());
        }

    }
}