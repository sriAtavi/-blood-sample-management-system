package com.Atavi.bsm.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorStructure<T>
{
    private int status;
    private String message;
    private T rootCause;
}
