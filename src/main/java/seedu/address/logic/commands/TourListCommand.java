package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TOURS;

import seedu.address.model.Model;
import seedu.address.model.tour.Tour;

/**
 * Lists all unique tours in the address book to the user.
 */
public class TourListCommand extends Command {

    public static final String COMMAND_WORD = "tour-list";

    public static final String MESSAGE_SUCCESS = "Tour Packages Available";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTourList(PREDICATE_SHOW_ALL_TOURS);

        StringBuilder sb = new StringBuilder(MESSAGE_SUCCESS + ":\n");
        int index = 1;
        for (Tour tour : model.getFilteredTourList()) {
            sb.append(index).append(". ").append(tour.getTourName()).append("\n");
            index++;
        }
        return new CommandResult(sb.toString());
    }
}
