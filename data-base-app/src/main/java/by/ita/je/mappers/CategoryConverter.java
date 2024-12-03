package by.ita.je.mappers;

import by.ita.je.models.Category;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class CategoryConverter implements AttributeConverter<Category.CategoryEnum, String> {
    @Override
    public String convertToDatabaseColumn(Category.CategoryEnum category) {
        return category != null ? category.getCode() : null;
    }

    @Override
    public Category.CategoryEnum convertToEntityAttribute(String code) {
        for (Category.CategoryEnum category : Category.CategoryEnum.values()) {
            if (category.getCode().equals(code)) return category;
        }
        throw new IllegalArgumentException("Неизвестная категория:" + code);
    }
}
