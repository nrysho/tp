package seedu.address.logic.commands.contact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showContactAtIndex;
import static seedu.address.testutil.TypicalContacts.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_ACCOMMODATION_CONTACT;
import static seedu.address.testutil.TypicalIndexes.INDEX_ATTRACTION_CONTACT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FNB_CONTACT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CONTACT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.contact.EditCommand.EditContactDescriptor;
import seedu.address.logic.commands.general.ClearCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.testutil.AccommodationBuilder;
import seedu.address.testutil.AttractionBuilder;
import seedu.address.testutil.ContactBuilder;
import seedu.address.testutil.EditContactDescriptorBuilder;
import seedu.address.testutil.FnbBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Contact editedContact = new PersonBuilder().build();
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder(editedContact).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_CONTACT, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_CONTACT_SUCCESS,
                Messages.format(editedContact));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setContact(model.getFilteredContactList().get(0), editedContact);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastContact = Index.fromOneBased(model.getFilteredContactList().size());
        Contact lastContact = model.getFilteredContactList().get(indexLastContact.getZeroBased());

        ContactBuilder contactInList = ContactBuilder.fromContact(lastContact);
        Contact editedContact = contactInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditContactDescriptor descriptor = new EditContactDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastContact, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_CONTACT_SUCCESS,
                Messages.format(editedContact));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setContact(lastContact, editedContact);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_failure() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_CONTACT, new EditContactDescriptor());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_NO_CHANGES);
    }

    @Test
    public void execute_editFnbWithHalalStatus_success() {
        Contact contactToEdit = model.getFilteredContactList().get(INDEX_FNB_CONTACT.getZeroBased());

        FnbBuilder halalFnbBuilder = (FnbBuilder) ContactBuilder.fromContact(contactToEdit);
        halalFnbBuilder.withHalalStatus("false");
        Contact editedContact = halalFnbBuilder.build();

        EditContactDescriptor descriptor = new EditContactDescriptorBuilder()
                .withHalalStatus("false")
                .build();
        EditCommand editCommand = new EditCommand(INDEX_FNB_CONTACT, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_CONTACT_SUCCESS,
                Messages.format(editedContact));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setContact(contactToEdit, editedContact);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editAttractionWithOpeningAndClosingHour_success() {
        Contact contactToEdit = model.getFilteredContactList().get(INDEX_ATTRACTION_CONTACT.getZeroBased());

        AttractionBuilder attractionBuilder = (AttractionBuilder) ContactBuilder.fromContact(contactToEdit);
        attractionBuilder.withOpeningHour("09:00");
        attractionBuilder.withClosingHour("18:00");
        Contact editedContact = attractionBuilder.build();

        EditContactDescriptor descriptor = new EditContactDescriptorBuilder()
                .withOpeningHour("09:00")
                .withClosingHour("18:00")
                .build();
        EditCommand editCommand = new EditCommand(INDEX_ATTRACTION_CONTACT, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_CONTACT_SUCCESS,
                Messages.format(editedContact));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setContact(contactToEdit, editedContact);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editAccommodationWithStars_success() {
        Contact contactToEdit = model.getFilteredContactList().get(INDEX_ACCOMMODATION_CONTACT.getZeroBased());

        AccommodationBuilder accommodationBuilder =
                (AccommodationBuilder) ContactBuilder.fromContact(contactToEdit);
        accommodationBuilder.withStars("5");
        Contact editedContact = accommodationBuilder.build();

        EditContactDescriptor descriptor = new EditContactDescriptorBuilder()
                .withStars("5")
                .build();
        EditCommand editCommand = new EditCommand(INDEX_ACCOMMODATION_CONTACT, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_CONTACT_SUCCESS,
                Messages.format(editedContact));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setContact(contactToEdit, editedContact);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editPersonWithHalalStatus_failure() {
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder()
                .withHalalStatus("true")
                .build();

        EditCommand command = new EditCommand(INDEX_FIRST_CONTACT, descriptor);

        assertCommandFailure(command, model,
                String.format(Messages.MESSAGE_NON_APPLICABLE_FIELDS, EditCommand.MESSAGE_USAGE));
    }

    @Test
    public void execute_editPersonWithOpeningHour_failure() {
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder()
                .withOpeningHour("09:00")
                .build();

        EditCommand command = new EditCommand(INDEX_FIRST_CONTACT, descriptor);

        assertCommandFailure(command, model,
                String.format(Messages.MESSAGE_NON_APPLICABLE_FIELDS, EditCommand.MESSAGE_USAGE));
    }

    @Test
    public void execute_editPersonWithStars_failure() {
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder()
                .withStars("5")
                .build();

        EditCommand command = new EditCommand(INDEX_FIRST_CONTACT, descriptor);

        assertCommandFailure(command, model,
                String.format(Messages.MESSAGE_NON_APPLICABLE_FIELDS, EditCommand.MESSAGE_USAGE));
    }

    @Test
    public void execute_editFnbWithStars_failure() {
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder()
                .withStars("4")
                .build();

        EditCommand command = new EditCommand(INDEX_SECOND_CONTACT, descriptor);

        assertCommandFailure(command, model,
                String.format(Messages.MESSAGE_NON_APPLICABLE_FIELDS, EditCommand.MESSAGE_USAGE));
    }

    @Test
    public void execute_editAttractionWithHalalStatus_failure() {
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder()
                .withHalalStatus("true")
                .build();

        EditCommand command = new EditCommand(INDEX_FIRST_CONTACT, descriptor);

        assertCommandFailure(command, model,
                String.format(Messages.MESSAGE_NON_APPLICABLE_FIELDS, EditCommand.MESSAGE_USAGE));
    }

    @Test
    public void execute_editAccommodationWithOpeningHour_failure() {
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder()
                .withOpeningHour("08:00")
                .build();

        EditCommand command = new EditCommand(INDEX_FIRST_CONTACT, descriptor);

        assertCommandFailure(command, model,
                String.format(Messages.MESSAGE_NON_APPLICABLE_FIELDS, EditCommand.MESSAGE_USAGE));
    }

    @Test
    public void execute_editPersonWithMultipleNonApplicableFields_failure() {
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder()
                .withHalalStatus("true")
                .withOpeningHour("09:00")
                .withClosingHour("18:00")
                .withStars("5")
                .build();

        EditCommand command = new EditCommand(INDEX_FIRST_CONTACT, descriptor);

        assertCommandFailure(command, model,
                String.format(Messages.MESSAGE_NON_APPLICABLE_FIELDS, EditCommand.MESSAGE_USAGE));
    }

    @Test
    public void execute_filteredList_success() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);

        Contact contactInFilteredList = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        Contact editedContact = ContactBuilder.fromContact(contactInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_CONTACT,
                new EditContactDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_CONTACT_SUCCESS,
                Messages.format(editedContact));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        showContactAtIndex(expectedModel, INDEX_FIRST_CONTACT);
        expectedModel.setContact(expectedModel.getFilteredContactList().get(0), editedContact);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateContactUnfilteredList_failure() {
        Contact firstContact = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder(firstContact).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_CONTACT, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_CONTACT);
    }

    @Test
    public void execute_duplicateContactFilteredList_failure() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);

        // edit contact in filtered list into a duplicate in address book
        Contact contactInList = model.getAddressBook().getContactList().get(INDEX_SECOND_CONTACT.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_CONTACT,
                new EditContactDescriptorBuilder(contactInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_CONTACT);
    }

    @Test
    public void execute_invalidContactIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredContactList().size() + 1);
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidContactIndexFilteredList_failure() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);
        Index outOfBoundIndex = INDEX_SECOND_CONTACT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getContactList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditContactDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_CONTACT, DESC_AMY);

        // same values -> returns true
        EditContactDescriptor copyDescriptor = new EditContactDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_CONTACT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_CONTACT, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_CONTACT, DESC_BOB)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditContactDescriptor editContactDescriptor = new EditContactDescriptor();
        EditCommand editCommand = new EditCommand(index, editContactDescriptor);
        String expected = EditCommand.class.getCanonicalName() + "{index=" + index + ", editContactDescriptor="
                + editContactDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }

}
