package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TOUR_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TOUR_NAME_DESC_JAMES;
import static seedu.address.logic.commands.CommandTestUtil.TOUR_NAME_DESC_JAMES_JR;
import static seedu.address.logic.commands.CommandTestUtil.TYPE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TOUR_NAME_JAMES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.tour.TourAddCommand;
import seedu.address.model.tour.Tour;

public class TourAddCommandParserTest {
    private TourAddCommandParser parser = new TourAddCommandParser();

    @Test
    public void parse_validArgs_success() {
        Tour expectedTour = new Tour(VALID_TOUR_NAME_JAMES);
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TOUR_NAME_DESC_JAMES,
                new TourAddCommand(expectedTour));
    }

    @Test
    public void parse_invalidArgsMissingField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, TourAddCommand.MESSAGE_USAGE);

        // empty input
        assertParseFailure(parser, "", expectedMessage);

        // whitespace only
        assertParseFailure(parser, PREAMBLE_WHITESPACE, expectedMessage);

        // other parameter supplied
        assertParseFailure(parser, PREAMBLE_WHITESPACE + PREFIX_EMAIL + VALID_TOUR_NAME_JAMES, expectedMessage);
    }

    @Test
    public void parse_repeatedFields_failure() {
        String validExpectedTourString = TOUR_NAME_DESC_JAMES;

        // multiple names
        assertParseFailure(parser, TOUR_NAME_DESC_JAMES_JR + validExpectedTourString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid then valid
        assertParseFailure(parser,  INVALID_TOUR_NAME_DESC + validExpectedTourString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // valid then invalid
        assertParseFailure(parser,  validExpectedTourString + INVALID_TOUR_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));
    }

    @Test
    public void parse_invalidValue_failure() {
        // blank name
        assertParseFailure(parser, " " + PREFIX_NAME, Tour.MESSAGE_CONSTRAINTS);

        // invalid name
        assertParseFailure(parser, INVALID_TOUR_NAME_DESC, Tour.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TOUR_NAME_DESC_JAMES,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TourAddCommand.MESSAGE_USAGE));
    }
}
