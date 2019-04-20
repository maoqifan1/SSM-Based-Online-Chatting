package com.mao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("All")
@Entity
@Table(name = "relationship", schema = "Project")
public class RelationshipEntity {
    private String relationship_name;

    @Id
    @Column(name = "relationship_name")
    public String getRelationshipName() {
        return relationship_name;
    }

    public void setRelationshipName(String relationshipName) {
        this.relationship_name = relationshipName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RelationshipEntity that = (RelationshipEntity) o;

        if (relationship_name != null ? !relationship_name.equals(that.relationship_name) : that.relationship_name != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return relationship_name != null ? relationship_name.hashCode() : 0;
    }
}
