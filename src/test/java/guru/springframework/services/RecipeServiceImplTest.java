package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceImplTest {

    private  RecipeServiceImpl recipeServiceImpl;

    @Mock
    private  RecipeRepository recipeRepository;

    @BeforeEach
    public void setUp() {
        recipeServiceImpl = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    void shouldGetRecipes() {
        Recipe recipe = new Recipe();
        HashSet<Recipe> recipesData = new HashSet<>();
        recipesData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipesData);

        Set<Recipe> recipes = recipeServiceImpl.getRecipes();

        assertThat(recipes.size()).isEqualTo(1);
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    void shouldGetRecipeById() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeServiceImpl.findById(1L);

        assertThat(recipeReturned).isNotNull();
        assertThat(recipeReturned.getId()).isEqualTo(1L);
        verify(recipeRepository, times(1)).findById(anyLong());

    }
}