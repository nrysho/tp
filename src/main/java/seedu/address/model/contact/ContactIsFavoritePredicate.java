package seedu.address.model.contact;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Contact} is assigned to a given {@code Tour}.
 */
public class ContactIsFavoritePredicate implements Predicate<Contact> {
    public ContactIsFavoritePredicate() {
    }

    @Override
    public boolean test(Contact contact) {
        return contact.isFavorite();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }
}
