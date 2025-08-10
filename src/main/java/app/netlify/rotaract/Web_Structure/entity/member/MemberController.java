package app.netlify.rotaract.Web_Structure.entity.member;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
@CrossOrigin(origins = "http://localhost:4200")
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
    public Member getMember(@PathVariable int id) {
        return memberService.getMember(id);
    }
    @PostMapping
    public void saveMember(@RequestBody Member member) {
        memberService.saveMember(member);
    }
    @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable int id) {
        memberService.deleteMember(id);
    }
    @PutMapping("/{id}")
    public void updateMember(@RequestBody Member member, @PathVariable int id) {
        memberService.updadeMember(member, id);
    }
    @PatchMapping("/{id}")
    public void patchMember(@RequestBody Member member, @PathVariable int id) {
        memberService.updadeMember(member, id);
    }
}
