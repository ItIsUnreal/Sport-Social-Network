package dev.vlad.sport.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public Boolean register(CredentialsDTO dto) {
        UserDocument existingUser = userRepository.findByUsername(dto.getUsername());
        if (existingUser != null) {
            return false;
        }

        UserDocument user = new UserDocument();
        user.setUsername(dto.getUsername());
        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        user.setIsAdmin(false);
        userRepository.save(user);
        return true;
    }

    public String login(CredentialsDTO dto) {
        UserDocument existingUser = userRepository.findByUsername(dto.getUsername());

        if (existingUser == null) return null;

        if (!passwordEncoder.validate(dto.getPassword(), existingUser.getPasswordHash())) return null;

        return jwtUtil.generateToken(existingUser.getUsername(), existingUser.getIsAdmin());
    }

}
