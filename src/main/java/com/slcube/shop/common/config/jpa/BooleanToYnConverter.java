package com.slcube.shop.common.config.jpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import static com.slcube.shop.common.config.jpa.RowStatus.*;

@Converter
public class BooleanToYnConverter implements AttributeConverter<Boolean, String> {
    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return (attribute != null && attribute) ? YES.getRowStatus() : NO.getRowStatus();
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return YES.getRowStatus().equalsIgnoreCase(dbData);
    }
}
