package seedu.address.model.contact;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;

/**
 * Represents the closing hour of an Attraction contact.
 */
public class ClosingHour {
    public static final String MESSAGE_CONSTRAINTS =
            "Closing Hours should only be in a valid 24-hour format HH:mm (22:00 if unspecified).";

    /**
     * The string must be in a valid LocalTime format. (HH:mm)
     */
    public static final String VALIDATION_REGEX = "^([01]\\d|2[0-3]):[0-5]\\d$";

    public final LocalTime closingHour;

    /**
     * Constructs an {@code Closing Hour}.
     *
     * @param closingHour A valid input string for Closing Hour.
     */
    public ClosingHour(String closingHour) {
        checkArgument(isValidClosingHour(closingHour), MESSAGE_CONSTRAINTS);
        this.closingHour = LocalTime.parse(closingHour);
    }

    /**
     * Returns true if a given string is a valid Closing Hour.
     */
    public static boolean isValidClosingHour(String test) {
        if (test.isEmpty()) {
            return false;
        }
        return test.matches(VALIDATION_REGEX);
    }

    public LocalTime getClosingHour() {
        return closingHour;
    }


    @Override
    public String toString() {
        return closingHour.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClosingHour otherClosingHour)) {
            return false;
        }

        return closingHour.equals(otherClosingHour.getClosingHour());
    }

    @Override
    public int hashCode() {
        return closingHour.toString().hashCode();
    }
}
