package by.ita.je.mappers;

import by.ita.je.dto.CategoryDto;
import by.ita.je.models.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CategoryMapper {
    public CategoryDto toDto(Category category){
        return CategoryDto.builder()
                .id(category.getId())
                .category(categoryEnumToDto(category.getCategory()))
                .build();
    }

    public Category toEntity(CategoryDto categoryDto){
        return Category.builder()
                .id(categoryDto.getId())
                .category(categoryEnumToEntity(categoryDto.getCategory()))
                .build();

    }

    public CategoryDto.CategoryEnum categoryEnumToDto (Category.CategoryEnum categoryEnum){
        if (categoryEnum == null){
            return null;
        }
        switch (categoryEnum){
            case AI:
                return CategoryDto.CategoryEnum.AI;
            case JAVA:
                return CategoryDto.CategoryEnum.JAVA;
            case IT_HR:
                return CategoryDto.CategoryEnum.IT_HR;
            case PYTHON:
                return CategoryDto.CategoryEnum.PYTHON;
            default:
                throw new IllegalArgumentException("Unknown category: " + categoryEnum);
        }
    }

    public Category.CategoryEnum categoryEnumToEntity (CategoryDto.CategoryEnum categoryEnum){
        if (categoryEnum == null){
            return null;
        }
        switch (categoryEnum){
            case AI:
                return Category.CategoryEnum.AI;
            case JAVA:
                return Category.CategoryEnum.JAVA;
            case IT_HR:
                return Category.CategoryEnum.IT_HR;
            case PYTHON:
                return Category.CategoryEnum.PYTHON;
            default:
                throw new IllegalArgumentException("Unknown category: " + categoryEnum);
        }
    }
}
