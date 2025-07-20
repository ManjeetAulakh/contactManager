package com.scm.contactManager.payloads;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContactForm {

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Enter valid email..")
    @NotBlank(message = "Email is required")
    private String email;

    
    @NotBlank(message = "PhoneNumber is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Invlid Number")
    private String phoneNumber;

    @NotBlank(message = "Address is required")
    private String address;

    private MultipartFile picture;
    private String description;
    private boolean favorite;
    private String linkedinUrl;
    private String facebookUrl;

}
