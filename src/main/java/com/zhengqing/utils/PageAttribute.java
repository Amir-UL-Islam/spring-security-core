package com.zhengqing.utils;

import org.springframework.data.domain.Pageable;

import java.util.List;

public class PageAttribute {
    public static <T> List<T> subSetListAsPageable(List<T> list, Pageable pageable) {

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());
        if (start > end) {
            start = 0; // Reset the start index if it's greater than the end index
        }
        return list.subList(start, end);

    }
}
