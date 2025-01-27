package com.example.testovoe_birthday.Service;

import com.example.testovoe_birthday.Model.Birthday;
import com.example.testovoe_birthday.Repository.BirthdayRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BirthdayService {
    private final BirthdayRepository birthdayRepository;

    @Cacheable(value = "allBirthdays")
    public List<Birthday> getAllBirthdays() {
        log.info("Получаем все ДР");
        return birthdayRepository.findAll();
    }

    public Birthday getCurrentBirthday(Long id) {
        log.info("Получаем ДР конкретного человека");
        return birthdayRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error"));

    }

    @CacheEvict(value = "allBirthdays", allEntries = true)
    public Birthday saveBirthday(Birthday birthday) {
        log.info("Пытаемся сохранить новое ДР");
        if (birthday.getBirthdayDate().after(new Date())) {
            log.info("Ошибка при сохранении ДР, неверная дата");
            throw new IllegalArgumentException("Ошибка при сохранении ДР, неверная дата");
        }
        log.info("Сохраняем ДР");
        return birthdayRepository.save(birthday);
    }
    @CacheEvict(value = "allBirthdays", allEntries = true)
    public void deleteBirthday(Long id) {
        log.info("Удаляем ДР");
        birthdayRepository.deleteById(id);
    }


}
