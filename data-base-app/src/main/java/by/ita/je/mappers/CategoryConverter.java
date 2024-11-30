package by.ita.je.mappers;

import by.ita.je.models.Category;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class CategoryConverter implements AttributeConverter<Category.CategoryEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Category.CategoryEnum category) {
        return category != null ? category.getCode() : null;
    }

    @Override
    public Category.CategoryEnum convertToEntityAttribute(Integer dbData) {
        for (Category.CategoryEnum category : Category.CategoryEnum.values()) {
            if (category.getCode().equals(dbData)) return category;
        }
        throw new IllegalArgumentException("Неизвестная категория:" + dbData);
    }
}
