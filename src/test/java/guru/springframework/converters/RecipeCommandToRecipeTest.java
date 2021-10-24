package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.NotesCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeCommandToRecipeTest {
    final Long recipeId = 1L;
    final Integer cookTime = 5;
    final Integer prepareTime = 7;
    final String description = "My Recipe";
    final String directions = "Directions";
    final Difficulty difficulty = Difficulty.EASY;
    final Integer servings = 3;
    final String source = "Source";
    final String url = "Some URL";
    final Long catId1 = 1L;
    final Long catId2 = 2L;
    final Long ingredientId1 = 3L;
    final Long ingredientId2 = 4L;
    final Long notesId = 9L;

    RecipeCommandToRecipe converter;


    @BeforeEach
    void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NotesCommandToNotes());
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    void convert() {
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(recipeId);
        recipeCommand.setCookTime(cookTime);
        recipeCommand.setPrepTime(prepareTime);
        recipeCommand.setDescription(description);
        recipeCommand.setDifficulty(difficulty);
        recipeCommand.setDirections(directions);
        recipeCommand.setServings(servings);
        recipeCommand.setSource(source);
        recipeCommand.setUrl(url);

        NotesCommand notes = new NotesCommand();
        notes.setId(notesId);

        recipeCommand.setNotes(notes);

        CategoryCommand category = new CategoryCommand();
        category.setId(catId1);

        CategoryCommand category2 = new CategoryCommand();
        category2.setId(catId2);

        recipeCommand.getCategories().add(category);
        recipeCommand.getCategories().add(category2);

        IngredientCommand ingredient = new IngredientCommand();
        ingredient.setId(ingredientId1);

        IngredientCommand ingredient2 = new IngredientCommand();
        ingredient2.setId(ingredientId2);

        recipeCommand.getIngredients().add(ingredient);
        recipeCommand.getIngredients().add(ingredient2);

        //when
        Recipe recipe  = converter.convert(recipeCommand);

        assertNotNull(recipe);
        assertEquals(recipeId, recipe.getId());
        assertEquals(cookTime, recipe.getCookTime());
        assertEquals(prepareTime, recipe.getPrepTime());
        assertEquals(description, recipe.getDescription());
        assertEquals(difficulty, recipe.getDifficulty());
        assertEquals(directions, recipe.getDirections());
        assertEquals(servings, recipe.getServings());
        assertEquals(source, recipe.getSource());
        assertEquals(url, recipe.getUrl());
        assertEquals(notesId, recipe.getNotes().getId());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getIngredients().size());
    }
}