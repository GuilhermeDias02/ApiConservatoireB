package fr.efrei.apiConservatoire.service;

import fr.efrei.apiConservatoire.constants.Role;
import fr.efrei.apiConservatoire.dto.LoginDTO;
import fr.efrei.apiConservatoire.dto.RegisterDTO;
import fr.efrei.apiConservatoire.model.Utilisateur;
import fr.efrei.apiConservatoire.repository.UtilisateurRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UtilisateurRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthService(
            UtilisateurRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Utilisateur signup(RegisterDTO input) {
        Utilisateur user = new Utilisateur();
        user.setNom(input.getNom());
        user.setTelephone(input.getTelephone());
        user.setEmail(input.getEmail());
        user.setRole(Role.Parent);
        user.setMdp(passwordEncoder.encode(input.getMdp()));

        return userRepository.save(user);
    }

    public Utilisateur authenticate(LoginDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getMdp()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}