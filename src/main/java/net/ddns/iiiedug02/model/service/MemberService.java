package net.ddns.iiiedug02.model.service;

import java.util.Collections;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.ddns.iiiedug02.exception.UserNotFoundException;
import net.ddns.iiiedug02.model.bean.MemberBean;
import net.ddns.iiiedug02.model.repository.MemberRepository;

@Service
@Transactional
public class MemberService implements UserDetailsService {
  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  public MemberBean findByUsername(String username) {
    Optional<MemberBean> opt = memberRepository.findByUsername(username);
    if (opt.isEmpty()) {
      return null;
    }
    return opt.get();
  }

  public MemberBean createMemberBean(MemberBean mb) {
    String encodedpassword = bCryptPasswordEncoder.encode(mb.getPassword());
    mb.setPassword(encodedpassword);
    return memberRepository.save(mb);
  }


  @Override
  public UserDetails loadUserByUsername(String username) {
    MemberBean user = this.findByUsername(username); // User類別第三參數為權限設定，目前先以List空值表示

    if (null == user) {
      throw new UserNotFoundException("User not found");
    }
    return new User(user.getUsername(), user.getPassword(), Collections.emptyList());
  }

}
