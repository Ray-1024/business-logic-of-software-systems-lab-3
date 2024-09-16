package ray1024.blps.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;

@AllArgsConstructor
@Getter
public enum Role {
    CLIENT(Set.of(new SimpleGrantedAuthority(Authority.CREATE_ORDER.name()))),
    PACKER(Set.of(new SimpleGrantedAuthority(Authority.SEARCH_ORDER.name())
            , new SimpleGrantedAuthority(Authority.CHANGE_ORDER.name()))),
    COURIER(Set.of(new SimpleGrantedAuthority(Authority.SEARCH_ORDER.name())
            , new SimpleGrantedAuthority(Authority.CHANGE_ORDER.name())));

    private final Set<GrantedAuthority> authorities;

    public enum Authority {
        CREATE_ORDER,
        SEARCH_ORDER,
        CHANGE_ORDER
    }
}
