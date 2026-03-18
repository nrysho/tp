package seedu.address.model.contact;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents number of stars of an Accommodation contact.
 * Contains the enumeration that holds data for number of stars.
 */
public class AccommodationStars {
    /**
     * Contains values for number of stars of the accommodation.
     */
    public enum Stars {
        ONE_STAR("1"),
        TWO_STAR("2"),
        THREE_STAR("3"),
        FOUR_STAR("4"),
        FIVE_STAR("5");

        private final String value;

        Stars(String value) {
            this.value = value;
        }

        public String getStars() {
            return value;
        }

        /**
         * Returns Stars value from an input string.
         */
        public static Stars fromString(String input) {
            for (Stars r : Stars.values()) {
                if (r.value.equals(input)) {
                    return r;
                }
            }
            throw new IllegalArgumentException("Invalid stars: " + input);
        }
    }

    public static final String MESSAGE_CONSTRAINTS =
            "Accommodation Stars should only be a single character of a digit 1-5 (default 3 if unspecified).";

    /*
     * The string must be a single character of a digit 1-5.
     */
    public static final String VALIDATION_REGEX = "^[1-5]$";

    public final AccommodationStars.Stars stars;

    /**
     * Constructs an {@code Accommodation Stars}.
     *
     * @param stars A valid input string for Accommodation Stars.
     */
    public AccommodationStars(String stars) {
        checkArgument(isValidAccommodationStars(stars), MESSAGE_CONSTRAINTS);
        this.stars = Stars.fromString(stars);
    }

    /**
     * Constructs an {@code Accommodation Stars}.
     * Used for default case only.
     *
     * @param stars A valid input value for Accommodation Stars.
     */
    public AccommodationStars(Stars stars) {
        this.stars = stars;
    }

    /**
     * Returns true if a given string is a valid accommodation star.
     */
    public static boolean isValidAccommodationStars(String test) {
        if (test.isEmpty()) {
            return false;
        }
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return this.stars.getStars();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AccommodationStars otherStars)) {
            return false;
        }

        return stars == otherStars.stars;
    }

    @Override
    public int hashCode() {
        String starsString = stars.toString();
        return starsString.hashCode();
    }
}
