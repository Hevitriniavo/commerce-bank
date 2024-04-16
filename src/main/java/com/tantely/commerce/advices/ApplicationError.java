package com.tantely.commerce.advices;

import java.time.LocalDate;

public record ApplicationError(
        String message,
        LocalDate errorDate,
        int status
) {}
