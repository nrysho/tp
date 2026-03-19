package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalContacts.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tour.Tour;

/**
 * Contains integration tests (interaction with the Model) and unit tests for TourListCommand.
 */
public class TourListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listTours_success() {
        StringBuilder expectedMessage = new StringBuilder(TourListCommand.MESSAGE_SUCCESS);
        expectedMessage.append(":\n");
        int index = 1;
        for (Tour tour : model.getFilteredTourList()) {
            expectedMessage.append(index).append(". ").append(tour.getTourName()).append("\n");
            index++;
        }
        assertCommandSuccess(new TourListCommand(), model, expectedMessage.toString(), expectedModel);
    }
}
