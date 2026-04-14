package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX;
import static seedu.address.logic.Messages.MESSAGE_INVALID_INDEX_OVERFLOW;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.favourite.TourFavouriteRemoveCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new TourFavouriteRemoveCommand object
 */
public class TourFavouriteRemoveCommandParser implements Parser<TourFavouriteRemoveCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the TourFavouriteAddCommand
     * and returns a TourFavouriteAddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TourFavouriteRemoveCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new TourFavouriteRemoveCommand(index);
        } catch (ParseException pe) {
            if (pe.getMessage().equals(MESSAGE_INVALID_INDEX_OVERFLOW)) {
                throw new ParseException(MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
            }
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TourFavouriteRemoveCommand.MESSAGE_USAGE), pe);
        }
    }
}
