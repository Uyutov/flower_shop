package uyutov.flower_shop.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uyutov.flower_shop.models.users.Provider;

import java.util.Collection;
import java.util.Collections;

public class ProviderDetails implements UserDetails {
    private final Provider provider;

    public ProviderDetails(Provider provider) {
        this.provider = provider;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(provider.getRole()));
    }

    @Override
    public String getPassword() {
        return provider.getPassword();
    }

    @Override
    public String getUsername() {
        return provider.getPhone_number();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Provider getProvider() {
        return provider;
    }
}
