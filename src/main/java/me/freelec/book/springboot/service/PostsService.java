package me.freelec.book.springboot.service;

import lombok.RequiredArgsConstructor;
import me.freelec.book.springboot.domain.posts.Posts;
import me.freelec.book.springboot.domain.posts.PostsRepository;
import me.freelec.book.springboot.web.dto.PostsResponseDto;
import me.freelec.book.springboot.web.dto.PostsSaveRequestDto;
import me.freelec.book.springboot.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. id="+id));

        posts.update(requestDto.getTitle(),requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다 . id=" + id));
        return new PostsResponseDto(entity);
    }
//    public PostsResponseDto get(Long id){
//
//    }
}
