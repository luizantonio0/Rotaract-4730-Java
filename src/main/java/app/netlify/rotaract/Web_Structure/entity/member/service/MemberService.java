package app.netlify.rotaract.Web_Structure.entity.member.service;

import app.netlify.rotaract.Web_Structure.entity.member.model.Member;
import app.netlify.rotaract.Web_Structure.entity.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member getMember(String id) {
        return memberRepository.getMember(id);
    }
    public List<Member> getMembers() {
        return memberRepository.getMembers();
    }
    public void saveMember(Member member) {
        memberRepository.saveMember(member);
    }
    public void deleteMember(String id) {
        memberRepository.deleteMember(id);
    }
}
