package seedu.address.logic.commands.favorite;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FAVORITE_STATUS_FALSE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FAVORITE_STATUS_TRUE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalContacts.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CONTACT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.testutil.ContactBuilder;

public class FavoriteRemoveCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndex_success() {
        Contact originalContact = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        Contact contactToUnfavorite = ContactBuilder.fromContact(
                originalContact).withFavoriteStatus(VALID_FAVORITE_STATUS_TRUE).build();
        model.setContact(originalContact, contactToUnfavorite);

        FavoriteRemoveCommand command = new FavoriteRemoveCommand(INDEX_FIRST_CONTACT);

        Contact expectedEditedContact = ContactBuilder.fromContact(
                contactToUnfavorite).withFavoriteStatus(VALID_FAVORITE_STATUS_FALSE).build();

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setContact(contactToUnfavorite, expectedEditedContact);
        expectedModel.updateFilteredContactList(Model.PREDICATE_SHOW_ALL_CONTACTS);

        assertCommandSuccess(command, model,
                String.format(FavoriteRemoveCommand.MESSAGE_REMOVE_FAVORITE_SUCCESS,
                        Messages.format(expectedEditedContact)),
                expectedModel);
    }

    @Test
    public void execute_invalidIndex_failure() {
        Index outOfBoundsIndex = Index.fromOneBased(model.getFilteredContactList().size() + 1);
        FavoriteRemoveCommand command = new FavoriteRemoveCommand(outOfBoundsIndex);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_alreadyNonFavorite_failure() {
        FavoriteRemoveCommand command = new FavoriteRemoveCommand(INDEX_FIRST_CONTACT);

        assertCommandFailure(command, model, FavoriteRemoveCommand.MESSAGE_DUPLICATE_NON_FAVORITE);
    }

    @Test
    public void equals() {
        FavoriteRemoveCommand firstCommand = new FavoriteRemoveCommand(INDEX_FIRST_CONTACT);
        FavoriteRemoveCommand secondCommand = new FavoriteRemoveCommand(INDEX_SECOND_CONTACT);

        assertTrue(firstCommand.equals(firstCommand));
        assertTrue(firstCommand.equals(new FavoriteRemoveCommand(INDEX_FIRST_CONTACT)));
        assertFalse(firstCommand.equals(secondCommand));
        assertFalse(firstCommand.equals(1));
        assertFalse(firstCommand.equals(null));
    }

    @Test
    public void toStringMethod() {
        FavoriteRemoveCommand command = new FavoriteRemoveCommand(INDEX_FIRST_CONTACT);
        String expected = FavoriteRemoveCommand.class.getCanonicalName()
                + "{contactIndex=" + INDEX_FIRST_CONTACT + "}";
        assertEquals(expected, command.toString());
    }
}
