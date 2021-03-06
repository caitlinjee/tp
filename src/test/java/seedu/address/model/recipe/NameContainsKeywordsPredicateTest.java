package seedu.address.model.recipe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.RecipeBuilder;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameContainsKeywordsPredicate firstPredicate = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        NameContainsKeywordsPredicate secondPredicate = new NameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeywordsPredicate firstPredicateCopy = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different recipe -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.singletonList("Pasta"));
        assertTrue(predicate.test(new RecipeBuilder().withName("Pasta Sandwich").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Sandwich", "Pasta"));
        assertTrue(predicate.test(new RecipeBuilder().withName("Sandwich Pasta").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Pasta", "Pork"));
        assertTrue(predicate.test(new RecipeBuilder().withName("Sandwich Pasta").build()));
        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("sANdWich", "pasTa"));
        assertTrue(predicate.test(new RecipeBuilder().withName("Sandwich Pasta").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new RecipeBuilder().withName("Sandwich").build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Pork"));
        assertFalse(predicate.test(new RecipeBuilder().withName("Sandwich Pasta").build()));

        // Keywords match ingredients and calories but does not match name
        predicate = new NameContainsKeywordsPredicate(
                Arrays.asList("Kaiser", "Rolls", "Or", "Other", "Bread", "2", "whole", "70"));
        assertFalse(predicate.test(new RecipeBuilder().withName("Sandwich")
                .withIngredient("Kaiser Rolls Or Other Bread", "2 whole")
                .withCalories(70)
                .build()));
    }
}
