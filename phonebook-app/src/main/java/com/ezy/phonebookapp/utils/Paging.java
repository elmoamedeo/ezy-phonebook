package com.ezy.phonebookapp.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Paging {

    private long total;
    private long offset;
    private long limit;
}
