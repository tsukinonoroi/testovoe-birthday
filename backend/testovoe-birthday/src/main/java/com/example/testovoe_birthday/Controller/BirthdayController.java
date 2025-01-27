package com.example.testovoe_birthday.Controller;

import com.example.testovoe_birthday.Model.Birthday;
import com.example.testovoe_birthday.Repository.BirthdayRepository;
import com.example.testovoe_birthday.Service.BirthdayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller

@RequiredArgsConstructor
public class BirthdayController {
    private final BirthdayService birthdayService;
    private final BirthdayRepository birthdayRepository;

    @GetMapping("/")
    public ResponseEntity<List<Birthday>> getAllBirthdays() {
        List<Birthday> birthdays = birthdayService.getAllBirthdays();

        return ResponseEntity.status(HttpStatus.OK).body(birthdays);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Birthday> getCurrentBirthday(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(birthdayService.getCurrentBirthday(id));
    }

    @PostMapping("/")
    public ResponseEntity<Birthday> postBirthday(@RequestBody Birthday birthday) {
        try {
            Birthday savedBirthday = birthdayService.saveBirthday(birthday);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBirthday);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Birthday> putBirthday(@PathVariable Long id, @RequestBody Birthday birthday) {
        Optional<Birthday> existingBirthday = birthdayRepository.findById(id);
        if (!existingBirthday.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Birthday updatedBirthday = existingBirthday.get();
        updatedBirthday.setName(birthday.getName());
        updatedBirthday.setSurname(birthday.getSurname());
        updatedBirthday.setBirthdayDate(birthday.getBirthdayDate());
        updatedBirthday.setPhotoUrl(birthday.getPhotoUrl());

        Birthday savedBirthday = birthdayService.saveBirthday(updatedBirthday);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(savedBirthday);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteBirthday(@PathVariable Long id) {
        birthdayService.deleteBirthday(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .build();
    }

}
