package app.netlify.rotaract.Web_Structure.entity.member.controller;

import app.netlify.rotaract.Web_Structure.entity.member.model.Member;
import app.netlify.rotaract.Web_Structure.entity.member.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/model")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public List<Member> getMembers() {
        return memberService.getMembers();
    }
    @GetMapping("/{id}")
    public Member getMember(@PathVariable String id) {
        return memberService.getMember(id);
    }
    @PostMapping
    public void saveMember(@RequestBody Member member) {
        memberService.saveMember(member);
    }
    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable String id) {
        memberService.deleteMember(id);
    }
}
