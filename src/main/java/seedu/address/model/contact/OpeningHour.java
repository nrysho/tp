package seedu.address.model.contact;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;

/**
 * Represents the opening hour of an Attraction contact.
 */
public class OpeningHour {
    public static final String MESSAGE_CONSTRAINTS =
            "Opening Hours should only be in a valid 24-hour format HH:mm (08:00 if unspecified).";

    /*
     * The string must be in a valid LocalTime format. (HH:mm)
     */
    public static final String VALIDATION_REGEX = "^([01]\\d|2[0-3]):[0-5]\\d$";

    public final LocalTime openingHour;

    /**
     * Constructs an {@code Opening Hour}.
     *
     * @param openingHour A valid input string for Opening Hour.
     */
    public OpeningHour(String openingHour) {
        if (openingHour.isEmpty()) {
            this.openingHour = LocalTime.parse("08:00");
        } else {
            checkArgument(isValidOpeningHour(openingHour), MESSAGE_CONSTRAINTS);
            this.openingHour = LocalTime.parse(openingHour);
        }
    }

    /**
     * Returns true if a given string is a valid Opening Hour.
     */
    public static boolean isValidOpeningHour(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public LocalTime getOpeningHour() {
        return openingHour;
    }


    @Override
    public String toString() {
        return openingHour.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof OpeningHour otherOpeningHour)) {
            return false;
        }

        return openingHour.equals(otherOpeningHour.getOpeningHour());
    }

    @Override
    public int hashCode() {
        return openingHour.toString().hashCode();
    }
}
