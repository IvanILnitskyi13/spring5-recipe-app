package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NotesCommandToNotesTest {

    final Long idValue = 1L;
    final String recipeNotes = "Notes";

    NotesCommandToNotes converter;

    @BeforeEach
    void setUp() throws Exception {
        converter = new NotesCommandToNotes();

    }

    @Test
    void testNullParameter(){
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject(){
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    void convert(){
        //given
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(idValue);
        notesCommand.setRecipeNotes(recipeNotes);

        //when
        Notes notes = converter.convert(notesCommand);

        //then
        assertNotNull(notes);
        assertEquals(idValue, notes.getId());
        assertEquals(recipeNotes, notes.getRecipeNotes());
    }

}