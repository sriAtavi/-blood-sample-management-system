package com.Atavi.bsm.util;

//30-01-2025

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageStructure<T> extends ResponseStructure<T>
{
    private int page;
    private int totalPages;
    private int size;
}
