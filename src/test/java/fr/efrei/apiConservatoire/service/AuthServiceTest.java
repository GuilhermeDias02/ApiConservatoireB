package fr.efrei.apiConservatoire.service;

import fr.efrei.apiConservatoire.dto.LoginDTO;
import fr.efrei.apiConservatoire.model.Utilisateur;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.web.servlet.MockMvc;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class AuthServiceTest {
    @Autowired
    private AuthService service;

    @Test
    public void AuthService_authenticate_ReturnsUser(){
        LoginDTO input1 = new LoginDTO("test@email.com", "motdepasse");
        LoginDTO input2 = new LoginDTO("testabc", "");

        Utilisateur utilisateur1 = service.authenticate(input1);
        Utilisateur utilisateur2 = service.authenticate(input2);

        Assertions.assertNotNull(utilisateur1);
        Assertions.assertNull(utilisateur2);
    }
}
