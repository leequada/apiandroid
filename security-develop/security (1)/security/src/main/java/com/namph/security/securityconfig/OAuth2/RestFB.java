package com.namph.security.securityconfig.OAuth2;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.namph.security.repository.UserRepository;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class RestFB {
    @Autowired
    UserRepository userRepository;

    public static String FACEBOOK_APP_ID = "397189641352092";
    public static String FACEBOOK_APP_SECRET = "44aa5a86136487769afc5d73024820b4";
    public static String FACEBOOK_REDIRECT_URL = "https://localhost:8081/oauth/AccessFacebook/login-facebook";
    public static String FACEBOOK_LINK_GET_TOKEN = "https://graph.facebook.com/oauth/access_token?client_id=%s&client_secret=%s&redirect_uri=%s&code=%s";

    public static String getToken(final String code) throws ClientProtocolException, IOException {
        String link = String.format(FACEBOOK_LINK_GET_TOKEN, FACEBOOK_APP_ID, FACEBOOK_APP_SECRET, FACEBOOK_REDIRECT_URL, code);
        String response = Request.Get(link).execute().returnContent().asString();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(response).get("access_token");
        return ((JsonNode) node).textValue();
    }

    public static com.restfb.types.User getUserInfo(final String accessToken) {
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken, FACEBOOK_APP_SECRET, Version.LATEST);
        com.restfb.types.User user= facebookClient.fetchObject("me", com.restfb.types.User.class);
        System.out.println(user.getName());
        return user;
    }
    public static String getUserName(final String accessToken) throws IOException {
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken, FACEBOOK_APP_SECRET, Version.LATEST);
        com.restfb.types.User user= facebookClient.fetchObject("me", com.restfb.types.User.class);
        System.out.println(user.getName());
        return user.getId()+user.getName();
    }

    public UserDetails buildUser(com.restfb.types.User user) {
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        UserDetails userDetail = new User(user.getId()+user.getLastName(), "", enabled, accountNonExpired, credentialsNonExpired,
                accountNonLocked, authorities);
        return userDetail;
    }
    public UserDetails buildUser(String username) {
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        com.namph.security.domain.User u= new com.namph.security.domain.User();
        u.setName(username);
        u.setUsername(username);
        u.setPassword(encoder.encode("1"));
        u.setRole("ROLE_USER");
        userRepository.save(u);
        UserDetails userDetail = new User(username, "1", enabled, accountNonExpired, credentialsNonExpired,
                accountNonLocked, authorities);
        return userDetail;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(getToken("AQDDMDZEQ8RtJRNZCdV6oFornUbQ5ETDYf_Xchb3T1R8OMs2QsJGFiZ-llxUB6XR4oOAiHvvx2IdX15JBAraGzk635sd9A-rTseS6MJ1o2rEOEPZYiM3PCyfuFA4cJnFWKWB6Dpg2D3cDuOEBqX8ngdqFYrCgZfwUz2KDP1Lq3NlHpBxvEI-yL-ic1jHxjE3jZKurvek9sYEvuuJ-kdR2S01MuIGfccNXA3HdJcYB3YpHDYM_0WN9ru_kFt0kwbqAbFwWGq0MouWz-MSHKcax8KdFvV33jKS5gFr-xq9q2Y3ObG-X-o8_3Gj8YA9_lQa32NrYzr3XAPLF5UAZQ6fU593lMzbj271f3vIJdPKzE0MLw#_=_"));
//        System.out.println(getUserName("EAAFpPet7M5wBAGW03R5RGZBkMVXGIYrzTnMlKnCGKPDscsRTyUTUzmZBaZB2zra43SrMbVfeVDvwep7kikszXjsNRxVa4H1nTumUAB2ZCCiRotlFxLwkFU0ZAeZB4vJRsGkhOZBXmRQfOVXCYwsZC2uHwVtFenaGEOPF5xZCGeY1IvAZDZD"));
    }


}
