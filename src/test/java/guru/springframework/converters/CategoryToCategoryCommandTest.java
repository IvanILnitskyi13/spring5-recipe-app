package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CategoryToCategoryCommandTest {

    final Long idValue = 1L;
    final String description = "description";
    CategoryToCategoryCommand converter;

    @BeforeEach
    void setUp() throws Exception {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    void convert() throws Exception {
        //given
        Category category = new Category();
        category.setId(idValue);
        category.setDescription(description);

        //when
        CategoryCommand categoryCommand = converter.convert(category);

        //then
        assertEquals(idValue, categoryCommand.getId());
        assertEquals(description, categoryCommand.getDescription());

    }

}