package com.bikcode.models

import com.bikcode.util.Constants.LOREM_IPSUM_SHORT
import com.bikcode.util.Res


enum class Testimonial(
    val image: String,
    val fullName: String,
    val profession: String,
    val review: String,
    val rating: Double
) {
    First(
        image = Res.Image.avatar1,
        fullName = "Milica Ilic",
        profession = "Web Designer",
        review = LOREM_IPSUM_SHORT,
        rating = 3.5
    ),
    Second(
        image = Res.Image.avatar2,
        fullName = "Stefan Jovanovic",
        profession = "Android Developer",
        review = LOREM_IPSUM_SHORT,
        rating = 2.0
    ),
    Third(
        image = Res.Image.avatar3,
        fullName = "Ana Milic",
        profession = "Business Analyst",
        review = LOREM_IPSUM_SHORT,
        rating = 5.0
    ),
    Fourth(
        image = Res.Image.avatar4,
        fullName = "Darko Pesic",
        profession = "Top Manager",
        review = LOREM_IPSUM_SHORT,
        rating = 10.0
    ),
    Fifth(
        image = Res.Image.avatar5,
        fullName = "Milena Nesovic",
        profession = "HR Recruiter",
        review = LOREM_IPSUM_SHORT,
        rating = 8.0
    ),
    Sixth(
        image = Res.Image.avatar6,
        fullName = "Aca Rodic",
        profession = "Cyber Security Analyst",
        review = LOREM_IPSUM_SHORT,
        rating = 1.0
    )
}