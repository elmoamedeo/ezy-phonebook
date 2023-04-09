package com.ezy.phonebookapp.utils;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pagination<T> {

    private Paging paging;
    private List<T> results;
}
