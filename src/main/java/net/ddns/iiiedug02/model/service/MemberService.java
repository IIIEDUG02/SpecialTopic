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
import net.ddns.iiiedug02.model.bean.Member;
import net.ddns.iiiedug02.model.bean.MemberRole;
import net.ddns.iiiedug02.model.repository.MemberRepository;

@Service
@Transactional
public class MemberService implements UserDetailsService {
  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  public Member findByUsername(String username) {
    Optional<Member> opt = memberRepository.findByUsername(username);
    if (opt.isEmpty()) {
      return null;
    }
    return opt.get();
  }

  public Member createMemberBean(Member mb) {

    String encodedpassword = bCryptPasswordEncoder.encode(mb.getPassword());
    mb.setPassword(encodedpassword);

    MemberRole mrb = new MemberRole();
    mrb.setRole("normal");
    mrb.setMember(mb);

    MemberRole mrb2 = new MemberRole();
    mrb2.setRole("admin");
    mrb2.setMember(mb);

    HashSet<MemberRole> rs = new HashSet<>();
    rs.add(mrb);
    rs.add(mrb2);

    mb.setRoles(rs);

    return memberRepository.save(mb);
  }


  @Override
  public UserDetails loadUserByUsername(String username) {
    Member mb = this.findByUsername(username); // User類別第三參數為權限設定，目前先以List空值表示

    if (null == mb) {
      throw new UserNotFoundException("User not found");
    }
    String[] ra = new String[mb.getRoles().size()];
    int i = 0;
    for (MemberRole rb : mb.getRoles()) {
      ra[i] = rb.getRole();
      i++;
    }

    return User.withUsername(mb.getUsername()).password(mb.getPassword()).roles(ra).build();
  }

  public Member findByUid(int uid) {
    Optional<Member> mb = memberRepository.findByUid(uid); // User類別第三參數為權限設定，目前先以List空值表示
    if (mb.isEmpty()) {
      throw new UserNotFoundException("User not found");
    }
    return mb.get();
  }

  public int countMember() {
    return memberRepository.countMember();
  }

}
