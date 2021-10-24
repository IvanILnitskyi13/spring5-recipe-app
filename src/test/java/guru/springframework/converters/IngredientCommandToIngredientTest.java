package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


class IngredientCommandToIngredientTest {

        final BigDecimal AMOUNT = new BigDecimal("1");
    final String DESCRIPTION = "Cheeseburger";
    final Long ID_VALUE = 1L;
    final Long UOM_ID = 2L;

    IngredientCommandToIngredient converter;

    @BeforeEach
    void setUp() {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    void convert() {
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(ID_VALUE);
        command.setAmount(AMOUNT);
        command.setDescription(DESCRIPTION);
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(UOM_ID);
        command.setUnitOfMeasure(unitOfMeasureCommand);

        //when
        Ingredient ingredient = converter.convert(command);

        //then
        assertNotNull(ingredient);
        assertNotNull(ingredient.getUnitOfMeasure());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(UOM_ID, ingredient.getUnitOfMeasure().getId());
    }

    @Test
    void convertWithNullUOM() {
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(ID_VALUE);
        command.setAmount(AMOUNT);
        command.setDescription(DESCRIPTION);


        //when
        Ingredient ingredient = converter.convert(command);

        //then
        assertNotNull(ingredient);
        assertNull(ingredient.getUnitOfMeasure());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());

    }

}