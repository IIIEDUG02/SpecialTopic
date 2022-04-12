package net.ddns.iiiedug02.model.service;

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
    private MemberRepository mbRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Member findByUsername(String username) {
        Optional<Member> opt = mbRepo.findByUsername(username);
        if (opt.isEmpty()) {
            return null;
        }
        return opt.get();
    }

    public Member save(Member mb) {

        String encodedpassword = bCryptPasswordEncoder.encode(mb.getPassword());
        mb.setPassword(encodedpassword);

        return mbRepo.save(mb);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
        Member mb = this.findByUsername(username);
        if (null == mb) {
            throw new UserNotFoundException();
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
        Optional<Member> mb = mbRepo.findByUid(uid); // User類別第三參數為權限設定，目前先以List空值表示
        if (mb.isEmpty()) {
            throw new UserNotFoundException();
        }
        return mb.get();
    }

    public int countMember() {
        return mbRepo.countMember();
    }
    
    public void deleteByUsername(String username) {
    	mbRepo.deleteByUsername(username);
    }

}
