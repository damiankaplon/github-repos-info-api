package pl.damiankaplon.atipera.repos;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FindingReposInfo {
    private final GitHubReposInfoClient gitHubReposInfoClient;

    public Set<Repo> findByUsername(final String username) {
        Set<GitHubReposInfoClient.Repo> reposFromApi = this.gitHubReposInfoClient.fetchUserRepositories(username);
        return reposFromApi.stream()
                .filter(repoFromApi -> !repoFromApi.fork())
                .map(repoFromApi ->
                        new Repo(
                                repoFromApi.name(),
                                repoFromApi.owner().login(),
                                this.gitHubReposInfoClient.fetchReposBranches(username, repoFromApi.name()).stream()
                                        .map(branchFromApi -> new Branch(branchFromApi.name(), branchFromApi.commit().sha()))
                                        .collect(Collectors.toSet())
                        )
        ).collect(Collectors.toSet());
    }

    public record Repo(String name, String ownerName, Set<Branch> branches){}
    public record Branch(String name, String lastCommitSh) {}

}
