package com.email.sendemail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class SendemailApplicationTests {

    @SpringBootTest
    static class ContextLoadsTest {
        @Test
        void contextLoads() {
            // Verifica que el ApplicationContext arranca sin excepciones
        }
    }

    @Test
    void mainDelegatesToSpringApplicationRun() {
        // Mock estático de SpringApplication.run(...)
        try (MockedStatic<SpringApplication> springApp = Mockito.mockStatic(SpringApplication.class)) {

            // Stub de la llamada esperada: clase principal + sin args
            springApp
                .when(() -> SpringApplication.run(SendemailApplication.class, new String[]{}))
                .thenReturn(null); // No necesitamos un ApplicationContext real

            // Ejecuta main y verifica que no lanza excepciones
            assertDoesNotThrow(() -> SendemailApplication.main(new String[]{}));

            // Verifica que se llamó exactamente 1 vez con esos argumentos
            springApp.verify(
                () -> SpringApplication.run(SendemailApplication.class, new String[]{}),
                Mockito.times(1)
            );
        }
    }
}
