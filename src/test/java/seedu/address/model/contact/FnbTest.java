package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HALAL_STATUS_FALSE_FNB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalContacts.AL_AZHAR;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.contact.EditCommand.EditContactDescriptor;
import seedu.address.model.tour.Tour;
import seedu.address.testutil.ContactBuilder;
import seedu.address.testutil.EditContactDescriptorBuilder;
import seedu.address.testutil.FnbBuilder;

public class FnbTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Contact contact = new FnbBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> contact.getTags().remove(0));
    }

    @Test
    public void constructor_defaultHalalStatus_success() {
        Fnb fnb = new Fnb(
                AL_AZHAR.getName(),
                AL_AZHAR.getPhone(),
                AL_AZHAR.getEmail(),
                AL_AZHAR.getAddress(),
                AL_AZHAR.getTags(),
                AL_AZHAR.getTours());

        assertFalse(fnb.isHalal());
    }

    @Test
    public void constructor_specifiedHalalStatus_success() {
        Fnb fnb = new Fnb(
                AL_AZHAR.getName(),
                AL_AZHAR.getPhone(),
                AL_AZHAR.getEmail(),
                AL_AZHAR.getAddress(),
                AL_AZHAR.getTags(),
                new HalalStatus(VALID_HALAL_STATUS_FALSE_FNB),
                AL_AZHAR.getTours());

        assertFalse(fnb.isHalal());
    }

    @Test
    public void getType_returnsFnb() {
        assertEquals("fnb", AL_AZHAR.getType());
    }

    @Test
    public void isSameContact() {
        // same object -> returns true
        assertTrue(AL_AZHAR.isSameContact(AL_AZHAR));

        // null -> returns false
        assertFalse(AL_AZHAR.isSameContact(null));

        // same name, all other attributes different -> returns true
        Fnb editedFnb = (Fnb) ContactBuilder.fromContact(AL_AZHAR)
                .withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(AL_AZHAR.isSameContact(editedFnb));

        // different name, all other attributes same -> returns false
        editedFnb = (Fnb) ContactBuilder.fromContact(AL_AZHAR).withName(VALID_NAME_BOB).build();
        assertFalse(AL_AZHAR.isSameContact(editedFnb));

        // name differs in case, all other attributes same -> returns true
        editedFnb = (Fnb) ContactBuilder.fromContact(AL_AZHAR).withName(
                AL_AZHAR.getName().fullName.toLowerCase()).build();
        assertTrue(AL_AZHAR.isSameContact(editedFnb));

        // name has trailing spaces, all other attributes same -> returns false
        editedFnb = (Fnb) ContactBuilder.fromContact(AL_AZHAR).withName(AL_AZHAR.getName().fullName + " ").build();
        assertFalse(AL_AZHAR.isSameContact(editedFnb));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Fnb copy = (Fnb) ContactBuilder.fromContact(AL_AZHAR).build();
        assertTrue(AL_AZHAR.equals(copy));

        // same object -> returns true
        assertTrue(AL_AZHAR.equals(AL_AZHAR));

        // null -> returns false
        assertFalse(AL_AZHAR.equals(null));

        // different type -> returns false
        assertFalse(AL_AZHAR.equals(5));

        // different name -> returns false
        Fnb editedFnb = (Fnb) ContactBuilder.fromContact(AL_AZHAR).withName(VALID_NAME_BOB).build();
        assertFalse(AL_AZHAR.equals(editedFnb));

        // different phone -> returns false
        editedFnb = (Fnb) ContactBuilder.fromContact(AL_AZHAR).withPhone(VALID_PHONE_BOB).build();
        assertFalse(AL_AZHAR.equals(editedFnb));

        // different email -> returns false
        editedFnb = (Fnb) ContactBuilder.fromContact(AL_AZHAR).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(AL_AZHAR.equals(editedFnb));

        // different address -> returns false
        editedFnb = (Fnb) ContactBuilder.fromContact(AL_AZHAR).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(AL_AZHAR.equals(editedFnb));

        // different tags -> returns false
        editedFnb = (Fnb) ContactBuilder.fromContact(AL_AZHAR).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(AL_AZHAR.equals(editedFnb));

        // different tours -> returns false
        editedFnb = (Fnb) ContactBuilder.fromContact(AL_AZHAR).withTours("Tour 1").build();
        assertFalse(AL_AZHAR.equals(editedFnb));

        // different halal status -> returns false
        FnbBuilder editedFnbBuilder = (FnbBuilder) ContactBuilder.fromContact(AL_AZHAR);
        editedFnb = (Fnb) editedFnbBuilder.withHalalStatus(VALID_HALAL_STATUS_FALSE_FNB).build();
        assertFalse(AL_AZHAR.equals(editedFnb));

    }

    @Test
    public void edit_updatesFieldsCorrectly() {
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder()
                .withName("New Fnb")
                .withHalalStatus(VALID_HALAL_STATUS_FALSE_FNB)
                .withTours("Tour 1")
                .build();

        Fnb edited = (Fnb) AL_AZHAR.edit(descriptor);

        assertEquals("New Fnb", edited.getName().fullName);
        assertFalse((edited).isHalal());
        assertEquals(Set.of(new Tour("Tour 1")), edited.getTours());
    }

    @Test
    public void getTypeSpecificDetails_returnsHalalAndTours() {
        Fnb fnb = (Fnb) new FnbBuilder().withHalalStatus("true").withTours("Tour 1").build();

        assertEquals(
                java.util.List.of("Halal Status: Halal", "Tours: Tour 1"),
                fnb.getTypeSpecificDetails());
    }
}
