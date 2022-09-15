package pl.damiankaplon.atipera.repos;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

@FeignClient(name = "github-repos-info-client", url = "${client.github-repos-info-client.url}")
public interface GitHubReposInfoClient {

    @RequestMapping(method = RequestMethod.GET, value = "/users/{username}/repos")
    Set<Repo> fetchUserRepositories(@PathVariable String username);

    @RequestMapping(method = RequestMethod.GET, value = "/repos/{username}/{repo}/branches")
    Set<Branch> fetchReposBranches(@PathVariable String username, @PathVariable String repo);


    record Repo(String name, Owner owner, boolean fork){}
    record Owner(String login){}
    record Branch(String name,  LastCommit commit){}
    record LastCommit(String sha){}

}
