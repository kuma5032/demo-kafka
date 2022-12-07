package com.example.kafka;

import com.github.javafaker.Faker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class SimpleMessage {

    private static final List<String> genres = List.of("action", "drama",
            "melo", "horror", "sports");

    private static final List<String> movieClass = List.of("A001", "B001", "C001",
            "D001", "E001", "F001", "G001", "H001", "I001", "J001", "K001", "L001", "M001", "N001",
            "O001", "P001", "Q001", "R001");

    public SimpleMessage() {}

    private String getRandomValueFromList(List<String> list, Random random) {
        int size = list.size();
        int index = random.nextInt(size);
        return list.get(index);
    }

    public HashMap<String, String> produce_msg(Faker faker, Random random, int id) {

        String movieClassId = getRandomValueFromList(movieClass, random);
        String movieGenre = getRandomValueFromList(genres, random);

        String companyName = faker.company().name();
        String directorName = faker.name().fullName();
        String nation = faker.nation().language();
        LocalDateTime now = LocalDateTime.now();
        String message = String.format("class_group_id:%s, movieGenre:%s, companyName:%s, directorName:%s, nation:%s, time:%s"
                , movieClassId, movieGenre, companyName, directorName, nation
                , now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.KOREAN)));
        HashMap<String, String> messageMap = new HashMap<>();
        messageMap.put("key", movieClassId);
        messageMap.put("message", message);

        return messageMap;
    }
}
