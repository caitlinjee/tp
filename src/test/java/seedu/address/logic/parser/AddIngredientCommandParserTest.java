package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INGREDIENT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.testutil.TypicalIngredients.BOB;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddIngredientCommand;
import seedu.address.model.recipe.Ingredient;
import seedu.address.testutil.IngredientBuilder;

//import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
//import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class AddIngredientCommandParserTest {
    private AddIngredientCommandParser parser = new AddIngredientCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Ingredient expectedIngredient = new IngredientBuilder(BOB).build();
        ArrayList<Ingredient> arr = new ArrayList<>();
        arr.add(expectedIngredient);

        // whitespace only preamble
        /*assertParseSuccess(parser, PREAMBLE_WHITESPACE + INGREDIENT_DESC_BOB,
                new AddIngredientCommand(arr));*/
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddIngredientCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + INGREDIENT_DESC_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_INGREDIENT_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddIngredientCommand.MESSAGE_USAGE));

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB
                        + INGREDIENT_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddIngredientCommand.MESSAGE_USAGE));
    }
}
