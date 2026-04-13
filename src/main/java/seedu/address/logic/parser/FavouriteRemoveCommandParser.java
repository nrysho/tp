package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX;
import static seedu.address.logic.Messages.MESSAGE_INVALID_INDEX_OVERFLOW;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.favourite.FavouriteRemoveCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FavouriteRemoveCommand object
 */
public class FavouriteRemoveCommandParser implements Parser<FavouriteRemoveCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FavouriteRemoveCommand
     * and returns a FavouriteRemoveCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FavouriteRemoveCommand parse(String args) throws ParseException {
        requireNonNull(args);
        try {
            Index index = ParserUtil.parseIndex(args);
            return new FavouriteRemoveCommand(index);
        } catch (ParseException pe) {
            if (pe.getMessage().equals(MESSAGE_INVALID_INDEX_OVERFLOW)) {
                throw new ParseException(MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
            }
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FavouriteRemoveCommand.MESSAGE_USAGE), pe);
        }
    }
}
