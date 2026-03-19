package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TOUR;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CONTACTS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;
import seedu.address.model.tour.Tour;

/**
 * Assigns a tour to an existing contact in the address book.
 */
public class AssignTourCommand extends Command {

    public static final String COMMAND_WORD = "assign-tour";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Assigns a tour to the contact identified by the index number used in the displayed contact list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_TOUR + "TOUR_NAME\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_TOUR + "walking tour";

    public static final String MESSAGE_ASSIGN_TOUR_SUCCESS = "Assigned tour to contact: %1$s";
    public static final String MESSAGE_DUPLICATE_TOUR = "Contact is already assigned to this tour.";

    private final Index index;
    private final Tour tour;

    /**
     * Creates an AssignTourCommand to assign the given {@code Tour} to the contact at {@code index}.
     */
    public AssignTourCommand(Index index, Tour tour) {
        requireNonNull(index);
        requireNonNull(tour);
        this.index = index;
        this.tour = tour;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Contact> lastShownList = model.getFilteredContactList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
        }

        Contact contactToEdit = lastShownList.get(index.getZeroBased());

        if (contactToEdit.isInTour(tour)) {
            throw new CommandException(MESSAGE_DUPLICATE_TOUR);
        }

        Set<Tour> updatedTours = new HashSet<>(contactToEdit.getTours());
        updatedTours.add(tour);

        EditCommand.EditContactDescriptor descriptor = new EditCommand.EditContactDescriptor();
        descriptor.setTours(updatedTours);

        Contact editedContact = contactToEdit.edit(descriptor);

        model.setContact(contactToEdit, editedContact);
        model.updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
        return new CommandResult(String.format(MESSAGE_ASSIGN_TOUR_SUCCESS, Messages.format(editedContact)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AssignTourCommand)) {
            return false;
        }

        AssignTourCommand otherCommand = (AssignTourCommand) other;
        return index.equals(otherCommand.index) && tour.equals(otherCommand.tour);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("tour", tour)
                .toString();
    }
}
