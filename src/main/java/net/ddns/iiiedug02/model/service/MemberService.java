package net.ddns.iiiedug02.model.service;

import java.util.HashSet;
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
import net.ddns.iiiedug02.model.bean.MemberRolesBean;
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

    MemberRolesBean mrb = new MemberRolesBean();
    mrb.setUid(mb.getUid());
    mrb.setRole("normal");
    mrb.setMember(mb);

    MemberRolesBean mrb2 = new MemberRolesBean();
    mrb2.setUid(mb.getUid());
    mrb2.setRole("admin");
    mrb2.setMember(mb);

    HashSet<MemberRolesBean> rs = new HashSet<>();
    rs.add(mrb);
    rs.add(mrb2);

    mb.setRoles(rs);

    return memberRepository.save(mb);
  }


  @Override
  public UserDetails loadUserByUsername(String username) {
    MemberBean mb = this.findByUsername(username); // User類別第三參數為權限設定，目前先以List空值表示

    if (null == mb) {
      throw new UserNotFoundException("User not found");
    }
    String[] ra = new String[mb.getRoles().size()];
    int i = 0;
    for (MemberRolesBean rb : mb.getRoles()) {
      ra[i] = rb.getRole();
      i++;
    }

    return User.withUsername(mb.getUsername()).password(mb.getPassword()).roles(ra).build();
  }

}
