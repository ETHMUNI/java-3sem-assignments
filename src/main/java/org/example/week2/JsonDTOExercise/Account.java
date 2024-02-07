package org.example.week2.JsonDTOExercise;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.File;
import java.io.IOException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {
    @Getter
    @JsonProperty("firstName")
    private String firstName;

    @Getter
    @JsonProperty("lastName")
    private String lastName;

    @Getter
    @JsonProperty("address")
    private AddressDTO address; // Adjusted to map the nested structure

    @Getter
    @JsonProperty("account")
    private AccountDetailsDTO accountDetails; // Adjusted to map the nested structure

    public static void main(String[] args) {
        Account account = new Account();
        Account[] accounts = account.readFile();
        NameDTO[] dtos = convertToDTO(accounts);
        printDTOs(dtos);
    }

    public Account[] readFile() {
        String fileName = "src/main/java/org/example/week2/JsonDTOExercise/account.json";
        File file = new File(fileName);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(file, Account[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static NameDTO[] convertToDTO(Account[] accounts) {
        if (accounts == null) {
            return null;
        }

        NameDTO[] dto = new NameDTO[accounts.length];
        for (int i = 0; i < accounts.length; i++) {
            Account account = accounts[i];
            NameDTO nameDTO = new NameDTO(); // Correctly instantiate a new NameDTO object
            nameDTO.setFullName(account.getFirstName() + " " + account.getLastName());
            nameDTO.setCity(account.getAddress().getCity()); // Correctly access nested city
            nameDTO.setZipCode(account.getAddress().getZipCode()); // Correctly access nested zipCode
            nameDTO.setIsActive(String.valueOf(account.getAccountDetails().isActive())); // Correctly access and convert boolean isActive
            dto[i] = nameDTO; // Assign the filled NameDTO object to the current index in the array
        }
        return dto;
    }

    public static void printDTOs(NameDTO[] dtos) {
        if (dtos == null) {
            return;
        }

        for (NameDTO dto : dtos) {
            System.out.println("Full Name: " + dto.getFullName());
            System.out.println("City: " + dto.getCity());
            System.out.println("Zip Code: " + dto.getZipCode());
            System.out.println("Is Active: " + dto.getIsActive());
            System.out.println();
        }
    }

    @Getter
    @Setter
    @ToString
    private static class NameDTO {
        private String fullName;
        private String city;
        private String zipCode;
        private String isActive;
    }

    @Getter
    @Setter
    public static class AddressDTO {
        private String city;
        private String zipCode;
        private String street;
    }

    @Getter
    @Setter
    public static class AccountDetailsDTO {
        private String id;
        private String balance;
        @JsonProperty("isActive")
        private boolean isActive;

    }
}
