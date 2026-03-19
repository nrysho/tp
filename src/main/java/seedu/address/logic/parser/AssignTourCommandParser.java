package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TOUR;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AssignTourCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tour.Tour;

/**
 * Parses input arguments and creates a new AssignTourCommand object.
 */
public class AssignTourCommandParser implements Parser<AssignTourCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AssignTourCommand
     * and returns an AssignTourCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AssignTourCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TOUR);

        if (!arePrefixesPresent(argMultimap, PREFIX_TOUR) || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignTourCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignTourCommand.MESSAGE_USAGE), pe);
        }

        Tour tour = ParserUtil.parseTour(argMultimap.getValue(PREFIX_TOUR).get());

        return new AssignTourCommand(index, tour);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
