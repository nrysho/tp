package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STARS_ACCOMMODATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalContacts.HOTEL;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.contact.EditCommand.EditContactDescriptor;
import seedu.address.model.tour.Tour;
import seedu.address.testutil.AccommodationBuilder;
import seedu.address.testutil.ContactBuilder;
import seedu.address.testutil.EditContactDescriptorBuilder;

public class AccommodationTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Contact contact = new AccommodationBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> contact.getTags().remove(0));
    }

    @Test
    public void constructor_defaultStars_success() {
        Accommodation accommodation = new Accommodation(
                HOTEL.getName(),
                HOTEL.getPhone(),
                HOTEL.getEmail(),
                HOTEL.getAddress(),
                HOTEL.getTags(),
                HOTEL.getTours());

        assertEquals("3", accommodation.getStars().toString());
    }

    @Test
    public void constructor_specifiedStars_success() {
        Accommodation accommodation = new Accommodation(
                HOTEL.getName(),
                HOTEL.getPhone(),
                HOTEL.getEmail(),
                HOTEL.getAddress(),
                HOTEL.getTags(),
                new AccommodationStars(VALID_STARS_ACCOMMODATION),
                HOTEL.getTours());

        assertEquals("4", accommodation.getStars().toString());
    }

    @Test
    public void getType_returnsAccommodation() {
        assertEquals("accomm", HOTEL.getType());
    }

    @Test
    public void isSameContact() {
        // same object -> returns true
        assertTrue(HOTEL.isSameContact(HOTEL));

        // null -> returns false
        assertFalse(HOTEL.isSameContact(null));

        // same name, all other attributes different -> returns true
        Accommodation editedAccommodation = (Accommodation) ContactBuilder.fromContact(HOTEL)
                .withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(HOTEL.isSameContact(editedAccommodation));

        // different name, all other attributes same -> returns false
        editedAccommodation = (Accommodation) ContactBuilder.fromContact(HOTEL).withName(VALID_NAME_BOB).build();
        assertFalse(HOTEL.isSameContact(editedAccommodation));

        // name differs in case, all other attributes same -> returns true
        editedAccommodation = (Accommodation) ContactBuilder.fromContact(HOTEL)
                .withName(HOTEL.getName().fullName.toLowerCase()).build();
        assertTrue(HOTEL.isSameContact(editedAccommodation));

        // name has trailing spaces, all other attributes same -> returns false
        editedAccommodation = (Accommodation) ContactBuilder.fromContact(HOTEL)
                .withName(HOTEL.getName().fullName + " ").build();
        assertFalse(HOTEL.isSameContact(editedAccommodation));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Accommodation copy = (Accommodation) ContactBuilder.fromContact(HOTEL).build();
        assertTrue(HOTEL.equals(copy));

        // same object -> returns true
        assertTrue(HOTEL.equals(HOTEL));

        // null -> returns false
        assertFalse(HOTEL.equals(null));

        // different type -> returns false
        assertFalse(HOTEL.equals(5));

        // different name -> returns false
        Accommodation editedAccommodation = (Accommodation) ContactBuilder.fromContact(HOTEL)
                .withName(VALID_NAME_BOB).build();
        assertFalse(HOTEL.equals(editedAccommodation));

        // different phone -> returns false
        editedAccommodation = (Accommodation) ContactBuilder.fromContact(HOTEL).withPhone(VALID_PHONE_BOB).build();
        assertFalse(HOTEL.equals(editedAccommodation));

        // different email -> returns false
        editedAccommodation = (Accommodation) ContactBuilder.fromContact(HOTEL).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(HOTEL.equals(editedAccommodation));

        // different address -> returns false
        editedAccommodation = (Accommodation) ContactBuilder.fromContact(HOTEL).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(HOTEL.equals(editedAccommodation));

        // different tags -> returns false
        editedAccommodation = (Accommodation) ContactBuilder.fromContact(HOTEL).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(HOTEL.equals(editedAccommodation));

        // different stars -> returns false
        AccommodationBuilder editedAccommodationBuilder = (AccommodationBuilder) ContactBuilder.fromContact(HOTEL);
        editedAccommodation = (Accommodation) editedAccommodationBuilder.withStars("5").build();
        assertFalse(HOTEL.equals(editedAccommodation));

        // different tours -> returns false
        editedAccommodationBuilder = (AccommodationBuilder) ContactBuilder.fromContact(HOTEL);
        editedAccommodation = (Accommodation) editedAccommodationBuilder.withTours("Tour 1").build();
        assertFalse(HOTEL.equals(editedAccommodation));
    }

    @Test
    public void edit_updatesFieldsCorrectly() {
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder()
                .withName("New Hotel")
                .withStars("5")
                .withTours("Tour 1")
                .build();

        Accommodation edited = (Accommodation) HOTEL.edit(descriptor);

        assertEquals("New Hotel", edited.getName().fullName);
        assertEquals("5", edited.getStars().toString());
        assertEquals(Set.of(new Tour("Tour 1")), edited.getTours());
    }

    @Test
    public void getTypeSpecificDetails_returnsStarsAndTours() {
        Accommodation accommodation = (Accommodation) new AccommodationBuilder()
                .withStars("4")
                .withTours("Tour 1")
                .build();

        assertEquals(
                java.util.List.of("Number of Stars: 4", "Tours: Tour 1"),
                accommodation.getTypeSpecificDetails());
    }
}
