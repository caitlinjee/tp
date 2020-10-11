package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CALORIES_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.CALORIES_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INGREDIENT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INGREDIENT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INSTRUCTION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INSTRUCTION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CALORIES_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INGREDIENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.RECIPE_IMAGE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.RECIPE_IMAGE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CALORIES_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INSTRUCTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECIPE_IMAGE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalRecipes.AMY;
import static seedu.address.testutil.TypicalRecipes.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddRecipeCommand;
import seedu.address.model.commons.Calories;
import seedu.address.model.recipe.Name;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.RecipeBuilder;

public class AddRecipeCommandParserTest {
    private AddRecipeCommandParser parser = new AddRecipeCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Recipe expectedRecipe = new RecipeBuilder(BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + INGREDIENT_DESC_BOB
                + CALORIES_DESC_BOB + INSTRUCTION_DESC_BOB + RECIPE_IMAGE_DESC_BOB
                + TAG_DESC_BOB, new AddRecipeCommand(expectedRecipe));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + INGREDIENT_DESC_BOB
                + CALORIES_DESC_BOB + INSTRUCTION_DESC_BOB + RECIPE_IMAGE_DESC_BOB
                + TAG_DESC_BOB, new AddRecipeCommand(expectedRecipe));

        // multiple ingredients - last ingredients accepted
        assertParseSuccess(parser, NAME_DESC_BOB + INGREDIENT_DESC_AMY + INGREDIENT_DESC_BOB
                + CALORIES_DESC_BOB + INSTRUCTION_DESC_BOB + RECIPE_IMAGE_DESC_BOB
                + TAG_DESC_BOB, new AddRecipeCommand(expectedRecipe));
        // multiple tags - all accepted
        Recipe expectedRecipeMultipleTags = new RecipeBuilder(BOB)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + INGREDIENT_DESC_BOB
                + CALORIES_DESC_BOB + INSTRUCTION_DESC_BOB + RECIPE_IMAGE_DESC_BOB
                + TAG_DESC_BOB + TAG_DESC_BOB, new AddRecipeCommand(expectedRecipeMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Recipe expectedRecipe = new RecipeBuilder(AMY).build();
        assertParseSuccess(parser, NAME_DESC_AMY + INGREDIENT_DESC_AMY
                        + CALORIES_DESC_AMY + INSTRUCTION_DESC_AMY + RECIPE_IMAGE_DESC_AMY,
                new AddRecipeCommand(expectedRecipe));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRecipeCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + INGREDIENT_DESC_BOB + CALORIES_DESC_BOB
                        + INSTRUCTION_DESC_BOB + RECIPE_IMAGE_DESC_BOB,
                expectedMessage);

        // missing ingredients prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_INGREDIENT_BOB + CALORIES_DESC_BOB
                        + INSTRUCTION_DESC_BOB + RECIPE_IMAGE_DESC_BOB,
                expectedMessage);

        // missing calories prefix
        assertParseFailure(parser, NAME_DESC_BOB + INGREDIENT_DESC_BOB + VALID_CALORIES_BOB
                        + INSTRUCTION_DESC_BOB + RECIPE_IMAGE_DESC_BOB,
                expectedMessage);

        // missing instruction prefix
        assertParseFailure(parser, NAME_DESC_BOB + INGREDIENT_DESC_BOB + VALID_CALORIES_BOB
                + VALID_INSTRUCTION_BOB + INSTRUCTION_DESC_BOB + RECIPE_IMAGE_DESC_BOB,
                expectedMessage);

        // missing image prefix
        assertParseFailure(parser, NAME_DESC_BOB + INGREDIENT_DESC_BOB + VALID_CALORIES_BOB
                        + VALID_INSTRUCTION_BOB + INSTRUCTION_DESC_BOB + VALID_RECIPE_IMAGE_BOB,
                expectedMessage);

        // missing tag prefix
        assertParseFailure(parser, NAME_DESC_BOB + INGREDIENT_DESC_BOB + VALID_CALORIES_BOB
                        + VALID_INSTRUCTION_BOB + INSTRUCTION_DESC_BOB + RECIPE_IMAGE_DESC_BOB + VALID_TAG_BOB,
                expectedMessage);

        // missing calories prefix
        assertParseFailure(parser, NAME_DESC_BOB + INGREDIENT_DESC_BOB + VALID_CALORIES_BOB
                        + INSTRUCTION_DESC_BOB + RECIPE_IMAGE_DESC_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_INGREDIENT_BOB + VALID_CALORIES_BOB
                        + VALID_INSTRUCTION_BOB + VALID_RECIPE_IMAGE_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + INGREDIENT_DESC_BOB + CALORIES_DESC_BOB
                + INSTRUCTION_DESC_BOB + RECIPE_IMAGE_DESC_BOB
                + TAG_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid ingredients
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_INGREDIENT_DESC + CALORIES_DESC_BOB
                + INSTRUCTION_DESC_BOB + RECIPE_IMAGE_DESC_BOB
                + TAG_DESC_BOB, ParserUtil.MESSAGE_CONSTRAINTS);

        // invalid calories
        assertParseFailure(parser, NAME_DESC_BOB + INGREDIENT_DESC_BOB + INVALID_CALORIES_DESC
                + INSTRUCTION_DESC_BOB + RECIPE_IMAGE_DESC_BOB + TAG_DESC_BOB, Calories.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + INGREDIENT_DESC_BOB + CALORIES_DESC_BOB
                + INSTRUCTION_DESC_BOB + RECIPE_IMAGE_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_BOB, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + INGREDIENT_DESC_BOB + CALORIES_DESC_BOB
                        + INSTRUCTION_DESC_BOB + RECIPE_IMAGE_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + CALORIES_DESC_BOB
                        + INSTRUCTION_DESC_BOB + RECIPE_IMAGE_DESC_BOB
                        + INGREDIENT_DESC_BOB + TAG_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRecipeCommand.MESSAGE_USAGE));
    }
}
