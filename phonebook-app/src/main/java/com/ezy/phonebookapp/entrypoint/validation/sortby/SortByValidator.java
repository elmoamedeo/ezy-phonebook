package com.ezy.phonebookapp.entrypoint.validation.sortby;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.LOWER_UNDERSCORE;
import static java.util.Arrays.stream;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toList;

import com.ezy.phonebookapp.entrypoint.exception.SortFieldException;
import com.ezy.phonebookapp.infra.gateway.database.entity.PhonebookEntity;
import java.lang.reflect.Field;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SortByValidator implements ConstraintValidator<ValidSortBy, String> {

    @Override
    public boolean isValid(final String sortField, final ConstraintValidatorContext context) {
        final Field[] fields = PhonebookEntity.class.getDeclaredFields();

        final List<String> possibleSortFields = stream(fields)
                .map(field -> LOWER_CAMEL.to(LOWER_UNDERSCORE, field.getName()))
                .collect(toList());

        if (nonNull(sortField) && possibleSortFields.stream().noneMatch(sortField::equalsIgnoreCase)) {
            throw new SortFieldException(possibleSortFields.toString(), sortField);
        }

        return true;
    }
}
