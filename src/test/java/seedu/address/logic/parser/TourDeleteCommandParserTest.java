package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TOUR;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.tour.TourDeleteCommand;

public class TourDeleteCommandParserTest {

    private TourDeleteCommandParser parser = new TourDeleteCommandParser();

    @Test
    public void parse_validArgs_success() {
        // no whitespace
        assertParseSuccess(parser, "1", new TourDeleteCommand(INDEX_FIRST_TOUR));

        // leading/trailing whitespace
        assertParseSuccess(parser, "  1 ", new TourDeleteCommand(INDEX_FIRST_TOUR));
    }

    @Test
    public void parse_invalidArgs_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, TourDeleteCommand.MESSAGE_USAGE);

        // not integer
        assertParseFailure(parser, "4.0", expectedMessage);

        // not numeric
        assertParseFailure(parser, "goated", expectedMessage);

        // zero
        assertParseFailure(parser, "0", expectedMessage);

        // negative
        assertParseFailure(parser, "-67", expectedMessage);
    }
}
