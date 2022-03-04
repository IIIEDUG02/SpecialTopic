// package net.ddns.iiiedug02.model.service;
//
// import java.util.List;
// import java.util.Optional;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
// import net.ddns.iiiedug02.model.bean.MemberBean;
// import net.ddns.iiiedug02.model.interfaces.demo.MemberRepository;
//
/// **
// * Service物件，將完成指定商業邏輯的MemberBean物件，呼叫MemberDao執行增刪改查
// */
// @Service()
// public class MemberService {
//
// @Autowired
// private MemberRepository memberRepository;
//
// @Transactional
// public MemberBean insert(MemberBean mb) {
// return memberRepository.save(mb);
// }
//
// @Transactional
// public MemberBean update(MemberBean mb) {
// return memberRepository.save(mb);
// }
//
// @Transactional
// public void deleteById(Integer id) {
// memberRepository.deleteById(id);
// }
//
// @Transactional(readOnly = true)
// public MemberBean findById(Integer id) {
// Optional<MemberBean> optional = memberRepository.findById(id);
// if (optional.isPresent()) {
// return optional.get();
// }
// return null;
// }
//
// @Transactional(readOnly = true)
// public List<MemberBean> findAll() {
// return memberRepository.findAll();
// }
//
// @Transactional(readOnly = true)
// public MemberBean findByUsername(String username) {
// return memberRepository.findByUsername(username);
// }
//
// }
