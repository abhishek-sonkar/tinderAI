package com.abhison.tinderAi.profiles;

public record Profile(
        String id,
        String firstName,
        String lastName,
        int age,
        Gender gender,
        String ethnicity,
        String bio,
        String imageUrl,
        String myersBriggsPersonalityType
) {
}
