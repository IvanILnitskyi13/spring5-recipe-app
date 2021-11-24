package guru.springframework.commands;

import guru.springframework.domain.Difficulty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;

    @NotBlank
    @Size(min = 3, max = 255, message = "Description must be between 3 and 255 characters")
    private String description;

    @Size(min = 1, max = 999, message = "Cook time must be between 1 and 999 characters")
    private Integer prepTime;

    @Size(min = 1, max = 999, message = "Cook time must be between 1 and 999 characters")
    private Integer cookTime;

    @Size(min = 1, max = 100, message = "Servings must be between 1 and 100 characters")
    private Integer servings;
    private String source;

    @URL
    private String url;

    @NotBlank
    private String directions;

    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Byte[] image;
    private Difficulty difficulty;
    private NotesCommand notes;
    private Set<CategoryCommand> categories = new HashSet<>();
}
