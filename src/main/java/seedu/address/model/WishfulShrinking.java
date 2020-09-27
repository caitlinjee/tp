package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.UniqueIngredientList;
import seedu.address.model.recipe.UniqueRecipeList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameRecipe comparison)
 */
public class WishfulShrinking implements ReadOnlyWishfulShrinking {

    private final UniqueRecipeList recipes;

    private final UniqueIngredientList ingredients;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        recipes = new UniqueRecipeList();
        ingredients = new UniqueIngredientList();
    }

    public WishfulShrinking() {}

    /**
     * Creates an WishfulShrinking using the Recipes in the {@code toBeCopied}
     */
    public WishfulShrinking(ReadOnlyWishfulShrinking toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the recipe list with {@code recipes}.
     * {@code recipes} must not contain duplicate recipes.
     */
    public void setRecipes(List<Recipe> recipes) {
        this.recipes.setRecipes(recipes);
    }

    /**
     * Replaces the contents of the ingredient list with {@code ingredients}.
     * {@code ingredients} must not contain duplicate ingredients.
     */
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients.setIngredients(ingredients);
    }

    /**
     * Resets the existing data of this {@code WishfulShrinking} with {@code newData}.
     */
    public void resetData(ReadOnlyWishfulShrinking newData) {
        requireNonNull(newData);

        setRecipes(newData.getRecipeList());
        setIngredients(newData.getIngredientList());
    }

    //// recipe-level operations

    /**
     * Returns true if a recipe with the same identity as {@code recipe} exists in the address book.
     */
    public boolean hasRecipe(Recipe recipe) {
        requireNonNull(recipe);
        return recipes.contains(recipe);
    }

    /**
     * Adds a recipe to the address book.
     * The recipe must not already exist in the address book.
     */
    public void addRecipe(Recipe p) {
        recipes.add(p);
    }

    /**
     * Replaces the given recipe {@code target} in the list with {@code editedRecipe}.
     * {@code target} must exist in the address book.
     * The recipe identity of {@code editedRecipe} must not be the same as another existing recipe in the address book.
     */
    public void setRecipe(Recipe target, Recipe editedRecipe) {
        requireNonNull(editedRecipe);

        recipes.setRecipe(target, editedRecipe);
    }

    /**
     * Removes {@code key} from this {@code WishfulShrinking}.
     * {@code key} must exist in the address book.
     */
    public void removeRecipe(Recipe key) {
        recipes.remove(key);
    }

    //// ingredient-level operations

    /**
     * Returns true if a ingredient with the same identity as {@code ingredient} exists in the fridge.
     */
    public boolean hasIngredient(Ingredient ingredient) {
        requireNonNull(ingredient);
        return ingredients.contains(ingredient);
    }

    /**
     * Adds a ingredient to the fridge.
     * The ingredient must not already exist in the fridge.
     */
    public void addIngredient(Ingredient i) {
        ingredients.add(i);
    }

    /**
     * Replaces the given ingredient {@code target} in the list with {@code editedIngredient}.
     * {@code target} must exist in the fridge.
     * The ingredient identity of {@code editedIngredient} must not be the same as another existing ingredient in the
     * fridge.
     */
    public void setIngredient(Ingredient target, Ingredient editedIngredient) {
        requireNonNull(editedIngredient);

        ingredients.setIngredient(target, editedIngredient);
    }

    /**
     * Removes {@code key} from this {@code WishfulShrinking}.
     * {@code key} must exist in the address book.
     */
    public void removeIngredient(Ingredient key) {
        ingredients.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return recipes.asUnmodifiableObservableList().size() + " recipes";
        // TODO: refine later
    }

    @Override
    public ObservableList<Recipe> getRecipeList() {
        return recipes.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Ingredient> getIngredientList() {
        return ingredients.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof WishfulShrinking // instanceof handles nulls
                && recipes.equals(((WishfulShrinking) other).recipes));
    }

    @Override
    public int hashCode() {
        return recipes.hashCode();
    }
}
