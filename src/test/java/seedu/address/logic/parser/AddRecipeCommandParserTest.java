package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
//import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INGREDIENT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INGREDIENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
//import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
//import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.INGREDIENT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
//import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
//import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
//import static seedu.address.testutil.TypicalRecipes.AMY;
import static seedu.address.testutil.TypicalRecipes.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddRecipeCommand;
import seedu.address.model.recipe.IngredientString;
import seedu.address.model.recipe.Name;
import seedu.address.model.recipe.Recipe;
//import seedu.address.model.tag.Tag;
import seedu.address.testutil.RecipeBuilder;

public class AddRecipeCommandParserTest {
    private AddRecipeCommandParser parser = new AddRecipeCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Recipe expectedRecipe = new RecipeBuilder(BOB).build();

        // whitespace only preamble
        /* assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + INGREDIENT_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddRecipeCommand(expectedRecipe));*/

        // multiple names - last name accepted
        // assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + INGREDIENT_DESC_BOB + EMAIL_DESC_BOB
        //  + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddRecipeCommand(expectedRecipe));

        // multiple ingredientss - last ingredients accepted
        // assertParseSuccess(parser, NAME_DESC_BOB + INGREDIENT_DESC_AMY + INGREDIENT_DESC_BOB + EMAIL_DESC_BOB
        //               + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddRecipeCommand(expectedRecipe));

        // multiple emails - last email accepted
        // assertParseSuccess(parser, NAME_DESC_BOB + INGREDIENT_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
        //             + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddRecipeCommand(expectedRecipe));

        // multiple addresses - last address accepted
        // assertParseSuccess(parser, NAME_DESC_BOB + INGREDIENT_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
        //              + ADDRESS_DESC_BOB + TAG_DESC_FRIEND, new AddRecipeCommand(expectedRecipe));

        // multiple tags - all accepted
        Recipe expectedRecipeMultipleTags = new RecipeBuilder(BOB)
                .build();
        // assertParseSuccess(parser, NAME_DESC_BOB + INGREDIENT_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
        //           + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddRecipeCommand(expectedRecipeMultipleTags));
    }

    /*@Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Recipe expectedRecipe = new RecipeBuilder(AMY).build();
        assertParseSuccess(parser, NAME_DESC_AMY + INGREDIENT_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
                new AddRecipeCommand(expectedRecipe));
    }*/

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRecipeCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + INGREDIENT_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing ingredients prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_INGREDIENT_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
                expectedMessage);

        // missing email prefix
        /* assertParseFailure(parser, NAME_DESC_BOB + INGREDIENT_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB,
                expectedMessage);*/

        // missing address prefix
        //        assertParseFailure(parser, NAME_DESC_BOB + INGREDIENT_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB,
        //               expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_INGREDIENT_BOB
                        + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + INGREDIENT_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid ingredients
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_INGREDIENT_DESC
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, IngredientString.MESSAGE_CONSTRAINTS);

        // invalid email
        /*  assertParseFailure(parser, NAME_DESC_BOB + INGREDIENT_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);*/

        // invalid address
        //  assertParseFailure(parser, NAME_DESC_BOB + INGREDIENT_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
        //               + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        // assertParseFailure(parser, NAME_DESC_BOB + INGREDIENT_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
        //               + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + INGREDIENT_DESC_BOB
                        + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB
                        + INGREDIENT_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRecipeCommand.MESSAGE_USAGE));
    }
}
