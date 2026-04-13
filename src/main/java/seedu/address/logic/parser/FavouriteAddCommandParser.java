package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX;
import static seedu.address.logic.Messages.MESSAGE_INVALID_INDEX_OVERFLOW;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.favourite.FavouriteAddCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FavouriteAddCommand object
 */
public class FavouriteAddCommandParser implements Parser<FavouriteAddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FavouriteAddCommand
     * and returns a FavouriteAddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FavouriteAddCommand parse(String args) throws ParseException {
        requireNonNull(args);
        try {
            Index index = ParserUtil.parseIndex(args);
            return new FavouriteAddCommand(index);
        } catch (ParseException pe) {
            if (pe.getMessage().equals(MESSAGE_INVALID_INDEX_OVERFLOW)) {
                throw new ParseException(MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
            }
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FavouriteAddCommand.MESSAGE_USAGE), pe);
        }
    }
}
