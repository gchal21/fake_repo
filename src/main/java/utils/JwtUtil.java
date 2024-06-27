package utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    private static final String SECRET = "your_secret_key"; // In production, use a secure way to store this
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days

    public static String generateToken(long userId, String username, int roleId) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("userId", userId);
        payload.put("username", username);
        payload.put("roleId", roleId);
        payload.put("exp", new Date(System.currentTimeMillis() + EXPIRATION_TIME).getTime() / 1000);

        String header = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";
        String encodedHeader = base64UrlEncode(header);
        String encodedPayload = base64UrlEncode(mapToJson(payload));

        String signature = createSignature(encodedHeader + "." + encodedPayload);

        return encodedHeader + "." + encodedPayload + "." + signature;
    }

    public static Map<String, Object> verifyToken(String token) {
        String[] parts = token.split("\\.");
        if (parts.length != 3) {
            return null;
        }

        String encodedHeader = parts[0];
        String encodedPayload = parts[1];
        String signature = parts[2];

        String newSignature = createSignature(encodedHeader + "." + encodedPayload);
        if (!newSignature.equals(signature)) {
            return null;
        }

        String payloadJson = new String(Base64.getUrlDecoder().decode(encodedPayload), StandardCharsets.UTF_8);
        Map<String, Object> payload = jsonToMap(payloadJson);

        long expiration = Long.parseLong(payload.get("exp").toString());
        if (expiration < System.currentTimeMillis() / 1000) {
            return null;
        }

        return payload;
    }

    private static String createSignature(String content) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest((content + SECRET).getBytes(StandardCharsets.UTF_8));
            return base64UrlEncode(Base64.getEncoder().encodeToString(hash));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error creating signature", e);
        }
    }

    private static String base64UrlEncode(String input) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(input.getBytes(StandardCharsets.UTF_8));
    }

    private static String mapToJson(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (sb.length() > 1) {
                sb.append(",");
            }
            sb.append("\"").append(entry.getKey()).append("\":\"").append(entry.getValue()).append("\"");
        }
        sb.append("}");
        return sb.toString();
    }

    private static Map<String, Object> jsonToMap(String json) {
        Map<String, Object> map = new HashMap<>();
        json = json.substring(1, json.length() - 1); // Remove { and }
        String[] keyValuePairs = json.split(",");
        for (String pair : keyValuePairs) {
            String[] entry = pair.split(":");
            map.put(entry[0].trim().replace("\"", ""), entry[1].trim().replace("\"", ""));
        }
        return map;
    }
}