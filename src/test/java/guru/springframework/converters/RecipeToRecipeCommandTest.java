package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class RecipeToRecipeCommandTest {

    final Long recipeId = 1L;
    final Integer cookTime = 5;
    final Integer PrepareTime = 7;
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
    RecipeToRecipeCommand converter;

    @BeforeEach
    void setUp() throws Exception {
        converter = new RecipeToRecipeCommand(
                new CategoryToCategoryCommand(),
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new NotesToNotesCommand());
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    void convert(){
        //given
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);
        recipe.setCookTime(cookTime);
        recipe.setPrepTime(PrepareTime);
        recipe.setDescription(description);
        recipe.setDifficulty(difficulty);
        recipe.setDirections(directions);
        recipe.setServings(servings);
        recipe.setSource(source);
        recipe.setUrl(url);

        Notes notes = new Notes();
        notes.setId(notesId);

        recipe.setNotes(notes);

        Category category = new Category();
        category.setId(catId1);

        Category category2 = new Category();
        category2.setId(catId2);

        recipe.getCategories().add(category);
        recipe.getCategories().add(category2);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientId1);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(ingredientId2);

        recipe.getIngredients().add(ingredient);
        recipe.getIngredients().add(ingredient2);

        //when
        RecipeCommand command = converter.convert(recipe);

        //then
        assertNotNull(command);
        assertEquals(recipeId, command.getId());
        assertEquals(cookTime, command.getCookTime());
        assertEquals(PrepareTime, command.getPrepTime());
        assertEquals(description, command.getDescription());
        assertEquals(difficulty, command.getDifficulty());
        assertEquals(directions, command.getDirections());
        assertEquals(servings, command.getServings());
        assertEquals(source, command.getSource());
        assertEquals(url, command.getUrl());
        assertEquals(notesId, command.getNotes().getId());
        assertEquals(2, command.getCategories().size());
        assertEquals(2, command.getIngredients().size());

    }

}