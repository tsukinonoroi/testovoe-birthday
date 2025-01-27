package com.example.testovoe_birthday.Repository;

import com.example.testovoe_birthday.Model.Birthday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BirthdayRepository extends JpaRepository<Birthday, Long> {

}
