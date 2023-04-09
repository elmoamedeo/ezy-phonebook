package com.ezy.phonebookapp.entrypoint.validation.sortdirection;

import static java.util.Objects.nonNull;

import com.ezy.phonebookapp.entrypoint.exception.SortDirectionException;
import java.util.stream.Stream;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SortDirectionValidator implements ConstraintValidator<ValidSortDirection, String> {

    @Override
    public boolean isValid(final String sortDirection, final ConstraintValidatorContext context) {
        if (nonNull(sortDirection)
                && Stream.of("asc", "desc").noneMatch(order -> order.equalsIgnoreCase(sortDirection))) {
            throw new SortDirectionException(sortDirection);
        }

        return true;
    }
}
