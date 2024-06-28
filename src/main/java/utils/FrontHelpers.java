package utils;

import entities.Category;
import entities.User;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FrontHelpers {

    public static String getUsernameById(long userId, List<User> userList) {
        return userList.stream()
                .filter(user -> user.getId() == userId)
                .map(User::getUsername)
                .findFirst()
                .orElse("User not found");
    }

    public static String getCategoryById(long categoryId, List<Category> categoriesList) {
        return categoriesList.stream()
                .filter(category -> category.getId() == categoryId)
                .map(Category::getName)
                .findFirst()
                .orElse("Category not found");
    }

    public static String formatLocalDateTime(LocalDateTime dateTime) {
        return formatDuration(Duration.between(dateTime, LocalDateTime.now()));
    }

    private static String formatDuration(Duration duration) {
        long minutes = duration.toMinutes();
        if (minutes < 60) {
            return minutes + " minutes ago";
        }
        long hours = duration.toHours();
        if (hours < 24) {
            return hours + " hours and " + (minutes % 60) + " minutes ago";
        }
        long days = duration.toDays();
        if (days < 10) {
            return days + " days ago";
        }
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now().minus(duration));
    }

    public static String formatTimestamp(Timestamp timestamp) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime timestampDateTime = convertToLocalDateTime(timestamp);

        Duration duration = Duration.between(timestampDateTime, now);

        if (duration.toMinutes() < 60) {
            return duration.toMinutes() + " minutes ago";
        } else if (duration.toHours() < 24) {
            long hours = duration.toHours();
            long minutes = duration.toMinutes() % 60;
            return hours + " hours and " + minutes + " minutes ago";
        } else if (duration.toDays() < 10) {
            return duration.toDays() + " days ago";
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return timestampDateTime.format(formatter);
        }
    }

    private static LocalDateTime convertToLocalDateTime(Timestamp timestamp) {
        return timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
