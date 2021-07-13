package com.codegym.project.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Data
@Entity
public class Address {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String countryName;
        private String city;
        private String street;
        private String detail;
        private String zipcode;

        public Address() {
        }

        public Address(String countryName, String city, String street, String detail, String zipcode) {
                this.countryName = countryName;
                this.city = city;
                this.street = street;
                this.detail = detail;
                this.zipcode = zipcode;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Address address = (Address) o;
                return Objects.equals(id, address.id);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id, countryName, city, street, detail, zipcode);
        }
}
