package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.favorite.FavoriteRemoveCommand;

public class FavoriteRemoveCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FavoriteRemoveCommand.MESSAGE_USAGE);

    private final FavoriteRemoveCommandParser parser = new FavoriteRemoveCommandParser();

    @Test
    public void parse_validArgs_returnsFavoriteRemoveCommand() {
        assertParseSuccess(parser, "1",
                new FavoriteRemoveCommand(INDEX_FIRST_CONTACT));
    }

    @Test
    public void parse_validArgsWithWhitespace_returnsFavoriteRemoveCommand() {
        assertParseSuccess(parser, "   1   ",
                new FavoriteRemoveCommand(INDEX_FIRST_CONTACT));
    }

    @Test
    public void parse_invalidArgs_failure() {
        assertParseFailure(parser, "a", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_emptyArgs_failure() {
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }
}
