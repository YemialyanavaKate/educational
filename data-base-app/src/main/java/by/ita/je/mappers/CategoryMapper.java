package by.ita.je.mappers;

import by.ita.je.dto.CategoryDto;
import by.ita.je.models.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CategoryMapper {
    public CategoryDto toDto(Category category) {
        return CategoryDto.builder()
                .number(category.getNumber())
                .category(categoryEnumToDto(category.getCategory()))
                .build();
    }

    public Category toEntity(CategoryDto categoryDto) {
        return Category.builder()
                .number(categoryDto.getNumber())
                .category(categoryEnumToEntity(categoryDto.getCategory()))
                .build();

    }

    public CategoryDto.CategoryEnum categoryEnumToDto(Category.CategoryEnum categoryEnum) {
        if (categoryEnum == null) {
            return null;
        }
        switch (categoryEnum) {
            case IT:
                return CategoryDto.CategoryEnum.IT;
            case MANAGEMENT:
                return CategoryDto.CategoryEnum.MANAGEMENT;
            default:
                throw new IllegalArgumentException("Unknown category: " + categoryEnum);
        }
    }

    public Category.CategoryEnum categoryEnumToEntity(CategoryDto.CategoryEnum categoryEnum) {
        if (categoryEnum == null) {
            return null;
        }
        switch (categoryEnum) {
            case IT:
                return Category.CategoryEnum.IT;
            case MANAGEMENT:
                return Category.CategoryEnum.MANAGEMENT;
            default:
                throw new IllegalArgumentException("Unknown category: " + categoryEnum);
        }
    }
}
