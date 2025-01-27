package com.example.testovoe_birthday.Config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dlclbqa0g",
                "api_key", "274114995943279",
                "api_secret", "v_SHMqZqgUiJVRY3ZrdzNb9zylo"
        ));
    }
}
