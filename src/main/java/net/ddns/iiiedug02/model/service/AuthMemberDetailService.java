package net.ddns.iiiedug02.model.service;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import net.ddns.iiiedug02.model.bean.MemberBean;

@Service
public class AuthMemberDetailService implements UserDetailsService {

  @Autowired
  private MemberService mbs;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    MemberBean md = mbs.findByUsername(username);

    if (md == null) {
      throw new UsernameNotFoundException(username + " not found");
    }
    return new User(md.getUsername(), md.getPassword(), Collections.emptyList());
  }

}
