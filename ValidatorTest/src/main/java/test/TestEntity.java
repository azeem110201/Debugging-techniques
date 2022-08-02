package test;

import com.fasterxml.jackson.annotation.JsonProperty;
import test.validation.NonNegative;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "test_entity")
@Entity
public class TestEntity {

    @Id
    private Long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "not blank number")
    @NonNegative(message = "number cannot be negative")
    private Long number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}
