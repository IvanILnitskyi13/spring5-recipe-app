package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class NotesToNotesCommandTest {

    final Long idValue = 1L;
    final String recipeNotes = "Notes";
    NotesToNotesCommand converter;

    @BeforeEach
    void setUp() throws Exception {
        converter = new NotesToNotesCommand();
    }

    @Test
    void convert() throws Exception {
        //given
        Notes notes = new Notes();
        notes.setId(idValue);
        notes.setRecipeNotes(recipeNotes);

        //when
        NotesCommand notesCommand = converter.convert(notes);

        //then
        assertEquals(idValue, notesCommand.getId());
        assertEquals(recipeNotes, notesCommand.getRecipeNotes());
    }

    @Test
    void testNull() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new Notes()));
    }
}