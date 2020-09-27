package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RECIPES;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.recipe.Recipe;

/**
 * Lists all recipes in the address book to the user.
 */
public class ListRecipesCommand extends Command {

    public static final String COMMAND_WORD = "recipes";

    public static final String MESSAGE_SUCCESS = "Listed all recipes" + "\n";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredRecipeList(PREDICATE_SHOW_ALL_RECIPES);
        ObservableList<Recipe> recipes = model.getFilteredRecipeList();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < recipes.size(); i++) {
            builder.append((i + 1) + ". " + recipes.get(i).toString() + "\n");
        }
        return new CommandResult(MESSAGE_SUCCESS + builder.toString());
    }
}
