package guru.springframework.converters;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UnitOfMeasureCommandToUnitOfMeasureTest {

    final String description = "unit";
    final Long identifier = 1L;

    UnitOfMeasureCommandToUnitOfMeasure converter;

    @BeforeEach
    void setUp() {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    void testNullParametr(){
        assertThat(converter.convert(null)).isNull();
    }

    @Test
    void testEmptyObject(){
        assertThat(converter.convert(new UnitOfMeasureCommand())).isNotNull();
    }

    @Test
    void convert() {
        UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setId(identifier);
        command.setDescription(description);

        UnitOfMeasure unitOfMeasure = converter.convert(command);

        assertThat(unitOfMeasure).isNotNull();
        assertThat(unitOfMeasure.getId()).isEqualTo(identifier);
        assertThat(unitOfMeasure.getDescription()).isEqualTo(description);
    }
}