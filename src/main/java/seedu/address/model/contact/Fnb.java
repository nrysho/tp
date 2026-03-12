package seedu.address.model.contact;

import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents an Fnb Contact in the address book.
 * Guarantees: default Halal status (false) if unspecified.
 */
public class Fnb extends Contact {
    private final boolean isHalal;

    /**
     * Constructs an {@code Fnb} contact with default Halal status (false).
     */
    public Fnb(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.isHalal = false;
    }

    /**
     * Constructs an {@code Fnb} contact with specified Halal status.
     * @param isHalal A Halal status.
     */
    public Fnb(Name name, Phone phone, Email email, Address address, Set<Tag> tags, boolean isHalal) {
        super(name, phone, email, address, tags);
        this.isHalal = isHalal;
    }

    public boolean isHalal() {
        return isHalal;
    }
}
