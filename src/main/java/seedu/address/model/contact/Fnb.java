package seedu.address.model.contact;

import static seedu.address.logic.parser.CliSyntax.TYPE_FNB;

import java.util.Set;

import seedu.address.logic.commands.EditCommand;
import seedu.address.model.tag.Tag;


/**
 * Represents an Fnb Contact in the address book.
 * Guarantees: default Halal status (false) if unspecified.
 */
public class Fnb extends Contact {
    private final HalalStatus isHalal;

    /**
     * Constructs an {@code Fnb} contact with default Halal status (false).
     */
    public Fnb(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        this.isHalal = new HalalStatus("false");
    }

    /**
     * Constructs an {@code Fnb} contact with specified Halal status.
     * @param isHalal A Halal status.
     */
    public Fnb(Name name, Phone phone, Email email, Address address, Set<Tag> tags, HalalStatus isHalal) {
        super(name, phone, email, address, tags);
        this.isHalal = isHalal;
    }

    public boolean isHalal() {
        return this.isHalal.isHalal;
    }

    public HalalStatus getHalalStatus() {
        return this.isHalal;
    }

    @Override
    public String getType() {
        return TYPE_FNB;
    }

    /**
     * Returns a copy of the contact with its values edited.
     */
    @Override
    public Contact edit(EditCommand.EditContactDescriptor editFnbDescriptor) {
        Name updatedName = editFnbDescriptor.getName().orElse(getName());
        Phone updatedPhone = editFnbDescriptor.getPhone().orElse(getPhone());
        Email updatedEmail = editFnbDescriptor.getEmail().orElse(getEmail());
        Address updatedAddress = editFnbDescriptor.getAddress().orElse(getAddress());
        Set<Tag> updatedTags = editFnbDescriptor.getTags().orElse(getTags());
        HalalStatus updatedHalalStatus = editFnbDescriptor.getHalalStatus().orElse(getHalalStatus());
        return new Fnb(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags, updatedHalalStatus);
    }
}
