package com.api.cobroking.config

import com.api.cobroking.domain.security.CustomOAuth2UserService
import com.api.cobroking.domain.security.DatabaseLoginSuccessHandler
import com.api.cobroking.domain.security.OAuthLoginSuccessHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.ClientAuthenticationMethod
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler


@Configuration
@EnableWebSecurity
class OAuth2LoginSecurityConfig(
    val customOAuth2UserService: CustomOAuth2UserService,
    val oauthLoginSuccessHandler: OAuthLoginSuccessHandler,
    val databaseLoginSuccessHandler: DatabaseLoginSuccessHandler
    ) {

    @Value("\${spring.security.oauth2.client.registration.google.client-id}")
    private val googleClientId: String? = null

    @Value("\${spring.security.oauth2.client.registration.google.client-secret}")
    private val googleClientSecret: String? = null

    @Bean
    open fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            //.csrf(Customizer.withDefaults())
            //.cors(Customizer.withDefaults())

            //.securityMatcher(AntPathRequestMatcher("/api/**"))
            .authorizeHttpRequests { authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/", "/login", "/oauth/**").permitAll()
                    //.requestMatchers("/**").hasRole("USER")
                    //.requestMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
            }
            .formLogin{ form -> form
                .permitAll()
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("pass")
                .successHandler(databaseLoginSuccessHandler)
            }
            .oauth2Login { oauth2 ->
                oauth2
                    .userInfoEndpoint{
                            infoEndpoint ->
                        infoEndpoint.userService(customOAuth2UserService)
                    }
                    .successHandler(oauthLoginSuccessHandler)
            }
            /*.oauth2Client { oauth2 ->
                oauth2
                    .clientRegistrationRepository(clientRegistrationRepository())
                    .authorizedClientRepository(authorizedClientRepository)
                    .authorizedClientService(authorizedClientService)
                    .authorizationCodeGrant { codeGrant ->
                        codeGrant
                            .authorizationRequestRepository(authorizationRequestRepository)
                            .authorizationRequestResolver(authorizationRequestResolver)
                            .accessTokenResponseClient(accessTokenResponseClient)
                    }
            }*/
            .logout{
                    it.logoutSuccessUrl("/login").permitAll()
            }
        return http.build()
    }

    @Bean
    fun clientRegistrationRepository(): ClientRegistrationRepository {
        return InMemoryClientRegistrationRepository(googleClientRegistration())
    }

    private fun googleClientRegistration(): ClientRegistration {
        return ClientRegistration.withRegistrationId("google")
            .clientId(googleClientId)
            .clientSecret(googleClientSecret)
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
            .scope("openid", "profile", "email", "phone")
            .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
            .tokenUri("https://www.googleapis.com/oauth2/v4/token")
            .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
            .userNameAttributeName(IdTokenClaimNames.SUB)
            .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
            .clientName("Google")
            .build()
    }

    @Bean
    fun authorizedClientManager(
        clientRegistrationRepository: ClientRegistrationRepository,
        authorizedClientRepository: OAuth2AuthorizedClientRepository): OAuth2AuthorizedClientManager {
        val authorizedClientProvider: OAuth2AuthorizedClientProvider = OAuth2AuthorizedClientProviderBuilder.builder()
            .authorizationCode()
            .refreshToken()
            .clientCredentials()
            .password()
            .build()
        val authorizedClientManager = DefaultOAuth2AuthorizedClientManager(
            clientRegistrationRepository, authorizedClientRepository)
        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider)
        return authorizedClientManager
    }
}