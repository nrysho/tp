package seedu.address.logic.commands.favorite;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FAVORITE_STATUS_TRUE;
import static seedu.address.testutil.TypicalContacts.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.testutil.ContactBuilder;

public class FavoriteViewCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_someFavoritesOnly_showsFavoriteContacts() {
        Contact firstContact = model.getFilteredContactList().get(0);
        Contact secondContact = model.getFilteredContactList().get(1);

        Contact favoriteFirstContact = ContactBuilder.fromContact(firstContact)
                .withFavoriteStatus(VALID_FAVORITE_STATUS_TRUE)
                .build();
        Contact favoriteSecondContact = ContactBuilder.fromContact(secondContact)
                .withFavoriteStatus(VALID_FAVORITE_STATUS_TRUE)
                .build();

        model.setContact(firstContact, favoriteFirstContact);
        model.setContact(secondContact, favoriteSecondContact);

        FavoriteViewCommand command = new FavoriteViewCommand();
        CommandResult result = command.execute(model);

        assertEquals(String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 2), result.getFeedbackToUser());
        assertEquals(2, model.getFilteredContactList().size());
        assertEquals(favoriteFirstContact, model.getFilteredContactList().get(0));
        assertEquals(favoriteSecondContact, model.getFilteredContactList().get(1));
    }

    @Test
    public void execute_noFavorites_showsEmptyList() {
        FavoriteViewCommand command = new FavoriteViewCommand();
        CommandResult result = command.execute(model);

        assertEquals(String.format(MESSAGE_CONTACTS_LISTED_OVERVIEW, 0), result.getFeedbackToUser());
        assertEquals(0, model.getFilteredContactList().size());
    }

    @Test
    public void toStringMethod() {
        FavoriteViewCommand command = new FavoriteViewCommand();
        String expected = FavoriteViewCommand.class.getCanonicalName()
                + "{predicate=" + new seedu.address.model.contact.ContactIsFavoritePredicate() + "}";
        assertEquals(expected, command.toString());
    }
}
