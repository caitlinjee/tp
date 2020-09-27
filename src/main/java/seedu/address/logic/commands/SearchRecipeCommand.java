package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.recipe.NameContainsKeywordsPredicate;

/**
 * Finds and lists all recipes in Recipe collection whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class SearchRecipeCommand extends Command {

    public static final String COMMAND_WORD = "searchR";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all recipes whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " n/salad";

    private final NameContainsKeywordsPredicate predicate;

    public SearchRecipeCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredRecipeList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_RECIPES_LISTED_OVERVIEW, model.getFilteredRecipeList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SearchRecipeCommand // instanceof handles nulls
                && predicate.equals(((SearchRecipeCommand) other).predicate)); // state check
    }
}
