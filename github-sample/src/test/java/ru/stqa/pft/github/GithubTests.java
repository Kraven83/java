package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("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");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("Kraven83", "java")).commits();
    for(RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String,String>().build())) {
      System.out.println(new RepoCommit.Smart(commit).message());
    }

  }
}
