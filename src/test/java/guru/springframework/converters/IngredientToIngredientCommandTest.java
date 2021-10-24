package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


class IngredientToIngredientCommandTest {

    final Recipe recipe = new Recipe();
    final BigDecimal amount = new BigDecimal("1");
    final String description = "Cheeseburger";
    final Long uomId = 2L;
    final Long idValue = 1L;


    IngredientToIngredientCommand converter;

    @BeforeEach
    void setUp() {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    void testNullConvert() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    void testConvertNullUOM() {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(idValue);
        ingredient.setRecipe(recipe);
        ingredient.setAmount(amount);
        ingredient.setDescription(description);
        ingredient.setUnitOfMeasure(null);

        //when
        IngredientCommand ingredientCommand = converter.convert(ingredient);

        //then
        assertNull(ingredientCommand.getUnitOfMeasure());
        assertEquals(idValue, ingredientCommand.getId());
        assertEquals(amount, ingredientCommand.getAmount());
        assertEquals(description, ingredientCommand.getDescription());
    }

    @Test
    void testConvertWithUom() throws Exception {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setId(idValue);
        ingredient.setRecipe(recipe);
        ingredient.setAmount(amount);
        ingredient.setDescription(description);

        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(uomId);

        ingredient.setUnitOfMeasure(uom);

        //when
        IngredientCommand ingredientCommand = converter.convert(ingredient);

        //then
        assertEquals(idValue, ingredientCommand.getId());
        assertNotNull(ingredientCommand.getUnitOfMeasure());
        assertEquals(uomId, ingredientCommand.getUnitOfMeasure().getId());
        assertEquals(amount, ingredientCommand.getAmount());
        assertEquals(description, ingredientCommand.getDescription());
    }
}