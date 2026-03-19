package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE =
            "=============================================================\n"
                    + "                    BIVAGO - HELP GUIDE                      \n"
                    + "=============================================================\n"
                    + "\n"
                    + "CONTACT MANAGEMENT\n"
                    + "-----------------------------------------------------------\n"
                    + "ADD CONTACT\n"
                    + "  Command: add type/TYPE n/NAME e/EMAIL [p/PHONE] [a/ADDRESS] [t/TAG]...\n"
                    + "  Example: add type/person n/John p/98736789 e/john@gmail.com\n"
                    + "  Info: Available types are 'person', 'fnb', 'accommodation', 'attraction'\n\n"
                    + "  FNB contacts\n"
                    + "  Additional field(s): [h/HALAL STATUS]\n"
                    + "  Format: true/false\n"
                    + "  Default value(s) if unspecified: h/false\n\n"
                    + "  Attraction contacts\n"
                    + "  Additional field(s) for attraction: [o/OPENING HOURS] [c/CLOSING HOURS]\n"
                    + "  Format: HH:mm\n"
                    + "  Default value(s) if unspecified: o/08:00 c/22:00\n\n"
                    + "  Accommodation contacts\n"
                    + "  Additional field(s) for accommodation: [s/STARS]\n"
                    + "  Format: 1-5\n"
                    + "  Default value(s) if unspecified: s/3\n"
                    + "\n"
                    + "EDIT CONTACT\n"
                    + "  Command: edit INDEX [PREFIX/NEW_DATA]...\n"
                    + "  Example: edit 1 n/Richard p/91822222\n"
                    + "  Info: INDEX refers to the number shown in the displayed list\n"
                    + "\n"
                    + "DELETE CONTACT\n"
                    + "  Command: delete INDEX\n"
                    + "  Example: delete 1\n"
                    + "  Info: INDEX refers to the number shown in the displayed list\n"
                    + "\n"
                    + "FIND CONTACT\n"
                    + "  Command: find PARTIAL_NAME\n"
                    + "  Example: find John\n"
                    + "  Info: Case-insensitive, searches across all categories\n"
                    + "\n"
                    + "OTHER\n"
                    + "-----------------------------------------------------------\n"
                    + "HELP\n"
                    + "  Command: help\n"
                    + "  Info: Displays this help guide\n"
                    + "\n"
                    + "=============================================================\n"
                    + "  Tip: Data is saved automatically after every command.\n"
                    + "=============================================================\n";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE);
    }
}
