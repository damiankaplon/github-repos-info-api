package pl.damiankaplon.atipera.repos.web;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.damiankaplon.atipera.repos.FindingReposInfo;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/profile")
class ProfileController {

    private final FindingReposInfo findingReposInfo;

    @GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Set<FindingReposInfo.Repo>> fetchGitHubUserRepositories(@PathVariable String username) {
        return ResponseEntity.ok(this.findingReposInfo.findByUsername(username));
    }

}
