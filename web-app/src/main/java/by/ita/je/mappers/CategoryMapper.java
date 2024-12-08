package by.ita.je.mappers;

import by.ita.je.dto.CategoryWebDto;
import by.ita.je.models.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CategoryMapper {
    public Category toEntityFromWebDto(CategoryWebDto categoryWebDto) {

        if (categoryWebDto != null) {
            return Category.builder()
                    .number(categoryWebDto.getNumber())
                    .categoryEnum(categoryEnumToEntity(categoryWebDto.getCategoryEnum()))
                    .build();
        } else return null;

    }

    public CategoryWebDto toWebDtoFromEntity(Category category) {
        if (category != null) {
            return CategoryWebDto.builder()
                    .number(category.getNumber())
                    .categoryEnum(categoryEnumToWebDto(category.getCategoryEnum()))
                    .build();
        } else return null;
    }

    public Category.CategoryEnum categoryEnumToEntity(CategoryWebDto.CategoryEnum categoryEnum) {
        if (categoryEnum == null) {
            return null;
        }
        return switch (categoryEnum) {
            case IT -> Category.CategoryEnum.IT;
            case MANAGEMENT -> Category.CategoryEnum.MANAGEMENT;
        };
    }

    public CategoryWebDto.CategoryEnum categoryEnumToWebDto(Category.CategoryEnum categoryEnum) {
        if (categoryEnum == null) {
            return null;
        }
        return switch (categoryEnum) {
            case IT -> CategoryWebDto.CategoryEnum.IT;
            case MANAGEMENT -> CategoryWebDto.CategoryEnum.MANAGEMENT;
        };
    }
}
