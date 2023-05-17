package dev.sheltonfrancisco.studentassistent.ui.listeners;

public interface AuthEventListener {
    public void handleSignIn(String email, String password);

    void handleRegistration(String email, String username, String passeord);
}
