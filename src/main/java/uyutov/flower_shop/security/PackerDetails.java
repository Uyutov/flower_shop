package uyutov.flower_shop.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uyutov.flower_shop.models.users.Packer;

import java.util.Collection;
import java.util.Collections;

public class PackerDetails implements UserDetails {
    private final Packer packer;

    public PackerDetails(Packer packer) {
        this.packer = packer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(packer.getRole()));
    }

    @Override
    public String getPassword() {
        return packer.getPassword();
    }

    @Override
    public String getUsername() {
        return packer.getPhone_number();
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

    public Packer getPacker() {
        return packer;
    }
}
