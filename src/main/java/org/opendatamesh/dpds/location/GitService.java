package org.opendatamesh.dpds.location;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.PushResult;

import java.util.List;

public interface GitService {
    void createRepo(String organization, String projectName, String repositoryName);

    Git cloneRepo(
            String sourceUrl,
            String destinationPath,
            Boolean cloneAllBranches,
            List<String> branchesToClone,
            String head
    );

    Git initTargetRepository(
            Git oldGitRepo,
            String blueprintDir,
            Boolean createRepoFlag,
            String destinationPath,
            String targetOrigin
    );

    Iterable<PushResult> commitAndPushRepo(Git gitRepo, String message);

    void deleteLocalRepository(String localRepositoryPath);
}
