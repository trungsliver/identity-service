package com.ductrungsl.identity_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

// annotation của lombok, tự động generate method getter, setter
//@Getter
//@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 3, message = "USERNAME_INVALID")
    @NotBlank(message = "INFO_INVALID")
    private String username;

    // annotation validate
    @Size(min = 8, message = "PASSWORD_INVALID")
    @NotBlank(message = "INFO_INVALID")
    private String password;

    // add annotation check not blank
    @NotBlank(message = "INFO_INVALID")
    private String firstName;

    @NotBlank(message = "INFO_INVALID")
    private String lastName;

//    @NotBlank(message = "INFO_INVALID")
    private LocalDate dob;
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public LocalDate getDob() {
//        return dob;
//    }
//
//    public void setDob(LocalDate dob) {
//        this.dob = dob;
//    }
}
