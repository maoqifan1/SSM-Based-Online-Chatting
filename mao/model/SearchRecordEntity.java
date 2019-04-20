package com.mao.model;

import javax.persistence.*;

@Entity
@Table(name = "search_record", schema = "Project", catalog = "")
public class SearchRecordEntity {
    private int id;
    private int suserid;
    private String record;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "suserid")
    public int getSuserid() {
        return suserid;
    }

    public void setSuserid(int suserid) {
        this.suserid = suserid;
    }

    @Basic
    @Column(name = "record")
    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchRecordEntity that = (SearchRecordEntity) o;

        if (id != that.id) return false;
        if (suserid != that.suserid) return false;
        if (record != null ? !record.equals(that.record) : that.record != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + suserid;
        result = 31 * result + (record != null ? record.hashCode() : 0);
        return result;
    }
}
