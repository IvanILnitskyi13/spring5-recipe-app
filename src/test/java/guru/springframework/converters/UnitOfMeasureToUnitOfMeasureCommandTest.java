package guru.springframework.converters;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureToUnitOfMeasureCommandTest {

    final String description = "unit";
    final Long identifier = 1L;

    UnitOfMeasureToUnitOfMeasureCommand converter;

    @BeforeEach
    void setUp() {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    void testNullParametr(){
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject(){
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    void convert() {
        UnitOfMeasure command = new UnitOfMeasure();
        command.setId(identifier);
        command.setDescription(description);

        UnitOfMeasureCommand unitOfMeasure = converter.convert(command);

        assertNotNull(unitOfMeasure);
        assertEquals(unitOfMeasure.getId(), identifier);
        assertEquals(unitOfMeasure.getDescription(), description);
    }
}