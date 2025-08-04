package app.netlify.rotaract.Web_Structure.entity.member;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member getMember(int id) {
        return memberRepository.get(id);
    }
    public List<Member> getMembers() {
        return memberRepository.getAll();
    }
    public void saveMember(Member member) {
        memberRepository.save(member);
    }
    public void deleteMember(int id) {
        memberRepository.deleteInSpreadSheet(id);
    }
    public void updadeMember(Member member, int id){
        memberRepository.update(member, id);
    }
}
