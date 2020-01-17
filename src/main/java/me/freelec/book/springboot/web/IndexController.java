package me.freelec.book.springboot.web;

import lombok.RequiredArgsConstructor;
import me.freelec.book.springboot.domain.config.auth.LoginUser;
import me.freelec.book.springboot.domain.user.User;
import me.freelec.book.springboot.service.PostsService;
import me.freelec.book.springboot.web.dto.PostsResponseDto;
import me.freelec.book.springboot.domain.config.auth.dto.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        if(user != null){
            model.addAttribute("userName",user.getName());
        }
        return "index";
    }
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id , Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);
        return "posts-update";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }


}
