package com.liserabackend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class LiserabackendApplicationTests {

    @Autowired
    WebTestClient webTestClient;
     /*TokenString tokenString;

    @BeforeEach
    void setUp() {
        tokenString = KeyCloakToken.acquire("http://localhost:8000/",
                        "test",
                        "test-client",
                        "christian",
                        "password")
                .block()
                .getAccessToken();
    }*/


        /* @Autowired
    JwtTokenProvider jwtTokenProvider;
     */

    @Test
    void lialisera_user_access_success() {
        var tokenString = ""; /* jwtTokenProvider.createToken(
                new UsernamePasswordAuthenticationToken("test-user","Password")):*/

        webTestClient
                .get().uri("/api/student")
                .headers(http -> http.setBearerAuth(tokenString))
                .exchange()
                .expectStatus().isOk();
    }
    @Test
    void test_users_access_failed_because_denied() {
        webTestClient
                .get().uri("/users")
                .exchange()
                .expectStatus().isUnauthorized();
    }
}
