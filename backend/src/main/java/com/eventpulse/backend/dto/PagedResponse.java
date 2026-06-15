package com.eventpulse.backend.dto;

import org.springframework.data.domain.Page;
import java.util.List;

public class PagedResponse<T> {

    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean lastPage;

    public static <T> PagedResponse<T> from(Page<T> page) {
        PagedResponse<T> response = new PagedResponse<>();
        response.content       = page.getContent();
        response.pageNumber    = page.getNumber();
        response.pageSize      = page.getSize();
        response.totalElements = page.getTotalElements();
        response.totalPages    = page.getTotalPages();
        response.lastPage      = page.isLast();
        return response;
    }
    public List<T> getContent()        { return content; }
    public int getPageNumber()         { return pageNumber; }
    public int getPageSize()           { return pageSize; }
    public long getTotalElements()     { return totalElements; }
    public int getTotalPages()         { return totalPages; }
    public boolean isLastPage()        { return lastPage; }

}
