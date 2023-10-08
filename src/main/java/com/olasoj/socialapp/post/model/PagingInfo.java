package com.olasoj.socialapp.post.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class PagingInfo implements Serializable {
    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final int MAX_PAGE_SIZE = 200;
    private int pageSize;
    private int currentPage;
    private int totalRecord;
    private int totalPages;
    private int limitMin;
    private int limitMax;

    public PagingInfo() {
        this(DEFAULT_PAGE_SIZE, 1, 0);
    }

    public PagingInfo(int pageSize, int currentPage, int totalRecord) {
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;
        this.totalPages = computeTotalPages(pageSize, totalRecord);
        this.currentPage = currentPage < 1 ? 1 : Math.min(Math.abs(currentPage), totalPages);

        this.limitMin = Math.max((this.currentPage - 5), 1);
        this.limitMax = (Math.min((this.currentPage) + 5, totalPages));
    }

    public PagingInfo(int pageSize, int currentPage) {
        this(pageSize, currentPage, 0);
    }

    public PagingInfo(KYCQueuePagingInfoBuilder kycQueuePagingInfoBuilder) {
        this(kycQueuePagingInfoBuilder.pageSize, kycQueuePagingInfoBuilder.currentPage, kycQueuePagingInfoBuilder.totalRecord);
    }

    public static KYCQueuePagingInfoBuilder builder() {
        return new KYCQueuePagingInfoBuilder();
    }

    private int computeTotalPages(int pageSize, int totalRecord) {
        if (pageSize < 1) throw new IllegalStateException("Page size must be greater than zero");
        int totalPagesLocal = (int) Math.ceil((double) totalRecord / pageSize);
        return Math.max(totalPagesLocal, 1);
    }

    public int getPageSize() {
        if ((this.pageSize <= 15)) {
            setPageSize(15);
        }
        if ((this.pageSize > MAX_PAGE_SIZE)) {
            setPageSize(MAX_PAGE_SIZE);
        }
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return (this.currentPage);
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return Math.max(this.totalPages, 1);
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = Math.max(totalPages, 1);
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getLimitMin() {
        return limitMin;
    }

    public void setLimitMin(int limitMin) {
        this.limitMin = limitMin;
    }

    public int getLimitMax() {
        return limitMax;
    }

    public void setLimitMax(int limitMax) {
        this.limitMax = limitMax;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.totalRecord)
                .append(this.pageSize)
                .append(this.currentPage)
                .append(this.totalPages)
                .append(this.limitMin)
                .append(this.limitMax)
                .toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this)
            return true;

        if (!(obj instanceof final PagingInfo otherPagingInfo))
            return false;

        return new EqualsBuilder()
                .append(this.pageSize, otherPagingInfo.pageSize)
                .append(this.currentPage, otherPagingInfo.currentPage)
                .append(this.totalRecord, otherPagingInfo.totalRecord)
                .append(this.totalPages, otherPagingInfo.totalPages)
                .append(this.limitMin, otherPagingInfo.limitMin)
                .append(this.limitMax, otherPagingInfo.limitMax)
                .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("pageSize", this.pageSize)
                .append("totalRecord", this.totalRecord)
                .append("currentPage", this.currentPage)
                .append("totalPages", this.totalPages)
                .append("limitMin", this.limitMin)
                .append("limitMax", this.limitMax)
                .toString();
    }

    public static class KYCQueuePagingInfoBuilder {

        private int pageSize;
        private int totalRecord;
        private int currentPage;

        public KYCQueuePagingInfoBuilder totalRecord(int totalRecord) {
            this.totalRecord = totalRecord;
            return this;
        }

        public KYCQueuePagingInfoBuilder pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public KYCQueuePagingInfoBuilder currentPage(int currentPage) {
            this.currentPage = currentPage;
            return this;
        }

        public PagingInfo build() {
            return new PagingInfo(this);
        }
    }

}
