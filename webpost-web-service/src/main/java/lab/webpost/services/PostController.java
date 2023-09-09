package lab.webpost.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lab.webpost.domain.Post;

@RestController
public class PostController {

    @Autowired
    PostRepository postRepository;

    // TODO: get all Posts
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getPosts() {
        return ResponseEntity.ok().body(postRepository.findAll());
    }

    //TODO: getting post by id
    @GetMapping("/posts/{id}")
    public ResponseEntity getPostById(@PathVariable Long id) {
        // TODO: check if post is null
        Optional<Post> posts = postRepository.findById(id);
        if(!posts.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }
        return ResponseEntity.ok().body(posts);
    }

    //TODO: find by title
    @GetMapping("/posts/title/{title}")
    public ResponseEntity<List<Post>> getPostByTitle(@PathVariable String title) {
        List<Post> posts = postRepository.findByTitle(title);
        return ResponseEntity.ok().body(posts);
    }

    // TODO: adding new post
    @PostMapping("/posts")
    public ResponseEntity<String> addPost(@RequestBody Post post) {
        postRepository.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).body("created");
    }

    // TODO: delete post by id
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        Optional<Post> posts = postRepository.findById(id);
        if(posts.isPresent()){
            postRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted");
        }
        return new ResponseEntity<>("not found",HttpStatus.NOT_FOUND);
    }

    //TODO: delete all posts
    @DeleteMapping("/posts")
    public ResponseEntity<String> deleteAllPosts() {
        postRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("deleted all");
    }

}
