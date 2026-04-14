package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLOSING_HOUR_ATTRACTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OPENING_HOUR_ATTRACTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalContacts.USS;

import java.time.LocalTime;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.contact.EditCommand.EditContactDescriptor;
import seedu.address.model.tour.Tour;
import seedu.address.testutil.AttractionBuilder;
import seedu.address.testutil.ContactBuilder;
import seedu.address.testutil.EditContactDescriptorBuilder;

public class AttractionTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Contact contact = new AttractionBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> contact.getTags().remove(0));
    }

    @Test
    public void constructor_defaultOperatingHours_success() {
        Attraction attraction = new Attraction(
                USS.getName(),
                USS.getPhone(),
                USS.getEmail(),
                USS.getAddress(),
                USS.getTags(),
                USS.getTours());

        assertEquals("08:00", attraction.getOpeningHour().toString());
        assertEquals("22:00", attraction.getClosingHour().toString());
    }

    @Test
    public void constructor_defaultOpeningHour_success() {
        Attraction attraction = new Attraction(
                USS.getName(),
                USS.getPhone(),
                USS.getEmail(),
                USS.getAddress(),
                USS.getTags(),
                new ClosingHour(VALID_CLOSING_HOUR_ATTRACTION),
                USS.getTours());

        assertEquals("08:00", attraction.getOpeningHour().toString());
        assertEquals("23:00", attraction.getClosingHour().toString());
    }

    @Test
    public void constructor_defaultClosingHour_success() {
        Attraction attraction = new Attraction(
                USS.getName(),
                USS.getPhone(),
                USS.getEmail(),
                USS.getAddress(),
                USS.getTags(),
                new OpeningHour(VALID_OPENING_HOUR_ATTRACTION),
                USS.getTours());

        assertEquals("09:00", attraction.getOpeningHour().toString());
        assertEquals("22:00", attraction.getClosingHour().toString());
    }

    @Test
    public void constructor_specifiedOpeningAndClosingHours_success() {
        Attraction attraction = new Attraction(
                USS.getName(),
                USS.getPhone(),
                USS.getEmail(),
                USS.getAddress(),
                USS.getTags(),
                new OpeningHour(VALID_OPENING_HOUR_ATTRACTION),
                new ClosingHour(VALID_CLOSING_HOUR_ATTRACTION),
                USS.getTours());

        assertEquals("09:00", attraction.getOpeningHour().toString());
        assertEquals("23:00", attraction.getClosingHour().toString());
    }

    @Test
    public void getType_returnsAttraction() {
        assertEquals("attraction", USS.getType());
    }

    @Test
    public void getOperatingHours_returnsCorrectHours() {
        Attraction attraction = (Attraction) USS;
        LocalTime[] expected = new LocalTime[] {LocalTime.of(9, 0), LocalTime.of(23, 0)};
        assertArrayEquals(expected, attraction.getOperatingHours());
    }

    @Test
    public void isSameContact() {
        // same object -> returns true
        assertTrue(USS.isSameContact(USS));

        // null -> returns false
        assertFalse(USS.isSameContact(null));

        // same name, all other attributes different -> returns true
        Attraction editedAttraction = (Attraction) ContactBuilder.fromContact(USS)
                .withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(USS.isSameContact(editedAttraction));

        // different name, all other attributes same -> returns false
        editedAttraction = (Attraction) ContactBuilder.fromContact(USS).withName(VALID_NAME_BOB).build();
        assertFalse(USS.isSameContact(editedAttraction));

        // name differs in case, all other attributes same -> returns true
        editedAttraction = (Attraction) ContactBuilder.fromContact(USS)
                .withName(USS.getName().fullName.toLowerCase()).build();
        assertTrue(USS.isSameContact(editedAttraction));

        // name has trailing spaces, all other attributes same -> returns false
        editedAttraction = (Attraction) ContactBuilder.fromContact(USS)
                .withName(USS.getName().fullName + " ").build();
        assertFalse(USS.isSameContact(editedAttraction));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Attraction copy = (Attraction) ContactBuilder.fromContact(USS).build();
        assertTrue(USS.equals(copy));

        // same object -> returns true
        assertTrue(USS.equals(USS));

        // null -> returns false
        assertFalse(USS.equals(null));

        // different type -> returns false
        assertFalse(USS.equals(5));

        // different name -> returns false
        Attraction editedAttraction = (Attraction) ContactBuilder.fromContact(USS).withName(VALID_NAME_BOB).build();
        assertFalse(USS.equals(editedAttraction));

        // different phone -> returns false
        editedAttraction = (Attraction) ContactBuilder.fromContact(USS).withPhone(VALID_PHONE_BOB).build();
        assertFalse(USS.equals(editedAttraction));

        // different email -> returns false
        editedAttraction = (Attraction) ContactBuilder.fromContact(USS).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(USS.equals(editedAttraction));

        // different address -> returns false
        editedAttraction = (Attraction) ContactBuilder.fromContact(USS).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(USS.equals(editedAttraction));

        // different tags -> returns false
        editedAttraction = (Attraction) ContactBuilder.fromContact(USS).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(USS.equals(editedAttraction));

        // different opening hour -> returns false
        AttractionBuilder editedAttractionBuilder = (AttractionBuilder) ContactBuilder.fromContact(USS);
        editedAttraction = (Attraction) editedAttractionBuilder.withOpeningHour("10:00").build();
        assertFalse(USS.equals(editedAttraction));

        // different closing hour -> returns false
        editedAttractionBuilder = (AttractionBuilder) ContactBuilder.fromContact(USS);
        editedAttraction = (Attraction) editedAttractionBuilder.withClosingHour("23:30").build();
        assertFalse(USS.equals(editedAttraction));

        // different tours -> returns false
        editedAttractionBuilder = (AttractionBuilder) ContactBuilder.fromContact(USS);
        editedAttraction = (Attraction) editedAttractionBuilder.withTours("Tour 1").build();
        assertFalse(USS.equals(editedAttraction));
    }

    @Test
    public void edit_updatesFieldsCorrectly() {
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder()
                .withName("New Attraction")
                .withOpeningHour("10:00")
                .withClosingHour("23:00")
                .withTours("Tour 1")
                .build();

        Attraction edited = (Attraction) USS.edit(descriptor);

        assertEquals("New Attraction", edited.getName().fullName);
        assertEquals("10:00", edited.getOpeningHour().toString());
        assertEquals("23:00", edited.getClosingHour().toString());
        assertEquals(Set.of(new Tour("Tour 1")), edited.getTours());
    }

    @Test
    public void getTypeSpecificDetails_returnsHoursAndTours() {
        Attraction attraction = (Attraction) new AttractionBuilder()
                .withClosingHour(VALID_CLOSING_HOUR_ATTRACTION)
                .withOpeningHour(VALID_OPENING_HOUR_ATTRACTION)
                .withTours("Tour 1")
                .build();

        assertEquals(
                java.util.List.of("Operating Hours: 09:00 to 23:00", "Tours: Tour 1"),
                attraction.getTypeSpecificDetails());
    }
}
