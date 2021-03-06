package seedu.address.model.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRecipes.MARGARITAS;
import static seedu.address.testutil.TypicalRecipes.SANDWICH;
import static seedu.address.testutil.TypicalRecipes.SANDWICH_DIFF_QTY;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.recipe.exceptions.DuplicateRecipeException;
import seedu.address.model.recipe.exceptions.RecipeNotFoundException;
import seedu.address.testutil.RecipeBuilder;

public class UniqueRecipeListTest {

    private final UniqueRecipeList uniqueRecipeList = new UniqueRecipeList();

    @Test
    public void contains_nullRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecipeList.contains(null));
    }

    @Test
    public void contains_recipeNotInList_returnsFalse() {
        assertFalse(uniqueRecipeList.contains(SANDWICH));
    }

    @Test
    public void contains_recipeInList_returnsTrue() {
        uniqueRecipeList.add(SANDWICH);
        assertTrue(uniqueRecipeList.contains(SANDWICH));
    }

    @Test
    public void contains_recipeWithSameIdentityFieldsInList_returnsTrue() {
        uniqueRecipeList.add(SANDWICH);
        Recipe editedRecipe = new RecipeBuilder(SANDWICH)
                .build();
        assertTrue(uniqueRecipeList.contains(editedRecipe));
    }

    @Test
    public void containsMinimalRecipe_nullRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecipeList.containsMinimalRecipe(null));
    }

    @Test
    public void containsMinimalRecipe_recipeNotInList_returnsFalse() {
        assertFalse(uniqueRecipeList.containsMinimalRecipe(SANDWICH));
    }

    @Test
    public void containsMinimalRecipe_recipeInList_returnsTrue() {
        uniqueRecipeList.add(SANDWICH);
        assertTrue(uniqueRecipeList.containsMinimalRecipe(SANDWICH));

        assertTrue(uniqueRecipeList.containsMinimalRecipe(SANDWICH_DIFF_QTY));
    }

    @Test
    public void containsMinimalRecipe_recipeWithSameIdentityFieldsInList_returnsTrue() {
        uniqueRecipeList.add(SANDWICH);
        Recipe editedRecipe = new RecipeBuilder(SANDWICH)
                .build();
        assertTrue(uniqueRecipeList.containsMinimalRecipe(editedRecipe));

        Recipe editedRecipe1 = new RecipeBuilder(SANDWICH_DIFF_QTY)
                .build();
        assertTrue(uniqueRecipeList.containsMinimalRecipe(editedRecipe1));
    }

    @Test
    public void add_nullRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecipeList.add(null));
    }

    @Test
    public void add_duplicateRecipe_throwsDuplicateRecipeException() {
        uniqueRecipeList.add(SANDWICH);
        assertThrows(DuplicateRecipeException.class, () -> uniqueRecipeList.add(SANDWICH));

        assertThrows(DuplicateRecipeException.class, () -> uniqueRecipeList.add(SANDWICH_DIFF_QTY));
    }

    @Test
    public void setRecipe_nullTargetRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecipeList.setRecipe(null, SANDWICH));
    }

    @Test
    public void setRecipe_nullEditedRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecipeList.setRecipe(SANDWICH, null));
    }

    @Test
    public void setRecipe_targetRecipeNotInList_throwsRecipeNotFoundException() {
        assertThrows(RecipeNotFoundException.class, () -> uniqueRecipeList.setRecipe(SANDWICH, SANDWICH));
    }

    @Test
    public void setRecipe_editedRecipeIsSameRecipe_success() {
        uniqueRecipeList.add(SANDWICH);
        uniqueRecipeList.setRecipe(SANDWICH, SANDWICH);
        UniqueRecipeList expectedUniqueRecipeList = new UniqueRecipeList();
        expectedUniqueRecipeList.add(SANDWICH);
        assertEquals(expectedUniqueRecipeList, uniqueRecipeList);
    }

    @Test
    public void setRecipe_editedRecipeHasSameIdentity_success() {
        uniqueRecipeList.add(SANDWICH);
        Recipe editedRecipe = new RecipeBuilder(SANDWICH)
                .build();
        uniqueRecipeList.setRecipe(SANDWICH, editedRecipe);
        UniqueRecipeList expectedUniqueRecipeList = new UniqueRecipeList();
        expectedUniqueRecipeList.add(editedRecipe);
        assertEquals(expectedUniqueRecipeList, uniqueRecipeList);
    }

    @Test
    public void setRecipe_editedRecipeHasDifferentIdentity_success() {
        uniqueRecipeList.add(SANDWICH);
        uniqueRecipeList.setRecipe(SANDWICH, MARGARITAS);
        UniqueRecipeList expectedUniqueRecipeList = new UniqueRecipeList();
        expectedUniqueRecipeList.add(MARGARITAS);
        assertEquals(expectedUniqueRecipeList, uniqueRecipeList);

        uniqueRecipeList.remove(MARGARITAS);
        uniqueRecipeList.add(SANDWICH);
        uniqueRecipeList.setRecipe(SANDWICH, SANDWICH_DIFF_QTY);
        UniqueRecipeList expectedUniqueRecipeList2 = new UniqueRecipeList();
        expectedUniqueRecipeList2.add(SANDWICH_DIFF_QTY);
        assertEquals(expectedUniqueRecipeList2, uniqueRecipeList);
    }

    @Test
    public void setRecipe_editedRecipeHasNonUniqueIdentity_throwsDuplicateRecipeException() {
        uniqueRecipeList.add(SANDWICH);
        uniqueRecipeList.add(MARGARITAS);
        assertThrows(DuplicateRecipeException.class, () -> uniqueRecipeList.setRecipe(SANDWICH, MARGARITAS));
    }

    @Test
    public void remove_nullRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecipeList.remove(null));
    }

    @Test
    public void remove_recipeDoesNotExist_throwsRecipeNotFoundException() {
        assertThrows(RecipeNotFoundException.class, () -> uniqueRecipeList.remove(SANDWICH));
    }

    @Test
    public void remove_existingRecipe_removesRecipe() {
        uniqueRecipeList.add(SANDWICH);
        uniqueRecipeList.remove(SANDWICH);
        UniqueRecipeList expectedUniqueRecipeList = new UniqueRecipeList();
        assertEquals(expectedUniqueRecipeList, uniqueRecipeList);
    }

    @Test
    public void setRecipes_nullUniqueRecipeList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecipeList.setRecipes((UniqueRecipeList) null));
    }

    @Test
    public void setRecipes_uniqueRecipeList_replacesOwnListWithProvidedUniqueRecipeList() {
        uniqueRecipeList.add(SANDWICH);
        UniqueRecipeList expectedUniqueRecipeList = new UniqueRecipeList();
        expectedUniqueRecipeList.add(MARGARITAS);
        uniqueRecipeList.setRecipes(expectedUniqueRecipeList);
        assertEquals(expectedUniqueRecipeList, uniqueRecipeList);
    }

    @Test
    public void setRecipes_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueRecipeList.setRecipes((List<Recipe>) null));
    }

    @Test
    public void setRecipes_list_replacesOwnListWithProvidedList() {
        uniqueRecipeList.add(SANDWICH);
        List<Recipe> recipeList = Collections.singletonList(MARGARITAS);
        uniqueRecipeList.setRecipes(recipeList);
        UniqueRecipeList expectedUniqueRecipeList = new UniqueRecipeList();
        expectedUniqueRecipeList.add(MARGARITAS);
        assertEquals(expectedUniqueRecipeList, uniqueRecipeList);
    }

    @Test
    public void setRecipes_listWithDuplicateRecipes_throwsDuplicateRecipeException() {
        List<Recipe> listWithDuplicateRecipes = Arrays.asList(SANDWICH, SANDWICH);
        assertThrows(DuplicateRecipeException.class, () -> uniqueRecipeList.setRecipes(listWithDuplicateRecipes));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueRecipeList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void clear_allRecipe_clearRecipe() {
        uniqueRecipeList.add(SANDWICH);
        uniqueRecipeList.add(MARGARITAS);
        uniqueRecipeList.clear();
        UniqueRecipeList expectedUniqueRecipeList = new UniqueRecipeList();
        assertEquals(expectedUniqueRecipeList, uniqueRecipeList);
    }
}
