package dev.vlad.sport.auth;

public class SecurityContext {

    private static final ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    public static void setUser(User userDetails) {
        userThreadLocal.set(userDetails);
    }

    public static User getUser() {
        return userThreadLocal.get();
    }

    public static void clear() {
        userThreadLocal.remove();
    }

}
