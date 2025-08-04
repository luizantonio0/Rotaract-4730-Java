package app.netlify.rotaract.Web_Structure.entity.member.repository;

import app.netlify.rotaract.Web_Structure.entity.member.model.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository {
    List<Member> getMembers();
    Member getMember(String id);
    void saveMember(Member member);
    void deleteMember(String id);
}
