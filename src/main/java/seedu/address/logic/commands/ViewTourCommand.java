package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.contact.ContactIsInTourPredicate;

/**
 * Finds and lists all contacts in the address book who are assigned to the specified tour.
 */
public class ViewTourCommand extends Command {

    public static final String COMMAND_WORD = "view-tour";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all contacts assigned to the specified tour (case-insensitive)"
            + " and displays them as a list with index numbers.\n"
            + "Parameters: TOUR_NAME\n"
            + "Example: " + COMMAND_WORD + " walking";

    private final ContactIsInTourPredicate predicate;

    public ViewTourCommand(ContactIsInTourPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredContactList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW, model.getFilteredContactList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewTourCommand)) {
            return false;
        }

        ViewTourCommand otherFindCommand = (ViewTourCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
