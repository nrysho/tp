package seedu.address.model.tour;

import seedu.address.model.contact.Contact;
import seedu.address.model.contact.UniqueContactList;

/**
 * Represents a tour package, which is associated with a collection of contacts associated with the tour.
 */
public class Tour {
    private final UniqueContactList contacts = new UniqueContactList();

    /**
     * Adds a contact to the tour.
     * The contact must not exist in the tour.
     */
    public void add(Contact contact) {
        contacts.add(contact);
    }

    /**
     * Returns true if the tour contains an equivalent contact as the given argument.
     */
    public boolean contains(Contact contact) {
        return contacts.contains(contact);
    }

    /**
     * Replaces the contact {@code target} in the tour with {@code editedContact}.
     * {@code target} must exist in the tour.
     * The contact identity of {@code editedContact} must not be the same as another existing contact in the tour.
     */
    public void setContact(Contact target, Contact editedContact) {
        contacts.setContact(target, editedContact);
    }

    /**
     * Removes the equivalent contact from the tour.
     * The contact must exist in the tour.
     */
    public void remove(Contact toRemove) {
        contacts.remove(toRemove);
    }

    /**
     * Replaces the contents of this tour with {@code replacement}.
     */
    public void setContacts(Tour replacement) {
        contacts.setContacts(replacement.contacts);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof Tour tour) {
            return this.contacts.equals(tour.contacts);
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Tour(%s)", contacts);
    }
}
