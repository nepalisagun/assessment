
package com.paypal.bfs.test.employeeserv.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@JsonPropertyOrder({
        "id",

        "first_name",
        "last_name",
        "date_of_birth",
        "address"
})
@Entity
@Table(name = "employee_data")
public class Employee {

    public Employee() {
    }

    public Employee(Integer id, @NotBlank(message = "First Name Required") String firstName,
                    @NotEmpty(message = "Last Name Required") String lastName,
                    @NotBlank(message = "Date of Birth Required") String dateOfBirth, @Valid Address address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    @JsonProperty("id")
    @JsonPropertyDescription("employee id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "First Name Required")
    @Column(name = "first_name")
    private String firstName;

    @JsonProperty("last_name")
    @JsonPropertyDescription("last name")
    @NotEmpty(message = "Last Name Required")
    private String lastName;

    @JsonProperty("date_of_birth")
    @JsonPropertyDescription("date of birth")
    @NotBlank(message = "Date of Birth Required")
    private String dateOfBirth;

    @JsonProperty("address")
    @Embedded
    @Valid
    @AttributeOverrides(value = {
            @AttributeOverride(name = "line1", column = @Column(name = "address_Line_1")),
            @AttributeOverride(name = "line2", column = @Column(name = "address_Line_2")),
            @AttributeOverride(name = "city", column = @Column(name = "city")),
            @AttributeOverride(name = "state", column = @Column(name = "state")),
            @AttributeOverride(name = "country", column = @Column(name = "country")),
            @AttributeOverride(name = "zip_code", column = @Column(name = "zip_code"))
    })
    private Address address;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("first_name")
    public String getFirstName() {
        return firstName;
    }

   @JsonProperty("first_name")
   public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("last_name")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("date_of_birth")
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    @JsonProperty("date_of_birth")
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @JsonProperty("address")
    public Address getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", address=" + address +
                '}';
    }
}
